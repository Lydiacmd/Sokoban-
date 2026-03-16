

package Modele;

import java.util.Random;

public class AleatoireCoupAi {
    Random randomDir = new Random();

    
    public Direction retournCoupAi(Niveau nv){
        Direction dir = Direction.values()[randomDir.nextInt(4)];
        return dir;
    }
}