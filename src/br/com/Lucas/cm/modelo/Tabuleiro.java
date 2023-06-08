package br.com.Lucas.cm.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

	public void abrirCampo(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.abrir());
		;
	}

	public void alterarMarcacao(int linha, int coluna) {
		campos.parallelStream().filter(c -> c.getLinha() == linha && c.getColuna() == coluna).findFirst()
				.ifPresent(c -> c.alternarMarcacao());
		;
	}

	private void gerarCampos() {
		// TODO
		for (int l = 0; l < linhas; l++) {
			for (int c = 0; c < colunas; c++) {
				campos.add(new Campo(l, c));

			}

		}

	}

	private void associarVizinhos() {
		// TODO Auto-generated method stub
		for (Campo c1 : campos) {
			for (Campo c2 : campos) {
				c1.adicionarVizinho(c2);
			}
		}
	}

	private void sortearMinas() {
		// TODO Auto-generated method stub
		long minasAmadas = 0;
		Predicate<Campo> minado = c -> c.isMinado();
		do {
			minasAmadas = campos.stream().filter(minado).count();
			int aleatorio = (int) (Math.random() * campos.size());
			campos.get(aleatorio).minar();
		} while (minasAmadas < minas);
	}

	public boolean objetivoAlcancado() {
		return campos.stream().allMatch(c -> c.objetivoAlcancado());
	}

	public void reiniciar() {
		campos.stream().forEach(c -> c.reininciar());
		sortearMinas();

	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(" ");
		for (int c = 0; c < colunas; c++) {
			sb.append("  ");
			sb.append(c);
			sb.append("");

		}
		sb.append("\n");

		int i = 0;

		for (int l = 0; l < linhas; l++) {
			sb.append(l);
			sb.append(" ");
			for (int c = 0; c < colunas; c++) {
				sb.append(" ");
				sb.append(campos.get(i));
				sb.append(" ");
				i++;
			}
			sb.append("\n");
		}

		return sb.toString();
	}
}
//fim do projeto.

