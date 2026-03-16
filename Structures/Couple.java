package Structures;

public class Couple<V, P extends Comparable<P>> implements Comparable<Couple<V,P>>{
    public V valeur;
    public P priorite;
    public Couple(V v,P p){
            this.valeur = v;
            this.priorite = p;
        }

    public int compareTo(Couple<V,P> autre){
        // compareTo prend un seul paramètre : l'autre objet à comparer
        return this.priorite.compareTo(autre.priorite);
    }

    }