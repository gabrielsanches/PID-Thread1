package GUI;

import Metodos.CaminhoImg;
import Metodos.Metodos;
import Metodos.Threads;
import ch.qos.logback.core.joran.spi.ConsoleTarget;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.opencv.core.Core;

public class Inicial extends javax.swing.JFrame {

    //ArrayList<MatImagem> lista_imagens = new ArrayList<>();
    ArrayList<CaminhoImg> lista_CImagens = new ArrayList<>();
    String diretorio = null;
    String destino = null;
    int execucao = 1, numero_imagens = 6, n_threads = 2;
    double tempo_inicio_sequencial, tempo_final_sequencial;

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
        setTitle("Consuelo System - Divisão de Dados");

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
        chooser.setCurrentDirectory(new File("C:\\Users\\Gabriel\\Documents\\PID-Thread1\\Entrada"));
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
        chooser.setCurrentDirectory(new File("C:\\Users\\Gabriel\\Documents\\PID-Thread1\\Saida"));
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

            Threads[] threads = new Threads[n_threads];
            Thread[] t = new Thread[n_threads];
            for (int i = 0; i < n_threads; i++) {
                threads[i] = new Threads(destino, i);
            }

            int k = 0;
            jTextArea1.setText(jTextArea1.getText() + "\nRealizando Filtros\n");
            while (!lista_CImagens.isEmpty()) {
                threads[k].addCImagem(lista_CImagens.remove(0));
                k++;
                if (k == n_threads) {
                    k = 0;
                }
            }
            tempo_inicio_sequencial = System.currentTimeMillis() - tempo_inicio;
            for (int i = 0; i < n_threads; i++) {
                t[i] = new Thread(threads[i]);
                t[i].start();
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

            double aux = System.currentTimeMillis();
            try {
                juntarResultados(threads);
            } catch (Exception ex) {
                Logger.getLogger(Inicial.class.getName()).log(Level.SEVERE, null, ex);
            }

            double tempo_final_total = System.currentTimeMillis();
            tempo_final_sequencial = tempo_final_total - aux;
            for (int i = 0; i < n_threads; i++) {

                double tempo_execucao = threads[i].getTempo_final() - threads[i].getTempo_inicial();
                jTextArea1.setText(jTextArea1.getText() + "\nReconheceu Thread " + i);
                jTextArea1.setText(jTextArea1.getText() + "\nTempo de execução: " + tempo_execucao + " milissegundos");
            }

            jTextArea1.setText(jTextArea1.getText() + "\nExecução Finalizada!");

            double tempo_execucao = tempo_final_total - tempo_inicio;
            jTextArea1.setText(jTextArea1.getText() + "\nTempo sequencial inicial: " + tempo_inicio_sequencial + " milissegundos");
            jTextArea1.setText(jTextArea1.getText() + "\nTempo sequencial final: " + tempo_final_sequencial + " milissegundos");
            jTextArea1.setText(jTextArea1.getText() + "\nTempo de execução total: " + tempo_execucao + " milissegundos");
        }
    }//GEN-LAST:event_jbExecutarActionPerformed

    public void juntarResultados(Threads[] lista_t) throws FileNotFoundException, IOException {
        int soma_tickets = 0;

        ArrayList<String> tickets = new ArrayList<>();
        ArrayList<Integer> contador = new ArrayList<>();

        FileWriter fw = new FileWriter("resultado_total.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        for (Threads t : lista_t) {
            Scanner scan = new Scanner(new File("resultado" + t.getThread() + ".txt"));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                if (line.length() == 7) {
                    String ticket = line.split("=")[0].trim();
                    Integer numbers = Integer.parseInt(line.split("=")[1].trim());
                    if (tickets.contains(ticket)) {
                        int id_ticket = tickets.indexOf(ticket);
                        contador.set(id_ticket, contador.get(id_ticket) + numbers);
                    } else {
                        contador.add(numbers);
                        tickets.add(ticket);
                    }
                }
                if (line.length() > 10) {
                    if (line.charAt(0) == 'T') {
                        Integer number = Integer.parseInt(line.substring(line.split("=")[0].length() + 2, line.length()));
                        soma_tickets += number;
                    }

                }

            }
        }
        
        bw.write("Número de repetições de Tickets em cada imagem\n\n");
        bw.write("Tickets encontrados nas imagens = " + soma_tickets + "\n");
        
        for (int i=0;i<tickets.size();i++){
            bw.write(tickets.get(i) + " = " + contador.get(i) + "\n");
        }
        bw.close();
    }

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
