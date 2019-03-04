package controller;

import java.util.ArrayList;
import datamanager.UserManager;
import exception.*;
import entity.BSDate;
import entity.User;
import logger.BSLogger;

//Controller class for Users

public class UserController {
	
	//Attributes
	private UserManager userManager;
	private User userLoggedIn;
	private BSLogger logger;

	//No-argument Constructor
	public UserController() {
		logger = new BSLogger();

		try {
			userManager = new UserManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage(), userLoggedIn.getUsername());
		}
	}

	//Constructor
	public UserController(User userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
		logger = new BSLogger();

		try {
			userManager = new UserManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage(), userLoggedIn.getUsername());
		}
	}	

	/**
    *Retrieve User with the specified username. Check whether the specified password is the same as the User's password
    *@param username Username to search for
    *@param password Password to verify against
    *@return If authentication is successful, return the User object. Otherwise, return null
    */
	public User authenticateUser(String username, String password) {
		User user = userManager.retrieveUser(username);

		if(user != null && user.getPassword().equals(password)) {
			userLoggedIn = user;
			String logMessage = "Successful Login: User name: \"" + username + "\" Password entered: \"" + password + "\"";
            logger.log("INFO", logMessage, userLoggedIn.getUsername());
		} else {
			userLoggedIn = null;
            String logMessage = "Login Error: User name: \"" + username + "\" Password entered: \"" + password + "\"";
            logger.log("ERROR", logMessage);
		}

		return userLoggedIn;
	}

	/**
	*@return the user who is currently logging in the application
	*/
	public User getUserLoggedIn() {
		return userLoggedIn;
	}

	/**
	*Retrieve User object with the specified username
	*@param username Username to search for
	*@return the User object that has the matching username. Otherwise, return null
	*/
	public User retrieveUser(String username) {
		return userManager.retrieveUser(username);
	}

	/**
	*Add a new user with the specified username, password, and character type
	*@param username
	*@param password
	*@param character
	*@throws RegistrationException if the new user account could not be created
	*/
	public void addUser(String username, String password, char character, BSDate dateJoined, BSDate lastLoggedIn) throws RegistrationException {
		try {
			userManager.addUser(username, password, character, dateJoined, lastLoggedIn);
		} catch(RegistrationException e) {
			logger.log("ERROR", e.getMessage());
			throw e;
		}
	}

	/**
    *Update the information about an existing user
    *@param user User object with updated information
    */
	public void updateUser(User user) {
		try {
			userManager.updateUser(user);
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Check whether this is the first time of the day the user logs in
	*@param user
	*@return true if this is the first time of the day the user logs in. Otherwise, return false
	*/
	public boolean isFirstTimeLoggedIn(User user) {
		//BSDate currentDate object represents the most current time that the user logs in the application
		BSDate currentDate = new BSDate();
		BSDate lastLoggedIn = user.getLastLoggedIn();

		if(!currentDate.sameDate(lastLoggedIn)) {
			//Add 120 action point if this is the first login of the day
			user.addAP(120);

			//Update the new lastLoggedIn BSDate object for the user
			user.setLastLoggedIn(currentDate);
			updateUser(user);
			return true;
		}

		//Update the new lastLoggedIn BSDate object for the user
		user.setLastLoggedIn(currentDate);
		updateUser(user);
		return false;
	}

	/**
	*Retrieve all Users except a particular user (For PVP)
	*@return all Users except a particular user (For PVP)
	*/
	public ArrayList<User> retrieveAllDefenders(User user) {
		return userManager.retrieveAllDefenders(user);
	}

	//Log out the currently logged in user
	public void logout() {
		String logMessage = "User: \"" + getUserLoggedIn().getUsername() + "\" just logged out.";
		logger.log("INFO", logMessage);
		userLoggedIn = null;
	}
}