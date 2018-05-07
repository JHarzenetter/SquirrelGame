package Commands;

public class Command {

    private CommandTypeInfo commandTypeInfo;
    private Object object;

    Command(CommandTypeInfo commandTypeInfo,Object[] object){
        this.commandTypeInfo = commandTypeInfo;
        this.object = object;
    }

    public CommandTypeInfo getCommandTypeInfo() {
        return commandTypeInfo;
    }

    public Object getObject() {
        return object;
    }
}
