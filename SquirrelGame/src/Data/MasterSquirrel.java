package Data;

public abstract class MasterSquirrel extends Squirrel {

    protected MasterSquirrel(int ID, int x, int y) {
        super(ID , 1000 , x,y);
    }

    public MiniSquirrel createMini(int miniID, int energy){
        if(energy>=100 && energy<=this.getEnergy()){
            MiniSquirrel mini = new MiniSquirrel(miniID/*eigene*/, energy, getPlace().getX(), getPlace().getY(), this.getID());
            updateEnergy(-energy);
            return mini;
        } else {
            throw new NotEnoughEnergyExeption();
        }
    }

    public boolean checkEntitie(Entity e){
        if(e instanceof MiniSquirrel)
            if(((MiniSquirrel)e).getMasterID() == this.getID()){
                return true;
            }
        return false;
    }


    public String toString(){
        return ("Type: MasterSquirrel " +super.toString());
    }
}