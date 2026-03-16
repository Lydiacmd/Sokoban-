package Structures;

public class FAPCouple<V, P extends Comparable<P>> implements FAP<Couple<V,P>> {

    private SequenceListe<Couple<V,P>> Fap;

    public FAPCouple() {
        Fap = new SequenceListe<Couple<V,P>>();
    }

    @Override
    public boolean estVide() {
        return Fap.estVide();
    }

    @Override
    public void enfiler(Couple<V,P> x) {
        Fap.insereQueue(x);
    }

    @Override
    public Couple<V,P> defiler() {
        if (estVide()) {
            throw new RuntimeException("FAP vide");
        }

        // trouver le minimum avec l'iterateur
        Iterateur<Couple<V,P>> it = Fap.iterateur();
        Couple<V,P> min = it.prochain();
        while (it.aProchain()) {
            Couple<V,P> courant = it.prochain();
            if (courant.compareTo(min) < 0) {
                min = courant;
            }
        }

        // supprimer le minimum avec un second iterateur
        it = Fap.iterateur();
        while (it.aProchain()) {
            Couple<V,P> courant = it.prochain();
            if (courant.compareTo(min) == 0) {
                it.supprime();
                break;
            }
        }

        return min;
    }
}