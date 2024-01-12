package br.com.cod3r.cm.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.cod3r.cm.excecao.ExplosaoException;

class CampoTest {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testCampo() {
		Campo vizinho = new Campo(-1, 2);
		assertEquals(vizinho, vizinho);
		assertEquals(vizinho.getLINHA(), 1);
		assertEquals(vizinho.getCOLUNA(), 2);
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
	void testAdicionarNaoVizinho() {
		Campo vizinho = new Campo(5, 5);
		boolean rsultado = campo.adicionarVizinho(vizinho);
		assertFalse(rsultado);
	}

	@Test
	void testMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testAlternarMarcacao() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());
	}

	@Test
	void testAlternarMarcacaoDuplo() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());
	}

	@Test
	void testAbrirPadrao() {
		assertTrue(campo.abrir());
	}

	@Test
	void testAbrirAbertoNaoMarcado() {
		campo.abrir();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testAbrirMarcadoNaoMinado() {
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testAbrirNaoMarcadoMinado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
			});	
	}
	
	@Test
	void testAbrirMarcadoMinado() {
		campo.minar();
		campo.alternarMarcacao();
		assertFalse(campo.abrir());
	}
	
	@Test
	void testAbrirComVizinhos() {
		Campo campo11 = new Campo(1,1);
		Campo campo22 = new Campo(2,2);

		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);

		campo.abrir();
		
		assertTrue(campo11.isAberto() && campo22.isAberto());
	}
	
	@Test
	void testAbrirComVizinhosMinado() {
		Campo campo11 = new Campo(1,1);
		Campo campo33 = new Campo(1,1);
		campo33.minar();
		
		Campo campo22 = new Campo(2,2);
		campo.adicionarVizinho(campo22);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo22);
		campo.abrir();
		
		assertTrue(campo11.isAberto() && campo33.isFechado());
	}
	@Test
	void testMinadoDuplo() {
		campo.minar();
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
			});	
	}
}
