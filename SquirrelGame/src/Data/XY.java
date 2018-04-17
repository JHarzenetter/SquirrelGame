package Data;

import java.util.Random;

public final class XY {

    private final int x;
    private final int y;
    private Random rand = new Random();

    public XY(int px , int py){
        x=px;
        y=py;
    }

    public int getRandDirection(){return (rand.nextInt(8) + 1);}

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
