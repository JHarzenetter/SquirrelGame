package BotAPI;

import Data.Entity;
import Data.EntityType;
import Data.XY;
import UI.MoveDirection;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    XY locate();
    XY directionOfMaster();

    EntityType getEntityAt(XY xy);
    Entity getEntity(XY xy);

    void move(MoveDirection direction);
    void spawnMiniBot(XY direction,int energy);
    void implode(int impactRadius) throws Exception;

    default void shout(String s) {}

    int getEnergy();
    long getRemainingSteps();
    boolean isMine(XY xy);

    //TODO: Fragen: Proxy einbindung , VL unter Patterns zu finden!
}