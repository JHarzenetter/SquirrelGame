package Data;

public class HandOperatedMasterSquirrel extends MasterSquirrel{

    public HandOperatedMasterSquirrel(int ID, int x, int y) {
        super(ID, x, y);
    }

    public XY moveUp(){
        return new XY(place.getX(),place.getY() - 1);
    }

    public XY moveDown(){
        return new XY(place.getX(),place.getY() + 1);
    }

    public XY moveLeft(){
        return new XY(place.getX()-1,place.getY());
    }

    public XY moveRight(){
        return new XY(place.getX()+1, place.getY());
    }

    @Override
    public void nextStep(EntityContext context) {
        context.tryToMove(this , moveDirection.getDirection());
        if(energy < 0){
            energy = 0;
        }
        System.out.println("MasterS Engery : " + this.energy);
    }
}
