package br.com.Lucas.cm.visao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import br.com.Lucas.cm.excecao.CordenadasInvalidasException;
import br.com.Lucas.cm.excecao.ExplosaoException;
import br.com.Lucas.cm.excecao.SairException;
import br.com.Lucas.cm.modelo.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;

	private Scanner entrada = new Scanner(System.in);

	public TabuleiroConsole(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;

		executarJogo();
	}

	private void executarJogo() throws CordenadasInvalidasException {
		try {
			boolean continuar = true;
			while (continuar) {
				cicloDoJogo();

				System.out.println("Outra partida? (S/n)");
				String resposta = entrada.nextLine();
				if ("n".equalsIgnoreCase(resposta)) {
					continuar = false;
				} else
					tabuleiro.reiniciar();

			}
		} catch (SairException e) {
			// TODO: handle exception
			System.out.println("Jogo Encerrado.");
		} finally {
			entrada.close();
		}

	}

	private void cicloDoJogo() throws CordenadasInvalidasException {
	    try {
	        while (!tabuleiro.objetivoAlcancado()) {
	            System.out.println(tabuleiro);
	            String digitado = capturarValorDigitado("digite x,y : ");
	            Iterator<Integer> xy = Arrays.stream(digitado.split(","))
	                    .map(e -> Integer.parseInt(e.trim()))
	                    .iterator();

	            int x = xy.hasNext() ? xy.next() : -1;
	            int y = xy.hasNext() ? xy.next() : -1;

	            if (x != -1 && y != -1) {
	                digitado = capturarValorDigitado("1- Abrir ou 2- (Des)Marcar");
	                if ("1".equals(digitado)) {
	                    tabuleiro.abrirCampo(x, y);
	                } else if ("2".equals(digitado)) {
	                    tabuleiro.alterarMarcacao(x, y);
	                }
	            } else {
	              throw new CordenadasInvalidasException();
	            }
	        }
			System.out.println(tabuleiro);
	        System.out.println("Parabéns!!! você está salvo");
	    } catch (ExplosaoException e) {
	    	System.out.println(tabuleiro);

	        System.out.println("Bummm!!! Você explodiu");
	    }
	}


	private String capturarValorDigitado(String texto) {
		System.out.print(texto);
		String digitado = entrada.nextLine();
		if ("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		return digitado;

	}
}
