package datamanager;

import java.io.*;
import java.util.*;
import java.text.*;

import entity.*;
import entity.BSDate;
import controller.ShipController;
import exception.*;

//Data Manager class to manage User object

public class UserManager {

	//Attributes
	private ArrayList<User> userList;
	private final String FILE_NAME = "data/users.csv";
	private final String CLASS_NAME = "UserManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load users' information from file
	*/
	public UserManager() throws LoadingException {
		//Creation of users.csv file
		PrintStream fileOut = null;
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) {
				fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
				fileOut.println("Username,Password,Character Type,Level,Stats Pts,Capacity Used,Full Capacity,Speed,Action Point,HP,"
					+ "Full HP,Total EXP,Slots,Gunnery,Craft,Navigation,Gold,Wood,Ore,Plasma Rock,Wins,Losses,Ship,Date Joined,Last Logged In");
			}
		} catch(IOException e) {
			String message = CLASS_NAME + " class: " + FILE_NAME + " NOT FOUND";
			throw new LoadingException(message);
		} finally {
			if(fileOut != null) {
				fileOut.close();
			}
		}

		userList = new ArrayList<User>();
		load();
	}

	/**
	*Load users' information from file
	*@throws LoadingException when unable to load users' information from file
	*/	
	private void load() throws LoadingException {
		Scanner fileIn = null;

		try {
			fileIn = new Scanner(new File(FILE_NAME));
			fileIn.useDelimiter(",|\r\n");
			fileIn.nextLine();

			while(fileIn.hasNext()) {
				String username = fileIn.next();
				String password = fileIn.next();
				
				String characterStr = fileIn.next();
				if(characterStr.length() != 1) {
					throw new InputMismatchException();
				}

				char character = characterStr.charAt(0);
				int level = fileIn.nextInt();
				double stats = fileIn.nextDouble();
				int capacity = fileIn.nextInt();
				int fullCapacity = fileIn.nextInt();
				int speed = fileIn.nextInt();
				int ap = fileIn.nextInt();
				int hp = fileIn.nextInt();
				int fullHP = fileIn.nextInt();
				int exp = fileIn.nextInt();
				int slots = fileIn.nextInt();
				double gunnery = fileIn.nextDouble();
				double craft = fileIn.nextDouble();
				double navigation = fileIn.nextDouble();
				int gold = fileIn.nextInt();
				int wood = fileIn.nextInt();
				int ore = fileIn.nextInt();
				int prock = fileIn.nextInt();
				int noWins = fileIn.nextInt();
				int noLosses = fileIn.nextInt();

				ShipController shipController = new ShipController();
				Ship ship = shipController.retrieveShipByName(fileIn.next());

				BSDate dateJoined = new BSDate(fileIn.next());
				BSDate lastLoggedIn = new BSDate(fileIn.next());

				userList.add(new User(username, password, character, level, stats, capacity, fullCapacity, speed, ap, hp, fullHP, 
					exp, slots, gunnery, craft, navigation, gold, wood, ore, prock, noWins, noLosses, ship, dateJoined, lastLoggedIn));
			}
		} catch(ParseException e) {
			//propagate error
			throw new InputMismatchException();
		} catch(InputMismatchException e) {
			//propagate error
			String message = "Reading error in File \"" + FILE_NAME + "\"";
			throw new LoadingException(message);
		} catch(FileNotFoundException e) {
			//propagate error
			String message = CLASS_NAME + " class : File " + FILE_NAME + " NOT FOUND";
            throw new LoadingException(message);
		} finally {
			if(fileIn != null) {
				fileIn.close();
			}
		}
	}

	/**
	*Save users' information to file
	*@throws SavingException when unable to save users' information to file
	*/
	private void save() throws SavingException {
		PrintStream fileOut = null;
		try {
			fileOut = new PrintStream(new FileOutputStream(FILE_NAME,false));
			fileOut.println("Username,Password,Character Type,Level,Stats Pts,Capacity Used,Full Capacity,Speed,Action Point,HP,"
				+ "Full HP,Total EXP,Slots,Gunnery,Craft,Navigation,Gold,Wood,Ore,Plasma Rock,Wins,Losses,Ship,Date Joined,Last Logged In");
			for(int i=0; i<userList.size(); ++i) {
				User user = userList.get(i);
				
				fileOut.print(user.getUsername());
				fileOut.print(",");
				fileOut.print(user.getPassword());
				fileOut.print(",");
				fileOut.print(user.getCharacter());
				fileOut.print(",");
				fileOut.print(user.getLevel());
				fileOut.print(",");
				fileOut.print(user.getStats());
				fileOut.print(",");
				fileOut.print(user.getCapacity());
				fileOut.print(",");
				fileOut.print(user.getFullCapacity());
				fileOut.print(",");
				fileOut.print(user.getSpeed());
				fileOut.print(",");
				fileOut.print(user.getAP());
				fileOut.print(",");
				fileOut.print(user.getHP());
				fileOut.print(",");
				fileOut.print(user.getFullHP());
				fileOut.print(",");
				fileOut.print(user.getEXP());
				fileOut.print(",");
				fileOut.print(user.getSlots());
				fileOut.print(",");
				fileOut.print(user.getGunnery());
				fileOut.print(",");
				fileOut.print(user.getCraft());
				fileOut.print(",");
				fileOut.print(user.getNavigation());
				fileOut.print(",");
				fileOut.print(user.getGold());
				fileOut.print(",");
				fileOut.print(user.getWood());
				fileOut.print(",");
				fileOut.print(user.getOre());
				fileOut.print(",");
				fileOut.print(user.getProck());
				fileOut.print(",");
				fileOut.print(user.getNoWins());
				fileOut.print(",");
				fileOut.print(user.getNoLosses());
				fileOut.print(",");
				fileOut.print(user.getShipName());
				fileOut.print(",");
				fileOut.print(user.getDateJoined().toString());
				fileOut.print(",");
				fileOut.println(user.getLastLoggedIn().toString());
			}
		} catch(FileNotFoundException e) {
			//propagate error
			String message = CLASS_NAME + " class : File " + FILE_NAME + " NOT FOUND";
            throw new SavingException(message);
		} finally {
			if(fileOut != null) {
				fileOut.close();
			}
		}
	}

	/**
	*Add new user to the user list when a new user is registered
	*@param username
	*@param password
	*@param character
	*@param dateJoined
	*@param lastLoggedIn
	*@throws RegistrationException when the new user cannot be registered
	*/
	public void addUser(String username, String password, char character, BSDate dateJoined, BSDate lastLoggedIn) throws RegistrationException {
		userList.add(new User(username, password, character, dateJoined, lastLoggedIn));
		try {
			save();
		} catch(SavingException e) {
			throw new RegistrationException();
		}
	}

    /**
    *Update the information about an existing user
    *@param user User object with updated information
    *@throws SavingException when unable to save user's information to file
    */	
	public void updateUser(User user) throws SavingException {
		User u = retrieveUser(user.getUsername());
		u.setCapacity(user.getCapacity());
		u.setFullCapacity(user.getFullCapacity());
		u.setSpeed(user.getSpeed());
		u.setStats(user.getStats());
		u.setGunnery(user.getGunnery());
		u.setCraft(user.getCraft());
		u.setNavigation(user.getNavigation());
		u.setHP(user.getHP());
		u.setFullHP(user.getFullHP());
		u.setAP(user.getAP());
		u.setSlots(user.getSlots());
		u.setLevel(user.getLevel());
		u.setEXP(user.getEXP());
		u.setShip(user.getShip());
		u.setResources(user.getGold(), user.getWood(), user.getOre(), user.getProck());
		u.setLastLoggedIn(user.getLastLoggedIn());
		save();
	}

	/**
	*Retrieve all available defenders for a particular user (For PVP)
	*@return all available defenders for a particular user (For PVP)
	*/
	public ArrayList<User> retrieveAllDefenders(User user) {
		ArrayList<User> list = new ArrayList<User>();
		
		for(int i=0; i<userList.size(); ++i) {
			User u = userList.get(i);
			if(!u.getUsername().equals(user.getUsername())) {
				if(u.ownShip() && u.getHP()>0) {
					list.add(userList.get(i));
				}
			}
		}
		
		return list;
	}

	/**
	*Retrieve user with specified username
	*@param username
	*@return User object with matching username. Otherwise, return null
	*/
	public User retrieveUser(String username) {
		for(int i=0; i<userList.size(); ++i) {
			User user = userList.get(i);
			if(user.getUsername().equals(username)) {
				return user;
			}
		}

		return null;
	}
}