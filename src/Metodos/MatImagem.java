/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metodos;

import java.awt.image.BufferedImage;
import org.opencv.core.Mat;

/**
 *
 * @author Gabriel-PC
 */
public class MatImagem {

    Mat img;
    String nome;

    public MatImagem(Mat img, String nome) {
        this.img = img;
        this.nome = nome;
    }

    public Mat getImg() {
        return img;
    }

    public void setImg(Mat img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
