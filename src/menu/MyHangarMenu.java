package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the My Hangar Option from the Main Menu

public class MyHangarMenu {
	//Attributes
	private User userLoggedIn;
	private MainController mainController;
	private Scanner sc;

	/**
	*Constructor
	*@param mainController
	*@param userLoggedIn
	*/
	public MyHangarMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		this.userLoggedIn = userLoggedIn;
		sc = new Scanner(System.in);
	}

	public void displayMyHangar() {

		System.out.println("\n== BattleStations :: Hangar ==\n");

		if(!userLoggedIn.ownShip()) {
			System.out.println("You don't own any ship yet!");
		} else {
			if(userLoggedIn.getHP() <= 0) {
				System.out.println("Your ship has been sunk. You need 10 AP to salvage.\n");
			}

			//Get all the equipped inventory objects that belong to the user
			ArrayList<Inventory> list = mainController.createInventoryController().retrieveAllEquippedInventories(userLoggedIn);
		
			//Display all the equipped parts that belong to the user
			System.out.println("Parts: ");
			for(int i=0; i<list.size(); ++i) {
				Inventory inventory = list.get(i);
				if(inventory.getInventory().equalsIgnoreCase("Part")) {
					System.out.println(inventory.getDescription() + ": \tL" + inventory.getLevel() + " - " + inventory.getName());
				}
			}

			System.out.println();

			//Display all the equipped weapons that belong to the user
			int numWeapon = 0;
			System.out.println("Weapons: ");
			for(int i=0; i<list.size(); ++i) {
				Inventory inventory = list.get(i);
				if(inventory.getInventory().equalsIgnoreCase("Weapon")) {
					++numWeapon;
					System.out.println("[W" + numWeapon + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
				}
			}

			System.out.println();
			System.out.println("Capacity: " + userLoggedIn.getCapacity() + " / " + userLoggedIn.getFullCapacity());
			System.out.println("Speed: " + userLoggedIn.getSpeed());
			System.out.println("HP: " + userLoggedIn.getHP() + " / " + userLoggedIn.getFullHP());
		}
	}

	public void readOption() {
		boolean cont = true;
		displayMyHangar();

		do {
			System.out.print("\n[R]eturn to main | [E]quip | [U]nequip | Re[p]air > ");
			String choice = sc.nextLine().toUpperCase();

			switch(choice.toUpperCase()) {
				case "R":
					cont = false;
					break;
				case "E":
					cont = false;
					processEquip();
					break;
				case "U":
					cont = false;
					processUnequip();
					break;
				case "P":
					cont = false;
					processRepair();
					break;
				default:
					//Display error message
					System.out.println("Please enter a valid option (R, E, U, P)!");
			}
		} while(cont);
	}

	public void processEquip() {
		System.out.println("\n== BattleStations :: Storage ==\n");

		//Get all inventory objects that are not equipped
		ArrayList<Inventory> unequippedList = mainController.createInventoryController().retrieveAllUnequippedInventories(userLoggedIn);

		//Get all the equipped inventory objects that belong to the user
		ArrayList<Inventory> list = mainController.createInventoryController().retrieveAllEquippedInventories(userLoggedIn);

		ArrayList<String> optionFigurehead = new ArrayList<String>(); //Generate multiple options for figureheads
		ArrayList<String> optionSail = new ArrayList<String>(); //Generate multiple options for sails
		ArrayList<String> optionStabilizer = new ArrayList<String>(); //Generate multiple options for stabilizers
		ArrayList<String> optionHull = new ArrayList<String>(); //Generate multiple options for hulls
		ArrayList<String> optionEngine = new ArrayList<String>(); //Generate multiple options for engines
		ArrayList<String> optionWeapon = new ArrayList<String>(); //Generate multiple options for weapons

		ArrayList<Inventory> figureHeadList = new ArrayList<Inventory>();
		ArrayList<Inventory> sailList = new ArrayList<Inventory>();
		ArrayList<Inventory> stabilizerList = new ArrayList<Inventory>();
		ArrayList<Inventory> hullList = new ArrayList<Inventory>();
		ArrayList<Inventory> engineList = new ArrayList<Inventory>();
		ArrayList<Inventory> weaponList = new ArrayList<Inventory>();

		int numFig = 0; //count the number of unequipped figureheads that belong to user
		System.out.println("Figureheads: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getDescription().equalsIgnoreCase("[F]igurehead")) {
				++numFig;
				String option = "F" + numFig;
				optionFigurehead.add(option);
				figureHeadList.add(inventory);
				System.out.println("[F" + numFig + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		} 

		if(numFig == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}

		int numSail = 0; //count the number of unequipped sails that belong to user
		System.out.println("Sails: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getDescription().equalsIgnoreCase("[S]ail")) {
				++numSail;
				String option = "S" + numSail;
				optionSail.add(option);
				sailList.add(inventory);
				System.out.println("[S" + numSail + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		}

		if(numSail == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}

		int numStab = 0; //count the number of unequipped stabilizers that belong to user
		System.out.println("Stabilizers: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getDescription().equalsIgnoreCase("S[t]abilizer")) {
				++numStab;
				String option = "T" + numStab;
				optionStabilizer.add(option);
				stabilizerList.add(inventory);
				System.out.println("[T" + numStab + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		}

		if(numStab == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}		

		int numHull = 0; //count the number of unequipped hulls that belong to user
		System.out.println("Hulls: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getDescription().equalsIgnoreCase("[H]ull")) {
				++numHull;
				String option = "H" + numHull;
				optionHull.add(option);
				hullList.add(inventory);
				System.out.println("[H" + numHull + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		}

		if(numHull == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}

		int numEng = 0; //count the number of unequipped engines that belong to user
		System.out.println("Engines: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getDescription().equalsIgnoreCase("[E]ngine")) {
				++numEng;
				String option = "E" + numEng;
				optionEngine.add(option);
				engineList.add(inventory);
				System.out.println("[E" + numEng + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		}

		if(numEng == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}

		int numWeapon = 0; //count the number of unequipped weapons that belong to user
		System.out.println("Weapons: ");
		for(int i=0; i<unequippedList.size(); ++i) {
			Inventory inventory = unequippedList.get(i);
			if(inventory.getInventory().equalsIgnoreCase("Weapon")) {
				++numWeapon;
				String option = "W" + numWeapon;
				optionWeapon.add(option);
				weaponList.add(inventory);
				System.out.println("[W" + numWeapon + "] \tL" + inventory.getLevel() + " - " + inventory.getName());
			}
		}

		if(numWeapon == 0) {
			System.out.println("NIL\n");
		} else {
			System.out.println();
		}

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | Enter weapon/part > ");
			String choice = sc.nextLine().toUpperCase();
			if(choice.equalsIgnoreCase("R")) {
				cont = false;
			} else {
				char c = choice.charAt(0); 
				switch(c) {
					case 'F':
						for(int i=0; i<optionFigurehead.size(); ++i) {
							if(choice.equalsIgnoreCase(optionFigurehead.get(i))) {
								cont = false;
								Inventory figurehead = figureHeadList.get(i);
								boolean check = false; //to check if Figurehead is already equipped

								for(int j=0; j<list.size();++j) {
									if(list.get(j).getDescription().equalsIgnoreCase("[F]igurehead")) {
										check = true; //true means Figurehead is already equipped
										System.out.println("Please unequip Figurehead \"" + list.get(i).getName() + "\" first!");
									}
								}

								if((!check) && userLoggedIn.equip(figurehead)) {
									figurehead.setStatus(true);
									mainController.createInventoryController().updateInventory(figurehead);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Figurehead \"" + figurehead.getName() + "\" equipped successfully!");
								} else if(!check) {
									System.out.println("Figurehead cannot be equipped! Exceed capacity!");
								}
							}
						}

						break;
					case 'S':
						for(int i=0; i<optionSail.size(); ++i) {
							if(choice.equalsIgnoreCase(optionSail.get(i))) {
								cont = false;
								Inventory sail = sailList.get(i);
								boolean check = false; //to check if Sail is already equipped								

								for(int j=0; j<list.size();++j) {
									if(list.get(j).getDescription().equalsIgnoreCase("[S]ail")) {
										check = true; //true means Sail is already equipped
										System.out.println("Please unequip Sail \"" + list.get(i).getName() +"\" first!");
									}
								}

								if((!check) && userLoggedIn.equip(sail)) {
									sail.setStatus(true);
									mainController.createInventoryController().updateInventory(sail);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Sail \"" + sail.getName() + "\" equipped successfully!");
								} else if(!check) {
									System.out.println("Sail cannot be equipped! Exceed capacity!");
								}
							}
						}
						
						break;
					case 'T':
						for(int i=0; i<optionStabilizer.size(); ++i) {
							if(choice.equalsIgnoreCase(optionStabilizer.get(i))) {
								cont = false;
								Inventory stabilizer = stabilizerList.get(i);
								boolean check = false; //to check if Stabilizer is already equipped

								for(int j=0; j<list.size();++j) {
									if(list.get(j).getDescription().equalsIgnoreCase("S[t]abilizer")) {
										check = true; //true means Stabilizer is already equipped
										System.out.println("Please unequip Stabilizer \"" + list.get(i).getName() +"\" first!");
									}
								}

								if((!check) && userLoggedIn.equip(stabilizer)) {
									stabilizer.setStatus(true);
									mainController.createInventoryController().updateInventory(stabilizer);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Stabilizer \"" + stabilizer.getName() + "\" equipped successfully!");
								} else if(!check) {
									System.out.println("Stabilizer cannot be equipped! Exceed capacity!");
								}
							}
						}

						break;
					case 'H':
						for(int i=0; i<optionHull.size(); ++i) {
							if(choice.equalsIgnoreCase(optionHull.get(i))) {
								cont = false;
								Inventory hull = hullList.get(i);
								boolean check = false; //to check if Hull is already equipped								

								for(int j=0; j<list.size();++j) {
									if(list.get(j).getDescription().equalsIgnoreCase("[H]ull")) {
										check = true; //true means Hull is already equipped
										System.out.println("Please unequip Hull \"" + list.get(i).getName() +"\" first!");
									}
								}

								if((!check) && userLoggedIn.equip(hull)) {
									hull.setStatus(true);
									mainController.createInventoryController().updateInventory(hull);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Hull \"" + hull.getName() + "\" equipped successfully!");
								} else if(!check) {
									System.out.println("Hull cannot be equipped! Exceed capacity!");
								}
							}
						}

						break;
					case 'E':
						for(int i=0; i<optionEngine.size(); ++i) {
							if(choice.equalsIgnoreCase(optionEngine.get(i))) {
								cont = false;
								Inventory engine = engineList.get(i);
								boolean check = false; //to check if Engine is already equipped								

								for(int j=0; j<list.size();++j) {
									if(list.get(j).getDescription().equalsIgnoreCase("[E]ngine")) {
										check = true; //true means Engine is already equipped
										System.out.println("Please unequip Engine \"" + list.get(i).getName() +"\" first!");
									}
								}

								if((!check) && userLoggedIn.equip(engine)) {
									engine.setStatus(true);
									mainController.createInventoryController().updateInventory(engine);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Engine \"" + engine.getName() + "\" equipped successfully!");
								} else if(!check) {
									System.out.println("Engine cannot be equipped! Exceed capacity!");
								}
							}
						}

						break;
					case 'W':
						for(int i=0; i<optionWeapon.size(); ++i) {
							if(choice.equalsIgnoreCase(optionWeapon.get(i))) {
								cont = false;
								Inventory weapon = weaponList.get(i);
								if(userLoggedIn.equip(weapon)) {
									weapon.setStatus(true);
									mainController.createInventoryController().updateInventoryStats(weapon, userLoggedIn);
									mainController.createUserController().updateUser(userLoggedIn);
									System.out.println("Weapon \"" + weapon.getName() + "\" equipped successfully!");
								} else {
									System.out.println("Weapon cannot be equipped! Exceed capacity AND/OR no free slot!");
								}
							}
						}

						break;
				}

				if(cont) {
					System.out.println("Please enter a valid option!");
				}
			}
		} while(cont);	
	}

	public void processUnequip() {
		//Get all the equipped inventory objects that belong to the user
		ArrayList<Inventory> list = mainController.createInventoryController().retrieveAllEquippedInventories(userLoggedIn);

		boolean cont = true;
		do {
			System.out.print("[R]eturn to main | Enter weapon/part to be unequipped > ");
			String choiceStr = sc.nextLine().toUpperCase(); //Get the input from the user
			char choice = choiceStr.charAt(0);

			if(choice == 'F') {
				cont = false;

				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getDescription().equalsIgnoreCase("[F]igurehead")) {
						inventory.setStatus(false);
						userLoggedIn.unequip(inventory);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventory(inventory);		
						System.out.println("Figurehead unequipped successfully!");								
					}
				}

			} else if(choice == 'S') {
				cont = false;

				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getDescription().equalsIgnoreCase("[S]ail")) {
						inventory.setStatus(false);
						userLoggedIn.unequip(inventory);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventory(inventory);							
						System.out.println("Sail unequipped successfully!");								
					}
				}

			} else if(choice == 'T') {
				cont = false;

				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getDescription().equalsIgnoreCase("S[t]abilizer")) {
						inventory.setStatus(false);
						userLoggedIn.unequip(inventory);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventory(inventory);							
						System.out.println("Stabilizer unequipped successfully!");								
					}
				}

			} else if(choice == 'H') {
				cont = false;

				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getDescription().equalsIgnoreCase("[H]ull")) {
						inventory.setStatus(false);
						userLoggedIn.unequip(inventory);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventory(inventory);	
						System.out.println("Hull unequipped successfully!");								
					}
				}

			} else if(choice == 'E') {
				cont = false;

				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getDescription().equalsIgnoreCase("[E]ngine")) {
						inventory.setStatus(false);
						userLoggedIn.unequip(inventory);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventory(inventory);							
						System.out.println("Engine unequipped successfully!");								
					}
				}

			} else if(choice == 'W') {
				ArrayList<String> optionWeapon = new ArrayList<String>(); //Generate multiple options for weapons
				ArrayList<Inventory> weaponList = new ArrayList<Inventory>();

				int numWeapon = 0; //to count the number of equipped weapons that belong to the userLoggedIn
				for(int i=0; i<list.size(); ++i) {
					Inventory inventory = list.get(i);
					if(inventory.getInventory().equalsIgnoreCase("Weapon")) {
						++numWeapon;
						String option = "W" + numWeapon;
						weaponList.add(inventory);
						optionWeapon.add(option);
					}
				}

				for(int i=0; i<optionWeapon.size(); ++i) {
					if(choiceStr.equalsIgnoreCase(optionWeapon.get(i))) {
						cont = false;
						Inventory weapon = weaponList.get(i);
						weapon.setStatus(false);
						userLoggedIn.unequip(weapon);
						mainController.createUserController().updateUser(userLoggedIn);
						mainController.createInventoryController().updateInventoryStats(weapon, userLoggedIn); 
						System.out.println("Weapon \"" + weapon.getName() + "\" unequipped successfully!");	
					}
				}
			} else if(choice == 'R') {
				cont = false;
			} else {
				if(cont) {
					System.out.println("Please enter a valid option!");
				}
			}
		} while(cont);
	}

	public void processRepair() {
		int fullHP = userLoggedIn.getFullHP(); //Full HP
		int hp = userLoggedIn.getHP(); //Current HP
		int amountAPForRepair = (fullHP - hp)/(fullHP/10); //1AP can restore 1/10 of full HP
		int amountGoldForRepair = fullHP - hp; //1 Gold can restore 1HP

		System.out.println("\n== BattleStations :: Storage ==\n");

		System.out.println("Gold available: " + userLoggedIn.getGold());
		System.out.println("AP: " + userLoggedIn.getAP());
		System.out.println();
		System.out.println("Status: " + hp + "/" + fullHP);
		System.out.println("Repair (Max)");
		System.out.println("[A]P: " + amountAPForRepair);
		System.out.println("[G]old: " + amountGoldForRepair);

		boolean cont = true;
		do {
			System.out.print("\n[R]eturn to main | Enter number of AP/Gold > ");
			String choice = sc.nextLine(); //Get the input from the user
			String optionAP = "A" + amountAPForRepair; //Store the possible option
			String optionGold = "G" + amountGoldForRepair; //Store the possible option

			if(choice.equalsIgnoreCase(optionAP)) {
				
				cont = false;
				userLoggedIn.setHP(userLoggedIn.getFullHP()); //Repair the ship
				userLoggedIn.addAP(-amountAPForRepair); //Deduct user's number of action point
				mainController.createUserController().updateUser(userLoggedIn); //Save to file
			} else if(choice.equalsIgnoreCase(optionGold)) {
				
				cont = false;
				userLoggedIn.addHP(amountGoldForRepair); //Repair the ship
				userLoggedIn.deductResources(amountGoldForRepair,0,0,0); //Deduct gold from user's account
				mainController.createUserController().updateUser(userLoggedIn); //Save to file
			} else {
				System.out.println("Please enter valid option (" + optionAP + " or " + optionGold + ")");
			}
		} while(cont);
	}
}