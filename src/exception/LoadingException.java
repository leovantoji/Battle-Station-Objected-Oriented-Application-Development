package exception;
//Signals an attempt to load data from persistent store has failed

public class LoadingException extends Exception {
    
    //No-argument constructor
    public LoadingException() {
    }

    /**
    *Default constructor of LoadingException
    *@param message the detail message
    */
    public LoadingException(String message) {
        super(message + ". Unable to load from file");
    }
}