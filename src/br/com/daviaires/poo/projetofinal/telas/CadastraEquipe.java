package br.com.daviaires.poo.projetofinal.telas;

import br.com.daviaires.poo.projetofinal.App;
import br.com.daviaires.poo.projetofinal.equipe.Equipe;
import br.com.daviaires.poo.projetofinal.jogador.*;
import br.com.daviaires.poo.projetofinal.validacao.Validardor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import java.util.List;

public class CadastraEquipe {
    private JFrame frame;
    private JTextField textEquipeNome, textRanking;
    private JTextField textJogadorNome, textJogadorNumero, textJogadorAltura;
    private JComboBox<String> comboJogadorFuncao;
    private String caminhoArquivoJson = "src/br/com/daviaires/poo/projetofinal/objetos/equipes/Equipes.json";
    private JTable tabelaEscalacao;
    private DefaultTableModel tableModel;
    private JButton buttonCadastrarJogador, buttonExcluirJogador, buttonConfirmar, buttonVoltar;

    // Constantes para as funções
    private static final String CENTRAL = "Central";
    private static final String LEVANTADOR = "Levantador";
    private static final String LIBERO = "Líbero";
    private static final String OPOSTO = "Oposto";
    private static final String PONTEIRO = "Ponteiro";

    public CadastraEquipe() {
        inicializa();
    }

    public void inicializa() {
        // --- 1. Inicialização e Configuração dos Componentes ---
        frame = new JFrame("Cadastrar Equipes");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 550);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        textEquipeNome = new JTextField(20);
        textRanking = new JTextField(20);

