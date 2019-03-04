package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LePartEngine Option from the LePartMenu

public class LePartEngineMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Engine> engines;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartEngineMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		engines = mainController.createPartController().retrieveAllEngines();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLePartEngine() {
		System.out.println("\n== BattleStations :: Le Part :: Engine ==\n");		

		for(int i=0; i<engines.size(); ++i) {
			Engine engine = engines.get(i);
			System.out.println((i+1) + ". " + engine.getName() + " (min: L" + engine.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLePartEngine();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= engines.size()) {
						cont = false;
						processLePartEngineDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + engines.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + engines.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLePartEngineDetails(int c) {
		System.out.println("\n== BattleStations :: Le Part :: Engine :: Details ==\n");
		Engine engine = engines.get(c-1);

		System.out.println("Engine: " + engine.getName() + "\n");
		System.out.println("Additional Speed: " + engine.getSpeed());
		System.out.println("Additional HP: " + engine.getHP());
		System.out.println("Additional Capacity: " + engine.getCapacity());
		System.out.println("Weight: " + engine.getWeight());
		System.out.println("Level Required: " + engine.getLevel() + "\n");
		System.out.println("Gold: " + df.format(engine.getGold()) + " | Wood: " + engine.getWood() + " | Ore: " + engine.getOre() + " | Prock: " + engine.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(engine)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Part", "[E]ngine", engine.getName(), false, engine.getLevel(), engine.getCapacity(), 
						engine.getHP(), 0, 0, 0, 0, 0, engine.getWeight(), engine.getSpeed(), userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Engine!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}