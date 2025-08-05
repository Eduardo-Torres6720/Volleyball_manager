package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Partida {
    private JFrame frame;

    public Partida(){
        inicializa();
    }

    public void inicializa(){
        frame = new JFrame();
        this.frame.setTitle("Partida");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(800, 500);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(5,5));

    }
    public void show(){
        frame.setVisible(true);
    }
}