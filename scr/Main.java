import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.*;
import Niveau.*;
import Global.*;
public class Main {
    
public static void main(String[] args) {
    try{
        InputStream file = Configuration.ouvre("case_jeu");
        Jeu jeu = new Jeu(file);
        int n = Integer.parseInt(args[0]);

        for (int i =0 ; i <n;i++){
            jeu.prochainNiveau();
        }
        SwingUtilities.invokeLater(new InterfaceGraphique(jeu));

    }catch (Exception e){
            e.printStackTrace();
            Configuration.erreur(e.getMessage());
    }
    

    
}
}