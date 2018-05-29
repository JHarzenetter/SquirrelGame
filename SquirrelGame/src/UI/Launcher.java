package UI;

import Data.*;
import Logs.SquirrelLogger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {

    private static Timer timerGame = new Timer();
    private static Timer timerInput = new Timer();
    private static SquirrelLogger logger = new SquirrelLogger();
    static String version = "";
    static BoardConfig bc = new BoardConfig();
    static Board b = new Board(bc);
    static State s = new State(b);

    public static void main(String[] args) {

        Game g = new GameImpl(s,b);

        switch(args[0]){
            case "old":
                logger.log.info("OLD Version");
                g.run();
                break;
            case "fps":
                logger.log.info("FPS Version");
                g.render();
                startGame(g);
                inputTimer(g);
                break;
            case "bot":
                version = "bot";
                logger.log.info("BOT Version");
                Application.launch(args);
                break;
            case "gui":
                logger.log.info("GUI Version");
                Application.launch(args);
                break;
        }
    }

    private static void startGame(Game g){
        //System.out.println(System.nanoTime());
        timerGame.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                g.render();
                g.update();
            }
        } , 0,100); // 1000 == sec
    }

    private static void inputTimer(Game g){
        timerInput.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                g.processInput();
                g.render();
            }
        } , 0 , 1000);
    }

    @Override
    public void start(Stage primaryStage) {

        if(version.equals("bot")){
            b = new Board(new BoardConfig(3,0));
            s = new State(b);
        }

        FxUI fxUI = FxUI.createInstance(b.getSize());
        final Game game;

        if(version.equals("bot")){
            game = new GameImplBots(s,b,fxUI);
        } else {
            game = new GameImplFxUI(s,b,fxUI);
        }

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));
        primaryStage.show();

        startGame(game);
        inputTimer(game);
    }
}