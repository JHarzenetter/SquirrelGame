package Data;

import java.util.Random;

public class Board{

    private BoardConfig bc = new BoardConfig();
    private Entity[] board = new Entity[bc.getAmoutnOfEntiies()];
    private XY[] xy;
    Random rand = new Random();
    private int id=0;

    public Board(){
        xy = getRandXY();
        for(int i=0; i<bc.getAmountOfBadBeast(); i++){
            board[id] = new BadBeast(id,xy[id].getX(),xy[id].getY());
            id++;
        }
        for(int i=0; i<bc.getAmountOfBadPlant(); i++){
            board[id] = new BadPlant(id,xy[id].getX(),xy[id].getY());
            id++;
        }
        for(int i=0; i<bc.getAmountOfGoodBeast(); i++){
            board[id] = new GoodBeast(id,xy[id].getX(),xy[id].getY());
            id++;
        }
        for(int i=0; i<bc.getAmountOfGoodPlant(); i++){
            board[id] = new GoodPlant(id,xy[id].getX(),xy[id].getY());
            id++;
        }
        for(int i=0; i<bc.getAmountOfWall(); i++){
            board[id] = new Wall(id,xy[id].getX(),xy[id].getY());
            id++;
        }

        for(int i=0; i<bc.getLength(); i++){
            for(int k=0; k<bc.getHeight(); k++){
                if(i >=1 && k>=1 && i<bc.getLength()-1 && k<bc.getHeight()-1){
                }
                else {
                    board[id] = new Wall(id,i,k);
                    id++;
                }
            }
        }

        board[id] = new HandOperatedMasterSquirrel(id/*ID*/, xy[id].getX()/*X*/, xy[id].getY()/*Y*/);
        id++;
    }

    private XY[] getRandXY(){
        XY[] randxy = new XY[bc.getAmoutnOfEntiies()];
        int count = 0;
        boolean check;

        while(randxy[bc.getAmoutnOfEntiies()-1] == null){
            check = true;
            int k = rand.nextInt((bc.getLength()-2))+1;
            int i = rand.nextInt((bc.getHeight()-2))+1;

            for(int j=0; j<count; j++){
                if(randxy[j].getX() == k && randxy[j].getY() == i){
                    check = false;
                    break;
                }
            }
            if(check){
                randxy[count] = new XY(k,i);
                count++;
            }
        }
        return randxy;
    }

    public Entity[][] flattend(){
        Entity[][] flattendBoard = new Entity[bc.getLength()][bc.getHeight()];
        for(int i=0; i<board.length-1; i++){
            flattendBoard[board[i].place.getX()][board[i].place.getY()] = board[i];
        }
        return flattendBoard;
    }

    public void removeEntity(Entity e){
        Entity[] tboard = new Entity[board.length-1];
        int k = 0;
        for(int i=0; i<board.length-1 ; i++){
            if(e.place == board[i].place) {
                k = 1;
            } else {
                tboard[i-k] = board[i];
            }
        }
        board = tboard;
    }

    public void addEntity(Entity e, int x, int y){
        Entity[] tboard = board.clone();
        board = new Entity[tboard.length+1];
        for(int i=0; i<tboard.length-1; i++) {
            board[i] = tboard[i];
        }
        if(e instanceof GoodPlant){
            board[tboard.length-1] = new GoodPlant(id,x,y);
            id++;
        }
        if(e instanceof BadPlant){
            board[tboard.length-1] = new BadPlant(id,x,y);
            id++;
        }
        if(e instanceof GoodBeast){
            board[tboard.length-1] = new GoodBeast(id,x,y);
            id++;
        }
        if(e instanceof BadBeast){
            board[tboard.length-1] = new BadBeast(id,x,y);
            id++;
        }
    }

    public void killAndReplace(Entity e) {
        removeEntity(e);
        addEntity(e,rand.nextInt((bc.getLength()-2))+1, rand.nextInt((bc.getHeight()-2))+1);
    }

    public Entity[] getBoard() {
        return board;
    }

    public int getXsize(){
        return bc.getLength();
    }

    public int getYsize(){
        return bc.getHeight();
    }

    public void update(EntityContext context) {
        for(int i=0; i<board.length; i++){
            board[i].nextStep(context);
        }
    }
}
