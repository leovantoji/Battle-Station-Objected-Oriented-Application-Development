package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LeArmoryCannon Option from the LeArmoryMenu

public class LeArmoryCannonMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Cannon> cannons;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeArmoryCannonMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		cannons = mainController.createWeaponController().retrieveAllCannons();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLeArmoryCannon() {
		System.out.println("\n== BattleStations :: Le Armory :: Cannon ==\n");		

		for(int i=0; i<cannons.size(); ++i) {
			Cannon cannon = cannons.get(i);
			System.out.println((i+1) + ". " + cannon.getName() + " (min: L" + cannon.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLeArmoryCannon();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= cannons.size()) {
						cont = false;
						processLeArmoryCannonDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + cannons.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + cannons.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLeArmoryCannonDetails(int c) {
		System.out.println("\n== BattleStations :: Le Armory :: Cannon :: Details ==\n");
		Cannon cannon = cannons.get(c-1);

		System.out.println("Cannon: " + cannon.getName() + "\n");
		System.out.println("Range: " + cannon.getRange());
		System.out.println("Min Damage: " + cannon.getMinDamage());
		System.out.println("Max Damage: " + cannon.getMaxDamage());
		System.out.println("Weight: " + cannon.getWeight());
		System.out.println("Level Required: " + cannon.getLevel() + "\n");
		System.out.println("Gold: " + df.format(cannon.getGold()) + " | Wood: " + cannon.getWood() + " | Ore: " + cannon.getOre() + " | Prock: " + cannon.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(cannon)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Weapon", "Cannon", cannon.getName(), false, cannon.getLevel(), 0, 0, 
						cannon.getRange(), cannon.getMinDamage(), cannon.getMaxDamage(), cannon.getMinDamage(), cannon.getMaxDamage(), cannon.getWeight(), 0, userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Cannon!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}