package br.com.daviaires.poo.projetofinal.partidas;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;

public class PartidaTerceiroLugar extends Partida{
    private final String nome = "Disputa de Terceiro Lugar";
    private final int pontosSet = 25;
    private final int pontosTieBreak = 15;
    private final int numSets = 5;
    private int[] placar = {0,0};


    public PartidaTerceiroLugar(String nome, Equipe equipe1, Equipe equipe2, int pontosSet, int pontosTieBreak, int numSets, int[] placar) {
        super(nome, equipe1, equipe2, pontosSet, pontosTieBreak,  numSets, placar);
    }

}
