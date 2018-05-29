package Data;

import BotAPI.Factory;

import java.util.Random;

public class Board{

    private Entity[] board;
    private XY size;
    private Random rand = new Random();
    private int count = 0;
    private int players = 0;
    //ID's: 0 = Wand , 1 = BAD_BEAST , 2 = GoodBeast , 3 = BAD_PLANT , 4 = GOOD_PLANT , 5 = MasterS , 6 = MiniS

    public Board(BoardConfig bc){
        board = new Entity[bc.getAmountOfEntities()];
        size = bc.getSize();
        XY [] xy = getRandXY(bc);
        for(int i=0; i<bc.getAmountOfBadBeast(); i++){
            board[count] = new BadBeast(xy[count].getX(),xy[count].getY());
            count++;
        }
        for(int i=0; i<bc.getAmountOfBadPlant(); i++){
            board[count] = new BadPlant(xy[count].getX(),xy[count].getY());
            count++;
        }
        for(int i=0; i<bc.getAmountOfGoodBeast(); i++){
            board[count] = new GoodBeast(xy[count].getX(),xy[count].getY());
            count++;
        }
        for(int i=0; i<bc.getAmountOfGoodPlant(); i++){
            board[count] = new GoodPlant(xy[count].getX(),xy[count].getY());
            count++;
        }
        for(int i=0; i<bc.getAmountOfWall(); i++){
            board[count] = new Wall(xy[count].getX(),xy[count].getY());
            count++;
        }
        for(int i = 0; i<bc.getAmountOfHandOperated(); i++){
            board[count] = new HandOperatedMasterSquirrel(xy[count].getX(), xy[count].getY());
            players++;
            count++;
        }
        for(int i=0; i<bc.getAmountOfBots(); i++){
            board[count] = new MasterSquirrelBot(xy[count].getX(), xy[count].getY(), new Factory());
            players++;
            count++;
        }
        for(int i=0; i<size.getX(); i++){
            for(int k=0; k<size.getY(); k++){
                if(i >=1 && k>=1 && i<size.getX()-1 && k<size.getY()-1){
                }
                else {
                    board[count] = new Wall(i,k);
                    count++;
                }
            }
        }
    }

    private XY[] getRandXY(BoardConfig bc){
        XY[] randXY = new XY[bc.getAmountOfEntities()];
        int count = 0;
        boolean check;

        while(randXY[bc.getAmountOfEntities()-1] == null){
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

    public FlattenedBoard flattened(){
        Entity[][] flattendBoard = new Entity[size.getX()][size.getY()];
        for(int i=0; i<board.length-1; i++){
            flattendBoard[board[i].getPlace().getX()][board[i].getPlace().getY()] = board[i];
        }
        return new FlattenedBoard(flattendBoard,this);
    }

    public MasterSquirrel[] getPlayer(){

        int c=0;
        MasterSquirrel[] player = new MasterSquirrel[players];

        for(int i=0; i<board.length-1; i++){
            if (board[i] instanceof MasterSquirrel){
                player[c] = (MasterSquirrel) board[i];
                c++;
            }
        }
        return player;
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
            board[tboard.length-1] = new GoodPlant(x,y);
            count++;
        }
        if(e instanceof BadPlant){
            board[tboard.length-1] = new BadPlant(x,y);
            count++;
        }
        if(e instanceof GoodBeast){
            board[tboard.length-1] = new GoodBeast(x,y);
            count++;
        }
        if(e instanceof BadBeast){
            board[tboard.length-1] = new BadBeast(x,y);
            count++;
        }
    }

    public void addEntity(Entity e){
        Entity[] tboard = board.clone();
        board = new Entity[tboard.length+1];

        for(int i=0; i<tboard.length-1; i++) {
            board[i] = tboard[i];
        }
        board[tboard.length-1] = e;
        count++;
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
                board[i].nextStep(flattened());
            }
        }
    }

    @Override
    public String toString() {
        String s = "";
        for(int i=0; i<board.length-1; i++){
            if(!(board[i] instanceof Wall)) {
                s+=board[i].toString();
                s+="\n";
            }
        }
        return s;
    }
}
