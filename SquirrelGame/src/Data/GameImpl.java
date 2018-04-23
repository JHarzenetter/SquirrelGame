package Data;

import UI.ConsoleUI;
import UI.UserInterface;

public class GameImpl extends Game {

    private BoardView bv;
    private UserInterface ui;
    private FlattenedBoard fb;

    public GameImpl(State state) {
        super(state);
        fb = new FlattenedBoard(state.getB());
        ui = new ConsoleUI();
        bv = fb;
    }

    @Override
    protected void update() {
        state.update();
        fb.updateBoard();
        bv=fb;
    }

    public void render(){
        ui.render(bv);
    }

    public void processInput(){
        for(int i=0; i<fb.getSize().getX(); i++){
            for(int k=0; k<fb.getSize().getY(); k++){
                if(fb.getEntityType(i,k) == EntityType.MasterSquirrel){
                    ((Character)fb.getFB()[i][k]).setMoveDirection(ui.getCommand());
                }
                if(fb.getEntityType(i,k) == EntityType.BadBeast){
                    ((Character) fb.getFB()[i][k]).setMoveDirection(ui.getRandCommand());
                }
                if(fb.getEntityType(i,k) == EntityType.Goodbeast){
                    ((Character) fb.getFB()[i][k]).setMoveDirection(ui.getRandCommand());
                }
                if(fb.getEntityType(i,k) == EntityType.MiniSquirrel){

                }
            }
        }
    }


}
