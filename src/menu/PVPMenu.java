package menu;

import java.io.*;
import java.util.*;
import entity.*;
import controller.*;

//This menu is to process the PVP Option from the Main Menu

public class PVPMenu {
	//Attributes
	private User attacker;
	private User defender;
	private MainController mainController;
	private ArrayList<User> users;
	private Scanner sc;
	
	private int attackerStart;
	private int defenderStart;
	private int aTotalDamage;
	private int dTotalDamage;
	private int aCombatXP;
	private int dCombatXP;
	private int aCombatGold;
	private int dCombatGold;
	private int extraWood;
	private int extraOre;
	private int extraProck;

	private double aMultiplier;
	private double dMultiplier;

	private static final int DEFENDER_START = 1000;
	private static final int ATTACKER_START = 0;
	private static final int FIRST_FIRING_POINT = 2500;
	private static final int SECOND_FIRING_POINT = 5000;
	private static final int THIRD_FIRING_POINT = 7500;
	private static final int PVPFEE = 8;

	/**
	*Constructor
	*@param mainController
	*@param attacker
	*/
	public PVPMenu(MainController mainController, User userLoggedIn) {
		this.mainController = mainController;
		attacker = userLoggedIn;
		defender = null;
		users = mainController.createUserController().retrieveAllDefenders(attacker);
		sc = new Scanner(System.in);

		attackerStart = ATTACKER_START;
		defenderStart = DEFENDER_START;
		aTotalDamage = 0;
		dTotalDamage = 0;
		aCombatXP = 0;
		dCombatXP = 0;
		aCombatGold = 0;
		dCombatGold = 0;
		extraWood = 0;
		extraOre = 0;
		extraProck = 0;
	}

	public void displayPVP() {
		System.out.println("\n== BattleStations :: PVP ==\n");

		if(users.size() == 0) {
			System.out.println("There is currently no other player to PVP!");
		} else {
			for(int i=0; i<users.size(); ++i) {
				User u = users.get(i);
				String status = "";

				if(u.getHP() < u.getFullHP()) {
					status = "Damaged";
				} else {
					status = "Perfect";
				}

				System.out.println((i+1) + ". " + u.getUsername() + " [" + u.getLevel() + "] - " + status);
			}
		}
	}

	public void readOption() {
		boolean cont = true;
		displayPVP();

		if(!attacker.ownShip()) {
			System.out.println("You need to buy a ship before PVP!");
			
			do {
				System.out.print("[R]eturn to main > ");
				String choice = sc.nextLine();
				if(choice.length() ==1 && choice.equalsIgnoreCase("R")) {
					cont = false;
				}

			} while(cont);
		} else if(attacker.getAP() < PVPFEE) {
			System.out.println("You need at least " + PVPFEE + "AP to PVP! Please get more PVP!");
		} else {
			do {
				System.out.print("\n[R]eturn to main | Attack (1-" + users.size() + ") > ");
				String choice = sc.nextLine();

				if(choice.equalsIgnoreCase("R")) {
					cont = false;
				} else {
					try {
						int c = Integer.parseInt(choice);

						if(c >= 1 && c <= users.size()) {
							cont = false;
							processPVP(c);
						} else {
							System.out.println("Please enter a valid number (1 to " + users.size() + ")!");
						}	
					} catch(NumberFormatException e) {
						System.out.println("Please enter a valid number (1 to " + users.size() + ")!");
					}
				}
			} while(cont);
		}
	}

	public void processPVP(int c) {
		this.defender = users.get(c-1); //Get the defender
		int levelDifference = defender.getLevel() - attacker.getLevel();

		if(Math.abs(levelDifference) > 5) {
			System.out.println("You cannot attack a player that is 5 level higher OR lower!");
		} else {
			attacker.addAP(-PVPFEE); //Minus away PVPFEE amount of AP from the attacker
			aMultiplier = computeMultiplier(-levelDifference); //compute attacker's multiplier
			dMultiplier = computeMultiplier(levelDifference); //compute defender's multiplier

			int firstCombat = fight(FIRST_FIRING_POINT, attackerStart, defenderStart);
			if(firstCombat == -1 || firstCombat == 1) {
				computeStats(firstCombat);
			} else {
				int secondCombat = fight(SECOND_FIRING_POINT, attackerStart, defenderStart);
				if(secondCombat == -1 || secondCombat == 1) {
					computeStats(secondCombat);
				} else {
					int thirdCombat = fight(THIRD_FIRING_POINT, attackerStart, defenderStart);
					computeStats(thirdCombat);
				}
			}

			displayResult(); //display combat's result to screen

			mainController.createUserController().updateUser(attacker); //save to file
			mainController.createUserController().updateUser(defender); // save to file
		}
	}

