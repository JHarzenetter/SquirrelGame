package Data;

import UI.ConsoleUI;
import UI.UserInterface;

public abstract class Game{
    protected State state;

    public Game(State state){
        this.state = state;
    }


    public static void main(String[] args) {
        UserInterface ui;
        ConsoleUI cui = new ConsoleUI();
        State s = new State(new Board());
        FlattenedBoard fb = s.flattenedBoard();
        GameImpl g = new GameImpl(s);

        System.out.println(""+cui.render(fb));
        g.run();
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
