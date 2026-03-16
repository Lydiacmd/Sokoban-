package Modele;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;


public class Coup {
    public int ligneDepart, colonneDepart;          // Pos de départ du joueur (ou il vient)
    public int ligneArrivee, colonneArrivee;        // Pos d'arrive du joueur (ou il va)
    public boolean caisseBougee;                    // Ya il une caisse poousser ??
    public int ligneDepartCaisse, colonneDepartCaisse;      //Pos deppart de la caisse
    public int ligneArriveeCaisse, colonneArriveeCaisse;    //Pos arrive de la caisse 
    private Map<String, Color> marques = new HashMap<>();


    public void ajouteMarque(int ligne, int colonne, Color couleur) {
        marques.put(ligne + "," + colonne, couleur);
    }

    public Map<String, Color> getMarques() {
        return marques;
    }

    public Coup(int ld, int cd, int la, int ca) {
        this.ligneDepart = ld;
        this.colonneDepart = cd;
        this.ligneArrivee = la;
        this.colonneArrivee = ca;
        this.caisseBougee = false;
    }

    public void setCaisse(int ld, int cd, int la, int ca) {
        this.caisseBougee = true;
        this.ligneDepartCaisse = ld;
        this.colonneDepartCaisse = cd;
        this.ligneArriveeCaisse = la;
        this.colonneArriveeCaisse = ca;
    }
}