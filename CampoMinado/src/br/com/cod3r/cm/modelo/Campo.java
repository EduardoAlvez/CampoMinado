package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

public class Campo {

	private final int LINHA;
	private final int COLUNA;

	private boolean minado;
	private boolean aberto;
	private boolean marcado;
	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.LINHA = linha;
		this.COLUNA = coluna;
	}

	boolean adicionarVizinho(Campo vizinho) {
		boolean linhaDiferente = LINHA != vizinho.LINHA;
		boolean colunaDiferente = COLUNA != vizinho.COLUNA;
		boolean diagonal = linhaDiferente && colunaDiferente;

		int deltaLinha = Math.abs(LINHA - vizinho.LINHA);
		int deltaColuna = Math.abs(COLUNA - vizinho.COLUNA);
		int deltaGeral = deltaLinha + deltaColuna;

		if (deltaGeral == 1 && !diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else if (deltaGeral == 2 && diagonal) {
			vizinhos.add(vizinho);
			return true;
		} else {
			return false;
		}
	}
}
