import java.io.FileInputStream;
import java.io.InputStream;
import javax.swing.*;
import Modele.*;
import Vue.*;
import Controleur.*;
public class Main {
    
public static void main(String[] args) {
    try{
        InputStream file = Configuration.ouvre("case_jeu");
        Jeu jeu = new Jeu(file);
        int n = Integer.parseInt(args[0]);

        for (int i =0 ; i <n;i++){
            jeu.prochainNiveau();
        }


        // Etape 7 : Creation des Ecouteur dans le main lui meme 
        InterfaceGraphique ig = new InterfaceGraphique(jeu);
        Controleur controleur = new Controleur(jeu, ig);
        EcouteurDeClavier clavier = new EcouteurDeClavier(jeu, ig, controleur);
        EcouteurDeSouris souris = new EcouteurDeSouris(jeu, ig, controleur);
            
        ig.ajouteEcouteurClavier(clavier);
        ig.ajouteEcouteurSouris(souris);

        SwingUtilities.invokeLater(ig);

    }catch (Exception e){
            e.printStackTrace();
            Configuration.erreur(e.getMessage());
    }
    

    
}
}