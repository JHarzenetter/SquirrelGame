package Data;


public class BadPlant extends Entity {

    public BadPlant(int pID, int x, int y) {
        super(pID,-100, x,y);
    }

    public boolean collision(Entity e){
        if(e instanceof Squirrel){
            e.updateEnergy(this.energy);
            return true;
        }
        return false;
    }

    public String toString(){
        return ("Type: BadPlant " +super.toString());
    }
}
