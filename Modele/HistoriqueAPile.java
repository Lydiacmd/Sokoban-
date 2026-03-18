package Modele;
import java.util.ArrayDeque;
import java.util.Deque;

public class HistoriqueAPile {

    private Deque<Coup> passe = new ArrayDeque<>();   // tout les coups deja jouer 
    private Deque<Coup> futur = new ArrayDeque<>();   // les coups annuler 

    public void faire(Coup c) {
        passe.push(c);
        futur.clear();    // A chaque this coup vider le future 

    }

    public void annuler() {
        if (!passe.isEmpty()) {
            Coup c = passe.pop();
            c.annulerCoup((Niveau)this);
            futur.push(c);
        }
    }

     public void refaire() {
        if (!futur.isEmpty()) {
            Coup c = futur.pop();   // reprendre le dernier coup fait 
            c.executer((Niveau)this);
            passe.push(c);
        }
    }

    public Coup dernierCoup() {
    return passe.peek();
}

    public Coup dernierCoupFutur() {
        return futur.peek();
    }

    public boolean peutAnnuler() { return !passe.isEmpty(); }
    public boolean peutRefaire() { return !futur.isEmpty(); }


}