package BotImpl;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;

public class Factory2 implements BotControllerFactory {
    @Override
    public BotController createMasterBotController() {
        return new RndController();
    }

    @Override
    public BotController createMiniBotController() {
        return new RndController();
    }
}
