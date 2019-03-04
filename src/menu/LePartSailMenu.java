package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LePartSail Option from the LePartMenu

public class LePartSailMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Sail> sails;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartSailMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sails = mainController.createPartController().retrieveAllSails();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLePartSail() {
		System.out.println("\n== BattleStations :: Le Part :: Sail ==\n");		

		for(int i=0; i<sails.size(); ++i) {
			Sail sail = sails.get(i);
			System.out.println((i+1) + ". " + sail.getName() + " (min: L" + sail.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLePartSail();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= sails.size()) {
						cont = false;
						processLePartSailDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + sails.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + sails.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLePartSailDetails(int c) {
		System.out.println("\n== BattleStations :: Le Part :: Sail :: Details ==\n");
		Sail sail = sails.get(c-1);

		System.out.println("Sail: " + sail.getName() + "\n");
		System.out.println("Additional Speed: " + sail.getSpeed());
		System.out.println("Additional HP: " + sail.getHP());
		System.out.println("Additional Capacity: " + sail.getCapacity());
		System.out.println("Weight: " + sail.getWeight());
		System.out.println("Level Required: " + sail.getLevel() + "\n");
		System.out.println("Gold: " + df.format(sail.getGold()) + " | Wood: " + sail.getWood() + " | Ore: " + sail.getOre() + " | Prock: " + sail.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(sail)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Part", "[S]ail", sail.getName(), false, sail.getLevel(), sail.getCapacity(), 
						sail.getHP(), 0, 0, 0, 0, 0, sail.getWeight(), sail.getSpeed(), userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Sail!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}