	/**
	*Compute stats after combat
	*@param combatResult	-1 when defender wins, 0 when it's a draw, 1 when attacker wins
	*/
	public void computeStats(int combatResult) {
		if(combatResult == -1) {
			//Defender wins
			//Compute EXP
			aCombatXP = (int) ((aTotalDamage/300)*attacker.getLevel()*5*aMultiplier);
			dCombatXP = (int) (1.5*((dTotalDamage/300)*defender.getLevel()*5*dMultiplier));

			//Compute Gold
			aCombatGold = (int) ((aTotalDamage/300)*attacker.getLevel()*aMultiplier);
			dCombatGold = (int) (1.5*((dTotalDamage/300)*defender.getLevel()*dMultiplier));

			extraWood = (int) (Math.random()*5);
			extraOre = (int) (Math.random()*5);
			extraProck = (int) (Math.random()*5);

			attacker.addEXP(aCombatXP);
			attacker.addResources(aCombatGold, extraWood, extraOre, extraProck);
			attacker.addNoLosses();

			defender.addEXP(dCombatXP);
			defender.addResources(dCombatGold, 0, 0, 0);
			defender.addNoWins();
		} else if(combatResult == 1) {
			//Attacker wins
			//Compute EXP
			dCombatXP = (int) ((dTotalDamage/300)*defender.getLevel()*5*dMultiplier);
			aCombatXP = (int) (1.5*((aTotalDamage/300)*attacker.getLevel()*5*aMultiplier));

			//Compute Gold
			dCombatGold = (int) ((dTotalDamage/300)*defender.getLevel()*dMultiplier);
			aCombatGold = (int) (1.5*((aTotalDamage/300)*attacker.getLevel()*aMultiplier));

			extraWood = (int) (Math.random()*5);
			extraOre = (int) (Math.random()*5);
			extraProck = (int) (Math.random()*5);

			attacker.addEXP(aCombatXP);
			attacker.addResources(aCombatGold, extraWood, extraOre, extraProck);
			attacker.addNoWins();

			defender.addEXP(dCombatXP);
			defender.addResources(dCombatGold, 0, 0, 0);
			defender.addNoLosses();
		} else {
			//It's a draw!
			//Compute EXP
			dCombatXP = (int) ((dTotalDamage/300)*defender.getLevel()*5*dMultiplier);
			aCombatXP = (int) ((aTotalDamage/300)*attacker.getLevel()*5*aMultiplier);

			//Compute Gold
			dCombatGold = (int) ((dTotalDamage/300)*defender.getLevel()*dMultiplier);
			aCombatGold = (int) ((aTotalDamage/300)*attacker.getLevel()*aMultiplier);

			extraWood = (int) (Math.random()*5);
			extraOre = (int) (Math.random()*5);
			extraProck = (int) (Math.random()*5);

			attacker.addEXP(aCombatXP);
			attacker.addResources(aCombatGold, extraWood, extraOre, extraProck);

			defender.addEXP(dCombatXP);
			defender.addResources(dCombatGold, 0, 0, 0);
		}

		attacker.computeLevel(attacker.getEXP());
		defender.computeLevel(defender.getEXP());
	}

	//Display combat result
	public void displayResult() {
		System.out.println();
		
		if(attacker.getHP() <= 0) {
			System.out.println(attacker.getUsername() + " has lost the battle to " + defender.getUsername() + "!");
		} else if(defender.getHP() <=0) {
			System.out.println(attacker.getUsername() + " has won the battle over " + defender.getUsername() + "!");
		} else {
			System.out.println("It's a draw!");
		}

		System.out.println(attacker.getUsername() + "'s HP: " + attacker.getHP() + " / " + attacker.getFullHP());
		System.out.println(defender.getUsername() + "'s HP: " + defender.getHP() + " / " + defender.getFullHP());
		System.out.println(attacker.getUsername() + " gained " + aCombatGold + " gold");
		System.out.println(defender.getUsername() + " gained " + dCombatGold + " gold");
		System.out.println(attacker.getUsername() + " gained " + aCombatXP + " exp");
		System.out.println(defender.getUsername() + " gained " + dCombatXP + " exp");
		System.out.println();
		System.out.println(attacker.getUsername() + " get " + extraProck + " prock(s), " + extraOre + " ore(s), " + extraWood + " wood(s).");
	}

