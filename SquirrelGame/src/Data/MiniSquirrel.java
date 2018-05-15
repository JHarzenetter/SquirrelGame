package Data;

public class MiniSquirrel extends Squirrel{

    protected MasterSquirrel master;

    public MiniSquirrel(int energy, int x, int y, MasterSquirrel master) {
        super(6, energy ,x, y);
        this.master = master;
    }

    public MasterSquirrel getMaster(){
        return master;
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
