package main;

import controller.Controller;
import model.Partida;
import view.MainGUI;

public class Main {
	public static void main(String[] args) {
		MainGUI theView = new MainGUI();
        
		Partida theModel = new Partida();
        
    	Controller theController = new Controller(theView, theModel);
		
    	theController.getTheView().getMainFrame().setVisible(true);
	}
}
