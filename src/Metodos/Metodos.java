package Metodos;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.sourceforge.tess4j.Tesseract1;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

//---------------------------estrutura basica para os metodos-------------------------------------------
//
//  public void METODO(ArrayList<Imagem> lista, int execucao,String destino) throws IOException {
//      int width;
//      int height;
//      BufferedImage image;
//      for (Imagem img : lista) {
//
//              ...
//
//            File ouptut = new File(destino+"/"+img.getNome()+"_METODO_"+execucao+".bmp");
//            ImageIO.write(image, "bmp", ouptut);
//        }
//    }
//
//-------------------------------------------------------------------------------------------------------
public class Metodos {

    public static BufferedImage mat2Img(Mat matrix) {
        int cols = matrix.cols();
        int rows = matrix.rows();
        int elemSize = (int) matrix.elemSize();
        byte[] data = new byte[cols * rows * elemSize];
        int type;

        matrix.get(0, 0, data);

        switch (matrix.channels()) {
            case 1:
                type = BufferedImage.TYPE_BYTE_GRAY;
                break;

            case 3:
                type = BufferedImage.TYPE_3BYTE_BGR;

                // bgr to rgb
                byte b;
                for (int i = 0; i < data.length; i = i + 3) {
                    b = data[i];
                    data[i] = data[i + 2];
                    data[i + 2] = b;
                }
                break;

            default:
                return null;
        }

        BufferedImage image = new BufferedImage(cols, rows, type);
        image.getRaster().setDataElements(0, 0, cols, rows, data);

        return image;
    }

