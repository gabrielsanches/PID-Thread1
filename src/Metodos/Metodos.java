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

    public ArrayList<Imagem> EscalaCinza(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        int width;
        int height;
        ArrayList<Imagem> imagens = new ArrayList<Imagem>();
        BufferedImage image;
        File a =new File(destino+"/Etapa1");
        a.mkdir();
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
            File ouptut = new File(destino + "/Etapa1/" + img.getNome() + "_Cinza_" + execucao + ".bmp");
            Imagem im = new Imagem(image, img.getNome());
            imagens.add(im);
            ImageIO.write(image, "bmp", ouptut);
        }
        return imagens;
    }

    public ArrayList<Imagem> Limiarizacao(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        int width;
        int height;
        BufferedImage imagem;
        ArrayList<Imagem> imagens = new ArrayList<Imagem>();
        File a =new File(destino+"/Etapa2");
        a.mkdir();
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
            File ouptut = new File(destino + "/Etapa2/" + img.getNome() + "_Limiarizado_" + execucao + ".bmp");
            Imagem im = new Imagem(imagem, img.getNome());
            imagens.add(im);
            ImageIO.write(imagem, "bmp", ouptut);
        }
        return imagens;
    }

    public int maior(int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        int valor = v1;
        if (valor < v2) {
            valor = v2;
        }
        if (valor < v3) {
            valor = v3;
        }
        if (valor < v4) {
            valor = v4;
        }
        if (valor < v5) {
            valor = v5;
        }
        if (valor < v6) {
            valor = v6;
        }
        if (valor < v7) {
            valor = v7;
        }
        if (valor < v8) {
            valor = v8;
        }
        if (valor < v9) {
            valor = v9;
        }
        return valor;
    }

    public int menor(int v1, int v2, int v3, int v4, int v5, int v6, int v7, int v8, int v9) {
        int valor = v1;
        if (valor > v2) {
            valor = v2;
        }
        if (valor > v3) {
            valor = v3;
        }
        if (valor > v4) {
            valor = v4;
        }
        if (valor > v5) {
            valor = v5;
        }
        if (valor > v6) {
            valor = v6;
        }
        if (valor > v7) {
            valor = v7;
        }
        if (valor > v8) {
            valor = v8;
        }
        if (valor > v9) {
            valor = v9;
        }
        return valor;
    }

    public ArrayList<Imagem> Mediana(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        ArrayList<Imagem> imagens = new ArrayList<Imagem>();
        int width;
        int height;
        BufferedImage image;
        File a =new File(destino+"/Etapa4");
        a.mkdir();
        for (Imagem img : lista) {
            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            int count = 0;
            int mat[][] = new int[height][width];

            for (int i = 0; i < height; i++) {

                for (int j = 0; j < width; j++) {
                    count++;
                    Color c = new Color(image.getRGB(j, i));
                    int red = (int) (c.getRed() * 0.299);
                    int green = (int) (c.getGreen() * 0.587);
                    int blue = (int) (c.getBlue() * 0.114);
                    int valor_pixel = red + green + blue;
                    mat[i][j] = valor_pixel;

                }
            }

            for (int i = 1; i < height - 1; i++) {
                for (int j = 1; j < width - 1; j++) {
                    int menor = menor(mat[i][j], mat[i][j + 1], mat[i + 1][j], mat[i + 1][j + 1], mat[i - 1][j], mat[i][j - 1], mat[i - 1][j - 1], mat[i + 1][j - 1], mat[i - 1][j + 1]);
                    int maior = maior(mat[i][j], mat[i][j + 1], mat[i + 1][j], mat[i + 1][j + 1], mat[i - 1][j], mat[i][j - 1], mat[i - 1][j - 1], mat[i + 1][j - 1], mat[i - 1][j + 1]);
                    int cor = (menor + maior) / 2;
                    image.setRGB(j, i, new Color(cor, cor, cor).getRGB());
                }
            }
            File ouptut = new File(destino + "/Etapa4/" + img.getNome() + "_Mediana_" + execucao + ".bmp");
            Imagem im = new Imagem(image, img.getNome());
            imagens.add(im);
            ImageIO.write(image, "bmp", ouptut);
        }
        return imagens;
    }

    public ArrayList<ArrayList<Integer>> varreduraColuna(ArrayList<Imagem> lista, int execucao, String destino, int tonalidade) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int width;
        int height;
        BufferedImage image;
        int count = 0;

        for (Imagem img : lista) {
            count++;

            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer linha = 0;
            ArrayList<Integer> linhasImg = new ArrayList<>();
            for (int i = 0; i < width; i++) {
                linha = 0;
                for (int j = 0; j < height; j++) {
                    Color c = new Color(image.getRGB(i, j));
                    if ((int) c.getGreen() < tonalidade) {
                        linha++;
                    }
                }
                linhasImg.add(linha);
            }

            result.add(linhasImg);
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> varreduraLinha(ArrayList<Imagem> lista, int execucao, String destino, int tonalidade) throws IOException {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        int width;
        int height;
        BufferedImage image;
        int count = 0;

        for (Imagem img : lista) {
            count++;

            image = img.getImg();
            width = image.getWidth();
            height = image.getHeight();
            Integer coluna = 0;
            ArrayList<Integer> colunasImg = new ArrayList<>();
            for (int i = 0; i < height; i++) {
                coluna = 0;
                for (int j = 0; j < width; j++) {
                    Color c = new Color(image.getRGB(j, i));
                    if ((int) c.getGreen() < tonalidade) {
                        coluna++;
                    }
                }

                colunasImg.add(coluna);
            }

            result.add(colunasImg);
        }
        return result;
    }

    public void Contraste(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {

    }

    public ArrayList<ArrayList<Recorte>> capturarRecorte(ArrayList<ArrayList<Integer>> linha, ArrayList<ArrayList<Integer>> coluna) {
        boolean flag = true;//true=busca limite minimo, false=busca limite maximo
        ArrayList<ArrayList<Recorte>> recortesAll = new ArrayList<ArrayList<Recorte>>();

        int contador;
        ArrayList<Integer> img;
        for (int nImg = 0; nImg < linha.size(); nImg++) {
            int pLinha[] = new int[1000];
            int pColuna[] = new int[1000];
            ArrayList<Recorte> recortes = new ArrayList<Recorte>();
            contador = 0;
            img = linha.get(nImg);
            for (int i = 0; i < img.size() - 5; i++) {
                if (flag) {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) < 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pLinha[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) > 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pLinha[contador] = i;
                        flag = true;
                        contador++;
                    }
                }
            }

            flag = true;
            img = coluna.get(nImg);
            contador = 0;

            for (int i = 0; i < img.size() - 5; i++) {
                if (flag) {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) < 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pColuna[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) > 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pColuna[contador] = i;
                        flag = true;
                        contador++;
                    }
                }
            }

            for (int i = 0; i < 1000; i = i + 2) {
                for (int j = 0; j < 1000; j = j + 2) {
                    if (pColuna[j] == 0) {
                        break;
                    }
                    if (((pLinha[i + 1] + 5 - pLinha[i] - 5) * (pColuna[j + 1] + 5 - pColuna[j] - 5)) > 10000) {
                        recortes.add(new Recorte(pLinha[i] - 5, pLinha[i + 1] + 5, pColuna[j] - 5, pColuna[j + 1] + 5));
                    }
                }
                if (pLinha[i] == 0) {
                    break;
                }
            }
            recortesAll.add(recortes);
        }
        return recortesAll;
    }

    public ArrayList<ArrayList<Recorte>> capturarRecorte1(ArrayList<ArrayList<Integer>> linha, ArrayList<ArrayList<Integer>> coluna) {

        boolean flag = true;//true=busca limite minimo, false=busca limite maximo
        ArrayList<ArrayList<Recorte>> recortesAll = new ArrayList<ArrayList<Recorte>>();
        
        int contador;
        ArrayList<Integer> img;
        for (int nImg = 0; nImg < linha.size(); nImg++) {
            int pLinha[] = new int[1000];
            int pColuna[] = new int[1000];
            ArrayList<Recorte> recortes = new ArrayList<Recorte>();
            contador = 0;
            img = linha.get(nImg);
            for (int i = 0; i < img.size() - 5; i++) {
                if (flag) {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) < 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pLinha[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) > 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pLinha[contador] = i;
                        flag = true;
                        contador++;
                    }
                }
            }

            flag = true;
            img = coluna.get(nImg);
            contador = 0;

            for (int i = 0; i < img.size() - 5; i++) {
                if (flag) {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) < 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pColuna[contador] = i;
                        flag = false;
                        contador++;
                    }
                } else {
                    boolean teste = true;
                    for (int k = 0; k < 5; k++) {
                        if (img.get(k + i) > 5) {
                            teste = false;
                        }
                    }
                    if (teste) {
                        pColuna[contador] = i;
                        flag = true;
                        contador++;
                    }
                }
            }

            for (int i = 0; i < 1000; i = i + 2) {
                for (int j = 0; j < 1000; j = j + 2) {
                    if (pColuna[j] == 0) {
                        break;
                    }
                    if (((pLinha[i + 1] + 5 - pLinha[i] - 5) * (pColuna[j + 1] + 5 - pColuna[j] - 5)) > 1000) {
                        recortes.add(new Recorte(pLinha[i] - 5, pLinha[i + 1] + 5, pColuna[j] - 5, pColuna[j + 1] + 5));
                    }
                }
                if (pLinha[i] == 0) {
                    break;
                }
            }
            recortesAll.add(recortes);
        }
        return recortesAll;
    }

    public ArrayList<Imagem> Recortar(ArrayList<Imagem> lista, int execucao, String destino, ArrayList<ArrayList<Recorte>> rec) throws IOException {
        BufferedImage image;
        int a, b;
        ArrayList<Imagem> im = new ArrayList<>();
        BufferedImage aux;
        File ff =new File(destino+"/Etapa3");
        ff.mkdirs();
        int count = 1;
        for (int nImg = 0; nImg < lista.size(); nImg++) {
            count = 1;
            image = lista.get(nImg).getImg();
            for (Recorte r : rec.get(nImg)) {
                aux = new BufferedImage(r.getyMax() - r.getyMin(), r.getxMax() - r.getxMin(), BufferedImage.TYPE_INT_RGB);
                for (int i = r.getxMin(); i < r.getxMax(); i++) {
                    for (int j = r.getyMin(); j < r.getyMax(); j++) {
                        a = i;
                        b = j;
                        if (i < 0) {
                            a = 0;
                        }
                        if (j < 0) {
                            b = 0;
                        }
                        if (i >= image.getHeight()) {
                            a = r.getxMax() - 1;
                        }
                        if (j >= image.getWidth()) {
                            b = r.getyMax() - 1;
                        }
                        aux.setRGB(b - r.getyMin(), a - r.getxMin(), image.getRGB(b, a));
                    }
                }
                File ouptut = new File(destino + "/Etapa3/" + lista.get(nImg).getNome() + "_Recorte_" + count + "_" + execucao + ".bmp");
                ImageIO.write(aux, "bmp", ouptut);
                im.add(new Imagem(aux, lista.get(nImg).getNome() + "_Recorte_" + count));
                count++;
            }
        }
        return im;
    }

    public ArrayList<Imagem> Recortar2(ArrayList<Imagem> lista, int execucao, String destino, ArrayList<ArrayList<Recorte>> rec) throws IOException {
        BufferedImage image;
        int a, b;
        ArrayList<Imagem> im = new ArrayList<>();
        BufferedImage aux;
        File ff =new File(destino+"/Etapa5");
        ff.mkdir();
        int count = 1;
        for (int nImg = 0; nImg < lista.size(); nImg++) {
            count = 1;
            image = lista.get(nImg).getImg();
            for (Recorte r : rec.get(nImg)) {
                System.out.println(r.getxMin()+" "+r.getxMax()+" "+r.getyMin()+" "+r.getyMax());
                System.out.println((r.getyMax() - r.getyMin())+" "+(r.getxMax() - r.getxMin()));
                aux = new BufferedImage(r.getyMax() - r.getyMin(), r.getxMax() - r.getxMin(), BufferedImage.TYPE_INT_RGB);
                for (int i = r.getxMin(); i < r.getxMax(); i++) {
                    for (int j = r.getyMin(); j < r.getyMax(); j++) {
                        a = i;
                        b = j;
                        if (i < 0) {
                            a = 0;
                        }
                        if (j < 0) {
                            b = 0;
                        }
                        if (i >= image.getHeight()) {
                            a = image.getHeight() - 1;
                        }
                        if (j >= image.getWidth()) {
                            b = image.getWidth() - 1;
                        }
                        aux.setRGB(b - r.getyMin(), a - r.getxMin(), image.getRGB(b, a));
                    }
                }
                File ouptut = new File(destino + "/Etapa5/" + lista.get(nImg).getNome() + "_Recorte2_" + count + "_" + execucao + ".bmp");
                ImageIO.write(aux, "bmp", ouptut);
                im.add(new Imagem(aux, lista.get(nImg).getNome() + "_Recorte2_" + count));
                count++;
            }
        }
        return im;
    }

    public double getAngulo(Ponto a, Ponto b) {//retorna o angulo
        return Math.acos(modulo((a.getX() * b.getX()) + (a.getY() * b.getY())) / (Math.sqrt(Math.pow(a.getX(), 2) + Math.pow(a.getY(), 2)) * Math.sqrt(Math.pow(b.getX(), 2) + Math.pow(b.getY(), 2))));

    }

    public Ponto[] getPontos(Imagem a) throws IOException {
        Ponto p[] = new Ponto[2];
        p[0] = new Ponto(0, 0);
        p[1] = new Ponto(0, 0);
        boolean ok1 = true, ok2 = true;

        int X = a.getImg().getHeight() - 1, CountYp1 = 0;
        int CountYp2 = a.getImg().getWidth() - 1;

        while ((ok1 || ok2) && (X >= 0)) {
            while ((CountYp1 + 15 < CountYp2) && (ok1 || ok2)) {

                if (ok1) {
                    Color c = new Color(a.getImg().getRGB(CountYp1, X));
                    if (c.getGreen() < 240) {
                        p[0].setX(X);
                        p[0].setY(CountYp1);
                        ok1 = false;
                    } else {
                        CountYp1++;
                    }
                }
                if (ok2) {
                    Color c = new Color(a.getImg().getRGB(CountYp2, X));
                    if (c.getGreen() < 240) {
                        p[1].setX(X);
                        p[1].setY(CountYp2 - 2);
                        ok2 = false;
                    } else {
                        CountYp2--;
                    }
                }
            }
            X--;
            if (ok1) {
                CountYp1 = 0;
            }
            if (ok2) {
                CountYp2 = CountYp2 = a.getImg().getWidth() - 1;
            }
        }

        return p;
    }

    private float modulo(float a) {
        if (a < 0) {
            return -a;
        } else {
            return a;
        }
    }

    public ArrayList<Imagem> CorrigeInclinacao(ArrayList<Imagem> lista, int execucao, String destino) throws IOException {
        ArrayList<Imagem> result = new ArrayList<Imagem>();
        double matriz[][];
        BufferedImage aux, image;

        for (Imagem img : lista) {
            image = img.getImg();
            Ponto p[] = getPontos(img);
            double angulo = getAngulo(p[0], p[1]);
            matriz = geraMatriz(p[0], angulo);
            int a = 0, b = 0;
            aux = new BufferedImage(img.getImg().getWidth(), img.getImg().getHeight(), BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    a = (int) (matriz[0][0] * i + matriz[0][1] * j + matriz[0][2]);
                    b = (int) (matriz[1][0] * i + matriz[1][1] * j + matriz[1][2]);
                    if (a > 0 && a < image.getHeight() && b > 0 && b < image.getWidth()) {
                        aux.setRGB(b, a, image.getRGB(j, i));
                    }
                }
            }
            result.add(new Imagem(aux, img.getNome()));
            File ouptut = new File(destino + "/" + img.getNome() + "_Rotacao_" + execucao + ".bmp");
            ImageIO.write(aux, "bmp", ouptut);
        }
        return result;
    }

    public double[][] geraMatriz(Ponto p, double angulo) {
        double matriz[][] = new double[3][3];

        matriz[0][0] = Math.cos(angulo);
        matriz[0][1] = -Math.sin(angulo);
        matriz[0][2] = ((-p.getX()) * Math.cos(angulo)) + ((-Math.sin(angulo)) * (-p.getY())) + p.getX();
        matriz[1][0] = Math.sin(angulo);
        matriz[1][1] = Math.cos(angulo);
        matriz[1][2] = ((-p.getX()) * Math.sin(angulo)) + ((p.getY()) * Math.cos(angulo)) + p.getY();
        matriz[2][0] = 0;
        matriz[2][1] = 0;
        matriz[2][2] = 1;

        return matriz;
    }

}
