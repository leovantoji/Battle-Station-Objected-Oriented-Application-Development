package logger;

import java.io.*;
import java.util.logging.*;
import application.BattleStationsApplication;

//This class provides functions to log events to file

public class BSLogger {

    //Creation of a Logger object
    private final static Logger logger = Logger.getLogger(BattleStationsApplication.class.getName());

    //No-argument constructor
    public BSLogger() {
        try {
            FileInputStream configFile = new FileInputStream("./config/logging.properties");
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException e) {
            System.out.println("Sorry we could not open configuration file. The application shall terminate.");
            System.exit(1);
        }
    }

    /**
    *Append a new log in the file
    *@param messageType     Either "INFO" or "ERROR"
    *@param message         The message that contains details of the event to be logged
    */
    public void log(String messageType, String message) {
        log(messageType, message, null);
    }

    /**
    *Use the Logger object to log the event
    *@param messageType     Either "INFO" or "ERROR"
    *@param message         The message that contains details of the event to be logged
    *@param username        Username of the user who triggered this event
    */
    public void log(String messageType, String message, String username) {

        Level level = null;
        //Prepare level value for the Logger object
        if (messageType.equals("INFO")) {
            level = Level.INFO;
        } else if (messageType.equals("ERROR")) {
            level = Level.SEVERE;
        } else {
            level = Level.WARNING;
        }

        //Check if the user has logged in successfully or not
        if (username != null) {
            message += "\n User logged in: " + username + ".\n";
        } else {
            message += "\n No User logged in yet.\n";
        }

        //Invoke the log method from the Logger class
        logger.log(level, message);
    }
}