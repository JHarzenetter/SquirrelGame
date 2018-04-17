package Data;


public class BadBeast extends Character {

    private int bites = 0;

    public BadBeast(int pID, int x, int y) {
        super(pID, -150, x, y);
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof  Squirrel){
            bite(e);
            if(bites == 7){
                return false;
            }
            return true;
        }
        return false;
    }

    private void bite(Entity e){
        e.updateEnergy(energy);
        bites++;
    }

    public String toString(){
        return ("Type: BadBeast " +super.toString());
    }
}
