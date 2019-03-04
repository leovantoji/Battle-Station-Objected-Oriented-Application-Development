package datamanager;

import java.io.*;
import java.util.*;

import entity.*;
import controller.MainController;
import exception.*;

//Data Manager class to manage Inventory objects

public class InventoryManager {
	//Attributes
	private int noInventories; //The total number of inventories
	private ArrayList<Inventory> inventoryList;
	private final String FILE_NAME = "data/inventories.csv";
	private final String CLASS_NAME = "InventoryManager";

	/**
	*Default construct
	*@throws LoadingException when unable to load inventories' information from file
	*/
	public InventoryManager() throws LoadingException {

		//Creation of inventories.csv file
		PrintStream fileOut = null;
		try {
			File file = new File(FILE_NAME);
			if(!file.exists()) {
				fileOut = new PrintStream(new FileOutputStream(FILE_NAME, false));
				fileOut.println("ID,Inventory,Description,Name,Status,Level,Capacity,HP,Range,MinDamage,MaxDamage,Original MinDamage,Original MaxDamage,Weight,Speed,Owner");
			}
		} catch(IOException e) {
			String message = CLASS_NAME + " class: " + FILE_NAME + " NOT FOUND";
			throw new LoadingException(message);
		} finally {
			if(fileOut != null) {
				fileOut.close();
			}
		}

		inventoryList = new ArrayList<Inventory>();
		load();
		noInventories = inventoryList.size();
	}

	/**
	*Loads inventories' information from file
	*@throws LoadingException when unable to load inventories' information from file
	*/	
	private void load() throws LoadingException {
		Scanner fileIn = null;

		try {
			fileIn = new Scanner(new File(FILE_NAME));
			fileIn.useDelimiter(",|\r\n");
			fileIn.nextLine();

			while(fileIn.hasNext()) {
				int id = fileIn.nextInt();
				String inventory = fileIn.next();
				String description = fileIn.next();
				String name = fileIn.next();

				String statusStr = fileIn.next();
				if(!(statusStr.equalsIgnoreCase("true") || statusStr.equalsIgnoreCase("false"))) {
					throw new InputMismatchException();
				}

				boolean status = Boolean.parseBoolean(statusStr);
				int level = fileIn.nextInt();
				int capacity = fileIn.nextInt();
				int hp = fileIn.nextInt();
				int range = fileIn.nextInt();
				int minDamage = fileIn.nextInt();
				int maxDamage = fileIn.nextInt();
				int originalMinDamage = fileIn.nextInt();
				int originalMaxDamage = fileIn.nextInt();
				int weight = fileIn.nextInt();
				int speed = fileIn.nextInt();
				String username = fileIn.next();

				MainController mainController = new MainController();
				User owner = mainController.createUserController().retrieveUser(username);

				inventoryList.add(new Inventory(id, inventory, description, name, status, level, capacity, hp, range, minDamage, maxDamage, originalMinDamage, originalMaxDamage, weight, speed, owner));
			}
		} catch(InputMismatchException e) {
			//propagate error
			String message = "Reading error in File \"" + FILE_NAME + "\"";
			throw new LoadingException(message);
		} catch(FileNotFoundException e) {
			//propagate error
			String message = CLASS_NAME + " class : File " + FILE_NAME + " NOT FOUND";
            throw new LoadingException(message);
		} finally {
			if(fileIn != null) {
				fileIn.close();
			}
		}
	}

