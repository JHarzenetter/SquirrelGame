package Data;

import Commands.Command;
import UI.FxUI;
import UI.MoveDirection;
import java.io.PrintStream;

public class GameImplFxUI extends Game{

    private FlattenedBoard fb;
    private MasterSquirrel player;
    private Command c;
    private PrintStream printer = System.out;

    public GameImplFxUI(State state, Board board, FxUI fxUI) {
        super(state, board);
        ui = fxUI;
        player = board.getPlayer();
    }

    @Override
    public void update() {
        state.update();
    }

    @Override
    public void processInput() {
        if(player instanceof HandOperatedMasterSquirrel){
            while(true){
                player.setMoveDirection(MoveDirection.valueOf(ui.getCommand().getCommandTypeInfo().getName())); //TODO: enscheidung der Inteligenz in contorller auslagern
            }
        }
        if (player instanceof MasterSquirrelBot){
            while(true){
                player.setMoveDirection(MoveDirection.getRandCommand()); //--> bot mode
            }
        }
    }

    private void minispawn(Integer i){
        board.addEntity(player.createMini(i));
        player.setMoveDirection(MoveDirection.none);
    }
}
