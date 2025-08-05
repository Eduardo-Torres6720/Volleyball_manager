package br.com.daviaires.poo.projetofinal.jogador;

public class Ponteiro extends Jogador{

    public Ponteiro(String nome, String time, String funcao, String numero, String altura) {
        super(nome, time, funcao, numero, altura);
    }

    @Override
    public float defesa() {
        return 0;
    }

    @Override
    public float levantamento() {
        return 0;
    }

    public float saque(){
        return 0;
    }

    public float ataque(){
        return 0;
    }

    public float bloqueio(){
        return 0;
    }

}
