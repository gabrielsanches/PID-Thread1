package GUI;

import Metodos.CaminhoImg;
import Metodos.Imagem;
import Metodos.MatImagem;
import Metodos.Metodos;
import Metodos.Ponto;
import Metodos.Threads;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import net.sourceforge.tess4j.TesseractException;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Inicial extends javax.swing.JFrame {

    //ArrayList<MatImagem> lista_imagens = new ArrayList<>();
    ArrayList<CaminhoImg> lista_CImagens = new ArrayList<>();
    String diretorio = null;
    String destino = null;
    int execucao = 1, numero_imagens = 0, n_threads = 4;
    Metodos metodos = new Metodos();

    public Inicial() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbAbrir = new javax.swing.JButton();
        jbDestino = new javax.swing.JButton();
        jtAbrir = new javax.swing.JTextField();
        jtDestino = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jbExecutar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consuelo System");

        jbAbrir.setText("Abrir");
        jbAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAbrirActionPerformed(evt);
            }
        });

        jbDestino.setText("Destino");
        jbDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbDestinoActionPerformed(evt);
            }
        });

        jtAbrir.setEditable(false);

        jtDestino.setEditable(false);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jbExecutar.setText("Executar");
        jbExecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbExecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jbExecutar)
                .addContainerGap(156, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbDestino, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jbAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jtDestino)
                            .addComponent(jtAbrir))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbAbrir)
                    .addComponent(jtAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbDestino)
                    .addComponent(jtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbExecutar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAbrirActionPerformed
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        //lista = new ArrayList<>();
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File("C:\\Users\\Gabriel\\Documents\\PID6 - Thread1\\PID\\Entrada"));
        final int returnVal = chooser.showDialog(null, "Escolher Pasta");

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            diretorio = chooser.getSelectedFile().getAbsolutePath();
            jtAbrir.setText(diretorio);
            try {
                File file = new File(diretorio);
                File afile[] = file.listFiles();
                int i = 0;
                numero_imagens = afile.length;
                for (int j = numero_imagens; i < j; i++) {
                    File arquivos = afile[i];
                    if (arquivos.getName().substring(arquivos.getName().length() - 4).equals(".bmp")) {
                        jTextArea1.setText(jTextArea1.getText() + "\nAbrindo arquivo: " + arquivos.getName());
                        //Mat source = Imgcodecs.imread(jtAbrir.getText().replace("\\", "\\\\") + "\\\\" + arquivos.getName());
                        lista_CImagens.add(new CaminhoImg(jtAbrir.getText().replace("\\", "\\\\") + "\\\\" + arquivos.getName(), arquivos.getName().substring(0, arquivos.getName().length() - 4)));
                        //lista_imagens.add(new MatImagem(source, arquivos.getName().substring(0, arquivos.getName().length() - 4)));
                    }
                }
            } catch (final Exception ioException) {
                ioException.printStackTrace();
            }
        }
    }//GEN-LAST:event_jbAbrirActionPerformed

    private void jbDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbDestinoActionPerformed
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setCurrentDirectory(new File("C:\\Users\\Gabriel\\Documents\\PID6 - Thread1\\PID\\Saida"));
        final int returnVal = chooser.showDialog(null, "Escolher Pasta de Saída");
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            destino = chooser.getSelectedFile().getAbsolutePath();
            jtDestino.setText(destino);
        }
    }//GEN-LAST:event_jbDestinoActionPerformed

    private void jbExecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbExecutarActionPerformed
        if (lista_CImagens.isEmpty()) {
            jTextArea1.setText(jTextArea1.getText() + "\nNenhuma imagem selecionada");
        } else if (destino == null) {
            jTextArea1.setText(jTextArea1.getText() + "\nNenhum destino selecionado");
        } else {
            double tempo_inicio = System.currentTimeMillis();
            int k = 0;
            Threads[] threads = new Threads[n_threads];

            for (int i = 0; i < n_threads; i++) {
                threads[i] = new Threads(destino, i);
            }

            jTextArea1.setText(jTextArea1.getText() + "\nRealizando Filtros");
            while (!lista_CImagens.isEmpty()) {
                threads[k].addCImagem(lista_CImagens.remove(0));
                k++;
                if (k == n_threads) {
                    k = 0;
                }
            }

            for (int i = 0; i < n_threads; i++) {
                threads[i].run();
            }

            while (true) {
                int i, count_threads = 0;
                for (i = 0; i < n_threads; i++) {
                    if (!threads[i].isIsActive()) {
                        count_threads++;
                    }
                }
                if (count_threads == n_threads) {
                    break;
                }
            }

            for (int i = 0; i < n_threads; i++) {
                
                double tempo_execucao = threads[i].getTempo_final() - threads[i].getTempo_inicial();
                System.out.println("Reconheceu Thread " + i);
                System.out.println("Tempo inicial = " + threads[i].getTempo_inicial());
                System.out.println("Tempo final = " + threads[i].getTempo_final());
                System.out.println("\nTempo de execução: " + tempo_execucao + " segundos");
            }

            jTextArea1.setText(jTextArea1.getText() + "\nFinalizado");
            double tempo_final = System.currentTimeMillis();
            double tempo_execucao = tempo_final - tempo_inicio;
            jTextArea1.setText("\nTempo de execução total: " + tempo_execucao + " segundos");
        }
    }//GEN-LAST:event_jbExecutarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton jbAbrir;
    private javax.swing.JButton jbDestino;
    private javax.swing.JButton jbExecutar;
    private javax.swing.JTextField jtAbrir;
    private javax.swing.JTextField jtDestino;
    // End of variables declaration//GEN-END:variables
}
