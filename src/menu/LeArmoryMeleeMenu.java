package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LeArmoryMelee Option from the LeArmoryMenu

public class LeArmoryMeleeMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Melee> melees;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeArmoryMeleeMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		melees = mainController.createWeaponController().retrieveAllMelee();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLeArmoryMelee() {
		System.out.println("\n== BattleStations :: Le Armory :: Melee ==\n");		

		for(int i=0; i<melees.size(); ++i) {
			Melee melee = melees.get(i);
			System.out.println((i+1) + ". " + melee.getName() + " (min: L" + melee.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLeArmoryMelee();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= melees.size()) {
						cont = false;
						processLeArmoryMeleeDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + melees.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + melees.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLeArmoryMeleeDetails(int c) {
		System.out.println("\n== BattleStations :: Le Armory :: Melee :: Details ==\n");
		Melee melee = melees.get(c-1);

		System.out.println("Melee: " + melee.getName() + "\n");
		System.out.println("Range: " + melee.getRange());
		System.out.println("Min Damage: " + melee.getMinDamage());
		System.out.println("Max Damage: " + melee.getMaxDamage());
		System.out.println("Weight: " + melee.getWeight());
		System.out.println("Level Required: " + melee.getLevel() + "\n");
		System.out.println("Gold: " + df.format(melee.getGold()) + " | Wood: " + melee.getWood() + " | Ore: " + melee.getOre() + " | Prock: " + melee.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(melee)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Weapon", "Melee", melee.getName(), false, melee.getLevel(), 0, 0, 
						melee.getRange(), melee.getMinDamage(), melee.getMaxDamage(), melee.getMinDamage(), melee.getMaxDamage(), melee.getWeight(), 0, userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Melee!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}