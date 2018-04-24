package UI;

import Data.Board;
import Data.GameImpl;
import Data.State;

public class Launcher {
    public static void main(String[] args) {
        Board b = new Board();
        State s = new State(b);
        GameImpl g = new GameImpl(s,b);

        g.run();
    }
}
