package Data;


public class BadBeast extends Beast {

    private int bites = 0;

    public BadBeast(int pID, int x, int y) {
        super(pID, -150, x, y);
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof Squirrel){
            bite(e);
            return true;
        }
        return false;
    }

    public void bite(Entity e){
        e.updateEnergy(energy);
        bites++;
    }

    public String toString(){
        return ("Type: BadBeast " +super.toString());
    }

    public int getBites() {
        return bites;
    }

    @Override
    public void nextStep(EntityContext context) {
        context.tryToMove(this, moveDirection.getDirection());
    }
}
