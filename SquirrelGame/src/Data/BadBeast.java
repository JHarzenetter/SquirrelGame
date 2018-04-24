package Data;

public class BadBeast extends Character {

    private int bites;

    public BadBeast(int pID, int x, int y) {
        super(pID, -150, x, y);
        bites = 0;
    }

    public void bite(Entity e){
        e.updateEnergy(getEnergy());
        bites++;
        System.out.println("bites = " +bites);
    }

    public String toString(){
        return ("Type: BadBeast " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(bites == 7){
            context.killAndReplace(this);
        } else if(getWait() > 0){
            setWait(getWait()-1);
        }
        else {
            if(context.nearestPlayerEntity(getPlace()) != null){
                context.tryToMove(this, context.moveTo(this,context.nearestPlayerEntity(getPlace())));
                setWait(4);
            } else{
                context.tryToMove(this,context.getRandMoveDirection().getDirection());
                setWait(4);
            }
        }
    }
}
