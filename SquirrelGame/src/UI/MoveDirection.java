package UI;

import Data.XY;

public enum MoveDirection {
    Left(new XY(-1,0)) , Right(new XY(1,0)) , Up(new XY(0,-1)) , Down(new XY(0,1)) , None(new XY(0,0)) ,
    UpLeft(new XY(-1,-1)) , UpRight(new XY(1,-1)) , DownLeft(new XY(-1,1)) , DownRight(new XY(1,1));

    public final XY direction;

    MoveDirection(XY wert){
        direction = wert;
    }

    public XY getDirection() {
        return direction;
    }
}
