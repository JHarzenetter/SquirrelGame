package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.ControllerContext;

public class MiniSquirrelBot extends Squirrel implements BotController, BotControllerFactory {

    protected MiniSquirrelBot(int ID, int energy, int x, int y) {
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
