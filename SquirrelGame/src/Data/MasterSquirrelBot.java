package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.ControllerContext;
import Logs.ControllerContextProxy;
import UI.MoveDirection;

public class MasterSquirrelBot extends MasterSquirrel{

    private BotController controller;
    private final int viewSize = 15;

    protected MasterSquirrelBot(int x, int y, BotControllerFactory factory) {
        super(x, y);
        controller = factory.createMasterBotController();
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() > 0){
            setWait(getWait()-100);
        } else {
            ControllerContext contextImplMaster = new ControllerContextImplMaster(this,context);
            controller.nextStep(new ControllerContextProxy(contextImplMaster,false).proxy());
            context.tryToMove(this , getMD().getDirection());
        }
        if(getEnergy() < 0){
            updateEnergy(-getEnergy());
        }
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
        } // TODO: spielfeld begrenzung beachten (mini auch)

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
            return null;
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public Entity getEntity(XY xy) {
            return context.getEntity(xy);
        }

        @Override
        public void move(MoveDirection direction) { // TODO: tryMove einfügen
            if (direction.getDirection().getX() - bot.getPlace().getX() <= 1 && direction.getDirection().getY() - bot.getPlace().getY() <= 1) {
                bot.setMoveDirection(direction);
            } else {
                bot.setMoveDirection(MoveDirection.none);
            }
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            context.getBoard().addEntity(bot.createMiniBot(direction,energy,context));
        }

        @Override
        public void implode(int impactRadius) throws Exception { //TODO: runtime exception!
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