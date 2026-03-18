package Controleur;

import Modele.*;
import Vue.*;

public class AnimationJeuAutomatique extends Animation {
    private AleatoireCoupAi ai;
    private Jeu jeu;
    private Controleur controleur;
    private int cmp = 0;
    private static final int INTERVALLE = 30;

    public AnimationJeuAutomatique(InterfaceGraphique ig, Jeu jeu, Controleur controleur) {
        super(ig);
        this.ai = new AleatoireCoupAi();
        this.jeu = jeu;
        this.controleur = controleur;
    }

    private boolean active = false;


    public void setActive(boolean active) {
        this.active = active;
    }


    public boolean isActive() {
        return active;
    }


    @Override
    public boolean avance() {
        if (!active) return true;
        cmp++;
        if (cmp >= INTERVALLE) {
            cmp = 0;
            Niveau copie = jeu.niveau().clone();
            Direction dir = ai.retournCoupAi(copie);
            System.out.println("Ai joue: " + dir);
            Coup coup = jeu.niveau().deplacer(dir.deltaligne, dir.deltacolonne);
            System.out.println("Coup: " + coup);
            if (coup != null) {
                coup.ajouteMarque(coup.ligneArrivee, coup.colonneArrivee, java.awt.Color.RED);
                jeu.niveau().poseMarque(coup.ligneArrivee, coup.colonneArrivee, java.awt.Color.RED);
                controleur.ajouteAnimation(coup);
            }
        }
        return true;
    }
}