package br.com.daviaires.poo.projetofinal.partidas;

import br.com.daviaires.poo.projetofinal.equipe.Equipe;

public class PartidaGrupos extends Partida{

    public PartidaGrupos(String nome, Equipe equipe1, Equipe equipe2, int pontosSet, int pontosTieBreak, int numSets, int[] placar) {
        super(nome, equipe1, equipe2, pontosSet, pontosTieBreak,  numSets, placar);
    }
}
