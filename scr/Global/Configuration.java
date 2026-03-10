package Global;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Configuration {

    // affiche tout = true / desavtive tout les avetissements = false
    public static boolean afficherAvertissements = true;
    // affiche tout = true / desavtive tout les messages = false
    public static boolean afficherMessages = true;

    public static InputStream ouvre(String s) {
        try {
            return new FileInputStream("res/" + s);
        } catch (IOException e) {
            System.err.println("impossible de charger la ressource : " + s);
            System.exit(1);
        }
        return null;
    }


    public static void avertissement(String msg) {
        if (afficherMessages && afficherAvertissements) {
            System.err.println("[!! AVERTISSEMENT !! ] " + msg);
        }
    }

    public static void erreur(String msg) {
        if (afficherMessages) {
            System.err.println("[!! ERREUR !!] " + msg);
        }
        System.exit(1);
    }


}