	//Compute multiplier
	public double computeMultiplier(int levelDifference) {
		switch(levelDifference) {
			case -5: 
				return 1.3;
			case -4:
				return 1.2;
			case -3:
				return 1.1;
			case -2: case -1: case 0: case 1: case 2:
				return 1.0;
			case 3:
				return 0.9;
			case 4:
				return 0.8;
			case 5:
				return 0.7;
			default:
				return 0.0; //No battle between the attacker and defender since the level difference is greater than 5
		}
	}

	/**
	*Simulate the fight between the attacker and the defender
	*@param firingPoint 	The firing mark (e.g. 2500 or 5000 or 7500)
	*@param attackerStart 	The position when the attacker starts the fight
	*@param defenderStart 	The position when the defender starts the fight
	*@return -1 if the defender wins, 0 if it's a draw, 1 if the attacker wins
	*/
	public int fight(int firingPoint, int attackerStart, int defenderStart) {

		System.out.println(attacker.getUsername() + "'s HP: " + attacker.getHP() + " / " + attacker.getFullHP()); //Display attacker's HP
		System.out.println(defender.getUsername() + "'s HP: " + defender.getHP() + " / " + defender.getFullHP()); //Display defender's HP
		System.out.println();

		ArrayList<Inventory> aWeapons = mainController.createInventoryController().retrieveAllEquippedWeapons(attacker); //Attacker's weapons
		ArrayList<Inventory> dWeapons = mainController.createInventoryController().retrieveAllEquippedWeapons(defender); //Defender's weapons

		int aSpeed = attacker.getSpeed(); //Attacker's speed
		int dSpeed = defender.getSpeed(); //Defender's speed

		int time = Math.round((float) (firingPoint - defenderStart)/dSpeed);
		int aPosition = attackerStart + aSpeed*time; //Attacker's new position
		int dPosition = defenderStart + dSpeed*time; //Defender's new position
		int distance = dPosition - aPosition;
		
		//Attacker will also be 50m behind the Defender
		if(distance < 50) {
			distance = 50;
		}

		this.attackerStart = aPosition; //Update the next starting position for the next battle
		this.defenderStart = dPosition; //Update the next starting position for the next battle

		//Attacker attacks
		for(int i=0; i<aWeapons.size(); ++i) {
			Inventory weapon = aWeapons.get(i);

			if(weapon.getRange() >= distance) {
				int damageInterval = weapon.getMaxDamage() - weapon.getMinDamage(); //Damage interval
				int damage = weapon.getMinDamage() + (int) (Math.random()*damageInterval);
				
				aTotalDamage += damage; //increase the attacker's total damage
				defender.addHP(-damage); //minus the defender's HP

				System.out.println(attacker.getUsername() + " attacks with " + weapon.getName() + " at " + aPosition + " m (" + damage + " damage)");
				
				//Check if attacker win or not
				if(defender.getHP() <= 0) {
					defender.setHP(0);
					return 1;
				}
			}
		}

		//Defender defends
		for(int i=0; i<dWeapons.size(); ++i) {
			Inventory weapon = dWeapons.get(i);

			if(weapon.getRange() >= distance) {
				int damageInterval = weapon.getMaxDamage() - weapon.getMinDamage(); //Damage interval
				int damage = weapon.getMinDamage() + (int) (Math.random()*damageInterval);
				
				dTotalDamage += damage; //increase the defender's total damage
				attacker.addHP(-damage); //minus the attacker's HP

				System.out.println(defender.getUsername() + " attacks with " + weapon.getName() + " at " + dPosition + " m (" + damage + " damage)");

				//Check if defender win or not
				if(attacker.getHP() <= 0) {
					attacker.setHP(0);
					return -1;
				}
			}
		}

		return 0;
	}
}