	/**
	*Save inventories' information to file
	*@throws SavingException when unable to save inventories' information to file
	*/
	private void save() throws SavingException {
		PrintStream fileOut = null;
		try {
			fileOut = new PrintStream(new FileOutputStream(FILE_NAME,false));
			fileOut.println("ID,Inventory,Description,Name,Status,Level,Capacity,HP,Range,MinDamage,MaxDamage,Original MinDamage,Original MaxDamage,Weight,Speed,Owner");
			for(int i=0; i<inventoryList.size(); ++i) {
				Inventory inventory = inventoryList.get(i);

				fileOut.print(inventory.getID());
				fileOut.print(",");
				fileOut.print(inventory.getInventory());
				fileOut.print(",");
				fileOut.print(inventory.getDescription());
				fileOut.print(",");
				fileOut.print(inventory.getName());
				fileOut.print(",");
				fileOut.print(inventory.getStatus());
				fileOut.print(",");
				fileOut.print(inventory.getLevel());
				fileOut.print(",");
				fileOut.print(inventory.getCapacity());
				fileOut.print(",");
				fileOut.print(inventory.getHP());
				fileOut.print(",");
				fileOut.print(inventory.getRange());
				fileOut.print(",");
				fileOut.print(inventory.getMinDamage());
				fileOut.print(",");
				fileOut.print(inventory.getMaxDamage());
				fileOut.print(",");
				fileOut.print(inventory.getOriginalMinDamage());
				fileOut.print(",");
				fileOut.print(inventory.getOriginalMaxDamage());
				fileOut.print(",");
				fileOut.print(inventory.getWeight());
				fileOut.print(",");
				fileOut.print(inventory.getSpeed());
				fileOut.print(",");
				fileOut.println(inventory.getOwner().getUsername());
			}
		} catch(FileNotFoundException e) {
			//propagate error
			String message = CLASS_NAME + " class : File " + FILE_NAME + " NOT FOUND";
            throw new SavingException(message);
		} finally {
			if(fileOut != null) {
				fileOut.close();
			}
		}
	}

	//Reset the stats of the equipped weapons to default
	public void resetEquippedWeapons(User user) throws SavingException {
		ArrayList<Inventory> inventories = retrieveAllEquippedInventories(user);		
		
		for(int i=0; i<inventories.size(); ++i) {
			Inventory inventory = inventories.get(i);
			updateInventoryStats(inventory, user);
		}
	}

	//Update the stats of all equipped weapons
	public void updateEquippedWeapons(User user) throws SavingException {
		ArrayList<Inventory> inventories = retrieveAllEquippedInventories(user);
		
		for(int i=0; i<inventories.size(); ++i) {
			Inventory inventory = inventories.get(i);
			updateInventoryStats(inventory, user);
		}
	}

	/**
	*When the inventory weapon is equipped or unequipped, its statistics will change depending on the user's stats points.
	*@param user The user
	*@throws SavingException when unable to update inventory's information to file
	*/
	public void updateInventoryStats(Inventory inventory, User user) throws SavingException {
		if(inventory.getInventory().equalsIgnoreCase("Weapon") && inventory.getStatus() && (!inventory.getDescription().equalsIgnoreCase("Melee"))) {
			int minDamage = (int) (inventory.getOriginalMinDamage()*(1+0.005*user.getGunnery()));
			int maxDamage = (int) (inventory.getOriginalMaxDamage()*(1+0.005*user.getGunnery()));
			inventory.setMinDamage(minDamage);
			inventory.setMaxDamage(maxDamage);
			updateInventory(inventory);	
		} else if(inventory.getStatus() && inventory.getDescription().equalsIgnoreCase("Melee")) {
			int minDamage = (int) (inventory.getOriginalMinDamage()*(1+0.005*user.getNavigation()));
			int maxDamage = (int) (inventory.getOriginalMaxDamage()*(1+0.005*user.getNavigation()));
			inventory.setMinDamage(minDamage);
			inventory.setMaxDamage(maxDamage);
			updateInventory(inventory);
		} else if(inventory.getStatus() && inventory.getDescription().equalsIgnoreCase("Missile")) {
			int minDamage = (int) (inventory.getOriginalMinDamage()*(1+0.0025*user.getNavigation()));
			int maxDamage = (int) (inventory.getOriginalMaxDamage()*(1+0.0025*user.getNavigation()));
			inventory.setMinDamage(minDamage);
			inventory.setMaxDamage(maxDamage);
			updateInventory(inventory);
		} else if(inventory.getInventory().equalsIgnoreCase("Weapon") && !inventory.getStatus()) {
			inventory.setMinDamage(inventory.getOriginalMinDamage());
			inventory.setMaxDamage(inventory.getOriginalMaxDamage());
			updateInventory(inventory);
		} 
	}

