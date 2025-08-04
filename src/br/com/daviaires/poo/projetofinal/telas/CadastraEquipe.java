package br.com.daviaires.poo.projetofinal.telas;

import br.com.daviaires.poo.projetofinal.App;
import br.com.daviaires.poo.projetofinal.equipe.Equipe;
import br.com.daviaires.poo.projetofinal.jogador.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class CadastraEquipe {
    private JFrame frame;

    public CadastraEquipe(){
        inicializa();
    }

    public void inicializa(){
        frame = new JFrame();
        this.frame.setTitle("Cadastrar Equipes");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(800, 500);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new GridLayout(4,1,5,5));

        JPanel titulo = new JPanel();
        titulo.setLayout(new GridLayout(2,1,5,5));
        frame.add(titulo, BorderLayout.NORTH);

        JLabel tituloApp = new JLabel("CADASTRO DE EQUIPES");
        tituloApp.setHorizontalAlignment(SwingConstants.CENTER);
        JLabel sloganApp = new JLabel("Preencha com os dados da primeira equipe");
        sloganApp.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.add(tituloApp, BorderLayout.CENTER);
        titulo.add(sloganApp, BorderLayout.CENTER);

        JPanel dadosEquipe = new JPanel();
        dadosEquipe.setLayout(new GridLayout(2,2,5,5));
        frame.add(dadosEquipe, BorderLayout.CENTER);

        JLabel labelEquipeNome = new JLabel("Equipe");
        labelEquipeNome.setHorizontalAlignment(SwingConstants.CENTER);
        dadosEquipe.add(labelEquipeNome);
        JTextField textEquipeNome = new JTextField();
        dadosEquipe.add(textEquipeNome);
        JLabel labelRanking = new JLabel("Ranking");
        labelRanking.setHorizontalAlignment(SwingConstants.CENTER);
        dadosEquipe.add(labelRanking);
        JTextField textRanking = new JTextField();
        textRanking.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    e.setKeyChar('\u0000');
                }
            }
        });
        dadosEquipe.add(textRanking);

        JPanel dadosJogador = new JPanel();
        dadosJogador.setLayout(new GridLayout(4,2,5,5));

        JLabel labelJogadorNome = new JLabel("Nome");
        labelJogadorNome.setHorizontalAlignment(SwingConstants.CENTER);
        dadosJogador.add(labelJogadorNome);
        JTextField textJogadorNome = new JTextField();
        dadosJogador.add(textJogadorNome);
        JLabel labelJogadorNumero = new JLabel("Número");
        labelJogadorNumero.setHorizontalAlignment(SwingConstants.CENTER);
        dadosJogador.add(labelJogadorNumero);
        JTextField textJogadorNumero = new JTextField();
        textJogadorNumero.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    e.setKeyChar('\u0000');
                }
            }
        });
        dadosJogador.add(textJogadorNumero);
        JLabel labelJogadorAltura = new JLabel("Altura");
        labelJogadorAltura.setHorizontalAlignment(SwingConstants.CENTER);
        dadosJogador.add(labelJogadorAltura);
        JTextField textJogadorAltura = new JTextField();
        textJogadorAltura.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                if(!Character.isDigit(c)){
                    if(c != '.') {
                        e.setKeyChar('\u0000');
                    }
                }
            }
        });
        dadosJogador.add(textJogadorAltura);
        JLabel labelJogadorFuncao = new JLabel("Função");
        labelJogadorFuncao.setHorizontalAlignment(SwingConstants.CENTER);
        dadosJogador.add(labelJogadorFuncao);
        String[] funcoes = {"Central", "Levantador", "Líbero", "Oposto", "Ponteiro"};
        JComboBox<String> comboJogadorFuncao = new JComboBox<String>(funcoes);
        dadosJogador.add(comboJogadorFuncao);

        JPanel tabelaJogadores = new JPanel();
        String[] colunas = {"Nome", "Número", "Altura", "Função"};
        JTable tabelaEscalacao = new JTable();
        tabelaEscalacao.setModel(new DefaultTableModel(null,colunas){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        JScrollPane scrollPane = new JScrollPane(tabelaEscalacao);
        tabelaJogadores.add(scrollPane);

        JPanel areaDados = new JPanel();
        areaDados.setLayout(new GridLayout(1,2,5,5));
        areaDados.add(dadosJogador);
        areaDados.add(tabelaJogadores);

        frame.add(areaDados, BorderLayout.CENTER);

        JPanel botoes = new JPanel();
        botoes.setLayout(new GridLayout(4,2,5,5));
        frame.add(botoes, BorderLayout.CENTER);

        JButton buttonCadastrarJogador = new JButton("Cadastrar Jogador");
        buttonCadastrarJogador.setFocusable(false);
        buttonCadastrarJogador.setToolTipText("Inclui o jogador na lista da escalação");
        buttonCadastrarJogador.setPreferredSize(new Dimension(200, 50));
        buttonCadastrarJogador.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String[] dados = {textJogadorNome.getText(), textJogadorNumero.getText(), textJogadorAltura.getText(), comboJogadorFuncao.getSelectedItem().toString()};
                DefaultTableModel tableModel = (DefaultTableModel)tabelaEscalacao.getModel();
                tableModel.addRow(dados);
            }
        });
        botoes.add(buttonCadastrarJogador, BorderLayout.CENTER);

        JButton buttonExcluirJogador = new JButton("Excluir Jogador");
        buttonExcluirJogador.setFocusable(false);
        buttonExcluirJogador.setToolTipText("Exclui da lista o jogador selecionado");
        buttonExcluirJogador.setPreferredSize(new Dimension(200, 50));
        buttonExcluirJogador.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               DefaultTableModel tblModel = (DefaultTableModel)tabelaEscalacao.getModel();
               if(tabelaEscalacao.getSelectedRowCount()==1){
                   tblModel.removeRow(tabelaEscalacao.getSelectedRow());
               }
           }
        });
        botoes.add(buttonExcluirJogador);

        JButton buttonConfirmar = new JButton("Confirmar");
        buttonConfirmar.setFocusable(false);
        buttonConfirmar.setToolTipText("Confirma o cadastro e avança para o próximo passo");
        buttonConfirmar.setPreferredSize(new Dimension(200, 50));
        buttonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipe equipe = new Equipe(textEquipeNome.getText(), Integer.parseInt(textRanking.getText()));
                DefaultTableModel tableModel = (DefaultTableModel)tabelaEscalacao.getModel();
                Vector<Vector> dataVector =tableModel.getDataVector();
                for(int i = 0; i < dataVector.size(); i++){
                    Vector rowData = dataVector.elementAt(i);
                    switch (rowData.elementAt(3).toString()){
                        case "Central":
                            equipe.escalarJogador(new Central(rowData.elementAt(0).toString(),equipe.getNome(), rowData.elementAt(3).toString(),rowData.elementAt(1).toString(),rowData.elementAt(2).toString()));
                            break;
                        case "Levantador":
                            equipe.escalarJogador(new Levantador(rowData.elementAt(0).toString(),equipe.getNome(), rowData.elementAt(3).toString(),rowData.elementAt(1).toString(),rowData.elementAt(2).toString()));
                            break;
                        case "Libero":
                            equipe.escalarJogador(new Libero(rowData.elementAt(0).toString(),equipe.getNome(), rowData.elementAt(3).toString(),rowData.elementAt(1).toString(),rowData.elementAt(2).toString()));
                            break;
                        case "Oposto":
                            equipe.escalarJogador(new Oposto(rowData.elementAt(0).toString(),equipe.getNome(), rowData.elementAt(3).toString(),rowData.elementAt(1).toString(),rowData.elementAt(2).toString()));
                            break;
                        case "Ponteiro":
                            equipe.escalarJogador(new Ponteiro(rowData.elementAt(0).toString(),equipe.getNome(), rowData.elementAt(3).toString(),rowData.elementAt(1).toString(),rowData.elementAt(2).toString()));
                            break;
                    }
                }
                Gson gson = new GsonBuilder()
                        .setPrettyPrinting()
                        .create();
                FileWriter fw = null;
                try {
                    fw = new FileWriter("src/br/com/daviaires/poo/projetofinal/objetos/equipes/" + equipe.getNome() + ".json");
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                gson.toJson(equipe, fw);
                try {
                    fw.close();
                } catch (IOException exception) {
                    throw new RuntimeException(exception);
                }
                EquipeCadastrada equipeCadastrada = new EquipeCadastrada();
                equipeCadastrada.inicializa();
                equipeCadastrada.show();
                System.out.println(equipe.getNome() + " cadastrada");
                frame.dispose();
            }
        });


        botoes.add(buttonConfirmar, BorderLayout.CENTER);


        JButton buttonVoltar = new JButton("Voltar");
        buttonVoltar.setFocusable(false);
        buttonVoltar.setToolTipText("Retorna para a tela inicial");
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
    }

    public void show(){
        frame.setVisible(true);
    }
}
