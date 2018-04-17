package Data;

public abstract class Squirrel extends Character{

    protected int stuned = 0;

    Squirrel(int ID, int energy , int x, int y) {
        super(ID,energy, x,y);
    }

    public void setStuned(int i){
        stuned = i;
    }

    public String toString(){
        return super.toString();
    }
}