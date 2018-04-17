package Data;

import UI.ConsoleUI;
import UI.UserInterface;

public class GameImpl extends Game {

    private State state;
    private BoardView bv;
    private UserInterface ui;
    private FlattenedBoard fb;
    private Board b;

    public GameImpl(State state) {
        super(state);
        fb = new FlattenedBoard(b);
        ui = new ConsoleUI();
        bv = fb;
    }

    @Override
    protected void update() {

    }

    public void render(){
        ui.render(bv);
    }

    public void processInput(){

    }


}
