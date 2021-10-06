package model;

public class Tablero {
	private final int filas = 3;
	private final int columnas = 3;
	
	private Casilla [][] casillas;
	
	public Tablero() {
		casillas = new Casilla [this.getFilas()][this.getColumnas()];
		construirTablero();
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public Casilla[][] getCasillas() {
		return casillas;
	}

	public void setCasillas(Casilla[][] casillas) {
		this.casillas = casillas;
	}

	public void construirTablero() {
		// Inicializamos el tablero con espacios " ", es decir la matriz que almacena las jugadas
        for (int i = 0; i < this.getCasillas().length; i++) {
            for (int j = 0; j < this.getCasillas()[i].length; j++) {
            	casillas[i][j] = new Casilla();
            }
        }
	}
}
