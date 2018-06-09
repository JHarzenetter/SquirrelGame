package Data;

import Logs.SquirrelLogger;
import UI.FxUI;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GameImplBots extends Game {

    Map <String , List<Integer>> highscores;
    private static Logger log = new SquirrelLogger().log;
    private BoardConfig bc = new BoardConfig("bot");

    public GameImplBots(State state, Board board, FxUI fxUI) {
        super(state, board);
        ui = fxUI;
        highscores = new HashMap();
        for(String s : bc.getBotNames()){
            highscores.put(s,new LinkedList<Integer>(){});
        }
    }

    @Override
    public void run(){}

    private void restart() {
        bc = new BoardConfig("bot");
        log.fine("Restart Initiated");
        for(int i=0; i<bc.getBotNames().length; i++){
            highscores.get(bc.getBotNames()[i]).add(board.getPlayer()[i].getEnergy());
        }
        log.info(""+highscores);
        System.out.println(highscores);
        board = new Board(bc);
        state = new State(board);
    }

    @Override
    public void safeHighscores(){

    }

    @Override
    public void update() {
        state.update();
        if(board.getRemainingSteps() == 0){
            restart();
        }
    }

    @Override
    public void processInput() {

    }
}
