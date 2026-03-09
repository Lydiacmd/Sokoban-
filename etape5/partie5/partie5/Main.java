import java.io.FileInputStream;
import javax.swing.*;

public class Main {
    
public static void main(String[] args) {
    try{
        FileInputStream file = new FileInputStream("case_jeu");

        Jeu jeu = new Jeu(file);
        int n = Integer.parseInt(args[0]);

        for (int i =0 ; i <n;i++){
            jeu.prochainNiveau();
        }
        SwingUtilities.invokeLater(new InterfaceGraphique(jeu));

    }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
    }
    

    
}
}