package Data;

public class BadBeast extends Character {

    private int bites;

    public BadBeast(int x, int y) {
        super(1, -1050, x, y);
        bites = 0;
    }

    public void bite(Entity e){
        e.updateEnergy(getEnergy());
        updateEnergy(-150);
        bites++;
        System.out.println("bites = " +bites);
    }

    public String toString(){
        return ("Type: BAD_BEAST " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getEnergy() <= 0){
            context.killAndReplace(this);
        } else if(getWait() > 0){
            setWait(getWait()-100);
        }
        else {
            if(context.nearestPlayerEntity(getPlace()) != null){
                context.tryToMove(this, context.moveTo(this,context.nearestPlayerEntity(getPlace())));
                setWait(400);
            } else{
                context.tryToMove(this,context.getRandMoveDirection().getDirection());
                setWait(400);
            }
        }
    }
}
