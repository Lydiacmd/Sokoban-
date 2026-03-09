import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.*;


public class InterfaceGraphique implements Runnable {

    Jeu jeu;

    InterfaceGraphique(Jeu game) {
    this.jeu = game;
    }


    public void run() {
		// Creation d'une fenetre
		JFrame frame = new JFrame("Ma fenetre a moi :) ");
        // Ajout de notre composant de dessin dans la fenetre
		frame.add(new NiveauGraphique(jeu));

        // Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);
    }

    
    
}

