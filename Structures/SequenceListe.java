package Structures;


public class SequenceListe<T> implements Sequence<T> {
    
    private class noeud {
        T val ;
        noeud suiv;
    }

    

    private noeud tete;
    private noeud queue;

        
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
    public void supprime() {
            if (!peutSupprimer) {
                throw new IllegalStateException("supprime() ne peut pas etre appele ici\n");
            }
            if (avantPrecedent == null) {   // suppression de la tete
                tete = courant;
            } else {
                avantPrecedent.suiv = courant;
                precedent = avantPrecedent;
            }
            // mettre a jour queue si on a supprime le dernier element
            if (courant == null) {
                queue = avantPrecedent;
            }
            peutSupprimer = false;
        }
    }


    @Override
	public void insereQueue(T element) {
        noeud nv = new noeud();
        nv.val = element;           
        if (queue == null) {
            tete = queue = nv;
        } else {
            queue.suiv = nv;
            queue = nv;
        }
    }

    @Override
	 public void insereTete(T element) {
        noeud nv = new noeud();
        nv.val = element;
        if (tete == null) {
            tete = queue = nv;
        } else {
            nv.suiv = tete;     
            tete = nv;
        }
    }

    @Override
	public T extraitTete() {
        if (estVide()) {
            throw new RuntimeException("Sequence vide");
        }
        T resultat = tete.val;
        tete = tete.suiv;
        if (tete == null) {
            queue = null;
        }
        return resultat;
    }

	@Override
	public boolean estVide() {
		return tete == null;
	}

	@Override
	public String toString() {
		String resultat = "SequenceListe [ ";
		boolean premier = true;
		noeud nv = tete;
		while (nv != null) {
			if (!premier)
				resultat += ", ";
			resultat += nv.val;     
            nv = nv.suiv;           
			premier = false;
		}
		resultat += " ]";
		return resultat;
	}




    public Iterateur<T> iterateur(){
        return new IterateurSequenceListe();
    }

    }

