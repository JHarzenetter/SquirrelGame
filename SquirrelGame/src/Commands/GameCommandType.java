package Commands;

public enum GameCommandType implements CommandTypeInfo {
    HELP("help","list all commands" , "help"),
    EXIT("exit" , "exit pogramm" , "exit"),
    ALL("all" , "" , ""),
    LEFT("left" , "move left" , "move"),
    UP("up" , "move up" , "move"),
    DOWN("down" , "move down" , "move"),
    RIGHT("right" , "move right" , "move"),
    NONE("none" , "dont move" , "move"),
    MASTER_ENERGY("master_energy" , "shows masters Energy" , "playerengery"),
    SPAWN_MINI("spawn_mini" , "spawns a minisquirrel , energy" , "minispawn" , int.class);

    private final Class<?> param;
    private final String message;
    private final String name;
    private final String method;

    GameCommandType(String string, String s , String e) {
        name = string;
        message = s;
        method = e;
        param = null;
    }

    GameCommandType(String spawn_mini, String s, String e,Class<?> pclass) {
        this.name = spawn_mini;
        this.message = s;
        method = e;
        param = pclass;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHelpText() {
        return message;
    }

    @Override
    public Class<?>[] getParamTypes() {
        if(param == null)
            return null;
        return new Class<?>[]{param};
    }

    @Override
    public String methodType() {
        return method;
    }


}
