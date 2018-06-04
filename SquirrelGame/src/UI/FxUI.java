package UI;

import Commands.Command;
import Commands.GameCommandType;
import Data.BoardView;
import Data.XY;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class FxUI extends Scene implements UserInterface {

    private static final int CELL_SIZE = 10;
    private final Canvas boardCanvas;
    private final Label msgLabel;
    private static Command command;

    public FxUI(Parent parent, Canvas boardCanvas, Label msgLabel) {
        super(parent);
        this.boardCanvas = boardCanvas;
        this.msgLabel = msgLabel;
        setCommand("NONE");
    }

    public static FxUI createInstance(XY boardSize) {
        Canvas boardCanvas = new Canvas(boardSize.getX() * CELL_SIZE, boardSize.getY() * CELL_SIZE);
        Label statusLabel = new Label();
        VBox top = new VBox();
        top.getChildren().add(boardCanvas);
        top.getChildren().add(statusLabel);
        statusLabel.setText("Squirrel Game!");

        final FxUI fxUI = new FxUI(top, boardCanvas, statusLabel);
        fxUI.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                //System.out.println("Es wurde folgende Taste gedrückt: " + event.getCode() + " bitte behandeln!");
                switch(event.getCode()){
                    case UP:
                        setCommand("UP");
                        break;
                    case DOWN:
                        setCommand("DOWN");
                        break;
                    case LEFT:
                        setCommand("LEFT");
                        break;
                    case RIGHT:
                        setCommand("RIGHT");
                        break;
                    default:
                        setCommand("NONE");
                        break;
                }
            }
        });
        return fxUI;
    }

    @Override
    public void render(BoardView view) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                repaintBoardCanvas(view);
            }
        });
    }

    public static void setCommand(String c){
        command = new Command(GameCommandType.valueOf(c), new Object[]{});
    }

    @Override
    public Command getCommand() {
        return command;
    }

    private void repaintBoardCanvas(BoardView view) {
        GraphicsContext gc = boardCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0, boardCanvas.getWidth(), boardCanvas.getHeight());
        XY viewSize = view.getSize();

        for(int i=0; i<viewSize.getX();i++){
            for(int k=0; k<viewSize.getY();k++){
                switch (view.getEntityType(new XY(i,k))){
                    case BAD_PLANT:
                        gc.setFill(Color.RED);
                        gc.fillRect(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case GOOD_PLANT:
                        gc.setFill(Color.GREEN);
                        gc.fillRect(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case BAD_BEAST:
                        gc.setFill(Color.RED);
                        gc.fillOval(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case GOOD_BEAST:
                        gc.setFill(Color.GREEN);
                        gc.fillOval(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case MASTER_SQUIRREL:
                        gc.setFill(Color.BLUE);
                        gc.fillRect(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case MINI_SQUIRREL:
                        gc.setFill(Color.BLUE);
                        gc.fillOval(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case WALL:
                        gc.setFill(Color.ORANGE);
                        gc.fillRect(i*CELL_SIZE,k*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                        break;
                    case NONE:
                        break;
                }
            }
        }
    }


    @Override
    public void message(final String msg) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                msgLabel.setText(msg);
            }
        });
    }

}
