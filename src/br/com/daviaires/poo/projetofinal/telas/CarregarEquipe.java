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

        String[] colunas = {"Time", "Ranking"};
        modeloTabela = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabelaEquipes = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaEquipes);

        btnSelecionarEquipes = new JButton("Selecionar equipe");
        btnConfirmar = new JButton("Confirmar");

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(btnSelecionarEquipes);
        painelBotoes.add(btnConfirmar);
        
        
        btnSelecionarEquipes.addActionListener(e -> obterEquipesSelecionadas());
        btnConfirmar.addActionListener(e -> confirmarSelecao());
        
        areaEquipesSelecionadas = new JTextArea(8, 30); 
        areaEquipesSelecionadas.setEditable(false);
        areaEquipesSelecionadas.setBorder(BorderFactory.createTitledBorder("Equipes Selecionadas"));
        JScrollPane scrollPaneSelecionados = new JScrollPane(areaEquipesSelecionadas);
        
        add(scrollPane, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        add(scrollPaneSelecionados, BorderLayout.EAST);

        modeloTabela.setRowCount(0);

        Gson gson = new Gson();

        try (FileReader reader = new FileReader(caminhoArquivoJson)) {
            java.lang.reflect.Type tipoListaEquipe = new TypeToken<List<Equipe>>() {}.getType();
            equipes = gson.fromJson(reader, tipoListaEquipe);

            if (equipes != null) {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao processar dados da equipe: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        setVisible(true);
    }

    private void obterEquipesSelecionadas() {
        linhaSelecionada.add(tabelaEquipes.getSelectedRow());
        
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CarregarEquipe());
    }
}
