import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.*;
import Niveau.*;
import javax.swing.*;


public class InterfaceGraphique implements Runnable {

    Jeu jeu;
    JFrame frame;                   // Classe de Swing represente la fenetre (gere tout la zone d'affichage)
    boolean maximized = false;

    InterfaceGraphique(Jeu game) {
    this.jeu = game;
    }

    public void toggleFullscreen(){
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = env.getDefaultScreenDevice();
		if (maximized) {
			device.setFullScreenWindow(null);
			maximized = false;
		} else {
			device.setFullScreenWindow(frame);
			maximized = true;
		}
	}
    


    public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Ma fenetre a moi :) ");
        // Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique(jeu));

        // Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);

        // Basculer en plein ecran au demarrage 
        toggleFullscreen();
    }

    
    
}

