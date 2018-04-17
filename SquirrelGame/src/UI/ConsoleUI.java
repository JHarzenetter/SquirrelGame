package UI;

import Data.BoardView;

public class ConsoleUI implements UserInterface{

    public String render(BoardView view){
        String s = "";
        for(int i = 0; i< view.getSize().getX(); i++){
            for(int k = 0; k< view.getSize().getY(); k++){
                s += view.getEntityType(i,k).getWert();
            }
            s= s + "\n";
        }
        return s;
    }

    @Override
    public MoveDirection getCommand() {
        if(UserInterface.read() == 1)
            return MoveDirection.Left;
        if(UserInterface.read() == 2)
            return MoveDirection.Up;
        if(UserInterface.read() == 3)
            return MoveDirection.Right;
        if(UserInterface.read() == 4)
            return MoveDirection.Down;
        return MoveDirection.None;
    }
}
