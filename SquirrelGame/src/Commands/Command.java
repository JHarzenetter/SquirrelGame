package Commands;

public class Command {

    private String name;
    private String message;
    private Object[] params;
    private CommandInfo commandInfo;

    Command(CommandTypeInfo commandTypeInfo,Object[] params){
        this.commandInfo = commandTypeInfo.getType();
        name = commandTypeInfo.getName();
        message = commandTypeInfo.getHelpText();
        this.params = params;
    }

    public CommandInfo getCommandType(){
        return commandInfo.getType();
    }

    public Object getParams() {
        return params;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }
}
