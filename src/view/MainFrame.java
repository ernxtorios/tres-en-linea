package view;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame(String titulo) {
		Toolkit laPantalla = Toolkit.getDefaultToolkit();
		
		setTitle(titulo);
		
		setSize(laPantalla.getScreenSize().width/4, laPantalla.getScreenSize().height/2);
		setLocation(laPantalla.getScreenSize().width/4, laPantalla.getScreenSize().height/4);
		
		setResizable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
