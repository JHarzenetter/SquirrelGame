package Data;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    public HandOperatedMasterSquirrel(int ID, int x, int y) {
        super(ID, x, y);
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() > 0){
            System.out.println("Stunned for "+getWait()+" rounds");
            setWait(getWait()-1);
        } else {
            context.tryToMove(this , getMD().getDirection());
        }
        if(getEnergy() < 0){
            updateEnergy(-getEnergy());
        }
        System.out.println("MasterS Engery : " + this.getEnergy());
    }
}
