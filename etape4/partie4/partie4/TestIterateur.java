import Structures.*;

public class TestIterateur {

    public static void main(String[] args) {
        SequenceTableau<Integer> seq = new SequenceTableau<Integer>();

        seq.ajoute(0, 5);
        seq.ajoute(1, 2);
        seq.ajoute(2, 4);
        seq.ajoute(3, 8);
        seq.ajoute(4, 9);    
        
        System.out.println("Séquence initiale :");
        for (int i =0; i <= seq.taille()-1 ; i ++){
            int n = seq.lis(i);
            System.out.println(""+n);
        }
        System.out.println("\n");


        Iterateur<Integer> it = seq.iterateur();

        while(it.aProchain()){
            int n = it.prochain();
            if (n == 8 ){
                it.supprime();
            }
        }

        System.out.println("Séquence apres iteration :");        
        for (int i =0; i <= seq.taille()-1 ; i ++){
            int n = seq.lis(i);
            System.out.println(""+n);
        }
        System.out.println("\n");

}
}