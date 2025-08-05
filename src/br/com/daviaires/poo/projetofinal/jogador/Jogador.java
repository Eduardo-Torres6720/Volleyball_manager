package br.com.daviaires.poo.projetofinal.jogador;

import java.io.Serializable;
import java.util.Objects;

public abstract class Jogador implements Serializable {
    private final String nome;
    private final String equipe;
    private final String funcao;
    private final String numero;
    private final String altura;

    public Jogador(String nome, String equipe, String funcao, String numero, String altura) {
        this.nome = nome;
        this.equipe = equipe;
        this.funcao = funcao;
        this.numero = numero;
        this.altura = altura;
    }

    public void infoJogador(){
        System.out.println("Dados do Jogador");
        System.out.println("Nome: " + this.getNome());
        System.out.println("Função: " + this.getFuncao());
        System.out.println("Número: " + this.getNumero());
        System.out.println("Altura: " + this.getAltura());
    }

    public abstract float defesa();

    public abstract float levantamento();

    public String getNome() {
        return nome;
    }

    public String getEquipe() {
        return equipe;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getNumero() {
        return numero;
    }

    public String getAltura(){
        return altura;
    }

}
