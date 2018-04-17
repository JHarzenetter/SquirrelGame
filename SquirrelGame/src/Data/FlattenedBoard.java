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

    public EntityType getEntityType(Entity e){
        if(e instanceof BadPlant)
            return EntityType.BadPlant;
        if(e instanceof BadBeast)
            return EntityType.BadBeast;
        if(e instanceof GoodBeast)
            return EntityType.Goodbeast;
        if(e instanceof GoodPlant)
            return EntityType.GoodPlant;
        if(e instanceof Wall)
            return EntityType.Wall;
        return EntityType.Air;
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
        if(fb[x][y] instanceof Wall)
            return EntityType.Wall;
        return EntityType.Air;

    }

    public void tryToMove(MiniSquirrel miniSquirrel , XY direction){
        if((miniSquirrel).stuned == 0){
            miniSquirrel.place = new XY(direction.getX() , direction.getY());
        }
    }

    public void tryToMove(MasterSquirrel masterSquirrel , XY direction){
        if(masterSquirrel.stuned == 0){
            masterSquirrel.place = new XY(direction.getX() , direction.getY());
        }
    }

    public void tryToMove(BadBeast badBeast , XY direction){
        badBeast.place = new XY(direction.getX() , direction.getY());
    }

    public void tryToMove(GoodBeast goodBeast , XY direction){
        goodBeast.place = new XY(direction.getX() , direction.getY());
    }

    @Override
    public void kill(Entity e) {
        b.removeEntity(e);
    }

    @Override
    public void killAndReplace(Entity e) {

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
