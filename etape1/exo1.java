import java.util.Scanner;

/*Classe utile a lire des entrées du clavier ou un fichier 

*/

class Essai_Scanner {
    public static void main(String [] args) {
        Scanner my_scanner;
        String ligne;

        my_scanner = new Scanner(System.in);           // Le scanner lit depuis le clavier
        System.out.println("Saisissez une ligne");     
        try{
            ligne = my_scanner.nextLine();
            System.out.println("Vous avez saisi la ligne : " + ligne);
        } catch (NoSuchElementException e){
            System.out.println("Aucune ligne saisie");
        }
    }
}