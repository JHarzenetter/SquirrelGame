package BotAPI;

import UI.MoveDirection;

public class RndController implements BotController {

    @Override
    public void nextStep(ControllerContext view) {
        view.move(MoveDirection.getRandCommand());
    }
}
