package menu;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.String;
import java.io.Console;

import controller.MainController;
import entity.*;
import exception.RegistrationException;

//Boundary class that allows users to login to this Battle Stations Application

public class BSLoginMenu {
	
	//Attributes
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController MainController class that provides Data Manager objects
	*/
	public BSLoginMenu(MainController mainController) {
		this.mainController = mainController;
		sc = new Scanner(System.in);
	}

	//Display the login menu to console
	public void displayLoginMenu() {
		System.out.println("\n== BattleStations :: Welcome ==\n");

		System.out.println("Good morning, player!");
		System.out.println("1. Login");
		System.out.println("2. Register");
		System.out.println("3. Exit");
		System.out.println();
		System.out.print("Enter your choice > ");
	}

	//Read user's selection of menu option and execute the request
	public void readOption() {
		int choice = 0;

		do {
			displayLoginMenu();

			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch(choice) {
					case 1: 
						processLogin();
						break;
					case 2: 
						processRegister();
						break;
					case 3:
						processExit();
						break;
					default:
						//Display error message
						System.out.println("Please enter a valid option (1 or 3)!");
				}
			} catch(InputMismatchException e) {
				//Display error message
				System.out.println("Please enter a valid option (1 or 3)!");
				sc.nextLine(); //flush
			}
		} while(choice != 3);
	}

	/**
	*Get user to provide username and password
	*If user logins successfully, launch the BSMainMenu object
	*Otherwise, go back to the login menu
	*/
	public void processLogin() {
		System.out.println("\n== BattleStations :: Login ==\n");

		System.out.print("Enter your username > ");
		String username = sc.nextLine();
		String password = "";

		//The password entered is masked in the console
		Console console = System.console();
		if (console != null && (password = new String(console.readPassword("Enter your password > "))) != null) {
		}

		//Authenticate User
		User user = mainController.createUserController().authenticateUser(username, password);
		
		//If username and password is correct, the main menu is launched. Otherwise, go back to the login menu
		if(user != null) {
			//Check if this is the first time of the day that the user logs in
			mainController.createUserController().isFirstTimeLoggedIn(user);

			//Launch the main menu
			BSMainMenu mainMenu = new BSMainMenu(mainController, user);
			mainMenu.readOption();
		} else {
			System.out.println("Sorry, wrong username and/or password!");
		}
	}

	/**
	*Registration process for new user
	*Prompt the user to enter his/her details, then create a new account
	*/
	public void processRegister() {
		System.out.println("\n== Battle Stations :: Registration ==\n");
		
		boolean cont = true; 
		
		//Get the username input
		//Repeatedly prompt for username until a valid username input is keyed in
		String username = "";
		while(cont) {
			cont = false;
			System.out.print("Enter your username > ");
			username = sc.nextLine();

			User user = mainController.createUserController().retrieveUser(username);

			if(user != null) {
				System.out.println("Username : " + username + " has already been registered!");
				System.out.println("Please choose another username.");
				cont = true;
			}	
		}
		
		//Get the password input
		//If password does not match, prompt the user to enter the password again
		String password = "";
		while(!cont) {
			cont = true;
			System.out.print("Enter your password > ");
			password = sc.nextLine();
			System.out.print("Confirm your password > ");
			String confirmPassword = sc.nextLine();

			if(!(password.equals(confirmPassword))) {
				System.out.println("Password and Confirmed Password does not match!");
				System.out.println("Please re-type your password.");
				cont = false;
			}
		}
		
		//Prompt the user to choose his/her character type until a valid character type input is keyed in
		char character = 0;
		while(cont) {
			cont = false;
			System.out.print("Pirate (P) / (E)xplorer > ");
			String characterStr = sc.nextLine().toUpperCase().replaceAll(" ","");
			character = characterStr.charAt(0);

			if(characterStr.length() != 1 || (character != 'P' && character != 'E')) {
				System.out.println("Please choose a valid character type (P or E)!");
				cont = true;
			}	
		}

		//Account Creation
		try {
			BSDate dateJoined = new BSDate();
			BSDate lastLoggedIn = dateJoined;
			mainController.createUserController().addUser(username, password, character, dateJoined, lastLoggedIn);
			System.out.println("Account successfully created");
		} catch(RegistrationException e) {
			System.out.println(e.getMessage());
		}
	}

	//Exit the application
	public void processExit() {
		System.out.println("Bye Bye!");
		System.exit(1);
	}
}