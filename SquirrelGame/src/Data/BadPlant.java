package Data;


public class BadPlant extends Entity {

    public BadPlant(int pID, int x, int y) {
        super(pID,-100, x,y);
        eatable = true;
    }

    public boolean collision(Entity e){
        if(e instanceof Squirrel){
            return false;
        }
        return true;
    }

    public String toString(){
        return ("Type: BadPlant " +super.toString());
    }
}
