package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the View My Vital Statistics Option from the Main Menu

public class ViewMyVitalStatisticsMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public ViewMyVitalStatisticsMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sc = new Scanner(System.in);
	}

	public void displayViewMyVitalStatisticsMenu() {
		System.out.println("\n== BattleStations :: View My Vital Statistics ==\n");

		System.out.println("Craft: " + userLoggedIn.getCraft());
		if(userLoggedIn.ownShip()) {
			System.out.println("Gunnery: " + userLoggedIn.getGunnery() + "\t\tSpeed: " + userLoggedIn.getShip().getSpeed() + "(+" + userLoggedIn.getNavigation() + ")");
		} else {
			System.out.println("Gunnery: " + userLoggedIn.getGunnery() + "\t\tSpeed: " + userLoggedIn.getSpeed() + "(+" + userLoggedIn.getNavigation() + ")");
		}
		System.out.println("Navigation: " + userLoggedIn.getNavigation() + "\t\tStats Pts: " + userLoggedIn.getStats());
		System.out.println("Wins: " + userLoggedIn.getNoWins() + "\t\t\tLosses: " + userLoggedIn.getNoLosses());
		System.out.println("Total Exp: " + userLoggedIn.getEXP());
		System.out.println("Joined on: " + userLoggedIn.getDateJoined());
	}

	public void readOption() {
		boolean cont = true;
		displayViewMyVitalStatisticsMenu();

		do {
			System.out.print("\n[R]eturn to main | [A]llocate Stats Pts > ");
			String choice = sc.nextLine();

			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else if(choice.equalsIgnoreCase("A")) {
				cont = false;
				if(userLoggedIn.getStats() == 0) {
					System.out.println("You don't have any free stats points!");
				} else {
					System.out.println("\n== BattleStations :: Stats Allocations ==\n");

					boolean loop = true;

					do {
						try {
							System.out.print("Gunnery (+) > ");
							double gunneryBonus = sc.nextDouble();
							System.out.print("Craft (+) > ");
							double craftBonus = sc.nextDouble();
							System.out.print("Navigation (+) > ");
							double navigationBonus = sc.nextDouble();

							if((gunneryBonus + craftBonus + navigationBonus) > userLoggedIn.getStats()) {
								System.out.println("Cannot allocate more than " + userLoggedIn.getStats() + " Stats Pts!\n");
							} else if(gunneryBonus < 0 || craftBonus < 0 || navigationBonus < 0) {
								System.out.println("Cannot allocate negative Stats Pts!\n");
							} else {
								loop = false;
								mainController.createInventoryController().resetEquippedWeapons(userLoggedIn); //reset the stats of the equipped weapon to default
								userLoggedIn.addGunnery(gunneryBonus); //add aditional gunnery
								userLoggedIn.addCraft(craftBonus); //add additional craft
								userLoggedIn.addNavigation(navigationBonus); //add additional navigation
								userLoggedIn.addStats(-gunneryBonus-craftBonus-navigationBonus); //update the amount of free stats points
								mainController.createUserController().updateUser(userLoggedIn); //save to file
								mainController.createInventoryController().updateEquippedWeapons(userLoggedIn); //update the stats of the equipped weapon
								System.out.println("Successful Allocation!\n");
								sc.nextLine(); //flush
								
								boolean r = true;

								do {
									System.out.print("[R]eturn to main > ");
									String option = sc.nextLine();
									
									if(option.equalsIgnoreCase("R")) {
										r = false;
									} else {
										System.out.println("Press [R] to return to Main Menu");
									}
								} while(r);
							}
									
						} catch(InputMismatchException e) {
							System.out.println("Please enter number only!");
							sc.nextLine(); //flush
						}
					} while(loop);
				}
			} else {
				System.out.println("Please [R]eturn to main or [A]llocate Stats Pts!");
			}
		} while(cont);
	}
}