package Data;

import Commands.Command;
import Commands.GameCommandType;
import UI.ConsoleUI;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Implementation from Console input
 */
public class GameImpl extends Game {

    private FlattenedBoard fb;
    private MasterSquirrel[] player;
    private Command c;
    private PrintStream printer = System.out;

    public GameImpl(State state , Board b) {
        super(state,b);
        ui = new ConsoleUI();
        player = b.getPlayer();
    }

    @Override
    public void update() {
        state.update();
    }

    @Override
    public void render(){
        ui.render(state.flattenedBoard());
        fb = state.flattenedBoard();
    }

    /**
     * to handle the input from Console
     */
    public void processInput(){
        do{
            c = ui.getCommand();
            Class<?>[] classes = new Class<?>[c.getObject().length];
            for(int i=0; i<c.getObject().length ; i++){
                classes[i] = c.getObject()[i].getClass();
            }
            try {
                Method method = this.getClass().getDeclaredMethod(c.getCommandTypeInfo().methodType() , classes);
                method.invoke(this , c.getObject());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }while(!(c.getCommandTypeInfo().methodType().equals("move") || c.getCommandTypeInfo().methodType().equals("minispawn")));
    }

    private void help(){
        for(int i=0; i<GameCommandType.values().length; i++) {
            printer.println(GameCommandType.values()[i].getName()+" "+GameCommandType.values()[i].getHelpText());
        }
    }

    private void exit(){
        System.exit(0);
    }

    private void all(){
        System.out.println(board.toString());
    }

    private void playerengery(){
        printer.println(player[0].getEnergy());
    }

    private void move(){
        player[0].setMoveDirection(XYsupport.valueOf(c.getCommandTypeInfo().getName()));
    }

    private void minispawn(Integer i){
        if(player[0].createMini(i) != null){
            board.addEntity(player[0].createMini(i));
            player[0].setMoveDirection(XY.ZERO_ZERO);
        }
    }
}
