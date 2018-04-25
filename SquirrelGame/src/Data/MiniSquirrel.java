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

    public void nextStep(){}

    public String toString(){
        return ("Type: MiniSquirrel " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {

    }
}
