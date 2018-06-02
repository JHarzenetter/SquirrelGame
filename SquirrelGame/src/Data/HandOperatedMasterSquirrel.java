package Data;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    public HandOperatedMasterSquirrel(int x, int y) {
        super(x, y);
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() > 0){
            System.out.println("Stunned for "+getWait()+" rounds");
            setWait(getWait()-100);
        } else {
            context.tryToMove(this , getMD());
            setMoveDirection(XY.ZERO_ZERO);
        }
        if(getEnergy() < 0){
            updateEnergy(-getEnergy());
        }
    }
}
