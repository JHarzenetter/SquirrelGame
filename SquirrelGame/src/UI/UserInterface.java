package UI;

import Commands.Command;
import Data.BoardView;

public interface UserInterface {
    void render(BoardView view);
    Command getCommand();
}
