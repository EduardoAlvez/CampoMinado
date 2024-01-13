package br.com.cod3r.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.cod3r.cm.excecao.ExplosaoException;
import br.com.cod3r.cm.excecao.SairException;
import br.com.cod3r.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

	private Tabuleiro tabuleiro;
	private Scanner leitor = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() {

		try {
			cicloDoJogo();

			boolean continuar = true;
			while (continuar) {
				System.out.println("Deseija continuar (S/n) ");
				String res = leitor.nextLine();
				if ("n".equalsIgnoreCase(res)) {
					continuar = false;
				} else {
					tabuleiro.reiniciar();
				}
			}
		} catch (SairException e) {
			System.out.println("Até mais.");
		} finally {
			leitor.close();
		}
	}

	private void cicloDoJogo() {

		try {
			while (!tabuleiro.objetivoAlcancado()) {
				System.out.println(tabuleiro);

				String digitado = valorDigitador("Digite o valor de (x,y): ");
				Iterator<Integer> xy = Arrays.stream(digitado.split(",")).map(e -> Integer.parseInt(e.trim()))
						.iterator();

				digitado = valorDigitador("1 - Abrir \n2 - (Des)Marcar");
				if ("1".equals(digitado)) {
					tabuleiro.abrir(xy.next(), xy.next());
				} else if ("2".equals(digitado)) {
					tabuleiro.alternarMarcacao(xy.next(), xy.next());
				}
			}

			System.out.println(tabuleiro);
			System.out.println("Você ganhou!!!");
		} catch (ExplosaoException e) {

			System.out.println(tabuleiro);
			System.out.println("Você perdeu!");
		}
	}

	private String valorDigitador(String str) {
		System.out.println(str);
		String res = leitor.nextLine();
		if ("sair".equalsIgnoreCase(str))
			throw new SairException();

		return res;
	}
}