        textJogadorNome = new JTextField(15);
        textJogadorNumero = new JTextField(15);
        textJogadorAltura = new JTextField(15);
        textJogadorAltura.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && (c != '.' || textJogadorAltura.getText().contains("."))) {
                    e.consume();
                }
            }
        });

        String[] funcoes = {CENTRAL, LEVANTADOR, LIBERO, OPOSTO, PONTEIRO};
        comboJogadorFuncao = new JComboBox<>(funcoes);

        String[] colunas = {"Nome", "Número", "Altura", "Função"};
        tableModel = new DefaultTableModel(null, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabelaEscalacao = new JTable(tableModel);
        
        buttonCadastrarJogador = new JButton("Cadastrar Jogador");
        buttonExcluirJogador = new JButton("Excluir Jogador");
        buttonConfirmar = new JButton("Confirmar");
        buttonVoltar = new JButton("Voltar");

        buttonCadastrarJogador.addActionListener(e -> adicionarJogador());
        buttonExcluirJogador.addActionListener(e -> excluirJogador());
        buttonConfirmar.addActionListener(e -> confirmarCadastro());
        buttonVoltar.addActionListener(e -> voltarTela());


        // --- 2. Montagem do Layout ---
        frame.setLayout(new BorderLayout(5, 5));
        
        JPanel painelGeral = new JPanel(new BorderLayout(10, 10));
        painelGeral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        painelGeral.add(criarPainelTitulo(), BorderLayout.NORTH);
        painelGeral.add(criarPainelCentral(), BorderLayout.CENTER);
        painelGeral.add(criarPainelBotoes(), BorderLayout.SOUTH);

        frame.add(painelGeral, BorderLayout.CENTER);
    }

    private JPanel criarPainelTitulo() {
        JPanel painel = new JPanel(new GridLayout(2, 1));
        JLabel tituloApp = new JLabel("CADASTRO DE EQUIPES", SwingConstants.CENTER);
        tituloApp.setFont(new Font(tituloApp.getFont().getName(), Font.BOLD, 16));
        JLabel sloganApp = new JLabel("Preencha com os dados da equipe (2 Centrais, 2 Ponteiros, 1 Levantador, 1 Libero, 1 Oposto)", SwingConstants.CENTER);
        painel.add(tituloApp);
        painel.add(sloganApp);
        return painel;
    }

    private JPanel criarPainelCentral() {
        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel wrapperPainelDadosEquipe = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapperPainelDadosEquipe.add(criarPainelDadosEquipe());
        painel.add(wrapperPainelDadosEquipe, BorderLayout.NORTH);
        
        JPanel painelSplit = new JPanel(new GridLayout(1, 2, 10, 10));
        painelSplit.add(criarPainelFormJogador());
        JScrollPane scrollPaneTabela = new JScrollPane(tabelaEscalacao);
        painelSplit.add(scrollPaneTabela);
        
        painel.add(painelSplit, BorderLayout.CENTER);
        return painel;
    }

    private JPanel criarPainelDadosEquipe() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Dados da Equipe"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        painel.add(new JLabel("Equipe:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painel.add(textEquipeNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0;
        painel.add(new JLabel("Ranking:"), gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1.0;
        painel.add(textRanking, gbc);
        
        return painel;
    }

    private JPanel criarPainelFormJogador() {
        JPanel painel = new JPanel(new GridBagLayout());
        painel.setBorder(BorderFactory.createTitledBorder("Jogadores"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(textJogadorNome, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Número:"), gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(textJogadorNumero, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Altura (cm):"), gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(textJogadorAltura, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE;
        painel.add(new JLabel("Função:"), gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        painel.add(comboJogadorFuncao, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.weighty = 1.0;
        painel.add(new JLabel(""), gbc);

        return painel;
    }

    private JPanel criarPainelBotoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        painel.add(buttonCadastrarJogador);
        painel.add(buttonExcluirJogador);
        painel.add(buttonConfirmar);
        painel.add(buttonVoltar);
        return painel;
    }
    
    private void adicionarJogador() {
        if (textJogadorNome.getText().isBlank() || textJogadorNumero.getText().isBlank() || textJogadorAltura.getText().isBlank()) {
            JOptionPane.showMessageDialog(frame, "Preencha todos os campos do jogador.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String funcaoSelecionada = comboJogadorFuncao.getSelectedItem().toString();
        
        int[] limites = {2, 1, 1, 1, 2}; // Central, Levantador, Libero, Oposto, Ponteiro
        String[] funcoes = {CENTRAL, LEVANTADOR, LIBERO, OPOSTO, PONTEIRO};
        int[] contagemAtual = new int[5];

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String funcaoNaTabela = tableModel.getValueAt(i, 3).toString();
            for(int j = 0; j < funcoes.length; j++){
                if(funcaoNaTabela.equals(funcoes[j])){
                    contagemAtual[j]++;
                }
            }
        }
        
        for(int i = 0; i < funcoes.length; i++){
            if(funcaoSelecionada.equals(funcoes[i]) && contagemAtual[i] >= limites[i]){
                JOptionPane.showMessageDialog(frame, "A função " + funcaoSelecionada + " já atingiu o número máximo de jogadores (" + limites[i] + ").", "Limite Atingido", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String[] dados = {
            textJogadorNome.getText(), 
            textJogadorNumero.getText(), 
            textJogadorAltura.getText(), 
            funcaoSelecionada
        };
        tableModel.addRow(dados);
        
        textJogadorNome.setText("");
        textJogadorNumero.setText("");
        textJogadorAltura.setText("");
    }
    
    private void excluirJogador() {
        int selectedRow = tabelaEscalacao.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(frame, "Selecione um jogador na tabela para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void confirmarCadastro() {
        if (textEquipeNome.getText().isBlank() || textRanking.getText().isBlank()) {
            JOptionPane.showMessageDialog(frame, "Preencha o nome e o ranking da equipe.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (tableModel.getRowCount() != 7) {
             JOptionPane.showMessageDialog(frame, "A equipe deve ter exatamente 7 jogadores para ser cadastrada.", "Equipe Incompleta", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Equipe equipe = new Equipe(textEquipeNome.getText(), Integer.parseInt(textRanking.getText()));
        Vector<Vector> dataVector = tableModel.getDataVector();

        for (Vector<?> rowData : dataVector) {
            String nome = rowData.get(0).toString();
            String numero = rowData.get(1).toString();
            String altura = rowData.get(2).toString();
            String funcao = rowData.get(3).toString();

            switch (funcao) {
                case CENTRAL -> equipe.escalarJogador(new Central(nome, equipe.getNome(), funcao, numero, altura));
                case LEVANTADOR -> equipe.escalarJogador(new Levantador(nome, equipe.getNome(), funcao, numero, altura));
                case LIBERO -> equipe.escalarJogador(new Libero(nome, equipe.getNome(), funcao, numero, altura));
                case OPOSTO -> equipe.escalarJogador(new Oposto(nome, equipe.getNome(), funcao, numero, altura));
                case PONTEIRO -> equipe.escalarJogador(new Ponteiro(nome, equipe.getNome(), funcao, numero, altura));
            }
        }
        
        salvarEquipeComoJson(equipe);
        
        JOptionPane.showMessageDialog(frame, "Equipe " + equipe.getNome() + " cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        SelecionaFuncao selecaoTela = new SelecionaFuncao(null);
        selecaoTela.show();
        frame.dispose();
    }

    private List<Equipe> carregarDadosJson() {
        Gson gson = new Gson();

        List<Equipe> equipes = new ArrayList<>();
        try (FileReader reader = new FileReader(caminhoArquivoJson)) {
            java.lang.reflect.Type tipoListaEquipe = new TypeToken<List<Equipe>>() {}.getType();
            equipes = gson.fromJson(reader, tipoListaEquipe);
            return equipes;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao ler os dados da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao processar dados da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return null;
    }
    
    private void salvarEquipeComoJson(Equipe equipe){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<Equipe> equipes = carregarDadosJson();

        equipes.add(equipe);

        try (FileWriter fw = new FileWriter(caminhoArquivoJson)) {
            gson.toJson(equipes, fw);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Erro ao salvar o arquivo: " + e.getMessage(), "Erro de I/O", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void voltarTela() {
        SelecionaFuncao telaFuncao = new SelecionaFuncao(null);
        telaFuncao.show();
        frame.dispose();
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new CadastraEquipe().show();
        });
    }
}