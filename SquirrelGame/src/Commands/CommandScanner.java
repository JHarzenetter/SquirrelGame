package Commands;

import java.util.Scanner;

public class CommandScanner {

    String reader;
    CommandTypeInfo[] commandTypeInfos;
    Command command;
    int i = 0;
    CommandScanner(CommandTypeInfo[] cti , String input){
        reader = input;
        commandTypeInfos = cti;
    }

    public Command next(){
        if(reader.equals("help")){
            return new Command(commandTypeInfos[1],commandTypeInfos[1].getParamTypes());
        }
        if(reader.equals("exit")){
            return new Command(commandTypeInfos[0],commandTypeInfos[0].getParamTypes());
        }
        if(reader.equals("addi")){
            return new Command(commandTypeInfos[2],commandTypeInfos[2].getParamTypes());
        }
        if(reader.equals("echo")){
            return new Command(commandTypeInfos[4],commandTypeInfos[4].getParamTypes());
        }
        return new Command(commandTypeInfos[i++],commandTypeInfos[i++].getParamTypes());
    }
}
