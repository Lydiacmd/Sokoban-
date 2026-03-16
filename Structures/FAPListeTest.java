import Structures.*;

public class FAPListeTest{

    public static void main(String[] args) {
        FAPCouple<String,Integer> fap = new FAPCouple<String,Integer>();

        fap.enfiler(new Couple<>("chat", 3));
        fap.enfiler(new Couple<>("chien", 1));
        fap.enfiler(new Couple<>("oiseau", 2));
    
        System.out.println("Séquence initiale :");
        for (int i =0; i <3 ; i ++){
            Couple<String,Integer> n = fap.defiler();
            System.out.println(""+n.valeur);
        }
        System.out.println("\n");

    }
}