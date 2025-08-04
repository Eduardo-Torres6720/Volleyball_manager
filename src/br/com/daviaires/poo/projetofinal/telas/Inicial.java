package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inicial {
    private JFrame frame;

    public Inicial(){
        inicializa();
    }

    public void inicializa(){
        frame = new JFrame();
        this.frame.setTitle("Volleyball Manager");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(800, 500);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(5,5));

        JPanel titulo = new JPanel();
        titulo.setLayout(new GridLayout(2,1,5,5));
        frame.add(titulo, BorderLayout.CENTER);

        JLabel tituloApp = new JLabel("VOLLEYBALL MANAGER");
        tituloApp.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sloganApp = new JLabel("O App do Árbitro de Mesa");
        sloganApp.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.add(tituloApp, BorderLayout.CENTER);
        titulo.add(sloganApp, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(1,2,5,5));
        frame.add(botoes, BorderLayout.SOUTH);

        JButton buttonIniciar = new JButton("Iniciar");
        buttonIniciar.setFocusable(false);
        buttonIniciar.setToolTipText("Iniciar");
        buttonIniciar.setPreferredSize(new Dimension(200, 50));
        buttonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastraEquipe cadastraEquipe = new CadastraEquipe();
                cadastraEquipe.inicializa();
                cadastraEquipe.show();
                frame.dispose();
            }
        });
        botoes.add(buttonIniciar, BorderLayout.CENTER);


        JButton buttonEncerrar = new JButton("Encerrar");
        buttonEncerrar.setFocusable(false);
        buttonEncerrar.setToolTipText("Encerrar");
        buttonEncerrar.setPreferredSize(new Dimension(200, 50));
        buttonEncerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
        botoes.add(buttonEncerrar, BorderLayout.CENTER);
    }

    public void show(){
        frame.setVisible(true);
    }
}
