package Data;

import Logs.SquirrelLogger;
import UI.FxUI;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class GameImplBots extends Game {

    Map <String , int[]> highscores;
    private static Logger log = new SquirrelLogger().log;
    private BoardConfig bc = new BoardConfig("bot");

    public GameImplBots(State state, Board board, FxUI fxUI) {
        super(state, board);
        ui = fxUI;
        highscores = new HashMap();
        for(String s : bc.getBotNames()){
            highscores.put(s,new int[]{});
        }
    }

    @Override
    public void run(){}

    private void restart() {
        bc = new BoardConfig("bot");
        log.fine("Restart Initiated");
        for(int i=0; i<bc.getBotNames().length; i++){
            int[] highscoreSafe = new int[highscores.get(bc.getBotNames()[i]).length+1];
            for(int k=0; i<highscoreSafe.length-1; k++){
                highscoreSafe[k] = highscores.get(bc.getBotNames()[i])[k];
            }
            highscoreSafe[highscoreSafe.length-1] = board.getPlayer()[i].getEnergy();
            highscores.put(bc.getBotNames()[i],highscoreSafe);
        }
        log.info(""+highscores);
        System.out.println(highscores);
        board = new Board(bc);
        state = new State(board);
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
