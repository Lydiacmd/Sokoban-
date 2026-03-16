package Modele;


public enum Direction{
        HAUT(-1,0), BAS(1,0), GAUCHE(0,-1), DROITE(0,1);

        public final int deltaligne;
        public final int deltacolonne;

        Direction(int deltaligne, int deltacolonne){
            this.deltaligne = deltaligne;
            this.deltacolonne = deltacolonne;
        }
}


    