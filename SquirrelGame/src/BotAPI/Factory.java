package BotAPI;

public class Factory implements BotControllerFactory {

    //TODO: Bot KI schreiben!

    @Override
    public BotController createMasterBotController() {
        return new RndController();
    }

    @Override
    public BotController createMiniBotController() {
        return new RndController();
    }
}
