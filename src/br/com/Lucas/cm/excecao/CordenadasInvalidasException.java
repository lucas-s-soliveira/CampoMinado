package br.com.Lucas.cm.excecao;

public class CordenadasInvalidasException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CordenadasInvalidasException() {
		System.out.println("Cordenadas invalidas foram digitadas");
	}

}
