package Controleur;
import Modele.*;
import Vue.*;

public class AnimationCoup extends Animation {
    private int ligneDepart, colonneDepart;
    private int ligneArrivee, colonneArrivee;
    private int etape;
    private int nbEtapes = 10;

    public AnimationCoup(int ld, int cd, int la, int ca, InterfaceGraphique ig) {
        super(ig);          // Initialise la partie animation (le parent)
        this.ligneDepart = ld;
        this.colonneDepart = cd;
        this.ligneArrivee = la;
        this.colonneArrivee = ca;
        this.etape = 0;
        this.ig = ig;
        
        // Au début : décalage = position départ - position arrivée
        // (la pièce est déjà à l'arrivée dans le modèle)
        float dx = colonneDepart - colonneArrivee;
        float dy = ligneDepart - ligneArrivee;
        ig.setDecalage(colonneArrivee, ligneArrivee, dx, dy);
    }

    @Override
    // Retourne false si terminée
    public boolean avance() {
        System.out.println("Avance dans AnimationCoup appelr \n");
        etape++;
        float progress = (float) etape / nbEtapes;
        float dx = (colonneDepart - colonneArrivee) * (1 - progress);
        float dy = (ligneDepart - ligneArrivee) * (1 - progress);
        
        if (etape >= nbEtapes) {
            ig.supprimeDecalage(colonneArrivee, ligneArrivee);
            return false; // animation terminée
        }
        ig.setDecalage(colonneArrivee, ligneArrivee, dx, dy);
        return true;
    }
}