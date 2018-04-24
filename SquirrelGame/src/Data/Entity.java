package Data;

public abstract class Entity {

    private int ID;
    private int energy;     // TODO - kapselung attribute
    private XY place;
    protected boolean eatable;

    Entity(int pID, int pEnergy , int x , int y){
        ID = pID;
        energy = pEnergy;
        place = new XY(x,y);
        eatable = false;
    }

    public int getID(){
        return ID;
    }

    public int getEnergy(){
        return energy;
    }

    public XY getPlace(){return place;}

    public void setPlace(XY b){
        place = b;
    }

    public void updateEnergy(int delta){
        energy = energy + delta;
    }

    public boolean collision(Entity e){return false;}

    public void nextStep(EntityContext context){}

    public boolean isEatable() {
        return eatable;
    }

    public String toString(){
        return ("ID: "+ID+" , Energy: "+energy+" , X: "+place.getX()+" , Y: "+place.getY());
    }

}
