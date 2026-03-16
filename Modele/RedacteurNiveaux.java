package Modele;
import java.io.OutputStream;
import java.io.PrintStream;


public class RedacteurNiveaux {
    private PrintStream output;

    public RedacteurNiveaux(OutputStream outputStream) {
        this.output = new PrintStream(outputStream);
    }

    public void ecrisNiveau(Niveau niveau) {
        // Écriture case par case, ligne par ligne
        for (int ligne = 0; ligne < niveau.lignes(); ligne++) {
            for (int col = 0; col < niveau.colonnes(); col++) {
                this.output.print(niveau.getCase(ligne, col));
            }
            this.output.println();
        }

        // Nom du niveau en commentaire, suivi d'une ligne vide
        this.output.println("; " + niveau.nom());
        this.output.println();
    }
}