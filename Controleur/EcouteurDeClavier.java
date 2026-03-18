/*
 * Sokoban - Encore une nouvelle version (à but pédagogique) du célèbre jeu
 * Copyright (C) 2018 Guillaume Huard
 *
 * Ce programme est libre, vous pouvez le redistribuer et/ou le
 * modifier selon les termes de la Licence Publique Générale GNU publiée par la
 * Free Software Foundation (version 2 ou bien toute autre version ultérieure
 * choisie par vous).
 *
 * Ce programme est distribué car potentiellement utile, mais SANS
 * AUCUNE GARANTIE, ni explicite ni implicite, y compris les garanties de
 * commercialisation ou d'adaptation dans un but spécifique. Reportez-vous à la
 * Licence Publique Générale GNU pour plus de détails.
 *
 * Vous devez avoir reçu une copie de la Licence Publique Générale
 * GNU en même temps que ce programme ; si ce n'est pas le cas, écrivez à la Free
 * Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307,
 * États-Unis.
 *
 * Contact:
 *          Guillaume.Huard@imag.fr
 *          Laboratoire LIG
 *          700 avenue centrale
 *          Domaine universitaire
 *          38401 Saint Martin d'Hères
 */
package Controleur;
import Modele.*;
import Vue.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// Supprimer la vue vus que elle est cree dans le main 
public class EcouteurDeClavier extends KeyAdapter {
    private Jeu jeu;
    private InterfaceGraphique ig;
    private Controleur controleur;

    public EcouteurDeClavier(Jeu jeu,InterfaceGraphique ig,Controleur controleur) {
        this.jeu = jeu;
        this.ig = ig;
        this.controleur = controleur;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int dl = 0, dc = 0;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:     dl = -1; controleur.setDirection(0); break;
            case KeyEvent.VK_DOWN:   dl =  1; controleur.setDirection(2); break;
            case KeyEvent.VK_LEFT:   dc = -1; controleur.setDirection(1); break;
            case KeyEvent.VK_RIGHT:  dc =  1; controleur.setDirection(3); break;
            case KeyEvent.VK_A:
            case KeyEvent.VK_Q:      System.exit(0); return;
            case KeyEvent.VK_ESCAPE: ig.toggleFullscreen(); return;
            case KeyEvent.VK_P: controleur.toggleAnimations(); return;
            case KeyEvent.VK_I: controleur.toggleAI(); return;
            case KeyEvent.VK_U:
                if (jeu.niveau().peutAnnuler()) {
                    Coup c = jeu.niveau().dernierCoup();
                    jeu.niveau().annuler();
                    controleur.ajouteAnimationInverse(c);
                }
                return;

            case KeyEvent.VK_R:
                if (jeu.niveau().peutRefaire()) {
                    Coup c = jeu.niveau().dernierCoupFutur(); // faut ajouter cette méthode
                    jeu.niveau().refaire();
                    controleur.ajouteAnimation(c);
                }
                return;

            default: return;
        }

        Coup coup = jeu.deplace(dl, dc);
        if (coup != null) {
            jeu.niveau().faire(coup);  // ← ici
            controleur.ajouteAnimation(coup);
            if (jeu.niveau().estGagne()) {
                if (!jeu.prochainNiveau()) System.exit(0);
            }
        }
}
}