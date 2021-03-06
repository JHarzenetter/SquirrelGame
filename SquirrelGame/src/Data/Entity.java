package Data;

/**
 * The main Overclass of the Entities.
 */
public abstract class Entity {

    private int ID;
    private int energy;
    private XY place;

    protected Entity(int pID, int pEnergy , int x , int y){
        ID = pID;
        energy = pEnergy;
        place = new XY(x,y);

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

    public void nextStep(EntityContext context){}

    public boolean equals(Entity obj) {
        return (getID() == obj.getID());
    }

    public String toString(){
        return ("ID: "+ID+" , Energy: "+energy+" , X: "+place.getX()+" , Y: "+place.getY());
    }

}
