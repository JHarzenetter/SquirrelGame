package Data;

import BotAPI.ControllerContext;

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
        contextImplMini.implode(impactRadius);
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
            if(mini.getPlace().getX()-viewSize >= 0 && mini.getPlace().getY()+viewSize <= context.getSize().getY()){
                return new XY(mini.getPlace().getX()-viewSize,mini.getPlace().getY()+viewSize);
            } else if(mini.getPlace().getX()-viewSize < 0 && mini.getPlace().getY()+viewSize <= context.getSize().getY()) {
                return new XY(0,mini.getPlace().getY()+viewSize);
            } else if(mini.getPlace().getX()-viewSize >= 0 && mini.getPlace().getY()+viewSize > context.getSize().getY()){
                return new XY(mini.getPlace().getX()-viewSize,context.getSize().getY());
            } else {
                return new XY(0, context.getSize().getY());
            }
        }

        @Override
        public XY getViewUpperRight() {
            if(mini.getPlace().getX()+viewSize <= context.getSize().getX() && mini.getPlace().getY()-viewSize >= 0){
                return new XY(mini.getPlace().getX()+viewSize,mini.getPlace().getY()-viewSize);
            } else if(mini.getPlace().getX()+viewSize <= context.getSize().getX() && mini.getPlace().getY()-viewSize < 0) {
                return new XY(mini.getPlace().getX()+viewSize,0);
            } else if(mini.getPlace().getX()+viewSize > context.getSize().getX() && mini.getPlace().getY()-viewSize >= 0){
                return new XY(context.getSize().getX(),mini.getPlace().getY()-viewSize);
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
            if(master.getPlace().getX() < getPlace().getX() && master.getPlace().getY() < getPlace().getY()){
                return new XY(-1,-1); // NW
            }
            if(master.getPlace().getX() == getPlace().getX() && master.getPlace().getY() < getPlace().getY()){
                return new XY(0,1); // N
            }
            if(master.getPlace().getX() > getPlace().getX() && master.getPlace().getY() < getPlace().getY()){
                return new XY(1,1); // NO
            }
            if(master.getPlace().getX() > getPlace().getX() && master.getPlace().getY() == getPlace().getY()){
                return new XY(1,0); // O
            }
            if(master.getPlace().getX() > getPlace().getX() && master.getPlace().getY() > getPlace().getY()){
                return new XY(1,-1); // SO
            }
            if(master.getPlace().getX() == getPlace().getX() && master.getPlace().getY() > getPlace().getY()){
                return new XY(0,-1); // S
            }
            if(master.getPlace().getX() < getPlace().getX() && master.getPlace().getY() > getPlace().getY()){
                return new XY(-1,1); // SW
            }
            if(master.getPlace().getX() < getPlace().getX() && master.getPlace().getY() == getPlace().getY()){
                return new XY(-1,0); // W
            }
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
        public void move(XY direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public void implode(int impactRadius){
            if(!(impactRadius >= 2 && impactRadius <=10)){
                return;
            }
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

                switch (getEntityAt(eA[i].getPlace())){
                    case WALL: //W
                        break;
                    case BAD_BEAST: //BB
                    case BAD_PLANT: //BP
                        if(-eA[i].getEnergy()>= energyLoss){
                            eA[i].updateEnergy(energyLoss);
                        }else{
                            eA[i].updateEnergy(-eA[i].getEnergy());
                            contextImplMini.context.killAndReplace(eA[i]);
                        }
                        break;
                    case GOOD_BEAST: //GB
                    case GOOD_PLANT: //GP
                    case MINI_SQUIRREL: //MiS
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
                    case MASTER_SQUIRREL: //MaS
                        if(eA[i] == master){
                            break;
                        } else {
                            eA[i].updateEnergy(-energyLoss);
                            break;
                        }
                    default:
                        break;
                }
            }
            master.updateEnergy(sum);
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
