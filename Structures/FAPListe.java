package Structures;

public class FAPListe<T extends Comparable<T>> implements FAP<T> {

    private SequenceListe<T> Fap;

    FAPListe(){
        Fap = new SequenceListe<T>();
    }

    @Override
    public boolean estVide(){
        return Fap.estVide();
    }

    @Override
    public void enfiler(T x) {
        Fap.insereQueue(x);
    }

    @Override
    public T defiler() {
        if (estVide()) {
            throw new RuntimeException("FAP vide");
        }

        // trouver le minimum avec l'iterateur
        Iterateur<T> it = Fap.iterateur();
        T min = it.prochain();
        while (it.aProchain()) {
            T courant = it.prochain();
            if (courant.compareTo(min) < 0) {
                min = courant;
            }
        }

        // supprimer le minimum avec un second iterateur
        it = Fap.iterateur();
        while (it.aProchain()) {
            T courant = it.prochain();
            if (courant.compareTo(min) == 0) {
                it.supprime();
                break;
            }
        }

        return min;
    }
} 