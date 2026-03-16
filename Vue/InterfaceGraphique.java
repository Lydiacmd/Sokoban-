package Vue;
import Modele.*;   
import java.io.FileInputStream;
import java.io.InputStream;
import java.awt.*;
import java.awt.event.*; 
import javax.swing.*;


public class InterfaceGraphique implements Runnable {

    Jeu jeu;
    JFrame frame;                   // Classe de Swing represente la fenetre (gere tout la zone d'affichage)
    boolean maximized = false;
    NiveauGraphique niveauGraphique;
    private KeyListener ecouteurClavier;
    private MouseListener ecouteurSouris;

    public InterfaceGraphique(Jeu game) {
    this.jeu = game;
    }

    public void setDecalage(int col, int ligne, float dx, float dy) {
    niveauGraphique.setDecalage(col, ligne, dx, dy);
    }

    public void supprimeDecalage(int col, int ligne) {
        niveauGraphique.supprimeDecalage(col, ligne);
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
    
    public NiveauGraphique getNiveauGraphique() { return niveauGraphique; }
    public void ajouteEcouteurSouris(MouseListener m) { ecouteurSouris = m; }  // ← stocke
    public void ajouteEcouteurClavier(KeyListener k) { ecouteurClavier = k; }  // ← stocke


    public void repaint(){
        if (niveauGraphique != null) niveauGraphique.repaint(); 
    } 

    // Pour l'ecouteur souris 
    public int getLargeur() { return niveauGraphique.getWidth(); }
    public int getHauteur() { return niveauGraphique.getHeight(); }
    
    
    public void run() {
		// Creation d'une fenetre
		frame = new JFrame("Ma fenetre a moi :) ");
        
        niveauGraphique = new NiveauGraphique(jeu);

        // On les ajoute les ecouteur Clavier / Souris  quand frame existe
    if (ecouteurClavier != null) frame.addKeyListener(ecouteurClavier);
    if (ecouteurSouris != null) niveauGraphique.addMouseListener(ecouteurSouris);
        
        frame.add(niveauGraphique);

        // Un clic sur le bouton de fermeture clos l'application
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // On fixe la taille et on demarre
		frame.setSize(500, 300);
		frame.setVisible(true);

        // Basculer en plein ecran au demarrage 
        toggleFullscreen();
    }

    // etape 8 / Animation du pousseur 
    public Image getImagePousseurAnim(int direction,int frame){
        return niveauGraphique.getImageAnim(direction,frame);
    }
    
    public void setImagePousseur(Image img) {
    niveauGraphique.setImagePousseur(img);
}

    public void supprimeTousLesDecalages() {
    niveauGraphique.supprimeTousLesDecalages();
}
    
}

/**Modif etape 6 
 * modif dans run elever la creation de new NiveauGraphique(jeu) direct dans frame.add(..)
 * But : entre recupere par l'ecouteur 
 */