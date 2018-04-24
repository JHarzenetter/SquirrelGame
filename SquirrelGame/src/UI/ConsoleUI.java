package UI;

import Data.BoardView;
import Data.XY;

import java.util.Scanner;

public class ConsoleUI implements UserInterface{

    public void render(BoardView view){
        String s = "";
        for(int i = 0; i< view.getSize().getY(); i++){
            for(int k = 0; k< view.getSize().getX(); k++){
                s += view.getEntityType(new XY(k,i)).getWert();
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
}
