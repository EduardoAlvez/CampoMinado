package br.com.cod3r.cm.modelo;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CampoTest {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testAdicionarVizinhoEsquerda() {
		Campo vizinho = new Campo(3, 2);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertTrue(rsultado);
	}

	@Test
	void testAdicionarVizinhoDireita() {
		Campo vizinho1 = new Campo(4, 2);
		boolean resultado1 = campo.adicionarVizinho(vizinho1);
		Campo vizinho2 = new Campo(4, 3);
		boolean resultado2 = campo.adicionarVizinho(vizinho2);
		Campo vizinho3 = new Campo(4, 4);
		boolean resultado3 = campo.adicionarVizinho(vizinho3);

		assertTrue(resultado1);
		assertTrue(resultado2);
		assertTrue(resultado3);
	}

	@Test
	void testAdicionarVizinhoEmCima() {
		Campo vizinho = new Campo(2, 3);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertTrue(rsultado);
	}

	@Test
	void testAdicionarVizinhoEmBaixo() {
		Campo vizinho = new Campo(4, 3);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertTrue(rsultado);
	}

	@Test
	void testAdicionarVizinhoDiagonal() {
		Campo vizinho = new Campo(2, 2);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertTrue(rsultado);
	}

	@Test
	void testAdicionarVizinhoNaoVizinho() {
		Campo vizinho = new Campo(5, 5);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertFalse(rsultado);
	}

}
