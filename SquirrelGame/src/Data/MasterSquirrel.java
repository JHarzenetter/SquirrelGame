package Data;

public abstract class MasterSquirrel extends Squirrel {

    public MasterSquirrel(int ID, int x, int y) {
        super(ID , 1000 , x,y);
    }

    public MiniSquirrel createMini(int miniID, int energy){

        MiniSquirrel mini = new MiniSquirrel(miniID/*eigene*/, energy, getPlace().getX()+1, getPlace().getY(), this.getID()); //sp�ter noch �ndern

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
