package Data;

public interface EntityContext {

    void tryToMove(MiniSquirrel miniSquirrel , XY direction);
    void tryToMove(BadBeast badBeast , XY direction);
    void tryToMove(GoodBeast goodBeast , XY direction);
    void tryToMove(MasterSquirrel masterSquirrel , XY direction);
    void kill(Entity e);
    void killAndReplace(Entity e);

}
