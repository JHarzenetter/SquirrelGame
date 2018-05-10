package BotAPI;

import Data.EntityType;
import Data.XY;
import UI.MoveDirection;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewIpperRight();
    EntityType getEntityAt(XY xy);
    void move(MoveDirection direction);
    void spawnMiniBot(XY direction,int energy);
    int getEnergy();
}
