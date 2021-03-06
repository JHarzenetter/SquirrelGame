package Data;

import java.util.logging.Level;
import java.util.logging.Logger;

public class FlattenedBoard  implements BoardView, EntityContext{

    private Board b;
    private Entity[][] fb;
    private XY size;
    private Logger log = Logger.getLogger("Logger");

    public FlattenedBoard(Entity[][] fb, Board b) {
        this.fb = fb;
        this.b = b;
        size = b.getSize();
    }

    public EntityType getEntityType(XY xy){
        if(fb[xy.getX()][xy.getY()] instanceof BadBeast)
            return EntityType.BAD_BEAST;
        if(fb[xy.getX()][xy.getY()] instanceof BadPlant)
            return EntityType.BAD_PLANT;
        if(fb[xy.getX()][xy.getY()] instanceof GoodBeast)
            return EntityType.GOOD_BEAST;
        if(fb[xy.getX()][xy.getY()] instanceof GoodPlant)
            return EntityType.GOOD_PLANT;
        if(fb[xy.getX()][xy.getY()] instanceof MiniSquirrel)
            return EntityType.MINI_SQUIRREL;
        if(fb[xy.getX()][xy.getY()] instanceof MasterSquirrel)
            return EntityType.MASTER_SQUIRREL;
        if(fb[xy.getX()][xy.getY()] instanceof Wall)
            return EntityType.WALL;
        return EntityType.NONE;
    }

    @Override
    public Entity getEntity(XY xy) {
        return fb[xy.getX()][xy.getY()];
    }

    public XY getSize(){return size;}

    @Override
    public Board getBoard() {
        return b;
    }

    /**
     * Move an MasterSquirrel and handle its Collisions
     * @param masterSquirrel
     * @param direction
     */
    public void tryToMove(MasterSquirrel masterSquirrel , XY direction){
        XY moveTry = new XY (masterSquirrel.getPlace().getX()+direction.getX(),masterSquirrel.getPlace().getY()+direction.getY());
        if(direction.equals(XY.ZERO_ZERO)){
            return;
        }
        switch(getEntityType(moveTry)) {
            case NONE:
                break;
            case WALL:
                log.log(Level.WARNING,"MASTER_SQUIRREL collides with WALL");
                masterSquirrel.setWait(300);
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                return;
            case GOOD_PLANT:
            case GOOD_BEAST:
            case BAD_PLANT:
                log.info("Respawn from eatable initiated");
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                killAndReplace(fb[moveTry.getX()][moveTry.getY()]);
                break;
            case MASTER_SQUIRREL:
                return;
            case MINI_SQUIRREL:
                log.info("Eat MINI_SQUIRREL");
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                kill(fb[moveTry.getX()][moveTry.getY()]);
                break;
            case BAD_BEAST:
                log.warning("MASTER_SQUIRREL got bitten");
                ((BadBeast)fb[moveTry.getX()][moveTry.getY()]).bite(masterSquirrel);
                return;
        }
        masterSquirrel.setPlace(moveTry);
    } //collision ok

    /**
     * Move an MiniSquirrel and handle its Collisions
     * @param miniSquirrel
     * @param direction
     */
    public void tryToMove(MiniSquirrel miniSquirrel , XY direction){
        XY moveTry = new XY (miniSquirrel.getPlace().getX()+direction.getX(),miniSquirrel.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case MINI_SQUIRREL:
                kill(fb[moveTry.getX()][moveTry.getY()]);
                kill(miniSquirrel);
                return;
            case MASTER_SQUIRREL:
                fb[moveTry.getX()][moveTry.getY()].updateEnergy(miniSquirrel.getEnergy());
                kill(miniSquirrel);
                return;
            case WALL:
                log.warning("MINI_SQUIRREL collides with WALL");
                miniSquirrel.setWait(300);
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                return;
            case NONE:
                break;
            case GOOD_BEAST:
            case GOOD_PLANT:
            case BAD_PLANT:
                log.info("Respawn from eatable initiated");
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                killAndReplace(fb[moveTry.getX()][moveTry.getY()]);
                break;
            case BAD_BEAST:
                log.warning("MINI_SQUIRREL got bitten");
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                ((BadBeast)fb[moveTry.getX()][moveTry.getY()]).bite(miniSquirrel);
                return;
        }
        miniSquirrel.setPlace(moveTry);
    } //collision ok

