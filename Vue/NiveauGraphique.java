package Vue;
import Modele.*;   
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

public class NiveauGraphique extends JComponent {

    Jeu jeu;
    Image imgMur;
    Image imgPousseur;
    Image imgBut;
    Image imgCaisse;
    Image imgCaisseBut;
    Image imgSol;

    Image[][] imgPousseurAnim; //[4 direction][4 fram]
    
    /* Hash map pour les decalage (dico)
    cle : "ligne,colonne" , valeur = decalag en fraction case
    "0,0" → [0.0, 0.0]   // case (0,0) pas de décalage
    "2,3" → [0.5, 0.0]   // case (2,3) décalée de 0.5 case vers la droite 
     */
    private Map<String, float[]> decalages = new HashMap<>();

    NiveauGraphique(Jeu game){
        this.jeu = game;
        try{
        imgMur = ImageIO.read(Configuration.ouvre("Images/Mur.png"));
        imgPousseur = ImageIO.read(Configuration.ouvre("Images/Pousseur.png"));
        imgBut = ImageIO.read(Configuration.ouvre("Images/But.png"));
        imgCaisse = ImageIO.read(Configuration.ouvre("Images/Caisse.png"));
        imgCaisseBut = ImageIO.read(Configuration.ouvre("Images/Caisse_sur_but.png"));
        imgSol = ImageIO.read(Configuration.ouvre("Images/Sol.png"));

        // Charger les img dans le constructeur 
        imgPousseurAnim = new Image[4][4]; //[4 direction][4 fram]
        for (int i=0;i<4;i++){
            for (int j = 0;j<4;j++){
                imgPousseurAnim[i][j] = ImageIO.read(Configuration.ouvre("Images_/Pousseur_"+i+"_"+j+".png"));
            }
        }
        } catch (Exception e){
            e.printStackTrace();
            Configuration.erreur(e.getMessage());
        }
    }

    // defini le decalage d'une case (dx,dy en fraction de case)
    public void setDecalage(int col,int ligne,float dx,float dy){
        decalages.put(ligne+","+col, new float[]{dx,dy});
    }

    // supprime le decalage d'une case
    public void supprimeDecalage(int col,int ligne){
        decalages.remove(ligne+","+col);
    }

    @Override
    public void paintComponent(Graphics g){

        Niveau tmp = jeu.niveau();
        Graphics2D drawable = (Graphics2D) g;

        //Calculer la Hauteur et Largeur d'une case 
        int largeur = getWidth()/tmp.colonnes();
        int hauteur = getHeight()/tmp.lignes();

        // Fond noir pour eviter le fond blanc par defaut
        drawable.setColor(Color.BLACK);
        drawable.fillRect(0, 0, getWidth(), getHeight());

        // PASSE 1 : fond statique (murs, sol, buts)
        for (int i = 0; i < tmp.lignes(); i++) {
            for (int j = 0; j < tmp.colonnes(); j++) {
                int x = j*largeur;
                int y = i*hauteur;
                switch (tmp.getCase(i,j)) {
                    case '#': drawable.drawImage(imgMur, x, y, largeur, hauteur, null); break;
                    case '.': drawable.drawImage(imgBut, x, y, largeur, hauteur, null); break;
                    case '@': case ' ': drawable.drawImage(imgSol, x, y, largeur, hauteur, null); break;
                    case '$': drawable.drawImage(imgSol, x, y, largeur, hauteur, null); break;
                    case '*': drawable.drawImage(imgBut, x, y, largeur, hauteur, null); break;
                    case '+': drawable.drawImage(imgBut, x, y, largeur, hauteur, null); break;
                }
            }
        }

        // PASSE 2 : elements mobiles avec decalage (pousseur, caisses)
        for (int i = 0; i < tmp.lignes(); i++) {
            for (int j = 0; j < tmp.colonnes(); j++) {
                char c = tmp.getCase(i, j);
                float[] dec = decalages.get(i + "," + j);
                int offsetX = dec != null ? (int)(dec[0] * largeur) : 0;
                int offsetY = dec != null ? (int)(dec[1] * hauteur) : 0;
                int x = j*largeur + offsetX;
                int y = i*hauteur + offsetY;
                switch (c) {
                    case '@': case '+': drawable.drawImage(imgPousseur, x, y, largeur, hauteur, null); break;
                    case '$': drawable.drawImage(imgCaisse, x, y, largeur, hauteur, null); break;
                    case '*': drawable.drawImage(imgCaisseBut, x, y, largeur, hauteur, null); break;
                }
            }
        }
        // PASSE 3 : marques (croix colorées)
        for (int i = 0; i < tmp.lignes(); i++) {
            for (int j = 0; j < tmp.colonnes(); j++) {
                Color marque = tmp.getMarque(i, j);
                if (marque != null) {
                    int x = j * largeur;
                    int y = i * hauteur;
                    drawable.setColor(marque);
                    drawable.setStroke(new BasicStroke(4));
                    drawable.drawLine(x, y, x + largeur, y + hauteur);
                    drawable.drawLine(x + largeur, y, x, y + hauteur);
                }
            }
        }
        System.out.println("Pousseur : " + tmp.lignePousseur() + "," + tmp.colonnePousseur());

    }

    public Image getImageAnim(int direction, int frame){
        return imgPousseurAnim[direction][frame];
    }

    public void setImagePousseur(Image img) {
        imgPousseur = img;
    }

    public void supprimeTousLesDecalages() {
    decalages.clear();
}
}