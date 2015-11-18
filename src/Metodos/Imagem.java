package Metodos;

import java.awt.image.BufferedImage;

public class Imagem {
    BufferedImage img;
    String nome;

    public Imagem(BufferedImage img, String nome) {
        this.img = img;
        this.nome = nome;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
