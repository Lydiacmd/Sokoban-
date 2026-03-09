import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class scanner_int {
    public static void main(String [] args) {
        boolean condition = false;
        Scanner scanner;
        int nombre;

        while (condition == false){
        scanner = new Scanner(System.in);
        try {
            System.out.println("Veuillez saisir un entier : ");
            nombre = scanner.nextInt();
            condition = true;
            System.out.println("Vous avez saisi l'entier :" + nombre);
        } catch (InputMismatchException e){
            System.out.println("Entier incorrect error(1), veuillez recommencer");
        } catch (NoSuchElementException e){
            System.out.println("Entier incorrect error(2), veuillez recommencer");
        }

        }

    }
}


/* 2BIS !!! 
- A la lecture des fichier faux faire attention mieux vaux tjr utiliser 
un chemain relatifs exmple : 
new FileInputStream("C:\\Users\\Lydia\\Desktop\\tp\\data.txt");     <--- Marche que sur mon pc
new FileInputStream("data.txt");                                    <--- Marche partout si le fichier est dans le meme dossier que le .java
- Les classe Jave et les fichier de données pas dans le meme dossier 
  faut indiquer le chemain relatif exemple :
new FileInputStream("data/data.txt");  <--- si le fichier data.txt 
  On compile comme ce suit :
javac -d bin src/exo1.java
  On exécute comme ce suit :
java -cp bin exo1
  Si le fichier data.txt est dans le dossier src/data/data.txt
  Il faut indiquer le chemain relatif comme suit :
new FileInputStream("src/data/data.txt");
  Sinon Java ne trouvera pas le fichier.
*/