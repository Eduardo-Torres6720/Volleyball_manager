package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelecionaFuncao {
    private JFrame frame;

    public SelecionaFuncao(){ inicializa(); }

    public void inicializa(){
        frame = new JFrame();
        this.frame.setTitle("Volleyball Manager");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(800, 500);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(5,5));

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(2,2,5,5));
        frame.add(botoes, BorderLayout.SOUTH);

        JButton buttonIniciar = new JButton("Carregar Equipes");
        buttonIniciar.setFocusable(false);
        buttonIniciar.setToolTipText("Selecione as equipes que disputarão a partida");
        buttonIniciar.setPreferredSize(new Dimension(200, 50));
        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                    File[] selectedFiles = fileChooser.getSelectedFiles();
                    System.out.println(selectedFiles[0]);
                }
            }
        });
        botoes.add(buttonIniciar, BorderLayout.CENTER);
    }
    public void show(){
        frame.setVisible(true);
    }
}
