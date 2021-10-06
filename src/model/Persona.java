package model;

public class Persona {
	private String nombre;
	
	public Persona() {
		this.nombre = "";
	}
	
	public Persona(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
