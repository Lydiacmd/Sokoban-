package Structures;


public class SequenceTableau<T> implements Sequence<T>{
    // On Choisir l'implementation d'une liste chaine et d'un tableau d'entier 

    private Object[] Tableau;       // Toutes les class HERITE DE LA CLASS OBJECT !!!!
    private int nb_elem;         

    public SequenceTableau(){
        Tableau = new Object[10];
        nb_elem = 0;
    }

    private void agrandir() {
        if (nb_elem >= Tableau.length) {
            int nouvelleCapacite = Tableau.length * 2;
            Object[] nouveau = new Object[nouvelleCapacite];
            for (int i = 0; i < nb_elem; i++) {
                nouveau[i] = Tableau[i];
            }
            Tableau = nouveau;
        }
    }

    // methodes utilitaires pour l'iterateur (hors interface)
    public int taille() {
        return nb_elem;
    }

    @SuppressWarnings("unchecked")
    public T get(int i) {
        return (T) Tableau[i];
    }

    public void supprimeA(int i) {
        for (int k = i; k < nb_elem - 1; k++) {
            Tableau[k] = Tableau[k + 1];
        }
        nb_elem--;
    }



    @Override
    public void insereTete(T element) {
        if (nb_elem == Tableau.length) {
            agrandir();
        }
        // decaler tous les elements vers la droite
        for (int i = nb_elem; i > 0; i--) {
            Tableau[i] = Tableau[i - 1];
        }
        Tableau[0] = element;
        nb_elem++;
    }

    @Override
    public void insereQueue(T element) {
        if (nb_elem == Tableau.length) {
            agrandir();
        }
        Tableau[nb_elem] = element;
        nb_elem++;
    }

    @Override
    public T extraitTete() {
        if (estVide()) {
            throw new RuntimeException("Sequence vide");
        }
        T resultat = (T) Tableau[0];
        // decaler tous les elements vers la gauche
        for (int i = 0; i < nb_elem - 1; i++) {
            Tableau[i] = Tableau[i + 1];
        }
        nb_elem--;
        return resultat;
    }

    @Override
    public boolean estVide() {
        return nb_elem == 0;
    }


    @Override
    public String toString() {
        String resultat = "SequenceTableau [ ";
        for (int i = 0; i < nb_elem; i++) {
            if (i > 0) resultat += ", ";
            resultat += Tableau[i];
        }
        resultat += " ]";
        return resultat;
    }

    

    // pk le this la sequenceTableau courante
    public Iterateur<T> iterateur(){
        return new IterateurSequenceTab<T>(this);
    }
}