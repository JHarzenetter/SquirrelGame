package Data;

import Logs.SquirrelLogger;
import UI.FxUI;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

/**
 * The implementation for BotGameMode
 */
public class GameImplBots extends Game {

    Map <String , List<Integer>> highscores;
    File file;
    Properties properties = new Properties();
    private static Logger log = new SquirrelLogger().log;
    private BoardConfig bc = new BoardConfig("bot");

    public GameImplBots(State state, Board board, FxUI fxUI) {
        super(state, board);
        ui = fxUI;
        highscores = new HashMap();
        for(String s : bc.getBotNames()){
            highscores.put(s,new LinkedList<Integer>(){});
        }

        file = new File("Highscores.properties");
        BufferedInputStream input;

        try {
            input = new BufferedInputStream(new FileInputStream(file));
            properties.load(input);
        } catch (FileNotFoundException e) {
            log.fine("File was not found");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(file.exists()){
            for(String s : bc.getBotNames()){
                String sla = (String) properties.get(s);
                sla = sla.substring(1,sla.indexOf(']'));
                String[] ts = sla.split(",");
                for(int i=0; i<ts.length; i++){
                    highscores.get(s).add(Integer.parseInt(ts[i].trim()));
                }
            }
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
        Properties prop = new Properties();
        log.fine("Highscores safed");
        try{
            for(String s : bc.getBotNames()){
                prop.setProperty(s,highscores.get(s).toString());
            }
            FileOutputStream fout = new FileOutputStream("highscores.properties");
            prop.store(fout,"Highscores");
            fout.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
