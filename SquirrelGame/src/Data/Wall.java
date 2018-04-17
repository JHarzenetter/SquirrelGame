package Data;

public class Wall extends Entity {

    public Wall(int id, int x, int y) {
        super(id,-10, x,y);
    }

    public boolean collision(Entity e){
        if(e instanceof Squirrel) {
            e.updateEnergy(this.energy);
            ((Squirrel) e).setStuned(3);
            return true;
        }
        return true;
    }

    public String toString(){
        return ("Type: Wall " +super.toString());
    }
}