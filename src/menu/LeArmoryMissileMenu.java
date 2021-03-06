package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LeArmoryMissile Option from the LeArmoryMenu

public class LeArmoryMissileMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Missile> missiles;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeArmoryMissileMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		missiles = mainController.createWeaponController().retrieveAllMissiles();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLeArmoryMissile() {
		System.out.println("\n== BattleStations :: Le Armory :: Missile ==\n");		

		for(int i=0; i<missiles.size(); ++i) {
			Missile missile = missiles.get(i);
			System.out.println((i+1) + ". " + missile.getName() + " (min: L" + missile.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLeArmoryMissile();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= missiles.size()) {
						cont = false;
						processLeArmoryMissileDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + missiles.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + missiles.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLeArmoryMissileDetails(int c) {
		System.out.println("\n== BattleStations :: Le Armory :: Missile :: Details ==\n");
		Missile missile = missiles.get(c-1);

		System.out.println("Missile: " + missile.getName() + "\n");
		System.out.println("Range: " + missile.getRange());
		System.out.println("Min Damage: " + missile.getMinDamage());
		System.out.println("Max Damage: " + missile.getMaxDamage());
		System.out.println("Weight: " + missile.getWeight());
		System.out.println("Level Required: " + missile.getLevel() + "\n");
		System.out.println("Gold: " + df.format(missile.getGold()) + " | Wood: " + missile.getWood() + " | Ore: " + missile.getOre() + " | Prock: " + missile.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(missile)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Weapon", "Missile", missile.getName(), false, missile.getLevel(), 0, 0, 
						missile.getRange(), missile.getMinDamage(), missile.getMaxDamage(), missile.getMinDamage(), missile.getMaxDamage(), missile.getWeight(), 0, userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Missile!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}