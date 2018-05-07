package Commands;

public interface CommandTypeInfo {

    String getName();
    String getHelpText();
    Class<?>[] getParamTypes();
    String methodType();
}
