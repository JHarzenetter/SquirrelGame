package UI;

import Data.Board;
import Data.BoardConfig;
import Data.GameImpl;
import Data.State;

public class Launcher {
    public static void main(String[] args) {
        BoardConfig bc = new BoardConfig();
        Board b = new Board(bc);
        State s = new State(b);
        GameImpl g = new GameImpl(s,b);

        g.run();
    }
}
