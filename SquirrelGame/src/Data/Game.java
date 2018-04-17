package Data;

import UI.ConsoleUI;
import UI.UserInterface;

public abstract class Game{

    private State state;

    public Game(State state){
        this.state = state;
    }

    public static void main(String[] args) {
        FlattenedBoard fb = new FlattenedBoard(new Board());
        UserInterface ui;
        ConsoleUI cui = new ConsoleUI();
        cui.render(fb);
    }

    public void run(){
        while(true){
            render();
            processInput();
            update();
        }
    }

    protected abstract void update();

    protected abstract void processInput();

    protected abstract void render();

}
