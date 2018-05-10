package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import Data.EntityContext;
import Data.Squirrel;

public class MasterSquirrelBot extends Squirrel implements BotController, BotControllerFactory {

    protected MasterSquirrelBot(int ID, int energy, int x, int y) {
        super(ID, energy, x, y);
    }

    @Override
    public void nextStep(EntityContext context) {

    }
}
