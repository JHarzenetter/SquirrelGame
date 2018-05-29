package Logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SquirrelLogger {

    public static Logger log;
    FileHandler fh;

    public SquirrelLogger(){
        log = Logger.getLogger("Logger");
        try {
            fh = new FileHandler("C:\\SquirrelLogs\\LogFile.txt"); // WHY NOT WORKING?
            log.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
