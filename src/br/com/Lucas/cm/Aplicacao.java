package br.com.Lucas.cm;

import br.com.Lucas.cm.modelo.Tabuleiro;
import br.com.Lucas.cm.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		Tabuleiro tabuleiro = new Tabuleiro(6, 6, 6);
		
	new TabuleiroConsole(tabuleiro);
		
		
	}
}
