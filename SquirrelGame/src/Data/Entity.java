package Data;

public abstract class Entity {

    protected int ID;
    protected int energy;     // random spawn , flattend...?? --> was soll board beinhalten? , beast KI , interface use , stuned gute l�sung?
    protected XY place;

    Entity(int pID, int pEnergy , int x , int y){
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

    public void updateEnergy(int delta){
        energy = energy + delta;
    }

    public boolean collision(Entity e){return false;}

    public boolean equals(){return false;}

    public void nextStep(EntityContext context){}

    public String toString(){
        return ("ID: "+ID+" , Energy: "+energy+" , X: "+place.getX()+" , Y: "+place.getY());
    }

}
