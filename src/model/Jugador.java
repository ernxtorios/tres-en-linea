package model;

public class Jugador extends Persona {
	private String ficha;
	private int puntaje;
	
	public Jugador(String nombre) {
		super(nombre);
		this.ficha = "";
		this.puntaje = 0;
	}
	
	public Jugador(String nombre, String ficha) {
		super(nombre);
		this.ficha = ficha;
		this.puntaje = 0;
	}

	public String getFicha() {
		return ficha;
	}

	public void setFicha(String ficha) {
		this.ficha = ficha;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
}
