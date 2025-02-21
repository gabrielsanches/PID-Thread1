/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opencv.imgcodecs.Imgcodecs;

public class Threads implements Runnable {

    ArrayList<CaminhoImg> Cimagens = new ArrayList<>();
    String destino, nome_img;
    int id;
    boolean isActive = true;
    double tempo_inicial, tempo_final;
    String thread;

    public Threads(String b, int id) {
        destino = b;
        this.id = id;
        thread = "_Thread_" + id;
    }

    public boolean isIsActive() {
        return isActive;
    }

    @Override
    public void run() {
        if (!Cimagens.isEmpty()) {
            tempo_inicial = System.currentTimeMillis();
            int cont = 1;
            int execucao = 1;

            for (int i = 0; i < Cimagens.size(); i++) {
                cont = 1;
                execucao = 1;
                MatImagem img = new MatImagem(Imgcodecs.imread(Cimagens.get(i).getCaminho()), Cimagens.get(i).getNome());
                ArrayList<MatImagem> lista_img = new ArrayList<MatImagem>();
                lista_img.add(img);

                try {

                    Metodos metodos = new Metodos();
                    lista_img = metodos.TonsCinza(lista_img, execucao, destino, cont, thread);
                    execucao++;
                    cont++;
                    lista_img = metodos.Gaussian_Adp(lista_img, execucao, destino, cont, thread);
                    cont++;
                    execucao++;
                    lista_img = metodos.Otsu(lista_img, execucao, destino, cont, thread);
                    execucao++;
                    cont++;
                    lista_img = metodos.Otsu(lista_img, execucao, destino, cont, thread);
                    execucao++;
                    cont++;
                    lista_img = metodos.identificarContornos(lista_img, execucao, destino, cont, thread);
                    execucao++;
                    cont++;
                    lista_img = metodos.Erosao(lista_img, execucao, destino, cont, thread);
                    cont++;
                    execucao++;
                    lista_img = metodos.Dilatacao(lista_img, execucao, destino, cont, thread);
                    cont++;
                    execucao++;
                    lista_img = metodos.Dilatacao(lista_img, execucao, destino, cont, thread);
                    cont++;
                    execucao++;
                } catch (Exception ex) {
                    Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
                }

                lista_img.remove(0);
            }
            try {

                Metodos metodos = new Metodos();
                metodos.ReconhecerDigitos(execucao, destino, 8, thread);
                execucao++;
            } catch (Exception ex) {
                Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
            }

            tempo_final = System.currentTimeMillis();
            isActive = false;

        }
    }

    public void addCImagem(CaminhoImg c) {
        Cimagens.add(c);
    }

    public double getTempo_inicial() {
        return tempo_inicial;
    }
    
    public double getTempo_final() {
        return tempo_final;
    }

    public String getThread() {
        return thread;
    }
    
    
}
