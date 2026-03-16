package Controleur;
import Vue.*;

public abstract class Animation {
    protected InterfaceGraphique ig;
    
    public Animation(InterfaceGraphique ig) {
        this.ig = ig;
    }
    
    // Chaque sous-classe implémente sa propre logique
    public abstract boolean avance();
}