package Data;

public class GoodBeast extends Character {

    public GoodBeast(int pID, int x, int y) {
        super(pID,200, x,y);
    }

    public String toString(){
        return ("Type: GoodBeast " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() > 0){
            setWait(getWait()-100);
        }
        else {
            if(context.nearestPlayerEntity(getPlace()) != null){
                context.tryToMove(this, context.moveAway(this,context.nearestPlayerEntity(getPlace())));
                setWait(400);
            } else{
                context.tryToMove(this,context.getRandMoveDirection().getDirection());
                setWait(400);
            }
        }
    }
}
