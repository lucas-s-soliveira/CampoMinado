package br.com.Lucas.cm.modelo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.Lucas.cm.excecao.ExplosaoException;

public class CampoTeste {

	private Campo campo;

	@BeforeEach
	void iniciarCampo() {
		campo = new Campo(3, 3);
	}

	@Test
	void testeVizinhoEsquerda() {

		Campo vizinho = new Campo(3, 2);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertTrue(resulstado);
	}

	@Test
	void testeVizinhoDireita() {

		Campo vizinho = new Campo(3, 4);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertTrue(resulstado);
	}

	@Test
	void testeVizinhoSuperior() {

		Campo vizinho = new Campo(2, 3);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertTrue(resulstado);
	}

	@Test
	void testeVizinhoInferior() {

		Campo vizinho = new Campo(4, 3);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertTrue(resulstado);
	}

	@Test
	void testeVizinhoDiagonaSupeEsq() {

		Campo vizinho = new Campo(2, 2);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertTrue(resulstado);

	}

	@Test
	void testeNãoVizinho() {

		Campo vizinho = new Campo(1, 3);
		boolean resulstado = campo.adicionarVizinho(vizinho);

		assertFalse(resulstado);
	}

	@Test
	void testeValorAtributoPadraoMarcado() {
		assertFalse(campo.isMarcado());
	}

	@Test
	void testeAlternarMarcado() {
		campo.alternarMarcacao();
		assertTrue(campo.isMarcado());

	}

	@Test
	void testeAlternarMarcadoDuasChamadas() {
		campo.alternarMarcacao();
		campo.alternarMarcacao();
		assertFalse(campo.isMarcado());

	}

	@Test
	void testeAbrirNaoMinadoNaoMarcado() {
		assertTrue(campo.abrir());

	}

	@Test
	void testeAbrirNaoMinadoMarcado() {

		campo.alternarMarcacao();
		assertFalse(campo.abrir());

	}

	@Test
	void testeAbrirMinadoMarcado() {

		campo.alternarMarcacao();
		campo.minar();
		assertFalse(campo.abrir());

	}
	@Test
	void testeAbrirComVizinho() {
		Campo vizinho1 = new Campo(2, 2);
		Campo vizinho2 = new Campo(1, 1);
		vizinho1.adicionarVizinho(vizinho2);
		campo.adicionarVizinho(vizinho1);
		campo.abrir();
		assertTrue(vizinho1.isAberto() && vizinho2.isAberto());
	}
	@Test
	void testeAbrirComVizinho2() {
		Campo campo11 = new Campo(1, 1);
		Campo campo12 = new Campo(1, 1);
		campo12.minar();
		Campo campo22 = new Campo(2, 2);
		campo22.adicionarVizinho(campo11);
		campo22.adicionarVizinho(campo12);
		campo.adicionarVizinho(campo22);
		campo.abrir();
		assertTrue(campo22.isAberto() && campo11.isFechado());
	
	}

	@Test
	void testeAbrirMinadoNaoMarcado() {
		campo.minar();
		assertThrows(ExplosaoException.class, () -> {
			campo.abrir();
		});

	}
}
