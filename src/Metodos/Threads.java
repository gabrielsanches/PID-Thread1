/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import GUI.Inicial;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

/**
 *
 * @author Gabriel
 */
public class Threads implements Runnable {

    ArrayList<CaminhoImg> Cimagens = new ArrayList<>();
    Metodos metodos = new Metodos();
    String destino, nome_img;
    int id;
    boolean isActive = true;
    double tempo_inicial, tempo_final;

    public Threads(String b, int id) {
        destino = b;
        this.id = id;
    }

    public boolean isIsActive() {
        return isActive;
    }

    @Override
    public void run() {
        tempo_inicial = System.currentTimeMillis();
        String thread = "_Thread_" + id;
        int cont = 1;
        int execucao = 1;

        for (int i = 0; i < Cimagens.size(); i++) {
            cont = 1;
            execucao = 1;
            MatImagem img = new MatImagem(Imgcodecs.imread(Cimagens.get(i).getCaminho()), Cimagens.get(i).getNome());
            ArrayList<MatImagem> lista_img = new ArrayList<MatImagem>();
            lista_img.add(img);

            try {
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
            metodos.ReconhecerDigitos(execucao, destino, cont - 1, thread);
            execucao++;
        } catch (Exception ex) {
            Logger.getLogger(Threads.class.getName()).log(Level.SEVERE, null, ex);
        }

        tempo_final = System.currentTimeMillis();
        isActive = false;
        

    }

    public void addCImagem(CaminhoImg c) {
        Cimagens.add(c);
    }

    public double getTempo_inicial() {
        return tempo_inicial;
    }

    public void setTempo_inicial(double tempo_inicial) {
        this.tempo_inicial = tempo_inicial;
    }

    public double getTempo_final() {
        return tempo_final;
    }

    public void setTempo_final(double tempo_final) {
        this.tempo_final = tempo_final;
    }
    
    

}
