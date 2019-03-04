package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the Le Shippe Shoppe Option from the Main Menu

public class LeShippeShoppeMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeShippeShoppeMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sc = new Scanner(System.in);
	}

	public void displayLeShippeShoppe() {
		System.out.println("\n== BattleStations :: Le Shippe Shoppe ==\n");

		System.out.println("1. Le Shipyard");
		System.out.println("2. Le Armory");
		System.out.println("3. Le Part");
	}

	public void readOption() {
		boolean cont = true;
		displayLeShippeShoppe();

		do {
			System.out.print("\n[R]eturn to main | Enter choice > ");
			String choice = sc.nextLine();
			
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);
					
					switch(c) {
						case 1:
							cont = false;
							LeShipyardMenu leShipyardMenu = new LeShipyardMenu(mainController, userLoggedIn);
							leShipyardMenu.readOption();
							break;
						case 2:
							cont = false;
							LeArmoryMenu leArmoryMenu = new LeArmoryMenu(mainController, userLoggedIn);
							leArmoryMenu.readOption();
							break;
						case 3:
							cont = false;
							LePartMenu lePartMenu = new LePartMenu(mainController, userLoggedIn);
							lePartMenu.readOption();
							break;
						default:
							System.out.println("Please enter a valid option (1 to 3)!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid option (1 to 3)!");
				}
			}
		} while(cont);
	}
}