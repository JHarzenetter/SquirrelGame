package Data;

import BotAPI.ControllerContext;
import UI.MoveDirection;

import java.util.LinkedList;

import static java.lang.Math.PI;

public class MiniSquirrelBot extends MiniSquirrel{

    private MiniSquirrelBot mini;
    private ControllerContextImplMini contextImplMini;
    private int impactRadius;

    protected MiniSquirrelBot(int energy, int x, int y , MasterSquirrel masterSquirrel , EntityContext context, MiniSquirrelBot mini) {
        super(energy, x, y,masterSquirrel);
        this.mini = mini;
        contextImplMini = new ControllerContextImplMini(context);
    }

    @Override
    public void nextStep(EntityContext context) {

    }

    public void impolde(){
        double impactArea = impactRadius*impactRadius*PI;
        LinkedList<Entity> surrounding = new LinkedList<>();
        int index = 0;
        int sum = 0;

        for (int i = 0;i <= mini.getPlace().getX() + impactRadius; i++) {
            for (int j = 1;j <=mini.getPlace().getY() + impactRadius; j++) {
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

        ControllerContextImplMini(EntityContext context){
            this.context = context;
        }

        @Override
        public XY getViewLowerLeft() {
            return new XY(mini.getPlace().getX()-10, mini.getPlace().getY()+10);
        }

        @Override
        public XY getViewUpperRight() {
            return new XY(mini.getPlace().getX()+10, mini.getPlace().getY()-10);
        }

        @Override
        public EntityType getEntityAt(XY xy) {
            return context.getEntityType(xy);
        }

        @Override
        public Entity getEntity(XY xy){return context.getEntity(xy);}

        @Override
        public void move(MoveDirection direction) {

        }

        @Override
        public void spawnMiniBot(XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return getEnergy();
        }
    }
}
