package Metodos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.imageio.ImageIO;

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

    public void EscalaCinza(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        int width;
        int height;
        BufferedImage image;
        for (Imagem img : lista) {
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            int count = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    Color gray = new Color(red + green + blue, red + green + blue, red + green + blue);
                    image.setRGB(j, i, gray.getRGB());
                }
            }
            File ouptut = new File(destino + "/" + img.getNome() + "_Cinza_" + execucao + ".bmp");
            ImageIO.write(image, "bmp", ouptut);
        }

    }

    public void Limiarizacao(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        int width;
        int height;
        BufferedImage imagem;
        for (Imagem img : lista) {
            imagem = img.getImg();
            width = imagem.getWidth();
            height = imagem.getHeight();
            int count = 0;
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    count++;
                    Color c = new Color(imagem.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    int valor_pixel = red + green + blue;
                    Color gray;
                    if (valor_pixel < 180) {
                        gray = new Color(red + green + blue, red + green + blue, red + green + blue);
                    } else {
                        gray = new Color(255, 255, 255);
                    }

                    imagem.setRGB(j, i, gray.getRGB());
                }
            }
            File ouptut = new File(destino + "/" + img.getNome() + "_Limiarizado_" + execucao + ".bmp");
            ImageIO.write(imagem, "bmp", ouptut);
        }
    }

    public ArrayList<ArrayList<Integer>> varreduraColuna(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int width;
        int height;
        BufferedImage image;  
        int count = 0;
        
        for (Imagem img : lista) {
            count++;
            FileWriter arq = new FileWriter("Teste_coluna"+count+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.printf("Arquivo %d%n", count);
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer linha = 0;
            ArrayList<Integer> linhasImg = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                linha = 0;
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    if ((int) c.getGreen() < 0.4) {
                        linha++;
                    }
                }
                gravarArq.printf("%d ",linha);
                linhasImg.add(linha);
            }
            result.add(linhasImg);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> varreduraLinha(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int width;
        int height;
        BufferedImage image;
        int count=0;

        for (Imagem img : lista) {
            count++;
            FileWriter arq = new FileWriter("Teste_linha"+count+".txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.printf("Arquivo %d%n", count);
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer coluna = 0;
            ArrayList<Integer> colunasImg = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                coluna = 0;
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(i, j));
                    if ((int) c.getGreen() < 0.4) {
                        coluna++;
                    }
                }
                gravarArq.printf("%d ",coluna);
                colunasImg.add(coluna);
            }
            result.add(colunasImg);
        }
        return result;
    }

    public void Contraste(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {

    }

}
