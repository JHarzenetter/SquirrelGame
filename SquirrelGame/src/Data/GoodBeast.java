package Data;


public class GoodBeast extends Beast {

    private int wait = 4;


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
        if(wait > 0){
            wait --;
        } else {
            context.tryToMove(this, moveDirection.getDirection());
            wait = 4;
        }
    }
}
