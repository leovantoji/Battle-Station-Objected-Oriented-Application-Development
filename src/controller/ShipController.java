package controller;

import java.util.ArrayList;
import datamanager.ShipManager;
import logger.BSLogger;
import exception.*;
import entity.*;

//Controller class for all Ships

public class ShipController {
	
	//Attributes
	private ShipManager shipManager;
	private BSLogger logger;

	//No-argument constructor
	public ShipController() {
		logger = new BSLogger();

		try {
			shipManager = new ShipManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Retrieve all Ship objects
	*@return all Ship objects
	*/
	public ArrayList<Ship> retrieveAllShips() {
		return shipManager.retrieveAllShips();
	}

	/**
	*Retrieve the ship given the name of the ship
	*@return the ship with the matching name. Otherwise, return null
	*/
	public Ship retrieveShipByName(String shipName) {
		return shipManager.retrieveShipByName(shipName);
	}
}