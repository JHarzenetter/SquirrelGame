package UI;

import Data.BoardView;

public interface UserInterface {

    String render(BoardView view);

    MoveDirection getCommand();

    MoveDirection getRandCommand();

    int read();

}
