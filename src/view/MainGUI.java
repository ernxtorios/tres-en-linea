package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainGUI {
	// El frame
	MainFrame mainFrame;
	
	// Menú de opciones
	private JMenuBar elMenu;
	private ArrayList<JMenuItem> opcionesMenu = new ArrayList<>();
	
	// El contenedor principal
	JPanel panel;
	
	// El tablero
	private JPanel elTablero;
	
	// Las casillas del tablero
	private JPanel [][] lasCasillas;
	
	// Los botones de las casillas del tablero
	private JButton [][] losBotones;
			
	// Etiqueta informativa
	private JLabel mensajeJuego;
	
	// Mensaje final
	private String mensajeEmergente;
	
	// Línea ganadora
	private String lineaGanadora;
	
	public MainGUI() {
		this.mainFrame = new MainFrame("Tres en línea");
		
		// Contenedor principal de la interfaz gráfica
		this.panel = new JPanel(new BorderLayout());
		
		// En la sección norte se posiciona el menú de opciones
		this.elMenuOpciones();
		panel.add(this.elMenu, BorderLayout.NORTH);
		
		// En la sección central se posiciona el tablero
		this.lasCasillasTablero();
		this.losBotonesTablero(Color.GREEN);
		panel.add(this.elTablero, BorderLayout.CENTER);
		
		// En la sección sur se posiciona el panel informativo
		elPanelInformativo("Tres en línea: Dos jugadores");
		panel.add(this.mensajeJuego, BorderLayout.SOUTH);
		
		// Inicializamos el mensaje de las ventanas emergentes
		this.mensajeEmergente = "";
		
		// Inicializamos la línea ganadora
		this.lineaGanadora = "";
		
		mainFrame.add(panel);
	}	
	
	// Getters y setters
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JButton[][] getLosBotones() {
		return losBotones;
	}

	public void setLosBotones(JButton[][] losBotones) {
		this.losBotones = losBotones;
	}
	
	public String getMensajeEmergente() {
		return mensajeEmergente;
	}

	public void setMensajeEmergente(String mensajeEmergente) {
		this.mensajeEmergente = mensajeEmergente;
	}

	public String getLineaGanadora() {
		return lineaGanadora;
	}

	public void setLineaGanadora(String lineaGanadora) {
		this.lineaGanadora = lineaGanadora;
	}
	
	public JLabel getMensajeJuego() {
		return mensajeJuego;
	}

	public void setMensajeJuego(JLabel mensajeJuego) {
		this.mensajeJuego = mensajeJuego;
	}

	public JMenuBar getElMenu() {
		return elMenu;
	}

	public ArrayList<JMenuItem> getOpcionesMenu() {
		return opcionesMenu;
	}

	public void setOpcionesMenu(ArrayList<JMenuItem> opcionesMenu) {
		this.opcionesMenu = opcionesMenu;
	}

	public JPanel getElTablero() {
		return elTablero;
	}

	public JPanel[][] getLasCasillas() {
		return lasCasillas;
	}

	public JButton getButton(String elBotonClick){
		JButton elBoton = new JButton();
		
		if (elBotonClick.equals("button1")) {
			elBoton = getLosBotones()[0][0];
		} else if (elBotonClick.equals("button2")) {
			elBoton = getLosBotones()[0][1];
		} else if (elBotonClick.equals("button3")) {
			elBoton = getLosBotones()[0][2];
		} else if (elBotonClick.equals("button4")) {
			elBoton = getLosBotones()[1][0];
		} else if (elBotonClick.equals("button5")) {
			elBoton = getLosBotones()[1][1];
		} else if (elBotonClick.equals("button6")) {
			elBoton = getLosBotones()[1][2];
		} else if (elBotonClick.equals("button7")) {
			elBoton = getLosBotones()[2][0];
		} else if (elBotonClick.equals("button8")) {
			elBoton = getLosBotones()[2][1];
		} else if (elBotonClick.equals("button9")) {
			elBoton = getLosBotones()[2][2];
		}

		return elBoton;
	}
	
	public JPanel getCasilla(String elBotonClick){
		JPanel elPanel = new JPanel();
		
		if (elBotonClick.equals("button1")) {
			elPanel = getLasCasillas()[0][0];
		} else if (elBotonClick.equals("button2")) {
			elPanel = getLasCasillas()[0][1];
		} else if (elBotonClick.equals("button3")) {
			elPanel = getLasCasillas()[0][2];
		} else if (elBotonClick.equals("button4")) {
			elPanel = getLasCasillas()[1][0];
		} else if (elBotonClick.equals("button5")) {
			elPanel = getLasCasillas()[1][1];
		} else if (elBotonClick.equals("button6")) {
			elPanel = getLasCasillas()[1][2];
		} else if (elBotonClick.equals("button7")) {
			elPanel = getLasCasillas()[2][0];
		} else if (elBotonClick.equals("button8")) {
			elPanel = getLasCasillas()[2][1];
		} else if (elBotonClick.equals("button9")) {
			elPanel = getLasCasillas()[2][2];
		}

		return elPanel;
	}
	
	public String nombreBoton(int m, int n) {
		String elBoton = "";
		
		if (m == 0 && n == 0) {
			elBoton = "button1";
		} else if (m == 0 && n == 1) {
			elBoton = "button2";
		} else if (m == 0 && n == 2) {
			elBoton = "button3";
		} else if (m == 1 && n == 0) {
			elBoton = "button4";
		} else if (m == 1 && n == 1) {
			elBoton = "button5";
		} else if (m == 1 && n == 2) {
			elBoton = "button6";
		} else if (m == 2 && n == 0) {
			elBoton = "button7";
		} else if (m == 2 && n == 1) {
			elBoton = "button8";
		} else if (m == 2 && n == 2) {
			elBoton = "button9";
		}
		
		return elBoton;
	}
	
	// Métodos privados que modularizan la interfaz gráfica
	// Método que construye el menú de opciones
	private void elMenuOpciones() {
		this.elMenu = new JMenuBar();
		
		JMenu menuJuego = new JMenu("Juego");
		
		configurarMenu("Nuevo", menuJuego, 0);
		configurarMenu("Jugar con la computadora", menuJuego, 1);
		menuJuego.addSeparator();
		configurarMenu("Ficha", menuJuego, 2);
		menuJuego.addSeparator();
		configurarMenu("Propiedades", menuJuego, 3);
		configurarMenu("Resultados", menuJuego, 4);
		menuJuego.addSeparator();
		configurarMenu("Salir", menuJuego, 5);
		
		JMenu menuAyuda = new JMenu("Ayuda");
		
		configurarMenu("Versión", menuAyuda, 6);
		configurarMenu("Acerca de", menuAyuda, 7);

		this.elMenu.add(menuJuego);
		this.elMenu.add(menuAyuda);
	}

	// Método que configura las opciones del menú
	private void configurarMenu(String rotulo, JMenu menu, int orden) {
		this.opcionesMenu.add(orden, new JMenuItem(rotulo));
			
		menu.add(this.opcionesMenu.get(orden));
	}
	
	// Método que inicializa el panel informativo
	private void elPanelInformativo(String mensajeInicial) {
		this.mensajeJuego = new JLabel(mensajeInicial);
	}
	
	// Método que establece las casillas del tablero
	private void lasCasillasTablero() {
		this.elTablero = new JPanel(new GridLayout(3, 3));
		
		this.lasCasillas = new JPanel [3][3];
			
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.lasCasillas[i][j] = new JPanel();
				
				// Establecemos un nombre a cada panel que representa cada casillero del tablero
				// casillero1, casillero2, casillero3, ..., casillero9
				this.lasCasillas[i][j].setName("casillero" + (3*i+j+1));
				
				this.lasCasillas[i][j].setLayout(new BoxLayout(this.lasCasillas[i][j], BoxLayout.X_AXIS));
				this.lasCasillas[i][j].setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
			
				this.elTablero.add(this.lasCasillas[i][j]);
			}
		}
	}
	
	// Método que establece los botones de las casillas del tablero
	private void losBotonesTablero(Color color) {
		this.losBotones = new JButton [3][3];
				
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.losBotones[i][j] = new JButton("_");
				
				// Establecemos un nombre a cada botón que representa cada casillero del tablero
				// button1, button2, button3, ..., button9
				this.losBotones[i][j].setName("button" + (3*i+j+1));
				
				losBotones[i][j].setFont(new Font("Verdana", Font.BOLD, 34));
				losBotones[i][j].setForeground(color);
				
				this.lasCasillas[i][j].add(Box.createHorizontalGlue()); 
				this.lasCasillas[i][j].add(this.losBotones[i][j]);
				this.lasCasillas[i][j].add(Box.createHorizontalGlue()); 
			}
		}
	}
	
	// Si el botón de la casilla es clicked, se ejecuta el método en el Controller (actionPerformed)
	public void addClickButtonListener(ActionListener listenForClickButton) {
		getLosBotones()[0][0].addActionListener(listenForClickButton);
		getLosBotones()[0][1].addActionListener(listenForClickButton);
		getLosBotones()[0][2].addActionListener(listenForClickButton);
		getLosBotones()[1][0].addActionListener(listenForClickButton);
		getLosBotones()[1][1].addActionListener(listenForClickButton);
		getLosBotones()[1][2].addActionListener(listenForClickButton);
		getLosBotones()[2][0].addActionListener(listenForClickButton);
		getLosBotones()[2][1].addActionListener(listenForClickButton);
		getLosBotones()[2][2].addActionListener(listenForClickButton);
	}
	
	// Si el elemento del menú es clicked, se ejecuta el método en el Controller (actionPerformed)
	public void addClickMenuListener(ActionListener listenForClickMenu) {
		getOpcionesMenu().get(0).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(1).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(2).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(3).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(4).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(5).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(6).addActionListener(listenForClickMenu);
		getOpcionesMenu().get(7).addActionListener(listenForClickMenu);
	}
	
	// Muestra una ventana emergente que contiene el mensaje de error 
	public void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(getMainFrame(), errorMessage);
	}
	
	public void nuevaPartida(Color color, String mensajeInicial) {		
		getPanel().remove(getPanel().getComponent(2));
		getPanel().repaint();
		getPanel().remove(getPanel().getComponent(1));
		getPanel().repaint();
				
		// En la sección central se posiciona el tablero
		this.lasCasillasTablero();
		this.losBotonesTablero(color);
		panel.add(this.elTablero, BorderLayout.CENTER, 1);
		
		// En la sección sur se posiciona el panel informativo
		elPanelInformativo(mensajeInicial);
		panel.add(this.mensajeJuego, BorderLayout.SOUTH, 2);
				
		// Inicializamos el mensaje de las ventanas emergentes
		this.mensajeEmergente = "";
				
		// Inicializamos la línea ganadora
		this.lineaGanadora = "";
		
		getPanel().repaint();
		getPanel().updateUI();
	}
}
