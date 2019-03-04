package controller;

import controller.UserController;
import controller.WeaponController;
import controller.PartController;
import controller.ShipController;
import controller.InventoryController;
import entity.User;

//Main Controller class for the Battle Stations Application

public class MainController {
	
	/**
	*Creation of UserController object
	*@return UserController object
	*/
	public UserController createUserController() {
		UserController userController = new UserController();
		return userController;
	}

	/**
	*Creation of UserController object
	*@return UserController object
	*/
	public UserController createUserController(User userLoggedIn) {
		UserController userController = new UserController(userLoggedIn);
		return userController;
	}	

	/**
	*Creation of WeaponController object
	*@return WeaponController object
	*/
	public WeaponController createWeaponController() {
		WeaponController weaponController = new WeaponController();
		return weaponController;
	}

	/**
	*Creation of PartController object
	*@return PartController object
	*/
	public PartController createPartController() {
		PartController partController = new PartController();
		return partController;
	}

	/**
	*Creation of ShipController object
	*@return ShipController object
	*/
	public ShipController createShipController() {
		ShipController shipController = new ShipController();
		return shipController;
	}

	/**
	*Creation of InventoryController object
	*@return InventoryController object
	*/
	public InventoryController createInventoryController() {
		InventoryController inventoryController = new InventoryController();
		return inventoryController;
	}
}