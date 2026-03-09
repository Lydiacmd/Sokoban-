package Structures;


public class SequenceTableau<T> implements Sequence<T>{
    // On Choisir l'implementation d'une liste chaine et d'un tableau d'entier 

    private Object[] Tableau;       // Toutes les class HERITE DE LA CLASS OBJECT !!!!
    private int nb_elem;         

    public SequenceTableau(){
        Tableau = new Object[10];
        nb_elem = 0;
    }


    public int taille(){
        return nb_elem;
    }

    public T lis(int i){
        if ( i >= nb_elem ||i < 0){
            throw new IndexOutOfBoundsException("sors des limite du tableau\n");
        }else{
            return (T) Tableau[i];
        }
    }

    public void ajoute(int i, T x){
        if (i > nb_elem || i < 0){
            throw new IndexOutOfBoundsException("Indice Invalide\n");
        }else {
            if (nb_elem == Tableau.length){
                throw new IndexOutOfBoundsException("Pas de place\n");
            }else{
                int j = nb_elem -1;
                while (j >= i){
                    Tableau[j+1] = Tableau[j];
                    j --;
                }
                Tableau[i] = x;
                nb_elem ++;
            }
        }
        return;
    }

    public void supprime(int i){
        if (i >= nb_elem || i < 0){
            throw new IndexOutOfBoundsException("Indice Invalide\n");
        }else {
            if (i == nb_elem-1){
                // je le mets comme le mets java par defaut ? 
                // il suffit de decrementer le nb_elem
                nb_elem --;
            }else{
                int j = i;
                while (j < nb_elem-1){
                    Tableau[j] = Tableau[j+1];
                    j++;
                }
                nb_elem --;
            } 


        }
        return ;
    }

    // pk le this la sequenceTableau courante
    public Iterateur<T> iterateur(){
        return new IterateurSequenceTab<T>(this);
    }
}