package Data;

public class Wall extends Entity {

    public Wall(int id, int x, int y) {
        super(id,-10, x,y);
    }

    public String toString(){
        return ("Type: Wall " +super.toString());
    }
}