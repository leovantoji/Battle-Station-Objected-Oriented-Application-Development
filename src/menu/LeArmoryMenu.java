package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the LeArmory Option from the LeShippeShoppeMenu

public class LeArmoryMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeArmoryMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sc = new Scanner(System.in);
	}

	public void displayLeArmory() {
		System.out.println("\n== BattleStations :: Le Armory ==\n");

		System.out.println("1. View Cannon");
		System.out.println("2. View SubCannon");
		System.out.println("3. View Missile");
		System.out.println("4. View Melee");
	}

	public void readOption() {
		boolean cont = true;
		displayLeArmory();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);
					
					switch(c) {
						case 1:
							cont = false;
							LeArmoryCannonMenu leArmoryCannonMenu = new LeArmoryCannonMenu(mainController, userLoggedIn);
							leArmoryCannonMenu.readOption();
							break;
						case 2:
							cont = false;
							LeArmorySubCannonMenu leArmorySubCannonMenu = new LeArmorySubCannonMenu(mainController, userLoggedIn);
							leArmorySubCannonMenu.readOption();
							break;
						case 3:
							cont = false;
							LeArmoryMissileMenu leArmoryMissileMenu = new LeArmoryMissileMenu(mainController, userLoggedIn);
							leArmoryMissileMenu.readOption();
							break;
						case 4:
							cont = false;
							LeArmoryMeleeMenu leArmoryMeleeMenu = new LeArmoryMeleeMenu(mainController, userLoggedIn);
							leArmoryMeleeMenu.readOption();
							break;
						default:
							System.out.println("Please enter a valid option (1 to 4)!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid option (1 to 4)!");
				}
			}
		} while(cont);
	}
}