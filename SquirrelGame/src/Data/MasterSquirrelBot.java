package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.ControllerContext;
import Data.EntityContext;
import Data.Squirrel;
import UI.MoveDirection;

public class MasterSquirrelBot extends MasterSquirrel{

    protected MasterSquirrelBot(int x, int y) {
        super(x, y);
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    private class ControllerContextImplMaster implements ControllerContext {

        private MasterSquirrelBot bot;
        private EntityContext context;

        ControllerContextImplMaster(MasterSquirrelBot bot, EntityContext context) {
            this.bot = bot;
            this.context = context;
        }

        @Override
        public XY getViewLowerLeft() {
            return null;
        }

        @Override
        public XY getViewIpperRight() {
            return null;
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public void move(MoveDirection direction) {
            if (direction.getDirection().getX() - bot.getPlace().getX() <= 1 && direction.getDirection().getY() - bot.getPlace().getY() <= 1) {
                bot.setMoveDirection(direction);
            } else {
                bot.setMoveDirection(MoveDirection.none);
            }
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return bot.getEnergy();
        }
    }
}
