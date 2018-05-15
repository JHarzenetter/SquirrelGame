package UI;

import Data.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {

    private static Timer timerGame = new Timer();
    static BoardConfig bc = new BoardConfig();
    static Board b = new Board(bc);
    static State s = new State(b);

    public static void main(String[] args) { //TODO: schalter besser einbauen

        Game g = new GameImpl(s,b);
        int i =1;

        if(i==0){ // first mode
            g.run();
        } else if(i == 1){ // timer mode
            g.render();
            startGame(g);
            while(true){
                g.processInput();
            }
        }else if(i == 2){
            Application.launch(args); // javafx mode
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
    }
}