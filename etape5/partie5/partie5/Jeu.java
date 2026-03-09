import java.io.FileInputStream;
import java.io.InputStream;

public class Jeu{

    // Le flux d'entree 
        // initialise LectureNiveaux
        LecteurNiveaux jeu_lus;

        // niveau en cours 
        Niveau niveau_jeu ;

    Jeu(InputStream in) {
        this.jeu_lus = new LecteurNiveaux(in);
        this.niveau_jeu = jeu_lus.lisProchainNiveau();
    }

    public Niveau niveau(){
        return niveau_jeu;
    }

    public boolean prochainNiveau(){
        Niveau tmp = this.jeu_lus.lisProchainNiveau();
        if (tmp == null){
            return false;
        }else{
            this.niveau_jeu = tmp;
            return true;
        }
    }

    




}