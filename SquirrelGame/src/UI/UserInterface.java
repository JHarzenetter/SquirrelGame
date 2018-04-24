package UI;

import Data.BoardView;

public interface UserInterface {
    void render(BoardView view);
    MoveDirection getCommand();
}
