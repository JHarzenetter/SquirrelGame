package UI;

import Data.XY;

import java.util.Random;

public enum MoveDirection {
    left(new XY(-1,0)) , right(new XY(1,0)) , up(new XY(0,-1)) , down(new XY(0,1)) ,
    upleft(new XY(-1,-1)) , upright(new XY(1,-1)) , downleft(new XY(-1,1)) , downright(new XY(1,1)),
    none(new XY(0,0));

    public final XY direction;

    MoveDirection(XY wert){
        direction = wert;
    }

    public XY getDirection() {
        return direction;
    }

    public static MoveDirection getRandCommand() {
        int r = new Random().nextInt(8);
        return MoveDirection.values()[r];
    }
}
