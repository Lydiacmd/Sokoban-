package Controleur;
import Modele.*;
import Vue.*;

public class AnimationPousseur extends Animation {
    private int direction;
    private int frame;
    private int cmpt;          // pour ralentir l'animation ou pas 
    private int vitesse = 8;  // changement de frame tout les 8 ticks 

    public AnimationPousseur(InterfaceGraphique ig){
        super(ig);
        this.direction=0;
        this.frame=0;
        this.cmpt=0;
    }

    public void setDirection(int direction) {
    this.direction = direction;
    }

     @Override
    // Retourne false si terminée
    public boolean avance() {
        cmpt++;
        if (cmpt >= vitesse){
            cmpt = 0;
            frame = (frame + 1)%4;
            System.out.println("frame : " + frame + " direction : " + direction); // ← ici
            ig.setImagePousseur(ig.getImagePousseurAnim(direction, frame));
        }
        return true; // se termine jamais, le pousseur marche tjr meme quand il ne bouge pas 

    }


}