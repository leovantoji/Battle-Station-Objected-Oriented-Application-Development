package controller;

import java.util.ArrayList;
import datamanager.EngineManager;
import datamanager.FigureHeadManager;
import datamanager.HullManager;
import datamanager.SailManager;
import datamanager.StabilizerManager;
import logger.BSLogger;
import entity.*;
import exception.*;

//Controller class for all Parts

public class PartController {
	
	//Attributes
	private EngineManager engineManager;
	private FigureHeadManager figureHeadManager;
	private HullManager hullManager;
	private SailManager sailManager;
	private StabilizerManager stabilizerManager;
	private BSLogger logger;

	//No-argument constructor
	public PartController() {
		logger = new BSLogger();

		try {
			engineManager = new EngineManager();
			figureHeadManager = new FigureHeadManager();
			hullManager = new HullManager();
			sailManager = new SailManager();
			stabilizerManager = new StabilizerManager();
		} catch(LoadingException e) {
			logger.log("ERROR", e.getMessage());
		}
	}

	/**
	*Retrieve all Engine objects
	*@return all Engine objects
	*/
	public ArrayList<Engine> retrieveAllEngines() {
		return engineManager.retrieveAllEngines();
	}

	/**
	*Retrieve all FigureHead objects
	*@return all FigureHead objects
	*/
	public ArrayList<FigureHead> retrieveAllFigureHeads() {
		return figureHeadManager.retrieveAllFigureHeads();
	}

	/**
	*Retrieve all Hull objects
	*@return all Hull objects
	*/
	public ArrayList<Hull> retrieveAllHulls() {
		return hullManager.retrieveAllHulls();
	}

	/**
	*Retrieve all Sail objects
	*@return all Sail objects
	*/
	public ArrayList<Sail> retrieveAllSails() {
		return sailManager.retrieveAllSails();
	}

	/**
	*Retrieve all Stabilizer objects
	*@return all Stabilizer objects
	*/
	public ArrayList<Stabilizer> retrieveAllStabilizers() {
		return stabilizerManager.retrieveAllStabilizers();
	}	
}