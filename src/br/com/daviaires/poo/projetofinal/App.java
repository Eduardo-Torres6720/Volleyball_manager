package br.com.daviaires.poo.projetofinal;

import br.com.daviaires.poo.projetofinal.telas.Inicial;
import br.com.daviaires.poo.projetofinal.telas.SelecionaFuncao;

import javax.swing.*;

public class App {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Inicial inicial = new Inicial();
                inicial.show();
                //SelecionaFuncao selecionaFuncao = new SelecionaFuncao();
                //selecionaFuncao.show();
            }
        });
    }
}
