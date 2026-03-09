package Structures;

public class SequenceListe<T> implements Sequence<T> {
    
    private class noeud {
        T val ;
        noeud suiv;
    }

    

    private noeud tete;
        
    private class IterateurSequenceListe implements Iterateur<T> {

        private noeud courant;
        private noeud precedent;
        private boolean peutSupprimer;
        private noeud avantPrecedent;

    IterateurSequenceListe( ) {
        this.courant = tete;
        this.precedent = null;  // ^    [3] --> [7] --> [1] --> null ici
        this.peutSupprimer = false;
        this.avantPrecedent = null;
        }

    public boolean aProchain(){
        return courant != null;

    }

    public T prochain(){
        T elem;
        if (aProchain() == false){
             throw new IllegalStateException("sors des limite de la sequence\n");
        }else{
            elem = courant.val;
            avantPrecedent = precedent;
            precedent = courant;
            courant = courant.suiv;
            peutSupprimer = true;
        }
        return elem;

    }

    @Override
    public void supprime(){
        if (peutSupprimer == false){
            throw new IllegalStateException("sors des limite de la sequence\n");
        }
        else{
            if (avantPrecedent == null){     // tete 
                tete = courant;
                peutSupprimer = false;
            } else {
                avantPrecedent.suiv = courant;
                precedent = avantPrecedent;
                peutSupprimer = false;
            }
        }
    }
}



    

    public int taille(){
        noeud courant = tete;
        int cmpt =0;
            while(courant != null){
                cmpt++;
                courant = courant.suiv; 
           }
        
        return cmpt;
    }


    public T lis(int i){
        int taille = taille();
        noeud courant = tete;

        if (taille-1 < i || i <0){
            throw new IndexOutOfBoundsException("sors des limite de la Liste\n");
        } else {
            for(int j = 0; j<i ;j++){
                courant = courant.suiv;
            }
            return courant.val;
        }

    }

    public void ajoute(int i, T x){
        noeud courant = tete;
        noeud nvnoeud = new noeud();
        int cmp = 0;
        if (i > taille() || i <0){
                throw new IndexOutOfBoundsException("sors des limite de la Liste\n");
            }
        if (tete == null){
            nvnoeud.val = x;
            nvnoeud.suiv = null;
            tete = nvnoeud;
        }else if (i == 0){
            nvnoeud.val = x;
            nvnoeud.suiv = tete;
            tete = nvnoeud;
        }else{
             noeud courant2 = tete.suiv;
            while(cmp < i-1){
                cmp++;
                courant = courant.suiv;
                courant2 = courant2.suiv;
            }
            nvnoeud.val = x;
            nvnoeud.suiv = courant2;
            courant.suiv = nvnoeud;       
        }
    }


    public void supprime(int i){
        noeud courant = tete;
        int cmp=0;
        if (taille() <= i || i < 0){
            throw new IndexOutOfBoundsException("sors des limite de la Liste\n");
        }
        else if (i == 0){ // tete 
            tete = tete.suiv;
            return;
        }
        else{
            noeud courant2 = courant.suiv;
            while( cmp < i-1){
                courant = courant.suiv;
                courant2 = courant2.suiv;
                cmp++;
            }
                courant.suiv = courant2.suiv;
                courant2 = null;

            }

        }



    public Iterateur<T> iterateur(){
        return new IterateurSequenceListe();
    }

    }

