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
            FileWriter arq = new FileWriter("Teste_coluna" + count + ".txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("Arquivo " + count);
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer linha = 0;
            ArrayList<Integer> linhasImg = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                linha = 0;
                for (int j = 0; j < height; j++) {
                    Color c = new Color(image.getRGB(i, j));
                    if ((int) c.getGreen() < 0.4) {
                        linha++;
                    }
                }
                gravarArq.print(linha + " ");
                linhasImg.add(linha);
            }
            gravarArq.println();
            gravarArq.flush();
            gravarArq.close();
            arq.close();
            result.add(linhasImg);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> varreduraLinha(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int width;
        int height;
        BufferedImage image;
        int count = 0;

        for (Imagem img : lista) {
            count++;
            FileWriter arq = new FileWriter("Teste_linha" + count + ".txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println("Arquivo " + count);
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer coluna = 0;
            ArrayList<Integer> colunasImg = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                coluna = 0;
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    if ((int) c.getGreen() < 0.4) {
                        coluna++;
                    }
                }
                gravarArq.print(coluna + " ");
                colunasImg.add(coluna);
            }
            gravarArq.println();
            gravarArq.flush();
            gravarArq.close();
            arq.close();
            result.add(colunasImg);
        }
        return result;
    }

    public void Contraste(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {

    }

    public ArrayList<ArrayList<Recorte>> capturarRecorte(ArrayList<ArrayList<Integer>> linha, ArrayList<ArrayList<Integer>> coluna) {

        boolean flag = true;//true=busca limite minimo, false=busca limite maximo
        ArrayList<ArrayList<Recorte>> recortesAll = new ArrayList<ArrayList<Recorte>>();
        int pLinha[] = new int[1000];
        int pColuna[] = new int[1000];
        int contador;
        ArrayList<Integer> img;
        for (int nImg = 0; nImg < linha.size(); nImg++) {
            ArrayList<Recorte> recortes = new ArrayList<Recorte>();
            contador = 0;
            img = linha.get(nImg);
            for (int i = 0; i < img.size() - 2; i++) {
                if (flag) {
                    if (img.get(i) > 15 && img.get(i + 1) > 15 && img.get(i + 2) > 15) {
                        
                        pLinha[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else {
                    if (img.get(i) <= 15 && img.get(i + 1) <= 15 && img.get(i + 2) <= 15) {
                        pLinha[contador] = i;
                        flag = true;
                        contador++;
                    }
                }
            }

            flag = true;
            img = coluna.get(nImg);
            contador=0;
            
            for (int i = 0; i < img.size() - 2; i++) {
                if (flag) {
                    if (img.get(i) > 15 && img.get(i + 1) > 15 && img.get(i + 2) > 15) {
                        pColuna[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else if (img.get(i) <= 15 && img.get(i + 1) <=15 && img.get(i + 2) <= 15) {
                    pColuna[contador] = i;
                    flag = true;
                    contador++;
                }
            }

            for (int i = 0; i < 100; i = i + 2) {
                for (int j = 0; j < 100; j = j + 2) {
                    if (pColuna[j] == 0) {//nao possui mais elementos para corte
                        break;
                    }
                    recortes.add(new Recorte(pLinha[i]-5, pLinha[i + 1]+5, pColuna[j]-5, pColuna[j + 1]+5));
                }
                if (pLinha[i] == 0) {//nao possui mais elementos para corte
                    break;
                }
            }
            recortesAll.add(recortes);
        }
        return recortesAll;
    }

    public void Recortar(ArrayList<Imagem> lista, int execucao, String destino, ArrayList<ArrayList<Recorte>> rec) throws IOException {
        BufferedImage image;
        int a, b;
        for (int nImg = 0; nImg < lista.size(); nImg++) {
            image = lista.get(nImg).getImg();
            for (Recorte r : rec.get(nImg)) {
                for (int i = r.getxMin(); i < r.getxMax(); i++) {
                    for (int j = r.getyMin(); j < r.getyMax(); j++) {
                        a=i;
                        b=j;
                        if(i<0)
                            a=0;
                        if(j<0)
                            b=0;
                        Color c = new Color(image.getRGB(b, a));
                        int red = (int) (1);
                        int green = (int) (1);
                        int blue = (int) (1);
                        Color gray = new Color(red + green + blue, red + green + blue, red + green + blue);
                        image.setRGB(b, a, gray.getRGB());
                        
                    }
                }
            }
            File ouptut = new File(destino + "/" + lista.get(nImg).getNome() + "_TesteTTT_" + execucao + ".bmp");
            ImageIO.write(image, "bmp", ouptut);
        }

    }

}
