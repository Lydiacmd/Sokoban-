package Structures;

public class FAPListe<T extends Comparable<T>> implements FAP<T> {

    private SequenceListe<T> Fap;

    FAPListe(){
        Fap = new SequenceListe<T>();
    }

    public boolean estVide(){
        return Fap.taille() == 0;
    }

    public T defiler(){
        T x;
        x = Fap.lis(0);
        Fap.supprime(0);
        return x;

    }

    public void enfiler(T x){
        if (estVide()){
            Fap.ajoute(0, x);
        }else{
            // si c'est plus petit que 
            int cmp;
            int i = 0;
            boolean arret = false;

    
            while(i < Fap.taille() && arret == false){
                cmp = Fap.lis(i).compareTo(x);

                if (cmp >= 0){ // Ajout a l'index 
                    Fap.ajoute(i, x);
                    arret = true;
                }
                i++;

            }
            if (arret == false){
                // Arret de force insere a la fin 
                Fap.ajoute(i, x);
            }
                return;

    }

}
} 