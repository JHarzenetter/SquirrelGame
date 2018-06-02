package Data;

public class BadBeast extends Character {

    private int bites;

    public BadBeast(int x, int y) {
        super(1, -150, x, y);
        bites = 0;
    }

    public void bite(Entity e){
        e.updateEnergy(getEnergy());
        bites++;
        System.out.println("bites = " +bites);
    }

    public String toString(){
        return ("Type: BAD_BEAST " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(bites == 7){
            context.killAndReplace(this);
        } else if(getWait() > 0){
            setWait(getWait()-100);
        }
        else {
            if(context.nearestPlayerEntity(getPlace()) != null){
                context.tryToMove(this, context.moveTo(this,context.nearestPlayerEntity(getPlace())));
                setWait(400);
            } else{
                context.tryToMove(this,context.getRandMoveDirection());
                setWait(400);
            }
        }
    }
}
