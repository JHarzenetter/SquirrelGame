package Data;

public class MiniSquirrel extends Squirrel{


    private int MsID;

    public MiniSquirrel(int ID, int energy, int x, int y, int MsID) {
        super(ID, energy ,x, y);
        this.MsID = MsID;
        eatable = true;
    }

    public int getMasterID(){
        return MsID;
    }

    public void nextStep(){}

    @Override
    public boolean collision(Entity e) {
        if(e instanceof MasterSquirrel){
            if(e.getID() == MsID){
                return false;
            }
            if(e.getID() != MsID) {
                return false;
            }
        }
        if(e instanceof MiniSquirrel){
            if(((MiniSquirrel) e).MsID != MsID){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        return ("Type: MiniSquirrel " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {

    }
}
