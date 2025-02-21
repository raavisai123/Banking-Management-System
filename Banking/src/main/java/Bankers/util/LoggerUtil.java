package Bankers.util;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggerUtil {

    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        logger.setLevel(Level.ALL);
    }

    // Method to log information messages
    public static void logInfo(String message) {
        logger.log(Level.INFO, message);
    }

    // Method to log error messages
    public static void logError(String message) {
        logger.log(Level.SEVERE, message);
    }
}
