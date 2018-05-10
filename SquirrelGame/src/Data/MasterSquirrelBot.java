package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.ControllerContext;
import Data.EntityContext;
import Data.Squirrel;

public class MasterSquirrelBot extends Squirrel implements BotController, BotControllerFactory {

    protected MasterSquirrelBot(int ID, int energy, int x, int y) {
        super(ID, energy, x, y);
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    @Override
    public void nextStep(ControllerContext view) {

    }

    @Override
    public BotController createMasterBotController() {
        return null;
    }

    @Override
    public BotController createMiniBotController() {
        return null;
    }
}
