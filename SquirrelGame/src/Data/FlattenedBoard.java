package Data;

public class FlattenedBoard  implements BoardView, EntityContext{

    private Board b;
    private Entity[][] fb;
    private XY size;

    public FlattenedBoard(Board b) {
        this.b = b;
        fb = b.flattend();
        size = new XY(b.getXsize(),b.getYsize());
    }

    public EntityType getEntityType(int x , int y){
        if(fb[x][y] instanceof BadBeast)
            return EntityType.BadBeast;
        if(fb[x][y] instanceof BadPlant)
            return EntityType.BadPlant;
        if(fb[x][y] instanceof GoodBeast)
            return EntityType.Goodbeast;
        if(fb[x][y] instanceof GoodPlant)
            return EntityType.GoodPlant;
        if(fb[x][y] instanceof MiniSquirrel)
            return EntityType.MiniSquirrel;
        if(fb[x][y] instanceof MasterSquirrel)
            return EntityType.MasterSquirrel;
        if(fb[x][y] instanceof Wall)
            return EntityType.Wall;
        return EntityType.Air;

    }

    public void tryToMove(MiniSquirrel miniSquirrel , XY direction){
        if(getEntityType(miniSquirrel.place.getX()+direction.getX(),miniSquirrel.place.getY()+direction.getY()) == EntityType.Air && miniSquirrel.stuned == 0){
            miniSquirrel.place = new XY(miniSquirrel.place.getX()+direction.getX(),miniSquirrel.place.getY()+direction.getY());
        }else{
            if(fb[miniSquirrel.place.getX()+direction.getX()][miniSquirrel.place.getY()+direction.getY()].collision(miniSquirrel)){}
            else{
                if(fb[miniSquirrel.place.getX()+direction.getX()][miniSquirrel.place.getY()+direction.getY()] instanceof MiniSquirrel){
                    kill(miniSquirrel);
                    kill(fb[miniSquirrel.place.getX()+direction.getX()][miniSquirrel.place.getY()+direction.getY()]);
                }else{
                    miniSquirrel.updateEnergy(fb[miniSquirrel.place.getX()+direction.getX()][miniSquirrel.place.getY()+direction.getY()].energy);
                }
            }
            // pflanzen,bister fressen : wand stunned : master frisst
            miniSquirrel.setStuned(miniSquirrel.stuned-1);
        }
    } //collision müsste passen mit allen Entitys

    public void tryToMove(MasterSquirrel masterSquirrel , XY direction){
        if(getEntityType(masterSquirrel.place.getX()+direction.getX(),masterSquirrel.place.getY()+direction.getY()) == EntityType.Air && masterSquirrel.stuned == 0){
            masterSquirrel.place = new XY(masterSquirrel.place.getX()+direction.getX(),masterSquirrel.place.getY()+direction.getY());
        }else{
            if(masterSquirrel.stuned > 0){
                System.out.println("Stunned for " + masterSquirrel.stuned +" rounds");
                masterSquirrel.setStuned(masterSquirrel.stuned-1);
            }else if(fb[masterSquirrel.place.getX()+direction.getX()][masterSquirrel.place.getY()+direction.getY()].collision(masterSquirrel)){}
            else {
                masterSquirrel.updateEnergy(fb[masterSquirrel.place.getX()+direction.getX()][masterSquirrel.place.getY()+direction.getY()].energy);
            }
        }
    } // collision müsste passen mit allen entitys

    public void tryToMove(BadBeast badBeast , XY direction){
        if(getEntityType(badBeast.place.getX()+direction.getX(),badBeast.place.getY()+direction.getY()) == EntityType.Air){
            badBeast.place = new XY(badBeast.place.getX()+direction.getX(),badBeast.place.getY()+direction.getY());
        }else {
            if(fb[badBeast.place.getX()+direction.getX()][badBeast.place.getY()+direction.getY()] instanceof Squirrel){
                badBeast.bite(fb[badBeast.place.getX()+direction.getX()][badBeast.place.getY()+direction.getY()]);
            }
        }
    } // müste passen

    public void tryToMove(GoodBeast goodBeast , XY direction){
        if(getEntityType(goodBeast.place.getX()+direction.getX(),goodBeast.place.getY()+direction.getY()) == EntityType.Air){
            goodBeast.place = new XY(goodBeast.place.getX()+direction.getX(),goodBeast.place.getY()+direction.getY());
        }
    } // müsste passen

    public Entity[][] getFB(){return fb;}

    @Override
    public void kill(Entity e) {
        b.removeEntity(e);
    }

    @Override
    public void killAndReplace(Entity e) {
        b.killAndReplace(e);
    }

    public void updateBoard(){
        fb = b.flattend();
    }

    public XY getSize(){return size;}

    public String toSting(){
        String s = "";
        for(int i=0; i<b.getXsize(); i++){
            for(int k=0; k<b.getYsize(); k++){
                s=s+(""+fb[i][k]);
            }
            s=s+('\n');
        }
        return s;
    }

}
