package Data;

import BotAPI.ControllerContext;
import UI.MoveDirection;

public class MasterSquirrelBot extends MasterSquirrel{

    private ControllerContextImplMaster context;
    private final int viewSize = 15;

    protected MasterSquirrelBot(int x, int y, EntityContext context) {
        super(x, y);
        this.context = new ControllerContextImplMaster(this,context);
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
            return new XY(bot.getPlace().getX()-viewSize,bot.getPlace().getY()+viewSize);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(bot.getPlace().getX()+viewSize,bot.getPlace().getY()-viewSize);
        }

        @Override
        public XY locate() {
            return null;
        }

        @Override
        public XY directionOfMaster() {
            return bot.getPlace();
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public Entity getEntity(XY xy) {
            return null;
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
        public void implode(int impactRadius) throws Exception {
            throw new Exception();
        }

        @Override
        public int getEnergy() {
            return bot.getEnergy();
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

        @Override
        public boolean isMine(XY xy) {
            return checkEntitie(getEntity(xy));
        }
    }
}
