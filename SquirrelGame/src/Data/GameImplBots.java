package Data;

import UI.FxUI;

public class GameImplBots extends Game {

    private MasterSquirrel player[];

    public GameImplBots(State state, Board board, FxUI fxUI) {
        super(state, board);
        ui = fxUI;
        player = board.getPlayer();
    }

    @Override
    public void run(){
        while(true){
            for(int i=0; i<player.length; i++){
                player[i].nextStep(board.flattened());
            }
        }
    }

    @Override
    public void update() {
        state.update();
    }

    @Override
    public void processInput() {

    }
}
