package Data;

import UI.ConsoleUI;
import UI.UserInterface;

public class GameImpl extends Game {

    private UserInterface ui;
    private FlattenedBoard fb;
    private HandOperatedMasterSquirrel player;

    public GameImpl(State state , Board b) {
        super(state);
        ui = new ConsoleUI();
        player = b.getPlayer();
    }

    @Override
    protected void update() {
        state.update();
    }

    public void render(){
        ui.render(state.flattenedBoard());
        fb = state.flattenedBoard();
    }

    public void processInput(){
       player.setMoveDirection(ui.getCommand());
    }
}
