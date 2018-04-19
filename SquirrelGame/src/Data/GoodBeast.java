package Data;


public class GoodBeast extends Character {


    public GoodBeast(int pID, int x, int y) {
        super(pID,200, x,y);
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
        context.tryToMove(this, moveDirection.getDirection());
    }
}
