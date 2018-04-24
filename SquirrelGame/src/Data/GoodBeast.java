package Data;

public class GoodBeast extends Character {

    public GoodBeast(int pID, int x, int y) {
        super(pID,200, x,y);
        eatable = true;
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof Squirrel){
            return false;
        }
        return true;
    }

    public String toString(){
        return ("Type: GoodBeast " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() > 0){
            setWait(getWait()-1);
        } else {
            context.tryToMove(this, getMD().getDirection());
            setWait(4);
        }
    }
}
