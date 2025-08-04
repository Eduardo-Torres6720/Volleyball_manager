package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EquipeCadastrada {
    private JFrame frame;

    public EquipeCadastrada(){
        inicializa();
    }

    public void inicializa() {
        frame = new JFrame();
        this.frame.setTitle("Equipe Cadastrada");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(400, 200);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(5, 5));

        JPanel aviso = new JPanel();
        aviso.setLayout(new GridLayout(2, 1, 5, 5));
        frame.add(aviso, BorderLayout.CENTER);

        JLabel avisoEquipeCadastrada = new JLabel("Equipe cadastrada com sucesso");
        avisoEquipeCadastrada.setHorizontalAlignment(SwingConstants.CENTER);
        aviso.add(avisoEquipeCadastrada, BorderLayout.CENTER);

        JButton buttonOk = new JButton("Ok");
        buttonOk.setFocusable(false);
        buttonOk.setToolTipText("Ok");
        buttonOk.setPreferredSize(new Dimension(200, 50));
        buttonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                CadastraEquipe cadastraEquipe = new CadastraEquipe();
                cadastraEquipe.inicializa();
                cadastraEquipe.show();
            }
        });
        aviso.add(buttonOk, BorderLayout.CENTER);
    }

        public void show () {
            frame.setVisible(true);
        }
}

