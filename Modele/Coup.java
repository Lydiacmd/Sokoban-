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

        public void executer(Niveau nv){
        // Libere la case de départ du pousseur on verifie si c'est un but ou un mur dessus 
        boolean butDepart = nv.aBut(ligneDepart, colonneDepart);
        nv.videCase(ligneDepart, colonneDepart);
        if (butDepart) nv.ajouteBut(ligneDepart, colonneDepart);
        // Place le pousseur à l'arrivée
        nv.ajoutePousseur(ligneArrivee, colonneArrivee);

        if (caisseBougee) {
            boolean butDepartC = nv.aBut(ligneDepartCaisse, colonneDepartCaisse);
            nv.videCase(ligneDepartCaisse, colonneDepartCaisse);
            if (butDepartC) nv.ajouteBut(ligneDepartCaisse, colonneDepartCaisse);
            nv.ajouteCaisse(ligneArriveeCaisse, colonneArriveeCaisse);
        }
    }

    public void annulerCoup(Niveau nv) {
        // Remet le pousseur à son départ
        boolean butArrivee = nv.aBut(ligneArrivee, colonneArrivee);
        nv.videCase(ligneArrivee, colonneArrivee);
        if (butArrivee) nv.ajouteBut(ligneArrivee, colonneArrivee);
        nv.ajoutePousseur(ligneDepart, colonneDepart);

        if (caisseBougee) {
            boolean butArriveeC = nv.aBut(ligneArriveeCaisse, colonneArriveeCaisse);
            nv.videCase(ligneArriveeCaisse, colonneArriveeCaisse);
            if (butArriveeC) nv.ajouteBut(ligneArriveeCaisse, colonneArriveeCaisse);
            nv.ajouteCaisse(ligneDepartCaisse, colonneDepartCaisse);
        }
    }

}