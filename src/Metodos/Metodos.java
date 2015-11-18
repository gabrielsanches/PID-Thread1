package Metodos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
                    if (valor_pixel < 200) {
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

    public void Contraste(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {

    }

}
