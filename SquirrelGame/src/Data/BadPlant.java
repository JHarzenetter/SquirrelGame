package Data;

/**
 * A Plant which tastes not good, what means you will lose Energy
 */
public class BadPlant extends Entity {

    public BadPlant(int x, int y) {
        super(3,-100, x,y);
    }

    public String toString(){
        return ("Type: BAD_PLANT " +super.toString());
    }
}
