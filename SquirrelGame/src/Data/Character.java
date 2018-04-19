package Data;

import UI.MoveDirection;

public abstract class Character extends Entity{

    Character(int pID, int pEnergy, int x, int y) {
        super(pID, pEnergy, x, y);
    }
    MoveDirection moveDirection = MoveDirection.None;

    public void setMoveDirection(MoveDirection moveDirection) {
        this.moveDirection = moveDirection;
    }

    public abstract void nextStep(EntityContext context);
}
