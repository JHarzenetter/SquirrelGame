package Data;

import BotAPI.ControllerContext;
import UI.MoveDirection;

import java.util.LinkedList;

import static java.lang.Math.PI;

public class MiniSquirrelBot extends MiniSquirrel{

    private final int viewSize = 10;
    private ControllerContextImplMini contextImplMini;

    protected MiniSquirrelBot(int energy, int x, int y , MasterSquirrel masterSquirrel , EntityContext context) {
        super(energy, x, y,masterSquirrel);
        contextImplMini = new ControllerContextImplMini(context,this);
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    public void implode(int impactRadius){
        double impactArea = impactRadius*impactRadius*PI;
        LinkedList<Entity> surrounding = new LinkedList<>();
        int index = 0;
        int sum = 0;

        for (int i = 0;i <= getPlace().getX() + impactRadius; i++) {
            for (int j = 1;j <= getPlace().getY() + impactRadius; j++) {
                surrounding.add(index,contextImplMini.getEntity(new XY(i,j)));
                index++;
            }
        }

        Entity[] eA = (Entity[]) surrounding.toArray();

        for(int i=0; i<eA.length; i++){

            double distance = getPlace().distanceFrom(eA[i].getPlace());

            int energyLoss = (int) (200*(getEnergy()/impactArea)*(1-distance/impactRadius));

            switch (eA[i].getID()){
                case 0: //W
                    break;
                case 1: //BB
                case 3: //BP
                    if(-eA[i].getEnergy()>= energyLoss){
                        eA[i].updateEnergy(energyLoss);
                    }else{
                       eA[i].updateEnergy(-eA[i].getEnergy());
                       contextImplMini.context.killAndReplace(eA[i]);
                    }
                    break;
                case 2: //GB
                case 4: //GP
                case 6: //MiS
                    if(((MiniSquirrel)eA[i]).getMaster() == master){
                        break;
                    }else{
                        if(eA[i].getEnergy()>= energyLoss){
                            eA[i].updateEnergy(-energyLoss);
                            sum = sum+energyLoss;
                        } else {
                            eA[i].updateEnergy(-eA[i].getEnergy());
                            if(eA[i].getID() == 2 || eA[i].getID() == 4){
                                contextImplMini.context.killAndReplace(eA[i]);
                            }
                            sum = sum+eA[i].getEnergy();
                        }
                        break;
                    }
                case 5: //MaS
                    if(eA[i] == master){
                        break;
                    } else {
                        break;
                    }
                default:
                    break;
            }
        }
        master.updateEnergy(sum);
    }

    private class ControllerContextImplMini implements ControllerContext {

        private EntityContext context;
        private MiniSquirrelBot mini;

        ControllerContextImplMini(EntityContext context,MiniSquirrelBot mini){
            this.context = context;
            this.mini = mini;
        }

        @Override
        public XY getViewLowerLeft() {
            return new XY(mini.getPlace().getX()-viewSize, mini.getPlace().getY()+viewSize);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(mini.getPlace().getX()+viewSize, mini.getPlace().getY()-viewSize);
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
        public Entity getEntity(XY xy){
            return context.getEntity(xy);
        }

        @Override
        public void move(MoveDirection direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public void implode(int impactRadius){
            mini.implode(impactRadius);
        }

        @Override
        public int getEnergy() {
            return mini.getEnergy();
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }

        @Override
        public boolean isMine(XY xy) {
            return false;
        }
    }
}