	//Unequip all equipped inventories when user buy a new ship
	public void unequipAll(User user) throws SavingException {
		ArrayList<Inventory> inventories = retrieveAllEquippedInventories(user);
		
		for(int i=0; i<inventories.size(); ++i) {
			Inventory inventory = inventories.get(i);
			inventory.setStatus(false);
			updateInventoryStats(inventory, user);
			updateInventory(inventory);
		}
	}

	/**
    *Update the information about an existing inventory
    *@param inventory Inventory object with updated information
    *@throws SavingException when unable to save inventory's information to file
    */	
	public void updateInventory(Inventory inventory) throws SavingException {
		Inventory i = retrieveInventory(inventory.getID());
		i.setStatus(inventory.getStatus());
		i.setMinDamage(inventory.getMinDamage());
		i.setMaxDamage(inventory.getMaxDamage());
		save();
	}

	/**
	*Retrieve inventory object with specified description and owner
	*@param name
	*@param owner
	*@return inventory object
	*/
	public Inventory retrieveInventory(int id) {
		for(int i=0; i<inventoryList.size(); ++i) {
			Inventory inventory = inventoryList.get(i);
			// String ownerName = owner.getUsername();

			// if(ownerName.equals(inventory.getOwner().getUsername()) && inventory.getName().equals(name)) {
			// 	return inventory;
			// }

			if(id == inventory.getID()) {
				return inventory;
			}
		}

		return null;
	}	

	/**
	*Add a new inventory to the inventory list when an user buys/finds a weapon or part
	*@param inventory The inventory to be added
	*@throws SavingException when unable to add the new inventory to the inventory list
	*/
	public void addInventory(Inventory inventory) throws SavingException {
		inventoryList.add(inventory);
		save();
	}

	/**
	*Retrieve all Inventory objects belonging to a particular user that are equipped
	*@param user The particular user
	*@return all Inventory objects belonging to a particular user that are equipped
	*/
	public ArrayList<Inventory> retrieveAllEquippedInventories(User user) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();

		for(int i=0; i<inventoryList.size(); ++i) {
			Inventory inventory = inventoryList.get(i);

			//Check if this inventory is equipped and belongs to the user
			String owner = inventory.getOwner().getUsername();
			if(owner.equals(user.getUsername()) && inventory.getStatus()) {
				list.add(inventory);
			}
		}

		return list;
	}

	/**
	*Retrieve all Inventory objects belonging to a particular user that are not equipped
	*@param user The particular user
	*@return all Inventory objects belonging to a particular user that are not equipped
	*/
	public ArrayList<Inventory> retrieveAllUnequippedInventories(User user) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();

		for(int i=0; i<inventoryList.size(); ++i) {
			Inventory inventory = inventoryList.get(i);

			//Check if this inventory is unequipped and belongs to the user
			String owner = inventory.getOwner().getUsername();
			if(owner.equals(user.getUsername()) && !inventory.getStatus()) {
				list.add(inventory);
			}
		}

		return list;
	}

	/**
	*Get all Inventory objects
	*@return all Inventory objects
	*/
	public ArrayList<Inventory> retrieveAllInventories() {
		return inventoryList;
	}

	/**
	*Retrieve all Inventory Weapon objects belonging to a particular user that are equipped
	*@param user The particular user
	*@return all Inventory Weapon objects belonging to a particular user that are equipped
	*/
	public ArrayList<Inventory> retrieveAllEquippedWeapons(User user) {
		ArrayList<Inventory> list = new ArrayList<Inventory>();

		for(int i=0; i<inventoryList.size(); ++i) {
			Inventory inventory = inventoryList.get(i);

			//Check if this inventory is equipped and belongs to the user
			if(inventory.getOwner().getUsername().equals(user.getUsername()) && inventory.getStatus() && inventory.getInventory().equalsIgnoreCase("Weapon")) {
				list.add(inventory);
			}
		}

		return list;
	}

	//Get the total number of inventories
	public int getNoInventories() {
		return noInventories;
	}
}