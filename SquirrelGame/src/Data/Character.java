package Data;

public abstract class Character extends Entity{

    private int wait;
    private XY moveDirection = XY.ZERO_ZERO;

    protected Character(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

    public void setMoveDirection(XY moveDirection) {
        this.moveDirection = moveDirection;
    }

    public XY getMD() {
        return moveDirection;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getWait() {
        return wait;
    }

    public abstract void nextStep(EntityContext context);
}
