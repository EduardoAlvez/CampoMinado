package br.com.cod3r.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import br.com.cod3r.cm.excecao.ExplosaoException;

public class Tabuleiro {

	private int linhas;
	private int colunas;
	private int minas;

	private final List<Campo> campos = new ArrayList<>();

	public Tabuleiro(int linhas, int colunas, int minas) {
		this.linhas = linhas;
		this.colunas = colunas;
		this.minas = minas;

		gerarCampos();
		associarVizinhos();
		sortearMinas();
	}

//	IMPLEMENTAÇÃO DOS MÉTODOS DE USUARIO
	public void abrir(int linha, int coluna) {
		try {
			campos.parallelStream().filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna).findFirst()
					.ifPresent(c -> c.abrir());
		} catch (ExplosaoException e) {
			campos.forEach(c -> c.setAberto(true));
			throw e;
		}
	}

	public void alternarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLINHA() == linha && c.getCOLUNA() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcacao());
	}

	private void gerarCampos() {
		for (int linha = 0; linha < linhas; linha++) {
			for (int coluna = 0; coluna < colunas; coluna++) {
				campos.add(new Campo(linha, coluna));
			}
		}
	}

	private void associarVizinhos() {
		for (Campo primeiroCampo : campos) {
			for (Campo campoProximos : campos) {
				primeiroCampo.adicionarVizinho(campoProximos);
			}
		}
	}

	private void sortearMinas() {
		long minasArmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();

		do {
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
			minasArmadas = campos.stream().filter(minado).count();
		} while (minasArmadas < minas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.stream().forEach(c -> c.reiniciar());
		sortearMinas();
	}

	public String toString() {
		StringBuilder build = new StringBuilder();

		int i = 0;
		for (int c = 0; c < colunas; c++) {
			build.append("  ");
			build.append(c);
			build.append("");
		}
		build.append("\n");
		
		for (int linha = 0; linha < linhas; linha++) {
			build.append(linha);
			for (int coluna = 0; coluna < colunas; coluna++) {
				build.append("");
				build.append("[" + campos.get(i) + "]");
				build.append("");
				i++;
			}
			build.append("\n");
		}

		return build.toString();
	}

}
