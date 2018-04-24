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
        int xTry = miniSquirrel.place.getX()+direction.getX();
        int yTry = miniSquirrel.place.getY()+direction.getY();

        if(getEntityType(xTry,yTry) == EntityType.Air && miniSquirrel.stuned == 0){
            miniSquirrel.place = new XY(xTry,yTry);
        }else{
            if(miniSquirrel.stuned > 0){
                System.out.println("Stunned for " + miniSquirrel.stuned +" rounds");
                miniSquirrel.setStuned(miniSquirrel.stuned-1);
            }else if(fb[xTry][yTry].collision(miniSquirrel)){

            }
            else if(fb[xTry][yTry].isEatable()){
                miniSquirrel.updateEnergy(fb[xTry][yTry].energy);
                if(fb[xTry][yTry] instanceof MiniSquirrel){
                    kill(fb[xTry][yTry]);
                    kill(miniSquirrel);
                } else {
                    killAndReplace(fb[xTry][yTry]);
                }
                miniSquirrel.place = new XY(xTry,yTry);
            }
            else {
                miniSquirrel.updateEnergy(fb[xTry][yTry].energy);
            }
        }
    } //collision ok

    public void tryToMove(MasterSquirrel masterSquirrel , XY direction){
        int Xtry = masterSquirrel.place.getX()+direction.getX();
        int Ytry = masterSquirrel.place.getY()+direction.getY();

        if(getEntityType(Xtry,Ytry) == EntityType.Air && masterSquirrel.stuned == 0){
            masterSquirrel.place = new XY(Xtry,Ytry);
        }else{
            if(masterSquirrel.stuned > 0){
                System.out.println("Stunned for " + masterSquirrel.stuned +" rounds");
                masterSquirrel.setStuned(masterSquirrel.stuned-1);
            }else if(fb[Xtry][Ytry].collision(masterSquirrel)){

            }
            else if(fb[Xtry][Ytry].isEatable()){
                masterSquirrel.updateEnergy(fb[Xtry][Ytry].energy);
                if(fb[Xtry][Ytry] instanceof MiniSquirrel){
                    kill(fb[Xtry][Ytry]);
                } else {
                    killAndReplace(fb[Xtry][Ytry]);
                }
                masterSquirrel.place = new XY(Xtry,Ytry);
            }
            else {
                masterSquirrel.updateEnergy(fb[Xtry][Ytry].energy);
            }
        }
    } //collision ok

    public void tryToMove(BadBeast badBeast , XY direction){

        if(badBeast.isSquirrelNear(badBeast , this)){
            direction = moveToSquirrel(badBeast,badBeast.getNearestSquirrel()) ;    //set direction to squirrel
            System.out.println("Squirrel spotted!");
        }

        int Xtry = badBeast.place.getX()+direction.getX();
        int Ytry = badBeast.place.getY()+direction.getY();

        if(getEntityType(Xtry,Ytry) == EntityType.Air){
            badBeast.place = new XY(Xtry,Ytry);
        } else {
            if(getEntityType(Xtry,Ytry) == EntityType.MasterSquirrel){
                badBeast.bite(fb[Xtry][Ytry]);
            }
        }
    } //collision ok

    public void tryToMove(GoodBeast goodBeast , XY direction){
        if(goodBeast.isSquirrelNear(goodBeast , this)){
            direction = moveAwayFromSquirrel(goodBeast ,goodBeast.getNearestSquirrel()) ;    //set direction away squirrel
            System.out.println("Squirrel spotted!");
        }

        int Xtry = goodBeast.place.getX()+direction.getX();
        int Ytry = goodBeast.place.getY()+direction.getY();

        if(getEntityType(Xtry,Ytry) == EntityType.Air){
            goodBeast.place = new XY(Xtry,Ytry);
        }
    } //collision ok

    private XY moveToSquirrel(BadBeast beast, XY nearestSquirrel) {
        int x = 0;
        int y = 0;
        if(beast.place.getX() < nearestSquirrel.getX()){
            x = 1;
        }
        if(beast.place.getY() < nearestSquirrel.getY()){
            y = 1;
        }
        if(beast.place.getX() > nearestSquirrel.getX()){
            x = -1;
        }
        if(beast.place.getY() > nearestSquirrel.getY()){
            y = -1;
        }
        return new XY(x,y);
    }

    private XY moveAwayFromSquirrel(GoodBeast goodBeast , XY nearestSquirrel){
        int x = 0;
        int y = 0;
        if(goodBeast.place.getX() < nearestSquirrel.getX()){
            x = -1;
        }
        if(goodBeast.place.getX() > nearestSquirrel.getX()){
            x = 1;
        }
        if(goodBeast.place.getY() < nearestSquirrel.getY()){
            y = -1;
        }
        if(goodBeast.place.getY() > nearestSquirrel.getY()){
            y = 1;
        }
        return new XY(x,y);
    }

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