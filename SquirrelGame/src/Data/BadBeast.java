package Data;


public class BadBeast extends Beast {

    private int bites;
    private int wait;

    public BadBeast(int pID, int x, int y) {
        super(pID, -150, x, y);
        bites = 0;
        wait = 0;
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
        System.out.println("bites = " +bites);
    }

    public String toString(){
        return ("Type: BadBeast " +super.toString());
    }

    public int getBites() {
        return bites;
    }

    @Override
    public void nextStep(EntityContext context) {
        if(bites == 7){
            context.killAndReplace(this);
        } else if(wait > 0){
            wait--;
        }
        else {
            context.tryToMove(this, moveDirection.getDirection());
            wait = 4;
        }
    }
}
