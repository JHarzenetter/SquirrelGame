package Data;

public abstract class MasterSquirrel extends Squirrel {

    protected MasterSquirrel(int ID, int x, int y) {
        super(ID , 1000 , x,y);
    }

    public MiniSquirrel createMini(int miniID, int energy){ // TODO enery cap missing
        MiniSquirrel mini = new MiniSquirrel(miniID/*eigene*/, energy, getPlace().getX(), getPlace().getY(), this.getID());
        updateEnergy(-energy);
        return mini;
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