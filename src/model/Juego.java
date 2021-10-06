package model;

import java.util.ArrayList;

public class Juego {
	private ArrayList<Partida> partidas;
	
	public Juego() {
		partidas = new ArrayList<>();
	}

	public ArrayList<Partida> getPartidas() {
		return partidas;
	}

	public void setPartidas(ArrayList<Partida> partidas) {
		this.partidas = partidas;
	}
	
	// Método que calcula los puntajes acumulados por cada jugador
	public String puntajesAcumulados() {
		int puntajeJugador1 = 0;
		int puntajeJugador2 = 0;
		
		String jugador1 = "Jugador 1";
		String jugador2 = "Jugador 2";
		
		for (Partida p : getPartidas()) {
			jugador1 = p.getJugadores().get(0).getNombre();
			jugador2 = p.getJugadores().get(1).getNombre();
			puntajeJugador1 += p.getJugadores().get(0).getPuntaje();
			puntajeJugador2 += p.getJugadores().get(1).getPuntaje();
		}
		
		jugador1 = jugador1 + ": " + puntajeJugador1 + " puntos.\n";
		jugador2 = jugador2 + ": " +  puntajeJugador2 + " puntos.\n";
		
		return jugador1 + jugador2;
	}
}
