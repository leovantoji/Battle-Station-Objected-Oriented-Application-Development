package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LePartStabilizer Option from the LePartMenu

public class LePartStabilizerMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<Stabilizer> stabilizers;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartStabilizerMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		stabilizers = mainController.createPartController().retrieveAllStabilizers();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLePartStabilizer() {
		System.out.println("\n== BattleStations :: Le Part :: Stabilizer ==\n");		

		for(int i=0; i<stabilizers.size(); ++i) {
			Stabilizer stabilizer = stabilizers.get(i);
			System.out.println((i+1) + ". " + stabilizer.getName() + " (min: L" + stabilizer.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLePartStabilizer();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= stabilizers.size()) {
						cont = false;
						processLePartStabilizerDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + stabilizers.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + stabilizers.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLePartStabilizerDetails(int c) {
		System.out.println("\n== BattleStations :: Le Part :: Stabilizer :: Details ==\n");
		Stabilizer stabilizer = stabilizers.get(c-1);

		System.out.println("Stabilizer: " + stabilizer.getName() + "\n");
		System.out.println("Additional Speed: " + stabilizer.getSpeed());
		System.out.println("Additional HP: " + stabilizer.getHP());
		System.out.println("Additional Capacity: " + stabilizer.getCapacity());
		System.out.println("Weight: " + stabilizer.getWeight());
		System.out.println("Level Required: " + stabilizer.getLevel() + "\n");
		System.out.println("Gold: " + df.format(stabilizer.getGold()) + " | Wood: " + stabilizer.getWood() + " | Ore: " + stabilizer.getOre() + " | Prock: " + stabilizer.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(stabilizer)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Part", "S[t]abilizer", stabilizer.getName(), false, stabilizer.getLevel(), stabilizer.getCapacity(), 
						stabilizer.getHP(), 0, 0, 0, 0, 0, stabilizer.getWeight(), stabilizer.getSpeed(), userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this Stabilizer!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}