package model;

public class Casilla {
	private String valor;
	
	public Casilla() {
		this.valor = " ";
	}
	
	public Casilla(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
