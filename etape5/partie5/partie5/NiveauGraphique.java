import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;

public class NiveauGraphique extends JComponent {

    Jeu jeu;
    Image imgMur;
    Image imgPousseur;
    Image imgBut;
    Image imgCaisse;
    Image imgCaisseBut;
    Image imgSol;

    NiveauGraphique(Jeu game){
        this.jeu = game;

        // car read ne prend pas de String
        try{
        imgMur = ImageIO.read(new FileInputStream("Images/Mur.png"));
        imgPousseur = ImageIO.read(new FileInputStream("Images/Pousseur.png"));
        imgBut = ImageIO.read(new FileInputStream("Images/But.png"));
        imgCaisse = ImageIO.read(new FileInputStream("Images/Caisse.png"));
        imgCaisseBut = ImageIO.read(new FileInputStream("Images/Caisse_sur_but.png"));
        imgSol = ImageIO.read(new FileInputStream("Images/Sol.png"));
        } catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
    }

    @Override
    public void paintComponent(Graphics g){

        Niveau tmp = jeu.niveau();
        Graphics2D drawable = (Graphics2D) g;

        //Calculer la Hauteur et Largeur d'une case 
        int largeur = getWidth()/tmp.colonnes();
        int hauteur = getHeight()/tmp.lignes();

        // Efface tout les fentre pour ca pas utiliser Largeur/ Hauteur 
		drawable.clearRect(0, 0, getWidth(), getHeight());

        for (int i = 0; i < tmp.lignes(); i++) {
            for (int j = 0; j < tmp.colonnes(); j++) {


                 switch (tmp.getCase(i,j)) {
                        case '#':
                            drawable.drawImage(imgMur, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case '@':
                            drawable.drawImage(imgPousseur, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case '.':
                            drawable.drawImage(imgBut, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case '$':
                            drawable.drawImage(imgCaisse, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case '*':
                            drawable.drawImage(imgCaisseBut, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case ' ':
                            drawable.drawImage(imgSol, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        case '+':
                            drawable.drawImage(imgPousseur, j*largeur, i*hauteur, largeur, hauteur, null);
                            break;
                        default:
                            break;
                    }



            }
        }

       
        



    }

    
}