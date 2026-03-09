

/*Puzzle element	Character	ASCII Code
    Wall	        #	            0x23
    Player	        @	            0x40
    Player on goal
    square	        +	            0x2b
    Box	            $	            0x24
    Box on goal
    square	        *	            0x2a
    Goal square	    .	            0x2e
    Floor	        (Space)	        0x20 
    
    ----> DEUX cas ou : une case plusieurs éléments
    1) Joueur sur une case but  --> +
    2) Caisse sur une case but  --> *

    ----> 1 seul joueur et nb de caisse = nb de case buts
 
    */

import java.io.InputStream;
import java.util.Scanner;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.FileInputStream;


class Niveau {
    private char[][] monTableau;
    private String nomNiveau;
    private int nblignes;
    private int nbcolonnes;



    public Niveau(int lignes, int colonnes) {

        this.nblignes = lignes;
        this.nbcolonnes = colonnes;
        monTableau = new char[lignes][colonnes];
    }


    public void fixeNom(String s) {
        this.nomNiveau = s; 
    }


    public void videCase(int i, int j) {
        monTableau[i][j] = ' ';   
    }

    public void ajouteMur(int i, int j) {
        monTableau[i][j] = '#';   
    }

    public void ajouteCaisse(int i, int j) {
        if (monTableau[i][j] == '.'){
            monTableau[i][j] = '*';
        } else {
            monTableau[i][j] = '$';
        }

    }

    public void ajoutePousseur(int i, int j) {
        if (monTableau[i][j] == '.'){
            monTableau[i][j] = '+';
        } else {
            monTableau[i][j] = '@';
        }

    }

    public void ajouteBut(int i, int j) {
        if (monTableau[i][j] == '$'){
            monTableau[i][j] = '*';
        } else if (monTableau[i][j] == '@'){
            monTableau[i][j] = '+';
        } else {
            monTableau[i][j] = '.';
        }
    }

    public int lignes() {
        return monTableau.length;
    }

    public int colonnes() { 
        return monTableau[0].length;
    }

    public String nom() {
        return this.nomNiveau;
    }

    public boolean estVide(int l, int c) {
        if (monTableau[l][c] == ' '){
            return true;
        } else {
            return false;
        }
    }

    public boolean aMur(int l, int c) {
        return monTableau[l][c] == '#';
    }

    public boolean aBut(int l, int c) {
        if (monTableau[l][c] == '.' || monTableau[l][c] == '+' || monTableau[l][c] == '*'){
            return true;
        } else {
            return false;
        }
    }

    public boolean aCaisse(int l, int c) {
        return monTableau[l][c] == '$' || monTableau[l][c] == '*';
    }

    public boolean aPousseur(int l, int c) {
        return monTableau[l][c] == '@'|| monTableau[l][c] == '+';
    }

    public char getCase(int i, int j) {
    return monTableau[i][j];
    }


}

class LecteurNiveaux {
    private Scanner scanner;

    public LecteurNiveaux(InputStream input) {
        scanner = new Scanner(input);
    }

    public Niveau lisProchainNiveau() {
        String[] lignes = new String[10000];
        String dernierCom = "";
        int nbLignes = 0;

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();

            if (ligne.trim().isEmpty()) continue;

            if (ligne.trim().startsWith(";")) {
                dernierCom = ligne.substring(1).trim();
                continue;
            }

            lignes[nbLignes++] = ligne;
            break;
        }

        if (nbLignes == 0) return null;

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();

            if (ligne.trim().isEmpty()) break;

            if (ligne.trim().startsWith(";")) {
                dernierCom = ligne.substring(1).trim();
                continue;
            }

            lignes[nbLignes++] = ligne;
        }

        int nbColonnes = 0;
        for (int i = 0; i < nbLignes; i++) {
            if (lignes[i].length() > nbColonnes) nbColonnes = lignes[i].length();
        }

        Niveau niveau = new Niveau(nbLignes, nbColonnes);
        niveau.fixeNom(dernierCom);

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {

                char c = (j < lignes[i].length()) ? lignes[i].charAt(j) : ' ';

                switch (c) {
                    case '#': niveau.ajouteMur(i, j); break;
                    case '$': niveau.ajouteCaisse(i, j); break;
                    case '@': niveau.ajoutePousseur(i, j); break;
                    case '.': niveau.ajouteBut(i, j); break;
                    case '*': niveau.ajouteBut(i, j); niveau.ajouteCaisse(i, j); break;
                    case '+': niveau.ajouteBut(i, j); niveau.ajoutePousseur(i, j); break;
                    default:  niveau.videCase(i, j); break;
                }
            }
        }

        return niveau;
    }
}


class RedacteurNiveaux {
    private PrintStream output;

    public RedacteurNiveaux(OutputStream output) {
        this.output = new PrintStream(output);
    }

    public void ecrisNiveau(Niveau niveau) {
        for (int i = 0; i < niveau.lignes(); i++) {
            for (int j = 0; j < niveau.colonnes(); j++) {
                output.print(niveau.getCase(i, j));
            }
            output.println();
        }
        output.println("; " + niveau.nom());
        output.println(); // ligne vide entre niveaux

    }
}


public class Main {
    public static void main(String[] args) throws Exception {

        InputStream in = new FileInputStream("case_jeu"); // mets ton nom de fichier
        LecteurNiveaux lecteur = new LecteurNiveaux(in);

        RedacteurNiveaux redacteur = new RedacteurNiveaux(System.out);

        Niveau niveau;
        while ((niveau = lecteur.lisProchainNiveau()) != null) {
            redacteur.ecrisNiveau(niveau);
        }

        in.close();
    }
}