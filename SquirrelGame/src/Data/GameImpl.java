package Data;

import Commands.Command;
import Commands.GameCommandType;
import UI.ConsoleUI;
import UI.MoveDirection;
import UI.UserInterface;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameImpl extends Game {

    private UserInterface ui;
    private FlattenedBoard fb;
    private HandOperatedMasterSquirrel player;
    private Command c;
    private PrintStream printer = System.out;
    private Board b;

    public GameImpl(State state , Board b) {
        super(state);
        ui = new ConsoleUI();
        this.b = b;
        player = b.getPlayer();
    }

    @Override
    protected void update() {
        state.update();
    }

    public void render(){
        ui.render(state.flattenedBoard());
        fb = state.flattenedBoard();
    }

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
        //?????????????????????????
    }

    private void playerengery(){
        printer.println(player.getEnergy());
    }

    private void move(){
        player.setMoveDirection(MoveDirection.valueOf(c.getCommandTypeInfo().getName()));
    }

    private void minispawn(Integer i){
        b.addEntity(player.createMini(b.getID(),i));
        player.setMoveDirection(MoveDirection.None);
    }
}
