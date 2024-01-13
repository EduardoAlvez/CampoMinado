package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Campo {

	private final int LINHA;
	private final int COLUNA;

	private boolean minado;
	private boolean aberto;
	private boolean marcado;
	private List<Campo> vizinhos = new ArrayList<>();

	Campo(int linha, int coluna) {
		this.LINHA = Math.abs(linha);
		this.COLUNA = Math.abs(coluna);
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

	void alternarMarcacao() {
		if (!aberto) {
			this.marcado = !marcado;
		}
	}

	boolean abrir() {
		if (!aberto && !marcado) {
			this.aberto = true;

			if (minado) {
				throw new ExplosaoException();
			}

			if (vizinhacaSegura()) {
				vizinhos.forEach(v -> v.abrir());
			}
			return true;
		} else {
			return false;
		}
	}

	boolean vizinhacaSegura() {
		return vizinhos.stream().noneMatch(v -> v.minado);
	}

	boolean minar() {
		if (!minado) {
			return this.minado = true;
		}
		return minado;
	}

	boolean objetivoAlcancado() {
		boolean desvendado = !minado && aberto;
		boolean protegido = minado && marcado;
		return desvendado || protegido;
	}

	long minasNaVizinhaca() {
		return vizinhos.stream().filter(v -> v.minado).count();
	}

	void reiniciar() {
		aberto = false;
		minado = false;
		marcado = false;
	}

	public String toString() {
		if (marcado) {
			return "X";
		} else if (aberto && minado) {
			return "*";
		} else if (aberto && minasNaVizinhaca() > 0) {
			return Long.toString(minasNaVizinhaca());
		} else if (aberto) {
			return " ";
		} else {
			return "?";
		}
	}

	boolean isMarcado() {
		return marcado;
	}

	boolean isAberto() {
		return aberto;
	}

	void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}

	boolean isFechado() {
		return !isAberto();
	}

	boolean isMinado() {
		return minado;
	}

	public int getLINHA() {
		return LINHA;
	}

	public int getCOLUNA() {
		return COLUNA;
	}

}
