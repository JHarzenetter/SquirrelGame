package Data;
import java.util.Random;

public class Board{

    private BoardConfig bc = new BoardConfig();
    private Entity[] board = new Entity[bc.getAmoutnOfEntiies()];
    private XY[] xy;
    private int id=0;

    public Board(){
        xy = getRand();
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

    private XY[] getRand(){
        XY[] randxy = new XY[bc.getAmoutnOfEntiies()];
        Random rand = new Random();
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

    public void removeEntity(){}

    public int getXsize(){
        return bc.getLength();
    }

    public int getYsize(){
        return bc.getHeight();
    }
}
