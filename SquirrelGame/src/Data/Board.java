package Data;

import java.util.Random;

public class Board{

    private Entity[] board;
    private XY[] xy;
    private XY size;
    Random rand = new Random();
    private int id=0;

    public Board(BoardConfig bc){
        board = new Entity[bc.getAmoutnOfEntiies()];
        size = new XY(bc.getLength(),bc.getHeight());
        xy = getRandXY(bc);
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

        for(int i=0; i<size.getX(); i++){
            for(int k=0; k<size.getY(); k++){
                if(i >=1 && k>=1 && i<size.getX()-1 && k<size.getY()-1){
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

    private XY[] getRandXY(BoardConfig bc){
        XY[] randXY = new XY[bc.getAmoutnOfEntiies()];
        int count = 0;
        boolean check;

        while(randXY[bc.getAmoutnOfEntiies()-1] == null){
            check = true;
            int k = rand.nextInt((size.getX()-2))+1;
            int i = rand.nextInt((size.getY()-2))+1;

            for(int j=0; j<count; j++){
                if(randXY[j].getX() == k && randXY[j].getY() == i){
                    check = false;
                    break;
                }
            }
            if(check){
                randXY[count] = new XY(k,i);
                count++;
            }
        }
        return randXY;
    }

    public FlattenedBoard flattend(){
        Entity[][] flattendBoard = new Entity[size.getX()][size.getY()];
        for(int i=0; i<board.length-1; i++){
            flattendBoard[board[i].getPlace().getX()][board[i].getPlace().getY()] = board[i];
        }
        return new FlattenedBoard(flattendBoard,this);
    }

    public HandOperatedMasterSquirrel getPlayer(){
        for(int i=0; i<board.length-1; i++){
            if (board[i] instanceof HandOperatedMasterSquirrel){
                return (HandOperatedMasterSquirrel)board[i];
            }
        }
        return null;
    }

    public void removeEntity(Entity e){
        Entity[] tboard = new Entity[board.length-1];
        int k = 0;
        for(int i=0; i<board.length-1 ; i++){
            if(e.getPlace() == board[i].getPlace()) {
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
        addEntity(e,rand.nextInt((size.getX()-2))+1, rand.nextInt((size.getY()-2))+1);
    }

    public XY getSize() {
        return size;
    }

    public void update() {
        for(int i=0; i<board.length-1; i++){
            if(board[i] instanceof Character){
                board[i].nextStep(flattend());
            }
        }
    }
}
