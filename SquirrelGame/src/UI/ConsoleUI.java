package UI;

import Data.BoardView;

import java.util.Random;
import java.util.Scanner;

public class ConsoleUI implements UserInterface{

    public void render(BoardView view){
        String s = "";
        for(int i = 0; i< view.getSize().getX(); i++){
            for(int k = 0; k< view.getSize().getY(); k++){
                s += view.getEntityType(i,k).getWert();
            }
            s= s + "\n";
        }
        System.out.println("" + s);
    }

    public int read(){  //read playerinput
        System.out.println("1:Left 2:Up 3:Right 4:Down");
        int read;
        Scanner s = new Scanner(System.in);
        read = s.nextInt();

        if(read < 5 && read > 0){
            return read;
        } else {
            System.out.println("Falsche Eingabe! Erneut versuchen.");
            return read();
        }
    }

    @Override
    public MoveDirection getCommand() {
        int r = read();
        if(r == 1)
            return MoveDirection.Left;
        if(r == 2)
            return MoveDirection.Up;
        if(r == 3)
            return MoveDirection.Right;
        if(r == 4)
            return MoveDirection.Down;
        return MoveDirection.None;
    }

    @Override
    public MoveDirection getRandCommand() {
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
