package Data;

public class GoodPlant extends Entity {

    public GoodPlant(int x, int y) {
        super(4,100, x,y);
    }

    public String toString(){
        return ("Type: GOOD_PLANT " +super.toString());
    }
}
