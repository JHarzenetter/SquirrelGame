package Data;

public class BadPlant extends Entity {

    public BadPlant(int pID, int x, int y) {
        super(pID,-100, x,y);
    }

    public String toString(){
        return ("Type: BadPlant " +super.toString());
    }
}
