package Data;

import BotAPI.BotController;
import BotAPI.BotControllerFactory;
import BotAPI.ControllerContext;
import Logs.ControllerContextProxy;

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
            if(bot.getPlace().getX()-viewSize >= 0 && bot.getPlace().getY()+viewSize <= context.getSize().getY()){
                return new XY(bot.getPlace().getX()-viewSize,bot.getPlace().getY()+viewSize);
            } else if(bot.getPlace().getX()-viewSize < 0 && bot.getPlace().getY()+viewSize <= context.getSize().getY()) {
                return new XY(0,bot.getPlace().getY()+viewSize);
            } else if(bot.getPlace().getY()+viewSize > context.getSize().getY() && bot.getPlace().getX()-viewSize >= 0){
                return new XY(bot.getPlace().getX()-viewSize,context.getSize().getY());
            } else {
                return new XY(0,context.getSize().getY());
            }
        }

        @Override
        public XY getViewUpperRight() {
            if(bot.getPlace().getY()-viewSize >= 0 && bot.getPlace().getX()+viewSize <= context.getSize().getX()){
                return new XY(bot.getPlace().getX()+viewSize,bot.getPlace().getY()-viewSize);
            } else if(bot.getPlace().getY()-viewSize < 0 && bot.getPlace().getX()+viewSize <= context.getSize().getX()) {
                return new XY(bot.getPlace().getX()+viewSize,0);
            } else if(bot.getPlace().getX()+viewSize > context.getSize().getX() && bot.getPlace().getY()-viewSize >= 0){
                return new XY(context.getSize().getX(),bot.getPlace().getY()-viewSize);
            } else {
               return new XY(context.getSize().getX(),0);
            }
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
        public void move(XY direction) {
            if (direction.getX() - bot.getPlace().getX() <= 1 && direction.getY() - bot.getPlace().getY() <= 1) {
                context.tryToMove(bot,direction);
            } else {
                context.tryToMove(bot,XY.ZERO_ZERO);
            }
        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {
            context.getBoard().addEntity(bot.createMiniBot(direction,energy,context));
        }

        @Override
        public void implode(int impactRadius){
            throw new RuntimeException("You are not allowed to implode!");
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