package Data;

public class BadPlant extends Entity {

    public BadPlant(int x, int y) {
        super(3,-100, x,y);
    }

    public String toString(){
        return ("Type: BadPlant " +super.toString());
    }
}
