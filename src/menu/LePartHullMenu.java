package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LePartHull Option from the LePartMenu

public class LePartHullMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Hull> hulls;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartHullMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		hulls = mainController.createPartController().retrieveAllHulls();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLePartHull() {
		System.out.println("\n== BattleStations :: Le Part :: Hull ==\n");		

		for(int i=0; i<hulls.size(); ++i) {
			Hull hull = hulls.get(i);
			System.out.println((i+1) + ". " + hull.getName() + " (min: L" + hull.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLePartHull();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= hulls.size()) {
						cont = false;
						processLePartHullDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + hulls.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + hulls.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLePartHullDetails(int c) {
		System.out.println("\n== BattleStations :: Le Part :: Hull :: Details ==\n");
		Hull hull = hulls.get(c-1);

		System.out.println("Hull: " + hull.getName() + "\n");
		System.out.println("Additional Speed: " + hull.getSpeed());
		System.out.println("Additional HP: " + hull.getHP());
		System.out.println("Additional Capacity: " + hull.getCapacity());
		System.out.println("Weight: " + hull.getWeight());
		System.out.println("Level Required: " + hull.getLevel() + "\n");
		System.out.println("Gold: " + df.format(hull.getGold()) + " | Wood: " + hull.getWood() + " | Ore: " + hull.getOre() + " | Prock: " + hull.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(hull)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Part", "[H]ull", hull.getName(), false, hull.getLevel(), hull.getCapacity(), 
						hull.getHP(), 0, 0, 0, 0, 0, hull.getWeight(), hull.getSpeed(), userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Hull!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}