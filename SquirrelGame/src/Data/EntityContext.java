package Data;

import UI.MoveDirection;

public interface EntityContext {

    XY getSize();
    Board getBoard();
    EntityType getEntityType(XY xy);
    Entity getEntity(XY xy);

    void tryToMove(MiniSquirrel miniSquirrel , XY direction);
    void tryToMove(BadBeast badBeast , XY direction);
    void tryToMove(GoodBeast goodBeast , XY direction);
    void tryToMove(MasterSquirrel masterSquirrel , XY direction);
    Squirrel nearestPlayerEntity(XY place);

    void kill(Entity e);
    void killAndReplace(Entity e);

    XY moveTo(BadBeast badBeast , Entity en);
    XY moveAway(GoodBeast goodBeast , Entity en);
    MoveDirection getRandMoveDirection();
}
