package br.com.daviaires.poo.projetofinal.partidas;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;

import java.io.Serializable;

public abstract class Partida implements Serializable {
    private final String nome;
    private static Equipe equipe1;
    private static Equipe equipe2;
    private final int pontosSet;
    private final int pontosTieBreak;
    private final int numSets;
    private int[] placar;
    private static Equipe estaSacando;

    public Partida(String nome, Equipe equipe1, Equipe equipe2, int pontosSet, int pontosTieBreak, int numSets, int[] placar) {
        this.nome = nome;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
        this.pontosSet = pontosSet;
        this.pontosTieBreak = pontosTieBreak;
        this.numSets = numSets;
        this.placar = placar;
    }

    public static void caraOuCoroa(){
        estaSacando = (Math.random() < 0.5) ? equipe1 : equipe2;
    }

    public static void ponto(Equipe estaSacando){

    }

    public static void atualizaPlacar(){

    }

    public String getNome() {
        return nome;
    }

    public int getPontosSet() {
        return pontosSet;
    }

    public int getPontosTieBreak() {
        return pontosTieBreak;
    }

    public int getNumSets() {
        return numSets;
    }

    public int[] getPlacar() {
        return placar;
    }
}