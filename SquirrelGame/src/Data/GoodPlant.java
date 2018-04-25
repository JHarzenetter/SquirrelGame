package Data;

public class GoodPlant extends Entity {

    public GoodPlant(int pID, int x, int y) {
        super(pID,100, x,y);
    }

    public String toString(){
        return ("Type: GoodPlant " +super.toString());
    }
}
