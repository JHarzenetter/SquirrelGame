package BotAPI;

import Data.XYsupport;

public class RndController implements BotController {

    @Override
    public void nextStep(ControllerContext view) {
        view.move(XYsupport.getRandCommand());
    }
}
