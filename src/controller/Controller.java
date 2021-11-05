package controller;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.Casilla;
import model.Juego;
import model.Jugador;
import model.Partida;
import view.MainGUI;

public class Controller {
	// La vista
	private MainGUI theView;
	
	// Los modelos
	private Partida partida;
	private Juego elJuego;
	
	private Timer temporizador;
	
	public Controller(MainGUI theView, Partida partida) {
		// La vista
		this.theView = theView;
		
		// El modelo juego
		elJuego = new Juego();
		
		// El modelo partida
		this.setPartida(partida);
		
		if (getPartida().getTipo() == 1) {
			this.theView.addClickButtonListener(new GameListener());
		} else if (getPartida().getTipo() == 2) {
			this.theView.addClickButtonListener(new ComputerGameListener());
		}
		
		this.theView.addClickMenuListener(new MenuListener());
	}
	
	// Setters y getters
	public MainGUI getTheView() {
		return theView;
	}

	public void setTheView(MainGUI theView) {
		this.theView = theView;
	}
	
	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}

	public Juego getElJuego() {
		return elJuego;
	}

	public void setElJuego(Juego elJuego) {
		this.elJuego = elJuego;
	}

	// Gestionamos eventos del juego (tablero) mediante el uso de una clase interna
	private class GameListener implements ActionListener {	
		public void actionPerformed(ActionEvent e) {
			try {
				getPartida().setIniciado(true);
				if (e.getActionCommand().equals("_")) {
					getPartida().setTurno(getPartida().getTurno() + 1);
					if (getPartida().getTurno() <= 9) {
						if (getPartida().getTurno() % 2 == 0) {
							getTheView().getButton(((JButton) e.getSource()).getName()).setText(getPartida().getJugadores().get(1).getFicha());
							getTheView().getButton(((JButton) e.getSource()).getName()).setForeground(Color.BLUE);
							getTheView().getButton(((JButton) e.getSource()).getName()).setBackground(Color.YELLOW);
							getTheView().getMensajeJuego().setText("Jugador " + getPartida().getJugadores().get(1).getFicha() + " jugó el turno " + getPartida().getTurno());
							getTheView().getMensajeJuego().setForeground(Color.BLUE);
						} else {
							if (getPartida().getTurno() == 1) getPartida().setTiempoInicio(System.currentTimeMillis());
							getTheView().getButton(((JButton) e.getSource()).getName()).setText(getPartida().getJugadores().get(0).getFicha());
							getTheView().getButton(((JButton) e.getSource()).getName()).setForeground(Color.RED);
							getTheView().getButton(((JButton) e.getSource()).getName()).setBackground(Color.WHITE);
							getTheView().getMensajeJuego().setText("Jugador " + getPartida().getJugadores().get(0).getFicha() + " jugó el turno " + getPartida().getTurno());
							getTheView().getMensajeJuego().setForeground(Color.RED);
						}
					}
				}
				
				// Establecemos la acción de cada botón de acuerdo al nombre de cada casillero
				getTheView().getButton(((JButton) e.getSource()).getName()).setActionCommand(((JButton) e.getSource()).getName());
				
				// De acuerdo a la accion de cada casillero asignamos ese valor en la matriz que almacena las jugadas, de acuerdo a cada posición del tablero
				if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button1")) {
					getPartida().getTablero().getCasillas()[0][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button2")) {
					getPartida().getTablero().getCasillas()[0][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button3")) {
					getPartida().getTablero().getCasillas()[0][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button4")) {
					getPartida().getTablero().getCasillas()[1][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button5")) {
					getPartida().getTablero().getCasillas()[1][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button6")) {
					getPartida().getTablero().getCasillas()[1][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button7")) {
					getPartida().getTablero().getCasillas()[2][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button8")) {
					getPartida().getTablero().getCasillas()[2][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button9")) {
					getPartida().getTablero().getCasillas()[2][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				}
				
				// A partir del turno 5 evaluamos si hay tres elementos iguales en línea
				if (getPartida().getTurno() >= 5) {
	                if (tresEnLinea(getPartida().getTablero().getCasillas())) {
	                	pintarTresEnLinea();
	                	deshabilitarCasillas();
	                	JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                	getPartida().setTurno(0);
	                	getPartida().setFinalizado(true);
	                	
	                	getPartida().setTiempoFin(System.currentTimeMillis());
	                	
	                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());
	                	
	                	getElJuego().getPartidas().add(getPartida());
	                } if (getPartida().getTurno() == 9) {
	                	getTheView().setMensajeEmergente("¡Empate!");
	                	if (!getPartida().isFinalizado()) {
	                		JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                		getTheView().getMensajeJuego().setText(getTheView().getMensajeJuego().getText() + ", juego empatado.");
	                		
	                		getPartida().setFinalizado(true);
		                	getPartida().setResultado("Empate");
		                	
		                	getPartida().setTiempoFin(System.currentTimeMillis());
		                	
		                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());               	
		                	
		                	getElJuego().getPartidas().add(getPartida());
	                	}
	                }
	            }
			} catch (Exception ex) {
				System.out.println(ex);
				
				theView.displayErrorMessage("¡Tres en línea falló!");
			}
		}
		
	}
	
	// Método que busca si hay tres elementos en línea en filas, columnas y diagonales
	public boolean tresEnLinea(Casilla [][] matriz) {
		boolean resultado = false;
	        
	    // Buscamos tres en línea en las filas
	    for (int i = 0; i < matriz.length; i++) {
	    	if (!matriz[i][0].getValor().equals(" ") || !matriz[i][1].getValor().equals(" ") || !matriz[i][2].getValor().equals(" ")) {
	    		if (matriz[i][0].getValor().equals(matriz[i][1].getValor()) && matriz[i][1].getValor().equals(matriz[i][2].getValor())) {
	    			resultado = true;
	                if (getPartida().getTurno() % 2 == 0) {
	                	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(1).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(1).getFicha() + "!\nJugada ganadora: fila " + (i + 1));
	                	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(1).getNombre());
	                	getPartida().getJugadores().get(1).setPuntaje(getPartida().getJugadores().get(1).getPuntaje() + 1);
	                	
	                } else {
	                	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(0).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(0).getFicha() + "!\nJugada ganadora: fila " + (i + 1));
	                	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(0).getNombre());
	                	getPartida().getJugadores().get(0).setPuntaje(getPartida().getJugadores().get(0).getPuntaje() + 1);
	                }
	                getTheView().setLineaGanadora("Fila " + (i + 1));
	                break;
	            }
	    	}
	    }
	        
	    // Buscamos tres en línea en las columnas, solo en caso que no se haya encontrado tres en línea en las filas
	    if (!resultado) {
	    	for (int i = 0; i < matriz.length; i++) {
	    		if (!matriz[0][i].getValor().equals(" ") && !matriz[1][i].getValor().equals(" ") && !matriz[2][i].getValor().equals(" ")) {
	    			if (matriz[0][i].getValor().equals(matriz[1][i].getValor()) && matriz[1][i].getValor().equals(matriz[2][i].getValor())) {
	    				resultado = true;
	                    if (getPartida().getTurno() % 2 == 0) {
	                    	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(1).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(1).getFicha() + "!\nJugada ganadora: columna " + (i + 1));
	                    	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(1).getNombre());
	                    	getPartida().getJugadores().get(1).setPuntaje(getPartida().getJugadores().get(1).getPuntaje() + 1);
	                    } else {
	                    	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(0).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(0).getFicha() + "!\nJugada ganadora: columna " + (i + 1));
	                    	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(0).getNombre());
	                    	getPartida().getJugadores().get(0).setPuntaje(getPartida().getJugadores().get(0).getPuntaje() + 1);
	                    }
	                    getTheView().setLineaGanadora("Columna " + (i + 1));
	                    break;
	    			}
	    		}
	    	}
	    }
	        
	    // Buscamos tres en línea en la diagonal principal, solo en caso que no se haya encontrado tres en línea en las filas y en las columnas
	    if (!resultado) {
	    	if (!matriz[0][0].getValor().equals(" ") && !matriz[1][1].getValor().equals(" ") && !matriz[2][2].getValor().equals(" ")) {
	    		if (matriz[0][0].getValor().equals(matriz[1][1].getValor()) && matriz[1][1].getValor().equals(matriz[2][2].getValor())) {
	    			resultado = true;
	                if (getPartida().getTurno() % 2 == 0) {
	                	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(1).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(1).getFicha() + "!\nJugada ganadora: diagonal principal");
	                	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(1).getNombre());
	                	getPartida().getJugadores().get(1).setPuntaje(getPartida().getJugadores().get(1).getPuntaje() + 1);
	                } else {
	                	getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(0).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(0).getFicha() + "!\nJugada ganadora: diagonal principal");
	                	getPartida().setResultado("Ganador " + getPartida().getJugadores().get(0).getNombre());
	                	getPartida().getJugadores().get(0).setPuntaje(getPartida().getJugadores().get(0).getPuntaje() + 1);
	                }
	                getTheView().setLineaGanadora("Diagonal Principal");
	            }
	        }
	    }
	        
	    // Buscamos tres en línea en la diagonal secundaria, solo en caso que no se haya encontrado tres en línea en las filas, en las columnas y en la diagonal principal
	    if (!resultado) {
	    	if (!matriz[2][0].getValor().equals(" ") && !matriz[1][1].getValor().equals(" ") && !matriz[0][2].getValor().equals(" ")) {
	    		if (matriz[2][0].getValor().equals(matriz[1][1].getValor()) && matriz[1][1].getValor().equals(matriz[0][2].getValor())) {
	    			resultado = true;
	    			if (getPartida().getTurno() % 2 == 0) {
	    				getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(1).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(1).getFicha() + "!\nJugada ganadora: diagonal secundaria");
	    				getPartida().setResultado("Ganador " + getPartida().getJugadores().get(1).getNombre());
	    				getPartida().getJugadores().get(1).setPuntaje(getPartida().getJugadores().get(1).getPuntaje() + 1);
	    			} else {
	    				getTheView().setMensajeEmergente("¡Ganó " + getPartida().getJugadores().get(0).getNombre().toLowerCase() + ", ficha " + getPartida().getJugadores().get(0).getFicha() + "!\nJugada ganadora: diagonal secundaria");
	    				getPartida().setResultado("Ganador " + getPartida().getJugadores().get(0).getNombre());
	    				getPartida().getJugadores().get(0).setPuntaje(getPartida().getJugadores().get(0).getPuntaje() + 1);
	    			}
	    			getTheView().setLineaGanadora("Diagonal Secundaria");
	            }
	        }
	    }
	        
	    return resultado;
	}
	
	public void pintarTresEnLinea() {
		if (getTheView().getLineaGanadora().equals("Fila 1")) {
			getTheView().getCasilla("button1").setBackground(Color.GREEN);
			getTheView().getCasilla("button2").setBackground(Color.GREEN);
			getTheView().getCasilla("button3").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Fila 2")) {
			getTheView().getCasilla("button4").setBackground(Color.GREEN);
			getTheView().getCasilla("button5").setBackground(Color.GREEN);
			getTheView().getCasilla("button6").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Fila 3")) {
			getTheView().getCasilla("button7").setBackground(Color.GREEN);
			getTheView().getCasilla("button8").setBackground(Color.GREEN);
			getTheView().getCasilla("button9").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Columna 1")) {
			getTheView().getCasilla("button1").setBackground(Color.GREEN);
			getTheView().getCasilla("button4").setBackground(Color.GREEN);
			getTheView().getCasilla("button7").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Columna 2")) {
			getTheView().getCasilla("button2").setBackground(Color.GREEN);
			getTheView().getCasilla("button5").setBackground(Color.GREEN);
			getTheView().getCasilla("button8").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Columna 3")) {
			getTheView().getCasilla("button3").setBackground(Color.GREEN);
			getTheView().getCasilla("button6").setBackground(Color.GREEN);
			getTheView().getCasilla("button9").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Diagonal Principal")) {
			getTheView().getCasilla("button1").setBackground(Color.GREEN);
			getTheView().getCasilla("button5").setBackground(Color.GREEN);
			getTheView().getCasilla("button9").setBackground(Color.GREEN);
		} else if (getTheView().getLineaGanadora().equals("Diagonal Secundaria")) {
			getTheView().getCasilla("button3").setBackground(Color.GREEN);
			getTheView().getCasilla("button5").setBackground(Color.GREEN);
			getTheView().getCasilla("button7").setBackground(Color.GREEN);
		}
	}
	
	// Deshabilitar las casillas
	public void deshabilitarCasillas() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (getTheView().getLosBotones()[i][j].getText().equals("_")) {
					getTheView().getLosBotones()[i][j].setEnabled(false);
				}
			}
		}
	}
	
	// Habilitar las casillas
	public void habilitarCasillas() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (getTheView().getLosBotones()[i][j].getText().equals("_") || getTheView().getLosBotones()[i][j].getText().equals(getPartida().getJugadores().get(1).getFicha())) {
					getTheView().getLosBotones()[i][j].setEnabled(true);
				}
			}
		}
	}
	
	// Obtener la cadena obtenida de la fila dada
	public String getTheRow(int row) {
		String [] column = new String[3];
		
		for (int c = 0; c < getPartida().getTablero().getFilas(); c++) {
			column[c] = getPartida().getTablero().getCasillas()[row][c].getValor();
			
			if (column[c].equals(" ")) {
				column[c] = getTheEmptyString(); 
			}
		}
		
		return column[0] + column[1] + column[2]; 
	}
	
	// Obtener la cadena obtenida de la columna dada
	public String getTheColumn(int column) {
		String [] row = new String[3];
		
		for (int r = 0; r < getPartida().getTablero().getColumnas(); r++) {
			row[r] = getPartida().getTablero().getCasillas()[r][column].getValor();
			
			if (row[r].equals(" ")) {
				row[r] = getTheEmptyString(); 
			}
		}
		
		return row[0] + row[1] + row[2]; 
	}
	
	// Obtener la cadena obtenida de la diagonal principal
	public String getTheMainDiagonal() {
		String [] mainDiagonal = new String[3];
		
		mainDiagonal[0] = getPartida().getTablero().getCasillas()[0][0].getValor();
		mainDiagonal[1] = getPartida().getTablero().getCasillas()[1][1].getValor();
		mainDiagonal[2] = getPartida().getTablero().getCasillas()[2][2].getValor();
			
		if (mainDiagonal[0].equals(" ")) {
			mainDiagonal[0] = getTheEmptyString(); 
		}
		
		if (mainDiagonal[1].equals(" ")) {
			mainDiagonal[1] = getTheEmptyString(); 
		}
		
		if (mainDiagonal[2].equals(" ")) {
			mainDiagonal[2] = getTheEmptyString(); 
		}
		
		return mainDiagonal[0] + mainDiagonal[1] + mainDiagonal[2]; 
	}
	
	// Obtener la cadena obtenida de la diagonal secundaria
	public String getTheSecondaryDiagonal() {
		String [] secondaryDiagonal = new String[3];
		
		secondaryDiagonal[0] = getPartida().getTablero().getCasillas()[0][2].getValor();
		secondaryDiagonal[1] = getPartida().getTablero().getCasillas()[1][1].getValor();
		secondaryDiagonal[2] = getPartida().getTablero().getCasillas()[2][0].getValor();
			
		if (secondaryDiagonal[0].equals(" ")) {
			secondaryDiagonal[0] = getTheEmptyString();
			} 
		
		if (secondaryDiagonal[1].equals(" ")) {
			secondaryDiagonal[1] = getTheEmptyString();
		}
		
		if (secondaryDiagonal[2].equals(" ")) {
			secondaryDiagonal[2] = getTheEmptyString();
		}
		
		return secondaryDiagonal[0] + secondaryDiagonal[1] + secondaryDiagonal[2]; 
	}
	
	// Obtener la cadena vacía de acuerdo a la longitud de la ficha del jugador 1
	public String getTheEmptyString() {
		String theEmptyString = "";
		
		for (int b = 0; b < getPartida().getJugadores().get(0).getFicha().length(); b++) {
			theEmptyString += " ";
		}
		
		return theEmptyString;
	}
	
	// 
	public String blockPlay() {
		int fichas;
		
		int iterator;
		
		String result = "";
		
		// Búsqueda de posibles jugadas en cada fila
		for (int i = 0; i < getPartida().getTablero().getFilas(); i++) {
			fichas = 0;
			for (int j = 0; j < getTheRow(i).length(); j+=getPartida().getJugadores().get(0).getFicha().length()) {
				// Contamos el número de fichas del primer jugador por cada fila
				if (getTheRow(i).substring(j, j + getPartida().getJugadores().get(0).getFicha().length()).equals(getPartida().getJugadores().get(0).getFicha())) {
					fichas++;
				}
				// Verificamos las fichas vacías por cada fila
				if (fichas == 2) {
					iterator = -1;
					for (int k = 0; k < getTheRow(i).length(); k+=getPartida().getJugadores().get(0).getFicha().length()) {
						iterator++;
						if (getTheRow(i).substring(k, k + getPartida().getJugadores().get(0).getFicha().length()).equals(getTheEmptyString())) {
							result = i + "_" + iterator;
							break;
						}
					}
				}
				if (!result.equals("")) {
					break;
				}
			}
					
			if (!result.equals("")) {
				break;
			}
		}
		
		// Búsqueda de posibles jugadas en cada columna
		for (int i = 0; i < getPartida().getTablero().getColumnas(); i++) {
			fichas = 0;
			for (int j = 0; j < getTheColumn(i).length(); j+=getPartida().getJugadores().get(0).getFicha().length()) {
				// Contamos el número de fichas del primer jugador por cada columna
				if (getTheColumn(i).substring(j, j + getPartida().getJugadores().get(0).getFicha().length()).equals(getPartida().getJugadores().get(0).getFicha())) {
					fichas++;
				}
				// Verificamos las fichas vacías por cada columna
				if (fichas == 2) {
					iterator = -1;
					for (int k = 0; k < getTheColumn(i).length(); k+=getPartida().getJugadores().get(0).getFicha().length()) {
						iterator++;
						if (getTheColumn(i).substring(k, k + getPartida().getJugadores().get(0).getFicha().length()).equals(getTheEmptyString())) {
							result = iterator + "_" + i;
							break;
						}
					}
				}
				if (!result.equals("")) {
					break;
				}
			}
			
			if (!result.equals("")) {
				break;
			}
		}
		
		// Búsqueda de posibles jugadas en la diagonal principal
		fichas = 0;
		for (int i = 0; i < getTheMainDiagonal().length(); i+=getPartida().getJugadores().get(0).getFicha().length()) {
			// Contamos el número de fichas del primer jugador en la diagonal principal
			if (getTheMainDiagonal().substring(i, i + getPartida().getJugadores().get(0).getFicha().length()).equals(getPartida().getJugadores().get(0).getFicha())) {
				fichas++;
			}
			// Verificamos las fichas vacías en la diagonal principal
			if (fichas == 2) {
				iterator = -1;
				for (int j = 0; j < getTheMainDiagonal().length(); j+=getPartida().getJugadores().get(0).getFicha().length()) {
					iterator++;
					if (getTheMainDiagonal().substring(j, j + getPartida().getJugadores().get(0).getFicha().length()).equals(getTheEmptyString())) {
						switch (iterator) {
							case 0:
								result = "0_0";
								break;
							case 1: 
								result = "1_1";
								break;
							case 2: 
								result = "2_2";
								break;
						}
						break;
					}
				}
			}
			
			if (!result.equals("")) {
				break;
			}
		}
		
		// Búsqueda de posibles jugadas en la diagonal secundaria
		fichas = 0;
		for (int i = 0; i < getTheSecondaryDiagonal().length(); i+=getPartida().getJugadores().get(0).getFicha().length()) {
			// Contamos el número de fichas del primer jugador en la diagonal secundaria
			if (getTheSecondaryDiagonal().substring(i, i + getPartida().getJugadores().get(0).getFicha().length()).equals(getPartida().getJugadores().get(0).getFicha())) {
				fichas++;
			}
			// Verificamos las fichas vacías en la diagonal principal
			if (fichas == 2) {
				iterator = -1;
				for (int j = 0; j < getTheSecondaryDiagonal().length(); j+=getPartida().getJugadores().get(0).getFicha().length()) {
					iterator++;
					if (getTheSecondaryDiagonal().substring(j, j + getPartida().getJugadores().get(0).getFicha().length()).equals(getTheEmptyString())) {
						switch (iterator) {
							case 0:
								result = "0_2";
								break;
							case 1: 
								result = "1_1";
								break;
							case 2: 
								result = "2_0";
								break;
						}
						break;
					}
				}
			}
			
			if (!result.equals("")) {
				break;
			}
		}
		
		return result;
	}
	
	// Gestionamos eventos del menu de opciones mediante el uso de una clase interna 
	private class MenuListener implements ActionListener {
		private String ficha1, ficha2;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if (e.getActionCommand().equals("Nuevo")) {
					getTheView().setMensajeEmergente("Empezaremos un nuevo juego de tres en línea");
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					
					getTheView().nuevaPartida(Color.GREEN, "Tres en línea: Dos jugadores");
					
					this.ficha1 = getPartida().getJugadores().get(0).getFicha();
					this.ficha2 = getPartida().getJugadores().get(1).getFicha();
					
					setPartida(new Partida());
					
					getPartida().getJugadores().get(0).setFicha(ficha1);
					getPartida().getJugadores().get(1).setFicha(ficha2);
					
					getPartida().setTipo(1);
					getPartida().setTurno(0);
					getPartida().setIniciado(true);
					getPartida().setFinalizado(false);
					getPartida().getTablero().construirTablero();
					
					getTheView().addClickButtonListener(new GameListener());
				} else if (e.getActionCommand().equals("Ficha")) { 
					for (Jugador jugador : getPartida().getJugadores()) {
						String ficha = "";
						
						getTheView().setMensajeEmergente("Elección de ficha: " + jugador.getNombre() + " actualmente su ficha es " + jugador.getFicha()); 
						
						ficha = JOptionPane.showInputDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.QUESTION_MESSAGE);
						
						if (ficha == null) ficha = "";
						
						if (!ficha.strip().equals("")) {
							jugador.setFicha(ficha.toUpperCase());
						}
					}
				} else if (e.getActionCommand().equals("Jugar con la computadora")) { 
					getTheView().setMensajeEmergente("Jugar con la computadora: Usted comienza"); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					
					getTheView().nuevaPartida(Color.MAGENTA, "Tres en línea: Jugar con la computadora");
					
					this.ficha1 = getPartida().getJugadores().get(0).getFicha();
					this.ficha2 = getPartida().getJugadores().get(1).getFicha();
					
					setPartida(new Partida());
					
					getPartida().getJugadores().get(0).setFicha(ficha1);
					getPartida().getJugadores().get(1).setFicha(ficha2);
					
					getPartida().setTipo(2);
					getPartida().setTurno(0);
					getPartida().setIniciado(true);
					getPartida().setFinalizado(false);
					getPartida().getTablero().construirTablero();
					
					getTheView().addClickButtonListener(new ComputerGameListener());
				} else if (e.getActionCommand().equals("Propiedades")) {
					if (getPartida().getTipo() == 1) {
						getTheView().setMensajeEmergente("El tipo de partida es dos jugadores:\nhumano versus humano"); 
						
						JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					} else if (getPartida().getTipo() == 2) {
						getTheView().setMensajeEmergente("El tipo de partida es jugar con la computadora:\nhumano versus computadora"); 
						
						JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (e.getActionCommand().equals("Resultados")) {
					String resultados = "Resultados del Juego:\n";
					
					for (Partida p : getElJuego().getPartidas()) {
						resultados = resultados + "Partida " + (getElJuego().getPartidas().indexOf(p) + 1) + ": " + p.getResultado() + " en " + Math.round(p.getTiempo()*0.001) + " segundos.\n";
					}
					
					getTheView().setMensajeEmergente(resultados); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					
					getTheView().setMensajeEmergente("Puntajes Acumulados por Jugador:\n" + getElJuego().puntajesAcumulados()); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
				} else if (e.getActionCommand().equals("Salir")) {
					getTheView().setMensajeEmergente("¡Gracias por jugar tres en línea!"); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				} else if (e.getActionCommand().equals("Versión")) {
					getTheView().setMensajeEmergente("Tres en Línea\nVersión 0.2.0"); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
				} else if (e.getActionCommand().equals("Acerca de")) {
					getTheView().setMensajeEmergente("Ernesto Segundo\nGitHub: @ErnestoSegundo\nTwitter: @CapitanApiacho"); 
					
					JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (Exception ex) {
				System.out.println(ex);
				
				theView.displayErrorMessage("¡Tres en línea falló!");
			}
			
		}
	}
	
	// Gestionamos eventos del juego con la computadora (tablero) mediante el uso de una clase interna
	private class ComputerGameListener implements ActionListener {	
		public void actionPerformed(ActionEvent e) {
			try {
				getPartida().setIniciado(true);
				if (e.getActionCommand().equals("_")) {
					getPartida().setTurno(getPartida().getTurno() + 1);
					if (getPartida().getTurno() <= 9) {
						if (getPartida().getTurno() % 2 != 0) {
							if (getPartida().getTurno() == 1) getPartida().setTiempoInicio(System.currentTimeMillis());
							getTheView().getButton(((JButton) e.getSource()).getName()).setText(getPartida().getJugadores().get(0).getFicha());
							getTheView().getButton(((JButton) e.getSource()).getName()).setForeground(Color.RED);
							getTheView().getButton(((JButton) e.getSource()).getName()).setBackground(Color.WHITE);
							getTheView().getMensajeJuego().setText("Jugador " + getPartida().getJugadores().get(0).getFicha() + " jugó el turno " + getPartida().getTurno());
							getTheView().getMensajeJuego().setForeground(Color.RED);
							
							JugadaComputadora oyente = new JugadaComputadora();
							
							temporizador = new Timer(3000, oyente);
							
							temporizador.start();
						} else {
							getPartida().setTurno(getPartida().getTurno() - 1);
						}
					}
				}
				
				// Establecemos la acción de cada botón de acuerdo al nombre de cada casillero
				getTheView().getButton(((JButton) e.getSource()).getName()).setActionCommand(((JButton) e.getSource()).getName());
				
				// De acuerdo a la accion de cada casillero asignamos ese valor en la matriz que almacena las jugadas, de acuerdo a cada posición del tablero
				if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button1")) {
					getPartida().getTablero().getCasillas()[0][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button2")) {
					getPartida().getTablero().getCasillas()[0][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button3")) {
					getPartida().getTablero().getCasillas()[0][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button4")) {
					getPartida().getTablero().getCasillas()[1][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button5")) {
					getPartida().getTablero().getCasillas()[1][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button6")) {
					getPartida().getTablero().getCasillas()[1][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button7")) {
					getPartida().getTablero().getCasillas()[2][0].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button8")) {
					getPartida().getTablero().getCasillas()[2][1].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				} else if (getTheView().getButton(((JButton) e.getSource()).getName()).getActionCommand().equals("button9")) {
					getPartida().getTablero().getCasillas()[2][2].setValor(getTheView().getButton(((JButton) e.getSource()).getName()).getText());
				}
				
				// A partir del turno 5 evaluamos si hay tres elementos iguales en línea
				if (getPartida().getTurno() >= 5) {
	                if (tresEnLinea(getPartida().getTablero().getCasillas())) {
	                	pintarTresEnLinea();
	                	deshabilitarCasillas();
	                	temporizador.stop();
	                	JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                	getPartida().setTurno(0);
	                	getPartida().setFinalizado(true);
	                	
	                	getPartida().setTiempoFin(System.currentTimeMillis());
	                	
	                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());
	                	
	                	getElJuego().getPartidas().add(getPartida());
	                } if (getPartida().getTurno() == 9) {
	                	getTheView().setMensajeEmergente("¡Empate!");
	                	if (!getPartida().isFinalizado()) {
	                		temporizador.stop();
	                		JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                		getTheView().getMensajeJuego().setText(getTheView().getMensajeJuego().getText() + ", juego empatado.");
	                		
	                		getPartida().setFinalizado(true);
		                	getPartida().setResultado("Empate");
		                	
		                	getPartida().setTiempoFin(System.currentTimeMillis());
		                	
		                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());
		                	
		                	getElJuego().getPartidas().add(getPartida());
	                	}
	                }
	            }
			} catch (Exception ex) {
				System.out.println(ex);
				
				theView.displayErrorMessage("¡Tres en línea falló!");
			}
		}
	}
	
	// Gestionamos eventos del juego (tablero) para generar las jugadas de la computadora, mediante el uso de una clase interna
	private class JugadaComputadora implements ActionListener {
		int m, n;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String thePlay =  blockPlay();
			if (!getPartida().isFinalizado()) {
				if (thePlay.equals("")) {
					m = (int) (Math.round(Math.random()*2));
					n = (int) (Math.round(Math.random()*2));
					
					while (getPartida().getTablero().getCasillas()[m][n].getValor().equals(getPartida().getJugadores().get(0).getFicha()) || getPartida().getTablero().getCasillas()[m][n].getValor().equals(getPartida().getJugadores().get(1).getFicha())) {
						m = (int) (Math.round(Math.random()*2));
						n = (int) (Math.round(Math.random()*2));
					}
				} else {
					m = Integer.parseInt(thePlay.substring(0, 1));
					n = Integer.parseInt(thePlay.substring(2, 3));
					
					while (getPartida().getTablero().getCasillas()[m][n].getValor().equals(getPartida().getJugadores().get(0).getFicha()) || getPartida().getTablero().getCasillas()[m][n].getValor().equals(getPartida().getJugadores().get(1).getFicha())) {
						m = (int) (Math.round(Math.random()*2));
						n = (int) (Math.round(Math.random()*2));
					}
				}
				
				getPartida().setTurno(getPartida().getTurno() + 1);
				
				getTheView().getButton(getTheView().nombreBoton(m, n)).setText(getPartida().getJugadores().get(1).getFicha());
				getTheView().getButton(getTheView().nombreBoton(m, n)).setForeground(Color.BLUE);
				getTheView().getButton(getTheView().nombreBoton(m, n)).setBackground(Color.YELLOW);
				getTheView().getMensajeJuego().setText("Computadora (" + getPartida().getJugadores().get(1).getNombre() + "), ficha " + getPartida().getJugadores().get(1).getFicha() + " en turno " + getPartida().getTurno() + ". ¡Juegue!");
				getTheView().getMensajeJuego().setForeground(Color.BLUE);
				
				Toolkit.getDefaultToolkit().beep();
				
				temporizador.stop();
				
				// Establecemos la acción de cada botón de acuerdo al nombre de cada casillero
				getTheView().getButton(getTheView().nombreBoton(m, n)).setActionCommand(getTheView().nombreBoton(m, n));
				
				// De acuerdo a la accion de cada casillero asignamos ese valor en la matriz que almacena las jugadas, de acuerdo a cada posición del tablero
				if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button1")) {
					getPartida().getTablero().getCasillas()[0][0].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button2")) {
					getPartida().getTablero().getCasillas()[0][1].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button3")) {
					getPartida().getTablero().getCasillas()[0][2].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button4")) {
					getPartida().getTablero().getCasillas()[1][0].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button5")) {
					getPartida().getTablero().getCasillas()[1][1].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button6")) {
					getPartida().getTablero().getCasillas()[1][2].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button7")) {
					getPartida().getTablero().getCasillas()[2][0].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button8")) {
					getPartida().getTablero().getCasillas()[2][1].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				} else if (getTheView().getButton(getTheView().nombreBoton(m, n)).getActionCommand().equals("button9")) {
					getPartida().getTablero().getCasillas()[2][2].setValor(getTheView().getButton(getTheView().nombreBoton(m, n)).getText());
				}
				
				// A partir del turno 5 evaluamos si hay tres elementos iguales en línea
				if (getPartida().getTurno() >= 5) {
					if (tresEnLinea(getPartida().getTablero().getCasillas())) {
	                	pintarTresEnLinea();
	                	deshabilitarCasillas();
	                	JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                	getPartida().setTurno(0);
	                	getPartida().setFinalizado(true);
	                	
	                	getPartida().setTiempoFin(System.currentTimeMillis());
	                	
	                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());
	                	
	                	getElJuego().getPartidas().add(getPartida());
	                } if (getPartida().getTurno() == 9) {
	                	getTheView().setMensajeEmergente("¡Empate!");
	                	if (!getPartida().isFinalizado()) {
	                		JOptionPane.showMessageDialog(getTheView().getElTablero(), getTheView().getMensajeEmergente(), "Tres en línea", JOptionPane.INFORMATION_MESSAGE);
	                		getTheView().getMensajeJuego().setText(getTheView().getMensajeJuego().getText() + ", juego empatado.");
	                		
	                		getPartida().setFinalizado(true);
		                	getPartida().setResultado("Empate");
		                	
		                	getPartida().setTiempoFin(System.currentTimeMillis());
		                	
		                	getPartida().setTiempo(getPartida().getTiempoFin() - getPartida().getTiempoInicio());               	
		                	
		                	getElJuego().getPartidas().add(getPartida());
	                	}
	                }
	            }
			} 
		}
	}
}