package Commands;

import java.util.Scanner;

public class CommandProcessor {

    CommandScanner commandScanner;
    Command command;

    public void process(){
        while(true){
            commandScanner = new CommandScanner(CommandInfo.values() , read());

            command = commandScanner.next();

            CommandInfo commandInfo = command.getCommandType();
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

    public String read(){  //read playerinput
        System.out.println("Commands expected");
        String read;
        Scanner s = new Scanner(System.in);
        read = s.next();

        return read;
    }

    public static void main(String[] args){
        CommandProcessor cp = new CommandProcessor();

        cp.process();
    }
}
