package Structures;


public interface FAP<T extends Comparable<T>> {

//  T extends Comparable<T> : veut dire "T doit être un type qu'on peut comparer"
//                         donne accees a la methode compareTo
// C'est une contrainte de type borné
// T extends Comparable<T>  : se lit "T est contraint à être un type qui implémente Comparable".
    
    void enfiler(T x);
    T defiler();
    boolean estVide();
}