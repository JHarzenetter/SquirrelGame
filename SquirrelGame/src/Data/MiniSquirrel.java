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

    public String toString(){
        return ("Type: MiniSquirrel " +super.toString());
    }

    @Override
    public void nextStep(EntityContext context) {
        if(getWait() == 0){
            context.tryToMove(this,context.getRandMoveDirection().getDirection());
        } else {
            setWait(getWait()-100);
        }
    }
}
