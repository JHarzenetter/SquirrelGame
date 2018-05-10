package Data;

import UI.MoveDirection;

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
            setMoveDirection(MoveDirection.none);
        }
        if(getEnergy() < 0){
            updateEnergy(-getEnergy());
        }
    }
}
