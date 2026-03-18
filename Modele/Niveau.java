package Modele;

import java.util.HashMap;
import java.util.Map;
import java.awt.Color;

public class Niveau extends HistoriqueAPile {
    private char[][] monTableau;
    private String nomNiveau;
    private int nblignes;
    private int nbcolonnes;
    private Map<String, Color> marques = new HashMap<>();

    public void poseMarque(int ligne,int colonne, Color couleur){
        marques.put(ligne+","+colonne, couleur);
    }

    public void retireMarque(int ligne,int colonne){
       if  (marques.containsKey(ligne+","+colonne)){
            marques.remove(ligne+","+colonne);
       }
    }

    public Color getMarque(int ligne, int colonne) {
    return marques.get(ligne + "," + colonne);
    }

        public void videMarques() {
        marques.clear();
    }

    public Niveau(int nblignes, int nbcolonnes) {
        this.nblignes = nblignes;
        this.nbcolonnes = nbcolonnes;
        this.monTableau = new char[nblignes][nbcolonnes];
    }

    public void fixeNom(String nom) {
        this.nomNiveau = nom;
    }

    public void videCase(int ligne, int colonne) {
        this.monTableau[ligne][colonne] = ' ';
    }

    public void ajouteMur(int ligne, int colonne) {
        this.monTableau[ligne][colonne] = '#';
    }

    public void ajouteCaisse(int ligne, int colonne) {
        if (this.monTableau[ligne][colonne] == '.') {
            this.monTableau[ligne][colonne] = '*'; // caisse sur un but
        } else {
            this.monTableau[ligne][colonne] = '$'; // caisse normale
        }
    }

    public void ajoutePousseur(int ligne, int colonne) {
        if (this.monTableau[ligne][colonne] == '.') {
            this.monTableau[ligne][colonne] = '+'; // pousseur sur un but
        } else {
            this.monTableau[ligne][colonne] = '@'; // pousseur normal
        }
    }

    public void ajouteBut(int ligne, int colonne) {
        if (this.monTableau[ligne][colonne] == '$') {
            this.monTableau[ligne][colonne] = '*'; // caisse déjà là
        } else if (this.monTableau[ligne][colonne] == '@') {
            this.monTableau[ligne][colonne] = '+'; // pousseur déjà là
        } else {
            this.monTableau[ligne][colonne] = '.'; // case but vide
        }
    }

    public int lignes() {
        return this.monTableau.length;
    }

    public int colonnes() {
        return this.monTableau[0].length;
    }

    public String nom() {
        return this.nomNiveau;
    }

    public boolean estVide(int ligne, int colonne) {
        return this.monTableau[ligne][colonne] == ' ';
    }

    public boolean aMur(int ligne, int colonne) {
        return this.monTableau[ligne][colonne] == '#';
    }

    public boolean aBut(int ligne, int colonne) {
        return this.monTableau[ligne][colonne] == '.'
            || this.monTableau[ligne][colonne] == '+'
            || this.monTableau[ligne][colonne] == '*';
    }

    public boolean aCaisse(int ligne, int colonne) {
        return this.monTableau[ligne][colonne] == '$'
            || this.monTableau[ligne][colonne] == '*';
    }

    public boolean aPousseur(int ligne, int colonne) {
        return this.monTableau[ligne][colonne] == '@'
            || this.monTableau[ligne][colonne] == '+';
    }

    public char getCase(int ligne, int colonne) {
        return this.monTableau[ligne][colonne];
    }

    // Ajouter a partir de l'etape 6 car on as besoin de repere la mofition du pousseur 
    public int lignePousseur() {
        for (int l = 0; l < this.nblignes; l++) {
            for (int c = 0; c < this.nbcolonnes; c++) {
                if (aPousseur(l, c)) {
                    return l;
                }
            }
        }
        return -1; // Pousseur non trouver
    }

    public int colonnePousseur(){
    for (int l = 0; l < this.nblignes; l++) {
            for (int c = 0; c < this.nbcolonnes; c++) {
                if (aPousseur(l, c)) {
                    return c;
                }
            }
        }
        return -1; // Pousseur non trouver
    }

public Coup deplacer(int dl, int dc) {
    int l = lignePousseur();
    int c = colonnePousseur();
    int nl = l + dl;  // case cible
    int nc = c + dc;

    // Verifie les limites
    if (nl < 0 || nl >= nblignes || nc < 0 || nc >= nbcolonnes) return null;
    
    // mur -> on ne fait rien
    if (aMur(nl, nc)) {
        return null;

    } else if (!aCaisse(nl, nc)) {
        // Case libre : on deplace juste le pousseur
        // La case quittee redevient vide ou but
        monTableau[l][c] = aBut(l, c) ? '.' : ' ';
        ajoutePousseur(nl, nc);
        return new Coup(l,c,nl,nc);   // retourne un coup

    } else {
        // Il y a une caisse : on regarde la case derrière
        int nl2 = nl + dl;
        int nc2 = nc + dc;
        if (nl2 < 0 || nl2 >= nblignes || nc2 < 0 || nc2 >= nbcolonnes) return null;
        if (aMur(nl2, nc2) || aCaisse(nl2, nc2)) return null;

        // On pousse la caisse
        monTableau[nl][nc] = aBut(nl, nc) ? '.' : ' '; // libère la case caisse
        ajouteCaisse(nl2, nc2);                          // place la caisse
        monTableau[l][c] = aBut(l, c) ? '.' : ' ';     // libère la case pousseur
        ajoutePousseur(nl, nc);                          // place le pousseur
        
        Coup coup = new Coup(l, c, nl, nc);
        coup.setCaisse(nl, nc, nl2, nc2); // ← info sur la caisse
        return coup;
    }
}

 public boolean estGagne() {
        for (int i = 0; i < this.nblignes; i++) {
            for (int j = 0; j < this.nbcolonnes; j++) {
                if (this.monTableau[i][j] == '$') {
                    return false; // il reste un but non occupé
                }
            }
        }
        return true; // tous les buts sont occupés
    }


    public Niveau clone(){

        Niveau copie = new Niveau(nblignes, nbcolonnes);
        copie.nomNiveau = this.nomNiveau;
        for (int l = 0; l < nblignes; l++) {
            for (int c = 0; c < nbcolonnes; c++) {
                copie.monTableau[l][c] = this.monTableau[l][c];
            }
        }
        return copie;
        }

}


/** Modif etape 6 
 * - lignePousseur() / colonnePousseur() : retrouve la position du pousseur
 * - deplacer(dl, dc) : déplace le pousseur selon les règles du Sokoban
 * - estGagne() : retourne true si toutes les caisses sont sur un but
 */