package Data;

public class GoodPlant extends Entity {

    public GoodPlant(int pID, int x, int y) {
        super(pID,100, x,y);
    }

    public boolean collision(Entity e){
        if(e instanceof Squirrel){
            e.updateEnergy(this.energy);
            return true;
        }
        return false;
    }

    public String toString(){
        return ("Type: GoodPlant " +super.toString());
    }
}
