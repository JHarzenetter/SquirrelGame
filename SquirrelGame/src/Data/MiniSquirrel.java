package Data;

public class MiniSquirrel extends Squirrel{


    private int MsID;

    public MiniSquirrel(int ID, int energy, int x, int y, int MsID) {
        super(ID, energy ,x, y);
        this.MsID = MsID;
    }

    public int getMasterID(){
        return MsID;
    }

    public void nextStep(){
        energy--;
    }

    @Override
    public boolean collision(Entity e) {
        if(e instanceof MasterSquirrel){
            if(e.getID() == MsID){
                e.updateEnergy(energy);
                updateEnergy((-energy));
                return true;
            }
            if(e.getID() != MsID) {
                updateEnergy((-energy));
                return true;
            }
        }
        if(e instanceof MiniSquirrel){
            if(((MiniSquirrel) e).MsID != MsID){
               /* e.death();
                death();*/
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return ("Type: MiniSquirrel " +super.toString());
    }
}
