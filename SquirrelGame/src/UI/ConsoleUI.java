package UI;

import Commands.*;
import Data.BoardView;
import Data.GameImpl;
import Data.XY;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ConsoleUI implements UserInterface{

    private CommandTypeInfo[] cti;
    private BufferedReader reader;
    private PrintStream printer;

    public ConsoleUI(){
        cti = GameCommandType.values();
        reader = new BufferedReader(new InputStreamReader(System.in));
        printer = System.out;
    }

    @Override
    public Command getCommand(){
        CommandScanner commandScanner = new CommandScanner(cti,reader,printer);
        return commandScanner.next();
    }

    @Override
    public void message(String msg) {

    }

    public void render(BoardView view){
         String s = "";
        for(int i = 0; i< view.getSize().getY(); i++){
            for(int k = 0; k< view.getSize().getX(); k++){
                s += view.getEntityType(new XY(k,i)).getWert();
            }
            s= s + "\n";
        }
        System.out.println("" + s);
    }
}
