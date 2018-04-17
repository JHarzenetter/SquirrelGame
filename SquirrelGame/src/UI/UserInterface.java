package UI;

import java.util.Scanner;
import Data.BoardView;

public interface UserInterface {

    static int read(){				//read playerinput
        Scanner s = new Scanner(System.in);
        int read = s.nextInt();
        return read;
    }

    String render(BoardView view);

    MoveDirection getCommand();

}
