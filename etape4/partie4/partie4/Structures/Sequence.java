package Structures;
public interface Sequence<T> {      // cette interface a un parametre de type chose qui s'appele T 

    int taille();               // rien a changer besoin de retourner un int 
    T lis(int i);               // on lit quoi Un T mais l'index reste un int 
    void ajoute(int i,T x);     // On ajoute la chose T a l'index i
    void supprime(int i);       // rien ne change besoin de l'index i
    Iterateur<T> iterateur();     

}
