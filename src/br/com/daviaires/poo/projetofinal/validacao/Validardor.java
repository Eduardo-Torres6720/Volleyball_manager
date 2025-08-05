package br.com.daviaires.poo.projetofinal.validacao;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;

public class Validardor {

    public static boolean validarQuantFuncao(DefaultTableModel tableModel, String[] dados) {
        int[] quantfuncao = {0, 0, 0, 0, 0};
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            switch (tableModel.getValueAt(i, 3).toString()) {
                case "Central":
                    quantfuncao[0]++;
                    break;
                case "Levantador":
                    quantfuncao[1]++;
                    break;
                case "Líbero":
                    quantfuncao[2]++;
                    break;
                case "Oposto":
                    quantfuncao[3]++;
                    break;
                case "Ponteiro":
                    quantfuncao[4]++;
                    break;

                default:
                    break;
            }
        }
        if ((quantfuncao[0] == 2 && dados[3] == "Central") ||
                (quantfuncao[1] == 1 && dados[3] == "Levantador") ||
                (quantfuncao[2] == 1 && dados[3] == "Líbero") ||
                (quantfuncao[3] == 1 && dados[3] == "Oposto") ||
                (quantfuncao[4] == 2 && dados[3] == "Ponteiro")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validarDadosTamanhoEquipe(Equipe equipe) {
        if (equipe.getEscalacao().size() != 7) {
            return false;
        } else {
            return true;
        }
    }
}
