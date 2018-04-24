package UI;

import Data.XY;

import java.util.Random;

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

    public static MoveDirection getRandCommand() {
        int r = new Random().nextInt(8);

        switch (r){
            case 0:
                return MoveDirection.Up;
            case 1:
                return MoveDirection.UpRight;
            case 2:
                return MoveDirection.Right;
            case 3:
                return MoveDirection.DownRight;
            case 4:
                return MoveDirection.Down;
            case 5:
                return MoveDirection.DownLeft;
            case 6:
                return MoveDirection.Left;
            case 7:
                return MoveDirection.UpLeft;
            default:
                return MoveDirection.None;
        }
    }
}
