package Controleur;
import Modele.*;
import Vue.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controleur implements ActionListener {
    private Jeu jeu;
    private InterfaceGraphique ig;
    private List<Animation> animations;
    private List<Animation> pendingAnimations = new CopyOnWriteArrayList<>();
    private Timer timer;
    private AnimationPousseur animPousseur;
    private AnimationJeuAutomatique aiPousseur;
    private boolean animationsActives = true;

    public Controleur(Jeu jeu, InterfaceGraphique ig) {
        this.jeu = jeu;
        this.ig = ig;
        this.animations = Configuration.nouvelleSequence();
        animPousseur = new AnimationPousseur(ig);
        aiPousseur = new AnimationJeuAutomatique(ig, jeu, this);
        animations.add(animPousseur);
        animations.add(aiPousseur);
        this.timer = new Timer(16, this);
        this.timer.start();
    }

       public void setDirection(int direction) {
        //eleve les marques 
        jeu.niveau().videMarques();
        animPousseur.setDirection(direction);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (animationsActives) {
            animations.removeIf(a -> !a.avance());
            animations.addAll(pendingAnimations);
            pendingAnimations.clear();
        }
        ig.repaint();
    }

    public void ajouteAnimation(Coup coup) {
        animations.removeIf(a -> a instanceof AnimationCoup);
        pendingAnimations.removeIf(a -> a instanceof AnimationCoup);
        ig.supprimeTousLesDecalages();
        if (animationsActives) {
            pendingAnimations.add(new AnimationCoup(
                coup.ligneDepart, coup.colonneDepart,
                coup.ligneArrivee, coup.colonneArrivee,
                ig
            ));
            if (coup.caisseBougee) {
                pendingAnimations.add(new AnimationCoup(
                    coup.ligneDepartCaisse, coup.colonneDepartCaisse,
                    coup.ligneArriveeCaisse, coup.colonneArriveeCaisse,
                    ig
                ));
            }
        }
        ig.repaint();
    }

    public void toggleAI() {
        aiPousseur.setActive(!aiPousseur.isActive());
    }

    // Pour historique 
    public void ajouteAnimationInverse(Coup coup) {
         animations.removeIf(a -> a instanceof AnimationCoup);  // ← vide les anims en cours
         pendingAnimations.removeIf(a -> a instanceof AnimationCoup);
         ig.supprimeTousLesDecalages();
        if (animationsActives) {
            pendingAnimations.add(new AnimationCoup(
                coup.ligneArrivee, coup.colonneArrivee,
                coup.ligneDepart, coup.colonneDepart,
                ig
            ));
            if (coup.caisseBougee) {
                pendingAnimations.add(new AnimationCoup(
                    coup.ligneArriveeCaisse, coup.colonneArriveeCaisse,
                    coup.ligneDepartCaisse, coup.colonneDepartCaisse,
                    ig
                ));
            }
        }
        ig.repaint();
    }

    public void toggleAnimations() {
        animationsActives = !animationsActives;
        if (!animationsActives) {
            animations.removeIf(a -> a instanceof AnimationCoup);
            ig.supprimeTousLesDecalages();
        }
    }
}