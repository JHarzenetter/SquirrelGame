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

    public void nextStep(int i){
        switch (i){
            case 1:
                if(stuned==0){
                    moveUp();
                } else {
                    stuned--;
                }
            case 2:
                if(stuned == 0){
                    moveDown();
                }else{
                    stuned--;
                }
            case 3:
                if(stuned == 0){
                    moveLeft();
                }else{
                    stuned--;
                }
            case 4:
                if(stuned == 0){
                    moveRight();
                }else{
                    stuned--;
                }
        }
    }
}
