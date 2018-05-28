package UI;

import Data.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class Launcher extends Application {

    private static Timer timerGame = new Timer();
    private static Timer timerInput = new Timer();
    private static Logger logger = Logger.getLogger("Launcher");
    static BoardConfig bc = new BoardConfig();
    static Board b = new Board(bc);
    static State s = new State(b);

    public static void main(String[] args) {

        Game g = new GameImpl(s,b);

        switch(args[0]){
            case "old":
                logger.info("Version: old!");
                g.run();
                break;
            case "fps":
                logger.info("Version: fps!");
                g.render();
                startGame(g);
                inputTimer(g);
                break;
            case "gui":
                logger.info("Version: gui!");
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

        BoardConfig boardConfig = new BoardConfig();
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());
        final Game game = new GameImplFxUI(s,b,fxUI);

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));
        primaryStage.show();

        startGame(game);
        inputTimer(game);
    }
}