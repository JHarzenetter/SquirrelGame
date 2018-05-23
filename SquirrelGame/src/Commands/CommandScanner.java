package Commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private BufferedReader reader;
    CommandTypeInfo[] commandTypeInfos;
    private PrintStream outputStream;
    private Command buffer = null;

    public CommandScanner(CommandTypeInfo[] cti, BufferedReader input, PrintStream outputStream){
        reader = input;
        commandTypeInfos = cti;
        this.outputStream = outputStream;
    }

    public Command next(){
        try {
            outputStream.println("Input Command");
            String s;
            s = reader.readLine();

            System.out.println(s);

            CommandTypeInfo commandTypeInfo = null;
            Object[] o = new Object[]{};
            String su;

            if (s.contains(",")) {
                su = s.substring(0, s.indexOf(',')).trim();
            } else {
                su = s.trim();
            }

            for (int i = 0; i < commandTypeInfos.length; i++) {
                if (su.trim().equals(commandTypeInfos[i].getName())) {
                    commandTypeInfo = commandTypeInfos[i];
                    //outputStream.println("found " + commandTypeInfo.getName());
                    break;
                }
            }

            if(commandTypeInfo == null){
                throw new ScanException("ScanExeption!!"); //TODO: constuctor exeption
            }
            if (commandTypeInfo.getParamTypes() != null) {
                s = s.substring(s.indexOf(',') + 1).trim();
                o = new Object[commandTypeInfo.getParamTypes().length];

                for (int i = 0; i < o.length; i++) {
                    if (i<o.length-1) {
                        //s = s.substring(s.indexOf(',') + 1).trim();
                        o[i] = makeStringtoObject(s, commandTypeInfo.getParamTypes()[i]);
                    } else {
                        o[i] = makeStringtoObject(s.trim(), commandTypeInfo.getParamTypes()[i]);
                    }
                }
            }
            buffer = new Command(commandTypeInfo, o);
            return new Command(commandTypeInfo, o);
        } catch (ScanException s){
            outputStream.println("Wrong Input");
            return next();
        } catch (IOException e){
            return null;
        }
    }

    private Object makeStringtoObject(String su, Class<?> commandTypeInfo) throws ScanException {
        if(su.isEmpty()){
            throw new ScanException("Empty String!");
        }
        switch(commandTypeInfo.getName()){
            case "int":
                return Integer.parseInt(su.trim());
            case "java.lang.String":
                return su;
            case "float":
                return Float.parseFloat(su.trim());
            default:
                throw new ScanException("String isn't valid");
        }

    }
}
