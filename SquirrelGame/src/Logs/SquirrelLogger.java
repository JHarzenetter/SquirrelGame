package Logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class SquirrelLogger {

    public static Logger log = Logger.getLogger("Logger");
    FileHandler fh;

    public SquirrelLogger(){
        try {
            fh = new FileHandler("C:\\SquirrelLogs\\LogFile.txt");
            log.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.setUseParentHandlers(false);
    }
}
