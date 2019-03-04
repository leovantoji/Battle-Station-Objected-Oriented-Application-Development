package controller;

import java.util.ArrayList;
import datamanager.CannonManager;
import datamanager.SubCannonManager;
import datamanager.MissileManager;
import datamanager.MeleeManager;
import logger.BSLogger;
import entity.*;
import exception.*;

//Controller class for all Weapons 

public class WeaponController {
	
	//Attributes
	private CannonManager cannonManager;
	private SubCannonManager subCannonManager;
	private MissileManager missileManager;
	private MeleeManager meleeManager;
	private BSLogger logger;

	//No-argument constructor
	public WeaponController() {
		logger = new BSLogger();

		try {
			cannonManager = new CannonManager();
			subCannonManager = new SubCannonManager();
			missileManager = new MissileManager();
			meleeManager = new MeleeManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Retrieve all Cannon objects
	*@return all Cannon objects
	*/
	public ArrayList<Cannon> retrieveAllCannons() {
		return cannonManager.retrieveAllCannons();
	}

	/**
	*Retrieve all SubCannon objects
	*@return all SubCannon objects
	*/
	public ArrayList<SubCannon> retrieveAllSubCannons() {
		return subCannonManager.retrieveAllSubCannons();
	}

	/**
	*Retrieve all Missile objects
	*@return all Missile objects
	*/
	public ArrayList<Missile> retrieveAllMissiles() {
		return missileManager.retrieveAllMissiles();
	}

	/**
	*Retrieve all Melee objects
	*@return all Melee objects
	*/
	public ArrayList<Melee> retrieveAllMelee() {
		return meleeManager.retrieveAllMelee();
	}
}