    /**
     * Move an BadBeast and handle its Collision
     * @param badBeast
     * @param direction
     */
    public void tryToMove(BadBeast badBeast , XY direction){

        XY moveTry = new XY (badBeast.getPlace().getX()+direction.getX(),badBeast.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case NONE:
                break;
            case BAD_PLANT:
            case BAD_BEAST:
            case GOOD_PLANT:
            case GOOD_BEAST:
            case WALL:
                return;
            case MASTER_SQUIRREL:
            case MINI_SQUIRREL:
                badBeast.bite(fb[moveTry.getX()][moveTry.getY()]);
                return;
        }
        badBeast.setPlace(moveTry);
    } //collision ok

    /**
     * Move an GoodBeast and handle its Collision
     * @param goodBeast
     * @param direction
     */
    public void tryToMove(GoodBeast goodBeast , XY direction){
        XY moveTry = new XY (goodBeast.getPlace().getX()+direction.getX(),goodBeast.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case NONE:
                break;
            case BAD_PLANT:
            case BAD_BEAST:
            case GOOD_PLANT:
            case GOOD_BEAST:
            case WALL:
                return;
            case MASTER_SQUIRREL:
            case MINI_SQUIRREL:
                fb[moveTry.getX()][moveTry.getY()].updateEnergy(goodBeast.getEnergy());
                killAndReplace(goodBeast);
                return;
        }
        goodBeast.setPlace(moveTry);
    } //collision ok

    /**
     * Searches the nearest Squirrel around an Beast within Range of 6 Steps
     * @param place
     * @return
     */
    @Override
    public Squirrel nearestPlayerEntity(XY place) {
        int step = 1;
        while(step <= 6) {
            int tx = place.getX()-1;
            int ty = place.getY();

            while (ty != place.getY() - step) {
                ty--;
                if(ty < size.getY()&& ty > 0 && tx < size.getX() && tx > 0){
                    if (fb[tx][ty] instanceof Squirrel) {
                        return (Squirrel) fb[tx][ty];
                    }
                }
            }
            while (tx != place.getX() + step) { // nach rechts
                tx++;
                if(ty < size.getY()&& ty > 0 && tx < size.getX() && tx > 0){
                    if (fb[tx][ty] instanceof Squirrel) {
                        return (Squirrel) fb[tx][ty];
                    }
                }
            }
            while (ty != place.getY() + step) { // nach unten
                ty++;
                if(ty < size.getY()&& ty > 0 && tx < size.getX() && tx > 0){
                    if (fb[tx][ty] instanceof Squirrel) {
                        return (Squirrel) fb[tx][ty];
                    }
                }
            }
            while (tx != place.getX() - step) { // nach links
                tx--;
                if(ty < size.getY()&& ty > 0 && tx < size.getX() && tx > 0){
                    if (fb[tx][ty] instanceof Squirrel) {
                        return (Squirrel) fb[tx][ty];
                    }
                }
            }
            while (ty != place.getY()) { // nach oben
                ty--;
                if(ty < size.getY()&& ty > 0 && tx < size.getX() && tx > 0){
                    if (fb[tx][ty] instanceof Squirrel) {
                        return (Squirrel) fb[tx][ty];
                    }
                }
            }
            step++;
        }
        return null;
    }

    public XY getRandMoveDirection(){
        return XYsupport.getRandCommand();
    }

    /**
     * get an MoveDirection to the nearest Squirrel
     * @param badBeast
     * @param en
     * @return
     */
    public XY moveTo(BadBeast badBeast , Entity en){
        int x = 0;
        int y = 0;
        if(badBeast.getPlace().getX() < en.getPlace().getX()){
            x = 1;
        }
        if(badBeast.getPlace().getX() > en.getPlace().getX()){
            x = -1;
        }
        if(badBeast.getPlace().getY() < en.getPlace().getY()){
            y = 1;
        }
        if(badBeast.getPlace().getY() > en.getPlace().getY()){
            y = -1;
        }
        return new XY(x,y);
    }

    /**
     * get an MoveDirection away from the nearest Squirrel
     * @param goodBeast
     * @param en
     * @return
     */
    public XY moveAway(GoodBeast goodBeast, Entity en){
        int x = 0;
        int y = 0;
        if(goodBeast.getPlace().getX() < en.getPlace().getX()){
            x = -1;
        }
        if(goodBeast.getPlace().getX() > en.getPlace().getX()){
            x = 1;
        }
        if(goodBeast.getPlace().getY() < en.getPlace().getY()){
            y = -1;
        }
        if(goodBeast.getPlace().getY() > en.getPlace().getY()){
            y = 1;
        }
        return new XY(x,y);
    }

    @Override
    public void kill(Entity e) {
        b.removeEntity(e);
    }

    @Override
    public void killAndReplace(Entity e) {
        b.killAndReplace(e,this);
    }
}