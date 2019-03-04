package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LeArmorySubCannon Option from the LeArmoryMenu

public class LeArmorySubCannonMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<SubCannon> subCannons;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LeArmorySubCannonMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		subCannons = mainController.createWeaponController().retrieveAllSubCannons();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLeArmorySubCannon() {
		System.out.println("\n== BattleStations :: Le Armory :: SubCannon ==\n");		

		for(int i=0; i<subCannons.size(); ++i) {
			SubCannon subCannon = subCannons.get(i);
			System.out.println((i+1) + ". " + subCannon.getName() + " (min: L" + subCannon.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLeArmorySubCannon();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= subCannons.size()) {
						cont = false;
						processLeArmorySubCannonDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + subCannons.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + subCannons.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLeArmorySubCannonDetails(int c) {
		System.out.println("\n== BattleStations :: Le Armory :: SubCannon :: Details ==\n");
		SubCannon subCannon = subCannons.get(c-1);

		System.out.println("SubCannon: " + subCannon.getName() + "\n");
		System.out.println("Range: " + subCannon.getRange());
		System.out.println("Min Damage: " + subCannon.getMinDamage());
		System.out.println("Max Damage: " + subCannon.getMaxDamage());
		System.out.println("Weight: " + subCannon.getWeight());
		System.out.println("Level Required: " + subCannon.getLevel() + "\n");
		System.out.println("Gold: " + df.format(subCannon.getGold()) + " | Wood: " + subCannon.getWood() + " | Ore: " + subCannon.getOre() + " | Prock: " + subCannon.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(subCannon)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Weapon", "SubCannon", subCannon.getName(), false, subCannon.getLevel(), 0, 0, 
						subCannon.getRange(), subCannon.getMinDamage(), subCannon.getMaxDamage(), subCannon.getMinDamage(), subCannon.getMaxDamage(), subCannon.getWeight(), 0, userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this SubCannon!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}