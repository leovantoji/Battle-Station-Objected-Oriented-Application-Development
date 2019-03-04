package exception;
//Signals an attempt to register new user has failed

public class RegistrationException extends Exception {
    
    //No-argument constructor
    public RegistrationException() {
    }

    /**
    *Default constructor of AddUserException
    *@param message the detail message
    */
    public RegistrationException(String message) {
        super(message + ". Unable to register user");
    }
}