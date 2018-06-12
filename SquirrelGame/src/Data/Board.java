package Data;

import BotAPI.BotControllerFactory;
import Logs.SquirrelLogger;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * The Board with all the knowledge about every Entity
 */
public class Board{

    private List<Entity> board;
    private static Logger log = new SquirrelLogger().log;
    private XY size;
    private Random rand = new Random();
    private int count = 0;
    private int players = 0;
    private long remainingSteps;
    //ID's: 0 = Wand , 1 = BAD_BEAST , 2 = GoodBeast , 3 = BAD_PLANT , 4 = GOOD_PLANT , 5 = MasterS , 6 = MiniS

    /**
     * Initialize the Board
     * @param bc
     */
    public Board(BoardConfig bc){
        board = new LinkedList<>();
        size = bc.getSize();
        XY [] xy = getRandXY(bc);
        remainingSteps = bc.getRemainingSteps();
        for(int i=0; i<bc.getAmountOfBadBeast(); i++){
            board.add(new BadBeast(xy[count].getX(),xy[count].getY()));
            count++;
        }
        for(int i=0; i<bc.getAmountOfBadPlant(); i++){
            board.add(new BadPlant(xy[count].getX(),xy[count].getY()));
            count++;
        }
        for(int i=0; i<bc.getAmountOfGoodBeast(); i++){
            board.add(new GoodBeast(xy[count].getX(),xy[count].getY()));
            count++;
        }
        for(int i=0; i<bc.getAmountOfGoodPlant(); i++){
            board.add(new GoodPlant(xy[count].getX(),xy[count].getY()));
            count++;
        }
        for(int i=0; i<bc.getAmountOfWall(); i++){
            board.add(new Wall(xy[count].getX(),xy[count].getY()));
            count++;
        }
        for(int i = 0; i<bc.getAmountOfHandOperated(); i++){
            board.add(new HandOperatedMasterSquirrel(xy[count].getX(), xy[count].getY()));
            players++;
            count++;
        }
        for(int i=0; i<bc.getAmountOfBots(); i++){
            board.add(new MasterSquirrelBot(xy[count].getX(), xy[count].getY(), getNextFactory(bc.getBotNames()[i])));
            players++;
            count++;
        }
        for(int i=0; i<size.getX(); i++){
            for(int k=0; k<size.getY(); k++){
                if(i >=1 && k>=1 && i<size.getX()-1 && k<size.getY()-1){
                }
                else {
                    board.add(new Wall(i,k));
                    count++;
                }
            }
        }
        log.fine("Board created");
    }

    /**
     * To create different BotController for each and every Bot.
     * @param s
     * @return
     */
    private BotControllerFactory getNextFactory(String s){
        try {
            Class factory = Class.forName("BotImpl." + s);
            return (BotControllerFactory) factory.newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return null;
    }

    /**
     * Get random positions on the Board
     * @param bc
     * @return
     */
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

    /**
     * Make a new FlattenedBoard to keep it up to date
     * @return
     */
    public FlattenedBoard flattened(){
        Entity[][] flattenedBoard = new Entity[size.getX()][size.getY()];
        for(Entity e : board){
            flattenedBoard[e.getPlace().getX()][e.getPlace().getY()] = e;
        }
        return new FlattenedBoard(flattenedBoard,this);
    }

    /**
     * Get a Player Array with the Number of Players
     * @return
     */
    public MasterSquirrel[] getPlayer(){
        int c=0;
        MasterSquirrel[] player = new MasterSquirrel[players];

        for(Entity e : board){
            if (e instanceof MasterSquirrel){
                player[c] = (MasterSquirrel) e;
                c++;
            }
        }
        return player;
    }

    public void removeEntity(Entity e){
        board.remove(e);
    }

    public void addEntity(Entity e){
        board.add(e);
    }

    /**
     * To kill an Entity and replace it on a Different Random position.
     * @param e
     * @param fb
     */
    public void killAndReplace(Entity e, FlattenedBoard fb) {
        XY xy;
        do{
            xy = new XY(rand.nextInt((size.getX()-2))+1,rand.nextInt((size.getY()-2))+1);
        }while(fb.getEntityType(xy) == EntityType.NONE);
        try {
            board.set(board.indexOf(e),e.getClass().getDeclaredConstructor(int.class,int.class).newInstance(xy.getX(),xy.getY()));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e1) {
            e1.printStackTrace();
        }
    }

    public XY getSize() {
        return size;
    }

    public void update() {
        board.forEach(entity -> {
            if(entity instanceof Character){
                entity.nextStep(flattened());
            }
        });
        if(remainingSteps > 0){
            remainingSteps--;
        }
    }

    public long getRemainingSteps(){
        return remainingSteps;
    }

    @Override
    public String toString() {
        String s = "";
        for(Entity e : board){
            if(!(e instanceof Wall)) {
                s+=e.toString();
                s+="\n";
            }
        }
        return s;
    }
}
