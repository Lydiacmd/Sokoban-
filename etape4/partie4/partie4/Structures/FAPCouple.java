package Structures;


public class FAPCouple<V, P extends Comparable<P>> implements FAP<Couple<V,P>> {

    SequenceListe<Couple<V,P>> Fap;

    public FAPCouple(){
        Fap = new SequenceListe<Couple<V,P>>();
    }

    public boolean estVide(){
        return Fap.taille() == 0;
    }

    public Couple<V,P> defiler(){
        Couple<V,P> x;
        x = Fap.lis(0);
        Fap.supprime(0);
        return x;

    }

    public void enfiler(Couple<V,P> x){
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