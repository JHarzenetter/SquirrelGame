package Data;

import UI.UserInterface;

public abstract class Game{
    protected State state;
    protected Board board;
    protected UserInterface ui;
    private long FPS;

    public Game(State state , Board board){
        this.state = state;
        this.board = board;
        FPS = 100;
    }

    public void run() {
           while(true){
               render();
               processInput();
               update();
           }
    }

    public abstract void update();

    public abstract void processInput();

    public void render(){ui.render(state.flattenedBoard());}
}
