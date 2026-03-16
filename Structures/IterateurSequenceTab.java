package Structures;

public class IterateurSequenceTab<T> implements Iterateur<T>{

    // Pk ici falais mettre Private ??
    /*
    Iterateut encapsule un état de parcours 
    -> Privé
    -> Manipule uniquement apr ces methodes
     */
    private SequenceTableau<T> seq; 
    private int pos;
    private boolean peutSupprimer;          // 
    private int dernierIndice;              // Derinier indice lus Apres prochain 

    IterateurSequenceTab(SequenceTableau<T> s){
        this.seq = s;
        this.pos = 0;
        this.peutSupprimer = false;
        this.dernierIndice = -1;
    }


    // si il existe un prochain 
    public boolean aProchain(){
        return pos < seq.taille();          // taille() reste une methode publique de SequenceTableau

    }

    // avance le curseur au prochain
    public T prochain(){
        T elem;
        if (aProchain()){
            elem = seq.get(pos);            // CORRECTION : get(pos) au lieu de lis(pos)
            peutSupprimer = true;
            dernierIndice = pos;
            pos ++;

        }else{
            throw new IllegalStateException("sors des limite du tableau\n");
        }
        return elem;

    }

    @Override
    public void supprime(){
            if (peutSupprimer){
                seq.supprimeA(dernierIndice);  
                pos = dernierIndice;           // corrige le curseur apres decalage pour par saute d'elem 

                peutSupprimer = false;         // Pour eviter une 2eme suppression SAUF si il est passer par prochain()
            }else{
                throw new IllegalStateException("sors des limite du tableau\n");               
            
            }
}

}