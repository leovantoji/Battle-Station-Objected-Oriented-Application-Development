package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import java.text.DecimalFormat;

//This menu is to process the LeShipyard Option from the LeShippeShoppeMenu

public class LeShipyardMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Ship> ships;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeShipyardMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		ships = mainController.createShipController().retrieveAllShips();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLeShipyard() {
		System.out.println("\n== BattleStations :: Le Shipyard ==\n");		

		for(int i=0; i<ships.size(); ++i) {
			Ship ship = ships.get(i);
			System.out.println((i+1) + ". " + ship.getName() + " (min: L" + ship.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLeShipyard();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= ships.size()) {
						cont = false;
						processLeShipyardDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + ships.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + ships.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLeShipyardDetails(int c) {
		System.out.println("\n== BattleStations :: Le Shipyard :: Details ==\n");

		Ship ship = ships.get(c-1);
		System.out.println("Ship: " + ship.getName() + "\n");
		System.out.println("Speed: " + ship.getSpeed());
		System.out.println("HP: " + ship.getHP());
		System.out.println("Slots: " + ship.getSlots());
		System.out.println("Capacity: " + ship.getCapacity());
		System.out.println("Level Required: " + ship.getLevel() + "\n");
		System.out.println("Gold: " + df.format(ship.getGold()) + " | Wood: " + ship.getWood() + " | Ore: " + ship.getOre() + " | Prock: " + ship.getProck());

		boolean cont = true;

		do {
			System.out.print("\n[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.ownShip() && userLoggedIn.getShip().getName().equals(ship.getName())) { //Check if user has already owned the ship
					System.out.println("You have already owned this ship!");
				} else {
					if(userLoggedIn.isAbleToBuy(ship)) {
						mainController.createInventoryController().unequipAll(userLoggedIn); //unequip all the parts and weapons that were previously equipped
						mainController.createUserController().updateUser(userLoggedIn); //save to file
						System.out.println("You have successfully buy the \"" + ship.getName() + "\" ship!");
					} else {
						System.out.println("You cannot buy this ship!"); 
						System.out.println("Check your level AND/OR resources!");
					}
				}		
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);
	}
}