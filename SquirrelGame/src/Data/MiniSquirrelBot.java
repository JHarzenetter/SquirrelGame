package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;

public class MiniSquirrelBot extends Squirrel implements BotController, BotControllerFactory {

    protected MiniSquirrelBot(int ID, int energy, int x, int y) {
        super(ID, energy, x, y);
    }

    @Override
    public void nextStep(EntityContext context) {

    }
}
