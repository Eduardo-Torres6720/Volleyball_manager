package br.com.daviaires.poo.projetofinal.telas;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Partida {
    private JFrame frame;

    private int placarTimeA = 0;
    private int placarTimeB = 0;
    private JLabel placarLabelA;
    private JLabel placarLabelB;

    private DefaultListModel<String> historicoModel;
    private JList<String> historicoList;

    public Partida() {
        inicializa();
    }

    private class HistoricoCellRenderer extends JLabel implements ListCellRenderer<String> {
        public HistoricoCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            setText(value);
            Border padding = BorderFactory.createEmptyBorder(5, 5, 5, 5);
            Border linhaSeparadora = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.decode("#e0e0e0"));
            setBorder(BorderFactory.createCompoundBorder(linhaSeparadora, padding));

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(Color.WHITE);
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    public void inicializa() {
        frame = new JFrame();
        this.frame.setTitle("Partida");
        this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.frame.setSize(1024, 768);
        this.frame.setResizable(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout(10, 10));

        JPanel placarPanel = new JPanel(new GridLayout(1, 3));
        placarPanel.setBackground(Color.decode("#f0f0f0"));

        JPanel containerPlacar = new JPanel(new BorderLayout());
        containerPlacar.setBorder(BorderFactory.createTitledBorder("Placar"));
        containerPlacar.add(placarPanel, BorderLayout.CENTER);

        JPanel panelTimeA = new JPanel();
        panelTimeA.setBackground(Color.decode("#f0f0f0"));
        JButton btnMaisPontoA = new JButton("+1");
        JButton btnMenosPontoA = new JButton("-1");
        JButton btnTempoA = new JButton("Pedir Tempo");
        panelTimeA.add(new JLabel("Time A"));
        panelTimeA.add(btnMaisPontoA);
        panelTimeA.add(btnMenosPontoA);
        panelTimeA.add(btnTempoA);

        JPanel panelPlacarCentral = new JPanel();
        panelPlacarCentral.setBackground(Color.decode("#f0f0f0"));
        placarLabelA = new JLabel(String.valueOf(placarTimeA));
        placarLabelA.setFont(new Font("Arial", Font.BOLD, 40));
        placarLabelB = new JLabel(String.valueOf(placarTimeB));
        placarLabelB.setFont(new Font("Arial", Font.BOLD, 40));
        panelPlacarCentral.add(placarLabelA);
        panelPlacarCentral.add(new JLabel(" x ")).setFont(new Font("Arial", Font.BOLD, 40));
        panelPlacarCentral.add(placarLabelB);

        JPanel panelTimeB = new JPanel();
        panelTimeB.setBackground(Color.decode("#f0f0f0"));
        JButton btnMaisPontoB = new JButton("+1");
        JButton btnMenosPontoB = new JButton("-1");
        JButton btnTempoB = new JButton("Pedir Tempo");
        panelTimeB.add(new JLabel("Time B"));
        panelTimeB.add(btnMaisPontoB);
        panelTimeB.add(btnMenosPontoB);
        panelTimeB.add(btnTempoB);
        
        btnMaisPontoA.setFocusPainted(false);
        btnMenosPontoA.setFocusPainted(false);
        btnTempoA.setFocusPainted(false);
        btnMaisPontoB.setFocusPainted(false);
        btnMenosPontoB.setFocusPainted(false);
        btnTempoB.setFocusPainted(false);

        placarPanel.add(panelTimeA);
        placarPanel.add(panelPlacarCentral);
        placarPanel.add(panelTimeB);
        this.frame.add(containerPlacar, BorderLayout.NORTH);

        historicoModel = new DefaultListModel<>();
        historicoList = new JList<>(historicoModel);
        historicoList.setFont(new Font("Monospaced", Font.PLAIN, 12));
        historicoList.setBackground(Color.WHITE);
        historicoList.setCellRenderer(new HistoricoCellRenderer());
        JScrollPane historicoScrollPane = new JScrollPane(historicoList);
        historicoScrollPane.setPreferredSize(new Dimension(300, 0));
        historicoScrollPane.setBorder(BorderFactory.createTitledBorder("Histórico da Partida"));
        this.frame.add(historicoScrollPane, BorderLayout.EAST);

        btnMaisPontoA.addActionListener(e -> {
            placarTimeA++;
            atualizarPlacarEHistorico("Ponto para o Time A.");
        });
        btnMenosPontoA.addActionListener(e -> {
            if (placarTimeA > 0) {
                placarTimeA--;
                atualizarPlacarEHistorico("Ponto removido do Time A.");
            }
        });
        btnTempoA.addActionListener(e -> {
            adicionarAoHistorico("Time A pediu tempo.");
        });
        btnMaisPontoB.addActionListener(e -> {
            placarTimeB++;
            atualizarPlacarEHistorico("Ponto para o Time B.");
        });
        btnMenosPontoB.addActionListener(e -> {
            if (placarTimeB > 0) {
                placarTimeB--;
                atualizarPlacarEHistorico("Ponto removido do Time B.");
            }
        });
        btnTempoB.addActionListener(e -> {
            adicionarAoHistorico("Time B pediu tempo.");
        });

        JPanel quadraPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        quadraPanel.setBackground(Color.WHITE);
        quadraPanel.setBorder(BorderFactory.createTitledBorder("Jogadores em Quadra"));

        JPanel ladoA = new JPanel(new GridLayout(3, 2, 10, 10));
        ladoA.setBackground(Color.WHITE);
        ladoA.setBorder(BorderFactory.createTitledBorder("Time A"));
        for (int i = 0; i < 6; i++) {
            ladoA.add(new JLabel("Jogador A" + (i + 1), SwingConstants.CENTER));
        }
        JPanel ladoB = new JPanel(new GridLayout(3, 2, 10, 10));
        ladoB.setBackground(Color.WHITE);
        ladoB.setBorder(BorderFactory.createTitledBorder("Time B"));
        for (int i = 0; i < 6; i++) {
            ladoB.add(new JLabel("Jogador B" + (i + 1), SwingConstants.CENTER));
        }
        quadraPanel.add(ladoA);
        quadraPanel.add(ladoB);
        this.frame.add(quadraPanel, BorderLayout.CENTER);
    }

    private void adicionarAoHistorico(String evento) {
        historicoModel.add(0, evento);
    }

    private void atualizarPlacarEHistorico(String descricaoPonto) {
        placarLabelA.setText(String.valueOf(placarTimeA));
        placarLabelB.setText(String.valueOf(placarTimeB));
        String evento = String.format("%s Placar: %d x %d", descricaoPonto, placarTimeA, placarTimeB);
        adicionarAoHistorico(evento);
    }

    public void show() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Partida telaPartida = new Partida();
            telaPartida.show();
        });
    }
}