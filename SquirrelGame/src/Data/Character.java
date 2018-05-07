package Data;

import UI.MoveDirection;

public abstract class Character extends Entity{

    private int wait;
    private MoveDirection moveDirection = MoveDirection.none;

    protected Character(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }

    public void setMoveDirection(MoveDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    public MoveDirection getMD() {
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
