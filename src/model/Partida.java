package model;

import java.util.ArrayList;

public class Partida {
	private int turno;
	private int tipo;
	private boolean iniciado;
	private boolean finalizado;
	private String resultado;
	private double tiempoInicio;
	private double tiempoFin;
	private double tiempo;
	private Tablero tablero;
	private ArrayList<Jugador> jugadores;
	
	public Partida() {
		this.turno = 0;
		this.tipo = 1;
		this.iniciado = false;
		this.finalizado = false;
		this.resultado = "";
		this.tiempoInicio = 0;
		this.tiempoFin = 0;
		this.tiempo = tiempoFin - tiempoInicio;
		
		this.setTablero(new Tablero());
		
		this.jugadores = new ArrayList<Jugador>();
		
		registrarJugadores();
	}
	
	public int getTurno() {
		return turno;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isIniciado() {
		return iniciado;
	}
	
	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}
	
	public boolean isFinalizado() {
		return finalizado;
	}
	
	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}
	
	public String getResultado() {
		return resultado;
	}
	
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public double getTiempoInicio() {
		return tiempoInicio;
	}

	public void setTiempoInicio(double tiempoInicio) {
		this.tiempoInicio = tiempoInicio;
	}

	public double getTiempoFin() {
		return tiempoFin;
	}

	public void setTiempoFin(double tiempoFin) {
		this.tiempoFin = tiempoFin;
	}

	public double getTiempo() {
		return tiempo;
	}

	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Tablero getTablero() {
		return tablero;
	}

	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}
	
	public void registrarJugadores() {
		jugadores.add(new Jugador("Jugador 1"));
		jugadores.add(new Jugador("Jugador 2"));
		
		jugadores.get(0).setFicha("X");
		jugadores.get(1).setFicha("O");
	}
}
