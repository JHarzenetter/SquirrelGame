package Data;

import BotAPI.ControllerContext;
import UI.MoveDirection;

public class MiniSquirrelBot extends MiniSquirrel{

    private EntityContext context;
    private MiniSquirrelBot mini;

    protected MiniSquirrelBot(int energy, int x, int y , MasterSquirrel MsID , EntityContext context, MiniSquirrelBot mini) {
        super(energy, x, y,MsID);
        this.context = context;
        this.mini = mini;
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    private class ControllerContextImplMini implements ControllerContext {
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
            return null;
        }

        @Override
        public void move(MoveDirection direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }
    }
}
