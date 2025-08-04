package br.com.daviaires.poo.projetofinal.jogador;

public class Levantador extends Jogador{

    public Levantador(String nome, String time, String funcao, String numero, String altura) {
        super(nome, time, funcao, numero, altura);
    }

    public float saque(){
        return 0;
    }

    public float ataque() {
        return 0;
    }

    public float bloqueio(){
        return 0;
    }

}
