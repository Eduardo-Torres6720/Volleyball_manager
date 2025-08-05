package br.com.daviaires.poo.projetofinal.equipe;

import br.com.daviaires.poo.projetofinal.jogador.Jogador;

import java.io.*;
import java.util.ArrayList;

public class Equipe implements Serializable{
    private final String nome;
    private final int ranking;
    private ArrayList<Object> escalacao = new ArrayList<>();

    public Equipe(String nome, int ranking) {
        this.nome = nome;
        this.ranking = ranking;
    }

    public Equipe(String nome, int ranking, ArrayList<Object> escalacao) {
        this.nome = nome;
        this.ranking = ranking;
        this.escalacao = escalacao;
    }

    public void escalarJogador(Jogador jogador){
        this.escalacao.add(jogador);
    }

    public void equipeInfo(){
        System.out.println("Nome do time: " + this.getNome());
        System.out.println("Ranking do time: " + this.getRanking());
    }

    public void salvaEquipe() throws IOException {
        String fileName = "src/br/com/daviaires/poo/projetofinal/objetos/equipes/" + this.getNome() + ".ser";
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(this);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    public String getNome(){
        return nome;
    }

    public int getRanking(){
        return ranking;
    }

    public ArrayList<Object> getEscalacao() {
        return escalacao;
    }
}
