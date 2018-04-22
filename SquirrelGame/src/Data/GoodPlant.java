package Data;

public class GoodPlant extends Entity {

    public GoodPlant(int pID, int x, int y) {
        super(pID,100, x,y);
        eatable = true;
    }

    public boolean collision(Entity e){
        if(e instanceof Squirrel){
            return false;
        }
        return true;
    }

    public String toString(){
        return ("Type: GoodPlant " +super.toString());
    }
}
