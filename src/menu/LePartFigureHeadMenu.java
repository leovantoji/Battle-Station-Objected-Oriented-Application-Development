package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;
import datamanager.InventoryManager;
import java.text.DecimalFormat;

//This menu is to process the LePartFigureHead Option from the LePartMenu

public class LePartFigureHeadMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private ArrayList<FigureHead> figureHeads;
	private DecimalFormat df;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartFigureHeadMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		figureHeads = mainController.createPartController().retrieveAllFigureHeads();
		df = new DecimalFormat("#,###");
		sc = new Scanner(System.in);
	}

	public void displayLePartFigureHead() {
		System.out.println("\n== BattleStations :: Le Part :: FigureHead ==\n");		

		for(int i=0; i<figureHeads.size(); ++i) {
			FigureHead figureHead = figureHeads.get(i);
			System.out.println((i+1) + ". " + figureHead.getName() + " (min: L" + figureHead.getLevel() + ")");
		}
	}

	public void readOption() {
		boolean cont = true;
		displayLePartFigureHead();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);

					if(c >= 1 && c <= figureHeads.size()) {
						cont = false;
						processLePartFigureHeadDetails(c);
					} else {
						System.out.println("Please enter a valid number (1 to " + figureHeads.size() + ")!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid number (1 to " + figureHeads.size() + ")!");
				}
			}
		} while(cont);
	}

	public void processLePartFigureHeadDetails(int c) {
		System.out.println("\n== BattleStations :: Le Part :: FigureHead :: Details ==\n");
		FigureHead figureHead = figureHeads.get(c-1);

		System.out.println("Figurehead: " + figureHead.getName() + "\n");
		System.out.println("Additional Speed: " + figureHead.getSpeed());
		System.out.println("Additional HP: " + figureHead.getHP());
		System.out.println("Additional Capacity: " + figureHead.getCapacity());
		System.out.println("Weight: " + figureHead.getWeight());
		System.out.println("Level Required: " + figureHead.getLevel() + "\n");
		System.out.println("Gold: " + df.format(figureHead.getGold()) + " | Wood: " + figureHead.getWood() + " | Ore: " + figureHead.getOre() + " | Prock: " + figureHead.getProck());
		System.out.println();

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | [B]uy it > ");
			String choice = sc.nextLine();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("B")) {
				cont = false;
				if(userLoggedIn.isAbleToBuy(figureHead)) {
					int id = mainController.createInventoryController().getNoInventories() + 1;
					Inventory i = new Inventory(id, "Part", "[F]igurehead", figureHead.getName(), false, figureHead.getLevel(), figureHead.getCapacity(), 
						figureHead.getHP(), 0, 0, 0, 0, 0, figureHead.getWeight(), figureHead.getSpeed(), userLoggedIn);
					mainController.createInventoryController().addInventory(i);
					mainController.createUserController().updateUser(userLoggedIn);
					System.out.println("You have successfully bought the item!");
				} else {
					System.out.println("You CANNOT buy this FigureHead!");
					System.out.println("Item is RARE AND/OR Check your Level AND/OR Resources!");
				}
			} else {
				System.out.println("Invalid choice! Please choose again!");
			}
		} while(cont);	
	}
}