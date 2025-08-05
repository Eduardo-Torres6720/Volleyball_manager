package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;
import br.com.daviaires.poo.projetofinal.jogador.Jogador;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CarregarEquipe extends JFrame {
    private JTable tabelaEquipes;
    private DefaultTableModel modeloTabela;
    private JButton btnSelecionarEquipes;
    private JButton btnConfirmar;
    private String caminhoArquivoJson = "src/br/com/daviaires/poo/projetofinal/objetos/equipes/Equipes.json"; // Caminho para o seu arquivo JSON
    private JTextArea areaEquipesSelecionadas;
    List<Equipe> equipes = new ArrayList<>();
    List<Equipe> listaDeEquipesSelecionadas = new ArrayList<>();
    List<Integer> linhaSelecionada = new ArrayList<>();

    public CarregarEquipe() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);

        // 1. Configurar o DefaultTableModel
        String[] colunas = {"Time", "Ranking"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Impede que as células sejam editadas diretamente na tabela
                return false;
            }
        };

        // 2. Criar a JTable com o modelo
        tabelaEquipes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaEquipes);

        // 3. Criar o botão de carregar JSON
        btnSelecionarEquipes = new JButton("Selecionar equipe");
        btnConfirmar = new JButton("Confirmar");

        // Adicionar componentes à janela
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(btnSelecionarEquipes);
        painelBotoes.add(btnConfirmar);
        
        
        // 4. Adicionar ActionListener ao botão
        btnSelecionarEquipes.addActionListener(e -> obterEquipesSelecionadas());
        btnConfirmar.addActionListener(e -> confirmarSelecao());
        
        // --- Configuração da Área de Exibição de Selecionados ---
        areaEquipesSelecionadas = new JTextArea(8, 30); // Define tamanho
        areaEquipesSelecionadas.setEditable(false);
        areaEquipesSelecionadas.setBorder(BorderFactory.createTitledBorder("Equipes Selecionadas"));
        JScrollPane scrollPaneSelecionados = new JScrollPane(areaEquipesSelecionadas);
        
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        add(scrollPaneSelecionados, BorderLayout.EAST);

        // Limpa as linhas existentes na tabela antes de adicionar novas
        modeloTabela.setRowCount(0);

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(caminhoArquivoJson)) {
            // Define o tipo para a desserialização (lista de objetos Usuario)
            java.lang.reflect.Type tipoListaEquipe = new TypeToken<List<Equipe>>() {}.getType();
            equipes = gson.fromJson(reader, tipoListaEquipe);

            if (equipes != null) {
                // Adiciona cada usuário lido do JSON ao modelo da tabela
                for (Equipe equipe : equipes) {
                    modeloTabela.addRow(new Object[]{
                        equipe.getNome(),
                        equipe.getRanking()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nenhuma equipe encontrada!.", "Informação", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao ler os dados da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (Exception e) { // Captura outras exceções de desserialização do Gson
            JOptionPane.showMessageDialog(this, "Erro ao processar dados da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void obterEquipesSelecionadas() {
        linhaSelecionada.add(tabelaEquipes.getSelectedRow());
        int quantLinhas = linhaSelecionada.size();
        
        if (listaDeEquipesSelecionadas.size() < 2 && linhaSelecionada.getLast() != -1) {
            areaEquipesSelecionadas.setText("");
        } else {
            JOptionPane.showMessageDialog(btnSelecionarEquipes, "Limite máximo de 2 equipes.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }


        if (linhaSelecionada.getLast() != -1) {
            modeloTabela.removeRow(linhaSelecionada.getLast());
            Equipe equipe;
            if (linhaSelecionada.getFirst() <= linhaSelecionada.getLast() && linhaSelecionada.size() == 2) {
                equipe = equipes.get(linhaSelecionada.getLast() + 1);
            } else {
                equipe = equipes.get(linhaSelecionada.getLast());
            }
            listaDeEquipesSelecionadas.add(equipe);
            for (Equipe equipeSelecionada : listaDeEquipesSelecionadas) {
                areaEquipesSelecionadas.append(equipeSelecionada.getNome() + "\n");
            }
        } else {
            linhaSelecionada.removeLast();
            JOptionPane.showMessageDialog(btnSelecionarEquipes, "Selecione uma equipe para adicionar.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void confirmarSelecao() {
        if (listaDeEquipesSelecionadas.size() != 2) {
            JOptionPane.showMessageDialog(btnConfirmar, "Exige 2 equipes para confirmar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        SelecionaFuncao selecionaFuncao = new SelecionaFuncao(listaDeEquipesSelecionadas);
        selecionaFuncao.show();
        setVisible(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarregarEquipe());
    }
}
