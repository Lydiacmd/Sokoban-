package Niveau;
import java.io.InputStream;
import java.util.Scanner;

public class LecteurNiveaux {
    private Scanner scanner;

    public LecteurNiveaux(InputStream input) {
        this.scanner = new Scanner(input);
    }

    public Niveau lisProchainNiveau() {
        String[] lignes = new String[10000];
        String nomNiveau = "";
        int nbLignes = 0;

        // Lecture de la première ligne non vide (ignore les commentaires ';')
        while (this.scanner.hasNextLine()) {
            String ligne = this.scanner.nextLine();
            if (!ligne.trim().isEmpty()) {
                if (!ligne.trim().startsWith(";")) {
                    lignes[nbLignes++] = ligne;
                    break;
                }
                nomNiveau = ligne.substring(1).trim(); // commentaire = nom du niveau
            }
        }

        // Aucune ligne trouvée : fin de fichier
        if (nbLignes == 0) {
            return null;
        }

        // Lecture des lignes suivantes jusqu'à une ligne vide
        while (this.scanner.hasNextLine()) {
            String ligne = this.scanner.nextLine();
            if (ligne.trim().isEmpty()) {
                break;
            }
            if (ligne.trim().startsWith(";")) {
                nomNiveau = ligne.substring(1).trim();
            } else {
                lignes[nbLignes++] = ligne;
            }
        }

        // Calcul de la largeur max (nombre de colonnes)
        int nbColonnes = 0;
        for (int i = 0; i < nbLignes; i++) {
            if (lignes[i].length() > nbColonnes) {
                nbColonnes = lignes[i].length();
            }
        }

        // Construction du niveau
        Niveau niveau = new Niveau(nbLignes, nbColonnes);
        niveau.fixeNom(nomNiveau);

        for (int ligne = 0; ligne < nbLignes; ligne++) {
            for (int col = 0; col < nbColonnes; col++) {
                // Si la ligne est plus courte, on complète avec des espaces
                char c = col < lignes[ligne].length() ? lignes[ligne].charAt(col) : ' ';
                switch (c) {
                    case '#':
                        niveau.ajouteMur(ligne, col);
                        break;
                    case '$':
                        niveau.ajouteCaisse(ligne, col);
                        break;
                    case '*': // caisse sur un but
                        niveau.ajouteBut(ligne, col);
                        niveau.ajouteCaisse(ligne, col);
                        break;
                    case '+': // pousseur sur un but
                        niveau.ajouteBut(ligne, col);
                        niveau.ajoutePousseur(ligne, col);
                        break;
                    case '.':
                        niveau.ajouteBut(ligne, col);
                        break;
                    case '@':
                        niveau.ajoutePousseur(ligne, col);
                        break;
                    default:
                        niveau.videCase(ligne, col);
                }
            }
        }

        return niveau;
    }
}