package Data;

public class Wall extends Entity {

    public Wall(int x, int y) {
        super(0,-10, x,y);
    }

    public String toString(){
        return ("Type: WALL " +super.toString());
    }
}