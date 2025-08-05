package br.com.daviaires.poo.projetofinal.telas;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

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

        JPanel panelNomesEquipes = new JPanel();
        panelNomesEquipes.setLayout(new GridLayout(2,1,5,5));
        frame.add(panelNomesEquipes, BorderLayout.NORTH);

        JLabel labelTitulo = new JLabel();
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setText("PARTIDA");
        panelNomesEquipes.add(labelTitulo);

        JLabel labelEquipes = new JLabel();
        labelEquipes.setHorizontalAlignment(SwingConstants.CENTER);
        labelEquipes.setText("X");
        panelNomesEquipes.add(labelEquipes);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(2,2,5,5));
        frame.add(botoes, BorderLayout.CENTER);

        JButton buttonCadastraEquipes = new JButton("Cadastrar Equipes");
        buttonCadastraEquipes.setFocusable(false);
        buttonCadastraEquipes.setToolTipText("Cria e salva equipes");
        buttonCadastraEquipes.setPreferredSize(new Dimension(200, 50));
        buttonCadastraEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastraEquipe cadastraEquipe = new CadastraEquipe();
                cadastraEquipe.inicializa();
                cadastraEquipe.show();
                frame.dispose();
            }
        });
        botoes.add(buttonCadastraEquipes, BorderLayout.CENTER);

        JButton buttonCarregaEquipes = new JButton("Carregar Equipes");
        buttonCarregaEquipes.setFocusable(false);
        buttonCarregaEquipes.setToolTipText("Selecione as equipes que disputarão a partida");
        buttonCarregaEquipes.setPreferredSize(new Dimension(200, 50));
        buttonCarregaEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Equipe> equipes = new ArrayList<>();
                while(equipes.size()<2) {
                    JFileChooser fileChooser = new JFileChooser("src/br/com/daviaires/poo/projetofinal/objetos/equipes");
                    if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        try {
                            Reader reader = new FileReader(selectedFile.getPath());
                            Equipe equipe = new Gson().fromJson(reader, Equipe.class);
                            equipes.add(equipe);
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
                labelEquipes.setText(equipes.get(0).getNome() + " X " + equipes.get(1).getNome());
                equipes.get(0).equipeInfo();
                equipes.get(1).equipeInfo();
            }
        });
        botoes.add(buttonCarregaEquipes, BorderLayout.CENTER);

        JButton buttonVoltar = new JButton("Voltar");
        buttonVoltar.setFocusable(false);
        buttonVoltar.setToolTipText("Volta para a tela inicial");
        buttonVoltar.setPreferredSize(new Dimension(200, 50));
        buttonVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inicial inicial = new Inicial();
                inicial.inicializa();
                inicial.show();
                frame.dispose();
            }
        });
        botoes.add(buttonVoltar, BorderLayout.CENTER);

        JButton buttonIniciarPartida = new JButton("Iniciar Partida");
        buttonIniciarPartida.setFocusable(false);
        buttonIniciarPartida.setToolTipText("Inicia a partida entre as duas equipes selecionadas");
        buttonIniciarPartida.setPreferredSize(new Dimension(200, 50));
        buttonIniciarPartida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Partida partida = new Partida();
                partida.inicializa();
                partida.show();
                frame.dispose();
            }
        });
        botoes.add(buttonIniciarPartida, BorderLayout.CENTER);
    }
    public void show(){
        frame.setVisible(true);
    }
}
