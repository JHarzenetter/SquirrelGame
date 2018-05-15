package Data;

import UI.MoveDirection;

public interface EntityContext {

    XY getSize();
    Board getBoard();

    void tryToMove(MiniSquirrel miniSquirrel , XY direction);
    void tryToMove(BadBeast badBeast , XY direction);
    void tryToMove(GoodBeast goodBeast , XY direction);
    void tryToMove(MasterSquirrel masterSquirrel , XY direction);
    Squirrel nearestPlayerEntity(XY place);

    void kill(Entity e);
    void killAndReplace(Entity e);
    EntityType getEntityType(XY xy);

    XY moveTo(BadBeast badBeast , Entity en);
    XY moveAway(GoodBeast goodBeast , Entity en);
    MoveDirection getRandMoveDirection();
}
