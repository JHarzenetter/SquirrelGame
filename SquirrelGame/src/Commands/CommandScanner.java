package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private BufferedReader reader;
    CommandTypeInfo[] commandTypeInfos;
    private PrintStream outputStream;

    CommandScanner(CommandTypeInfo[] cti , BufferedReader input , PrintStream outputStream){
        reader = input;
        commandTypeInfos = cti;
        this.outputStream = outputStream;
    }

    public Command next() throws IOException, ScanException {

        outputStream.println("Input Command");
        String s = reader.readLine();

        CommandTypeInfo commandTypeInfo = null;
        Object[] o =  new Object[]{};
        String su;

        if(s.contains(",")){
            su = s.substring(0,s.indexOf(',')).trim();
        }else{
            su = s.trim();
        }

        for(int i=0; i < commandTypeInfos.length; i++){
            if(su.equals(commandTypeInfos[i].getName())){
                commandTypeInfo = commandTypeInfos[i];
                outputStream.println("found " + commandTypeInfo.getName());
                break;
            }
        }

        if(commandTypeInfo.getParamTypes() != new Class<?>[]{}){
            s = s.substring(s.indexOf(',')+1).trim();

            o = new Object[commandTypeInfo.getParamTypes().length];

            for(int i=0; i<o.length ; i++){
                if(s.contains(",")){
                    su = s.substring(0 , s.indexOf(',')).trim();
                    s = s.substring(s.indexOf(',')+1).trim();
                    o[i] = makeStringtoObject(su,commandTypeInfo.getParamTypes()[i]);
                }
            }
        }

        return new Command(commandTypeInfo,o);
    }

    private Object makeStringtoObject(String su, Class<?> commandTypeInfo) throws ScanException {

        switch(commandTypeInfo.getName()){
            case "int":
                return Integer.parseInt(su);
            case "java.lang.String":
                return su;
            case "float":
                return Float.parseFloat(su);
            default:
                throw new ScanException();
        }

    }
}
