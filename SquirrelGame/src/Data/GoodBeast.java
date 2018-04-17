package Data;


public class GoodBeast extends Character {


    public GoodBeast(int pID, int x, int y) {
        super(pID,200, x,y);
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof Squirrel){
            e.updateEnergy(this.energy);
            return true;
        }
        return false;
    }

    public String toString(){
        return ("Type: GoodBeast " +super.toString());
    }
}
