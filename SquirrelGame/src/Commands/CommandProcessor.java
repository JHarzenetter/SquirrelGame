package Commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class CommandProcessor {

    CommandScanner commandScanner;
    Command command;
    BufferedReader reader;
    PrintStream outputStream;

    CommandProcessor(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        outputStream = System.out;
    }

    public void process(){
        while(true){
            commandScanner = new CommandScanner(CommandInfo.values() , reader , outputStream);

            command = commandScanner.next();

            CommandInfo commandInfo = (CommandInfo) command.getCommandTypeInfo();
            switch (commandInfo){
                case EXIT:
                    System.exit(0);
                case HELP:
                    help();
                    break;
                case ADDF:
                    break;
                case ADDI:
                    break;
                case ECHO:
                    break;
                default:
                    break;
            }
        }
    }

    private void help(){
        for(int i=0; i<commandScanner.commandTypeInfos.length; i++){
            System.out.println(commandScanner.commandTypeInfos[i].getName()+": "+commandScanner.commandTypeInfos[i].getHelpText());
        }
    }

    public static void main(String[] args){
        CommandProcessor cp = new CommandProcessor();

        cp.process();
    }
}
