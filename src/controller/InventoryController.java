package controller;

import java.util.ArrayList;
import entity.*;
import datamanager.InventoryManager;
import logger.BSLogger;
import exception.*;

//Controller class for all inventories

public class InventoryController {
	
	//Attributes
	private InventoryManager inventoryManager;
	private BSLogger logger;

	//No-argument constructor
	public InventoryController() {
		logger = new BSLogger();

		try {
			inventoryManager = new InventoryManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Add new inventory when user buys or finds a new item
	*@param inventory Inventory object to be added
	*/
	public void addInventory(Inventory inventory) {
		try {
			inventoryManager.addInventory(inventory);
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	//Reset the stats of the equipped weapons to default
	public void resetEquippedWeapons(User user) {
		try {
			inventoryManager.resetEquippedWeapons(user);	
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	//Update the stats of the equipped weapons
	public void updateEquippedWeapons(User user) {
		try {
			inventoryManager.updateEquippedWeapons(user);
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*When the inventory is equipped, its statistics will change depending on the user's stats points.
	*@param user The user
	*/
	public void updateInventoryStats(Inventory inventory, User u) {
		try {
			inventoryManager.updateInventoryStats(inventory, u);
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

    /**
    *Update the information about an existing inventory
    *@param inventory Inventory object with updated information
    */	
	public void updateInventory(Inventory inventory) {
		try {
			inventoryManager.updateInventory(inventory);	
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	//Unequip all equipped inventories when user buy a new ship
	public void unequipAll(User user) {
		try {
			inventoryManager.unequipAll(user);
		} catch(SavingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Retrieve all Inventory objects
	*@return all Inventory objects
	*/
	public ArrayList<Inventory> retrieveAllInventories() {
		return inventoryManager.retrieveAllInventories();
	}

	/**
	*Retrieve all Inventory objects belonging to the particular user that are equipped
	*@param user The particular user
	*@return all Inventory objects belonging to the particular user that are equipped
	*/
	public ArrayList<Inventory> retrieveAllEquippedInventories(User user) {
		return inventoryManager.retrieveAllEquippedInventories(user);
	}

	/**
	*Retrieve all Inventory objects belonging to the particular user that are not equipped
	*@param user The particular user
	*@return all Inventory objects belonging to the particular user that are not equipped
	*/
	public ArrayList<Inventory> retrieveAllUnequippedInventories(User user) {
		return inventoryManager.retrieveAllUnequippedInventories(user);
	}

	/**
	*Retrieve all Inventory Weapon objects belonging to a particular user that are equipped
	*@param user The particular user
	*@return all Inventory Weapon objects belonging to a particular user that are equipped
	*/
	public ArrayList<Inventory> retrieveAllEquippedWeapons(User user) {
		return inventoryManager.retrieveAllEquippedWeapons(user);
	}

	//Get the total number of inventories
	public int getNoInventories() {
		return inventoryManager.getNoInventories();
	}
}