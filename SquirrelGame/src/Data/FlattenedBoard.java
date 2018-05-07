package Data;

import UI.MoveDirection;

public class FlattenedBoard  implements BoardView, EntityContext{

    private Board b;
    private Entity[][] fb;
    private XY size;

    public FlattenedBoard(Entity[][] fb, Board b) {
        this.fb = fb;
        this.b = b;
        size = b.getSize();
    }

    public EntityType getEntityType(XY xy){
        if(fb[xy.getX()][xy.getY()] instanceof BadBeast)
            return EntityType.BadBeast;
        if(fb[xy.getX()][xy.getY()] instanceof BadPlant)
            return EntityType.BadPlant;
        if(fb[xy.getX()][xy.getY()] instanceof GoodBeast)
            return EntityType.Goodbeast;
        if(fb[xy.getX()][xy.getY()] instanceof GoodPlant)
            return EntityType.GoodPlant;
        if(fb[xy.getX()][xy.getY()] instanceof MiniSquirrel)
            return EntityType.MiniSquirrel;
        if(fb[xy.getX()][xy.getY()] instanceof MasterSquirrel)
            return EntityType.MasterSquirrel;
        if(fb[xy.getX()][xy.getY()] instanceof Wall)
            return EntityType.Wall;
        return EntityType.Air;

    }

    public XY getSize(){return size;}

    public void tryToMove(MasterSquirrel masterSquirrel , XY direction){
        XY moveTry = new XY (masterSquirrel.getPlace().getX()+direction.getX(),masterSquirrel.getPlace().getY()+direction.getY());
        if(direction.equals(MoveDirection.None.getDirection())){
            return;
        }
        switch(getEntityType(moveTry)) {
            case Air:
                break;
            case Wall:
                masterSquirrel.setWait(3);
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                return;
            case GoodPlant:
            case BadPlant:
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                killAndReplace(fb[moveTry.getX()][moveTry.getY()]);
                return;
            case MasterSquirrel:
                return;
            case MiniSquirrel:
                break;
            case Goodbeast:
                masterSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                killAndReplace(fb[moveTry.getX()][moveTry.getY()]);
                return;
            case BadBeast:
                ((BadBeast)fb[moveTry.getX()][moveTry.getY()]).bite(masterSquirrel);
                return;
        }
        masterSquirrel.setPlace(moveTry);
    } //collision ok

    public void tryToMove(MiniSquirrel miniSquirrel , XY direction){
        XY moveTry = new XY (miniSquirrel.getPlace().getX()+direction.getX(),miniSquirrel.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case MiniSquirrel:
                kill(fb[moveTry.getX()][moveTry.getY()]);
                kill(miniSquirrel);
                return;
            case MasterSquirrel:
                fb[moveTry.getX()][moveTry.getY()].updateEnergy(miniSquirrel.getEnergy());
                kill(miniSquirrel);
                return;
            case Wall:
                miniSquirrel.setWait(3);
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                return;
            case Air:
                break;
            case Goodbeast:
            case GoodPlant:
            case BadPlant:
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
            case BadBeast:
                miniSquirrel.updateEnergy(fb[moveTry.getX()][moveTry.getY()].getEnergy());
                ((BadBeast)fb[moveTry.getX()][moveTry.getY()]).bite(miniSquirrel);
                return;
        }
        miniSquirrel.setPlace(moveTry);
    } //collision ok

    public void tryToMove(BadBeast badBeast , XY direction){

        XY moveTry = new XY (badBeast.getPlace().getX()+direction.getX(),badBeast.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case Air:
                break;
            case BadPlant:
            case BadBeast:
            case GoodPlant:
            case Goodbeast:
            case Wall:
                return;
            case MasterSquirrel:
            case MiniSquirrel:
                badBeast.bite(fb[moveTry.getX()][moveTry.getY()]);
                return;
        }
        badBeast.setPlace(moveTry);
    } //collision ok

    public void tryToMove(GoodBeast goodBeast , XY direction){
        XY moveTry = new XY (goodBeast.getPlace().getX()+direction.getX(),goodBeast.getPlace().getY()+direction.getY());

        switch (getEntityType(moveTry)){
            case Air:
                break;
            case BadPlant:
            case BadBeast:
            case GoodPlant:
            case Goodbeast:
            case Wall:
                return;
            case MasterSquirrel:
            case MiniSquirrel:
                fb[moveTry.getX()][moveTry.getY()].updateEnergy(goodBeast.getEnergy());
                killAndReplace(goodBeast);
                return;
        }
        goodBeast.setPlace(moveTry);
    } //collision ok

    public Entity[][] getFB(){return fb;}

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

    public MoveDirection getRandMoveDirection(){
        return MoveDirection.getRandCommand();
    }

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
        b.killAndReplace(e);
    }

    /*public String toSting(){
        String s = "";
        for(int i=0; i<b.getXsize(); i++){
            for(int k=0; k<b.getYsize(); k++){
                s=s+(""+fb[i][k]);
            }
            s=s+('\n');
        }
        return s;
    }*/
}