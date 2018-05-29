package Logs;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class SquirrelLogger {

    public static Logger log = Logger.getLogger("Logger");
    FileHandler fh;

    public SquirrelLogger(){
        try {
            fh = new FileHandler("C:\\SquirrelLogs\\LogFile.txt"); // TODO: properties file auslagern (siehe skript)
            log.addHandler(fh);
            fh.setFormatter(new SimpleFormatter());

        } catch (IOException e) {
            e.printStackTrace();
        }
        log.setUseParentHandlers(false);
    }
}
