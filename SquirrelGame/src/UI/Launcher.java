package UI;

import Data.*;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class Launcher extends Application {

    private static Timer timerGame = new Timer();
    private static Timer timerInput = new Timer();
    static BoardConfig bc = new BoardConfig();
    static Board b = new Board(bc);
    static State s = new State(b);
    static GameImpl g = new GameImpl(s,b);
    static ConsoleUI cui = new ConsoleUI();

    public static void main(String[] args) {

        int i=2;

        if(i==0){ // first mode
            g.run();
        } else if(i == 1){ // timer mode
            g.render();
            startGame();
            inputTimer();
        }else if(i == 2){
            Application.launch(args); // javafx mode
        }
    }

    private static void startGame(){
        //System.out.println(System.nanoTime());
        timerGame.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                g.update();
                g.render();
            }
        } , 0,3000); // 1000 == sec
    }

    private static void inputTimer(){
        timerInput.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run() {
                cui.inputLoop(g);
                g.render();
            }
        } , 10 , 1000);
    }

    @Override
    public void start(Stage primaryStage) {

        BoardConfig boardConfig = new BoardConfig();
        FxUI fxUI = FxUI.createInstance(boardConfig.getSize());

        primaryStage.setScene(fxUI);
        primaryStage.setTitle("Diligent Squirrel");
        fxUI.getWindow().setOnCloseRequest(evt -> System.exit(-1));
        primaryStage.show();

        startGame();
    }
}