    public static ArrayList<MatImagem> TonsCinza(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {
        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        //File a = new File(destino + "/Etapa" + etapa + "_TonsCinza"+thread);
        //a.mkdir();
        for (MatImagem img : lista) {
            Mat imagem = img.getImg();
            Mat Img_new = new Mat(imagem.rows(), imagem.cols(), CvType.CV_8UC1);
            File output = new File(destino + "/Etapa" + etapa + "_TonsCinza"+thread+"/"+img.getNome() + "_" + execucao + ".bmp");
            
            Imgproc.cvtColor(imagem, Img_new, Imgproc.COLOR_RGB2GRAY);
            //ImageIO.write(mat2Img(Img_new), "bmp", output);
            MatImagem image = new MatImagem(Img_new, img.getNome());
            imagens.add(image);
        }
        return imagens;
    }

    public static ArrayList<MatImagem> Gaussian_Adp(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {
        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        //File a = new File(destino + "/Etapa" + etapa + "_Gaussian_Adaptativo"+thread);
        //a.mkdir();
        for (MatImagem img : lista) {
            Mat Img_new = img.getImg();
            File output = new File(destino + "/Etapa" + etapa + "_Gaussian_Adaptativo"+thread +"/"+ img.getNome() + "_" + execucao + ".bmp");

            Imgproc.adaptiveThreshold(Img_new, Img_new, 255, Imgproc.ADAPTIVE_THRESH_GAUSSIAN_C, Imgproc.THRESH_BINARY, 11, 4);
            //ImageIO.write(mat2Img(Img_new), "bmp", output);
            MatImagem image = new MatImagem(Img_new, img.getNome());
            imagens.add(image);
        }
        return imagens;
    }

    public static ArrayList<MatImagem> Otsu(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {
        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        //File a = new File(destino + "/Etapa" + etapa + "_Otsu"+thread);
        //a.mkdir();
        for (MatImagem img : lista) {
            Mat Img_new = img.getImg();
            File output = new File(destino + "/Etapa" + etapa + "_Otsu"+thread +"/"+ img.getNome() + "_" + execucao + ".bmp");

            Imgproc.GaussianBlur(Img_new, Img_new, new Size(5, 5), 5);
            Imgproc.threshold(Img_new, Img_new, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
            //ImageIO.write(mat2Img(Img_new), "bmp", output);

            MatImagem image = new MatImagem(Img_new, img.getNome());
            imagens.add(image);
        }
        return imagens;
    }

    public ArrayList<MatImagem> identificarContornos(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {

        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        //File a = new File(destino + "/Etapa" + etapa + "_Contorno"+thread);
        //a.mkdir();
        File b = new File(destino + "/Etapa" + etapa + "_Tickets"+thread);
        b.mkdir();
        for (MatImagem img : lista) {
            Mat imagem = img.getImg();
            Mat Img_new = new Mat(imagem.rows(), imagem.cols(), imagem.type());
            imagem.copyTo(Img_new);

            Mat ImgCor_new = new Mat(imagem.rows(), imagem.cols(), imagem.type());
            imagem.copyTo(ImgCor_new);

            Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(15, 15));
            Imgproc.erode(imagem, Img_new, element);

            ArrayList<MatOfPoint> contours = new ArrayList<>();

            ArrayList<Rect> rects = new ArrayList<>();

            // Encontra os contornos
            Imgproc.findContours(Img_new, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            Imgproc.cvtColor(imagem, ImgCor_new, Imgproc.COLOR_GRAY2BGR);

            for (MatOfPoint m : contours) {
                Rect r = Imgproc.boundingRect(m);

                // Condição para não desenhar retângulos em contornos de ruídos
                if (r.height > 50 && r.width > 100 && r.tl().x != 1) {
                    rects.add(r);

                    Imgproc.rectangle(Img_new, new Point(r.x, r.y), new Point(r.x + r.width, r.y + r.height), new Scalar(255, 0, 0), 2);
                    Imgproc.rectangle(ImgCor_new, new Point(r.x, r.y), new Point(r.x + r.width - 10, r.y + r.height + 10), new Scalar(255, 0, 0), 2);
                }
            }

            String arqOriginal = img.getNome();
            int index = 0;

            // Separa os números dentro de cada contorno
            for (Rect r : rects) {

                // Aloca a matriz com o tamanho necessário
                Mat numero = new Mat(r.height, r.width - 10, imagem.type());

                // Percorre a matriz original no intervalo definido pelo contorno   
                for (int i = r.x; i < r.x + r.width - 10; ++i) {
                    for (int j = r.y; j < r.y + r.height + 10; ++j) {
                        if (imagem.get(j, i) != null) {
                            numero.put(j - r.y, i - r.x, imagem.get(j, i));
                        }
                    }
                }

                //ImageIO.write(mat2Img(numero), "bmp", new File(destino + "\\Etapa" + etapa + "_Tickets" + "\\" + arqOriginal +"_Figura"+ index + ".bmp"));
                // Diminui os "cantos" da imagem
                numero = numero.adjustROI((int) (80 - numero.height() - 5) / 2, (int) (80 - numero.height() - 5) / 2, -7, 135 - numero.width());
                BufferedImage aux = mat2Img(numero);
                if (ContaPixel(aux) > 2000) {
                    if (index == 36) {
                        index = 0;
                    }
                    ImageIO.write(aux, "bmp", new File(destino + "\\Etapa" + etapa + "_Tickets" +thread+ "\\" + arqOriginal + "_Figura" + index + ".bmp"));
                    MatImagem image = new MatImagem(numero, img.getNome());
                    imagens.add(image);
                    index++;

                }

            }

            //ImageIO.write(mat2Img(ImgCor_new), "bmp", new File(destino + "\\Etapa" + etapa + "_Contorno" +thread+ "\\" + arqOriginal + "_" + index + ".bmp"));

        }

        return imagens;
    }

    public ArrayList<MatImagem> Erosao(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {

        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        //File a = new File(destino + "/Etapa" + etapa + "_Erosao"+thread);
        //a.mkdir();
        int index = 0;
        for (MatImagem img : lista) {
            String arqOriginal = img.getNome();

            Mat imagem = img.getImg();
            Mat Img_new = new Mat(imagem.rows(), imagem.cols(), imagem.type());
            imagem.copyTo(Img_new);
            if (index == 36) {
                index = 0;
            }
            Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.erode(imagem, Img_new, element);
            //ImageIO.write(mat2Img(Img_new), "bmp", new File(destino + "\\Etapa" + etapa + "_Erosao" +thread+ "\\" + arqOriginal + "_Figura" + "_" + index + ".bmp"));
            MatImagem image = new MatImagem(Img_new, img.getNome());
            imagens.add(image);
            index++;
        }

        return imagens;
    }

    public ArrayList<MatImagem> Dilatacao(ArrayList<MatImagem> lista, int execucao, String destino, int etapa, String thread) throws IOException {

        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        File a = new File(destino + "/Etapa" + etapa + "_Dilatacao"+thread);
        a.mkdir();
        int index = 0;
        for (MatImagem img : lista) {
            String arqOriginal = img.getNome();

            Mat imagem = img.getImg();
            Mat Img_new = new Mat(imagem.rows(), imagem.cols(), imagem.type());
            imagem.copyTo(Img_new);
            if (index == 36) {
                index = 0;
            }
            Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
            Imgproc.dilate(imagem, Img_new, element);
            ImageIO.write(mat2Img(Img_new), "bmp", new File(destino + "\\Etapa" + etapa + "_Dilatacao" +thread+ "\\" + arqOriginal + "_Figura" + "_" + index + ".bmp"));
            MatImagem image = new MatImagem(Img_new, img.getNome());
            imagens.add(image);
            index++;
        }

        return imagens;
    }

    public void ReconhecerDigitos(int execucao, String destino, int etapa, String thread) throws IOException, TesseractException {

        ArrayList<String> tickets = new ArrayList<>();
        ArrayList<Integer> contador = new ArrayList<>();
        ITesseract tess = new Tesseract();
        tess.setLanguage("eng");
        tess.setTessVariable("tessedit_char_whitelist", "0123456789");
        ArrayList<MatImagem> imagens = new ArrayList<MatImagem>();
        int index = 0;

        File b = new File(destino + "/Etapa" + etapa + "_Dilatacao"+thread);
        File[] c = b.listFiles();

        FileWriter fw = new FileWriter("resultado"+thread+".txt");
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("Número de repetições de Tickets em cada imagem\n\n");

        for (int i = 0; i < c.length; i++) {

            //System.out.println(c[i].getName());
            String reconhecimento = tess.doOCR(c[i]).trim();
            //System.out.println(reconhecimento);

            if (tickets.contains(reconhecimento)) {
                int id_ticket = tickets.indexOf(reconhecimento);
                contador.set(id_ticket, contador.get(id_ticket) + 1);
            } else {
                contador.add(1);
                tickets.add(reconhecimento);
            }
        }

        bw.write("Tickets encontrados nas imagens = " + c.length + "\n");
        for (int i = 0; i < tickets.size(); i++) {
            bw.write(tickets.get(i) + " = " + contador.get(i) + "\n");
        }
        bw.close();
    }

    public int ContaPixel(BufferedImage imagem) {
        int width = imagem.getWidth();
        int height = imagem.getHeight();
        int count = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = new Color(imagem.getRGB(j, i));
                int red = (int) (c.getRed() * 0.299);
                int green = (int) (c.getGreen() * 0.587);
                int blue = (int) (c.getBlue() * 0.114);
                int valor_pixel = red + green + blue;
                Color gray;
                if (valor_pixel < 180) {
                    count++;
                }
            }
        }
        return count;
    }

}
