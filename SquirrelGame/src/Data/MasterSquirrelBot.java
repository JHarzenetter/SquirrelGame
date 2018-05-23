package Data;

import BotAPI.ControllerContext;
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
        public XY getViewLowerLeft() { // eckpunkte des sichtbereichs
            return new XY(bot.getPlace().getX()-15,bot.getPlace().getY()+15);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(bot.getPlace().getX()+15,bot.getPlace().getY()-15);
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
            context.getBoard().addEntity(bot.createMini(direction,energy));
        }

        @Override
        public int getEnergy() {
            return bot.getEnergy();
        }
    }
}
