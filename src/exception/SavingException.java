package exception;
//Signals an attempt to load data from persistent store has failed

public class SavingException extends Exception {
    
    //No-argument constructor
    public SavingException() {
    }

    /**
    *Default constructor of SavingException
    *@param message the detail message
    */
    public SavingException(String message) {
        super(message + ". Unable to save to file");
    }
}