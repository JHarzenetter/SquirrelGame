package Commands;

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    CommandInfo getType();
    Class<?>[] getParamTypes();
}
