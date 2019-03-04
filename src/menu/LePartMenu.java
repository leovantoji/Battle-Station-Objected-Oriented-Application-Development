package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the LePart Option from the LeShippeShoppeMenu

public class LePartMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public LePartMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sc = new Scanner(System.in);
	}

	public void displayLePart() {
		System.out.println("\n== BattleStations :: Le Part ==\n");

		System.out.println("1. View Engine");
		System.out.println("2. View Hull");
		System.out.println("3. View FigureHead");
		System.out.println("4. View Sail");
		System.out.println("5. View Stabilizer");
	}

	public void readOption() {
		boolean cont = true;
		displayLePart();

		do {
			System.out.print("\n[R]eturn to main | Enter number > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				try {
					int c = Integer.parseInt(choice);
					
					switch(c) {
						case 1:
							cont = false;
							LePartEngineMenu lePartEngineMenu = new LePartEngineMenu(mainController, userLoggedIn);
							lePartEngineMenu.readOption();
							break;
						case 2:
							cont = false;
							LePartHullMenu lePartHullMenu = new LePartHullMenu(mainController, userLoggedIn);
							lePartHullMenu.readOption();
							break;
						case 3:
							cont = false;
							LePartFigureHeadMenu lePartFigureHeadMenu = new LePartFigureHeadMenu(mainController, userLoggedIn);
							lePartFigureHeadMenu.readOption();
							break;
						case 4:
							cont = false;
							LePartSailMenu lePartSailMenu = new LePartSailMenu(mainController, userLoggedIn);
							lePartSailMenu.readOption();
							break;
						case 5:
							cont = false;
							LePartStabilizerMenu lePartStabilizerMenu = new LePartStabilizerMenu(mainController, userLoggedIn);
							lePartStabilizerMenu.readOption();
							break;
						default:
							System.out.println("Please enter a valid option (1 to 5)!");
					}
				} catch(NumberFormatException e) {
					System.out.println("Please enter a valid option (1 to 5)!");
				}
			}
		} while(cont);
	}
}