package menu;

import java.io.*;
import java.util.*;
import controller.*;
import entity.*;
import java.text.DecimalFormat;

//Boundary class that allows user to check his account details and PVP

public class BSMainMenu {

	//Attributes
	private MainController mainController;
	private User userLoggedIn;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController 	MainController class that provides data manager objects 
	*@param userLoggedIn 	Provide the user that is currently logging in
	*/
	public BSMainMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	//Display the user's statistics and various options to console
	public void displayMainMenu() {
		System.out.println("\n== BattleStations :: Main Menu ==\n");

		System.out.println("Captain: " + userLoggedIn.getUsername() + " [L" + userLoggedIn.getLevel() + "]");
		System.out.println("AP: " + userLoggedIn.getAP() + "\t\t\t\tHP: " + userLoggedIn.getHP() + "/" + userLoggedIn.getFullHP());
		System.out.println("Gold: " + df.format(userLoggedIn.getGold()) + "\t\tWood: " + userLoggedIn.getWood());
		System.out.println("Ore: " + userLoggedIn.getOre() + "\t\t\tProck: " + userLoggedIn.getProck());
		System.out.println("1. View my vital statistics");
		System.out.println("2. My hangar");
		System.out.println("3. Le Shippe Shoppe");
		System.out.println("4. PVP");
		System.out.println("5. Exit");
	}

	//Read user's selection of menu option and executes the request
	public void readOption() {
		int choice = 0;

		do {
			displayMainMenu();
			System.out.print("\nEnter your choice > ");

			try {
				choice = sc.nextInt();
				sc.nextLine();

				switch(choice) {
					case 1: 
						ViewMyVitalStatisticsMenu viewMyVitalStatisticsMenu = new ViewMyVitalStatisticsMenu(mainController, userLoggedIn);
						viewMyVitalStatisticsMenu.readOption();
						break;
					case 2: 
						MyHangarMenu myHangarMenu = new MyHangarMenu(mainController, userLoggedIn);
						myHangarMenu.readOption();
						break;
					case 3:
						LeShippeShoppeMenu leShippeShoppeMenu = new LeShippeShoppeMenu(mainController, userLoggedIn);
						leShippeShoppeMenu.readOption();
						break;
					case 4:
						PVPMenu pvpMenu = new PVPMenu(mainController, userLoggedIn);
						pvpMenu.readOption();
						break;
					case 5:
						processExit();
					default:
						//Display error message
						System.out.println("Please enter a valid option (1 to 5)!");
				}
			} catch(InputMismatchException e) {
				//Display error message
				System.out.println("Please enter a valid option (1 to 5)!");
				sc.nextLine(); //flush
			}
		} while(choice != 5);
	}

	public void processExit() {
		System.out.println("\nBye Bye!");
		mainController.createUserController(userLoggedIn).logout();
		System.exit(1);
	}
}