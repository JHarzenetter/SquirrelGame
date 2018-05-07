package Commands;

public enum CommandInfo implements CommandTypeInfo{

    EXIT("exit" , "exit pogramm"),
    HELP("help","list all commands"),
    ADDI("addi" , "param1 , param2 : simple integer add" , int.class , int.class),
    ADDF("addf" , "param1 , param2 : simple float add" , float.class , float.class),
    ECHO("echo" , "param1 , param2 : echos param1 prams2 times" , String.class , int.class);


    private String message;
    private String name;
    private Class param1;
    private Class param2;

    CommandInfo(String name, String message) {
        this.name = name;
        this.message = message;
    }

    CommandInfo(String name, String message, Class pClass, Class p2Class){
        this.name = name;
        this.message = message;
        param1 = pClass;
        param2 = p2Class;
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
        Class[] c = {param1 , param2};
        return c;
    }

    @Override
    public CommandInfo getType() {
        return this;
    }
}