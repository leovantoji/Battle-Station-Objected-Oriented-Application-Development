package datamanager;

import java.io.*;
import java.util.*;

import entity.Cannon;
import exception.*;

//Data Manager class to manage Cannon objects

public class CannonManager {
	
	//Attributes
	private ArrayList<Cannon> cannonList;
	private final String FILE_NAME = "data/cannons.csv";
	private final String CLASS_NAME = "CannonManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load cannons' information from file
	*/
	public CannonManager() throws LoadingException {
		cannonList = new ArrayList<Cannon>();
		load();
	}

	/**
	*Loads cannons' information from file
	*@throws LoadingException when unable to load cannons' information from file
	*/
	public void load() throws LoadingException {
		Scanner fileIn = null;
		
		try {
			fileIn = new Scanner(new File(FILE_NAME));
			fileIn.useDelimiter(",|\r\n");
			fileIn.nextLine();

			while(fileIn.hasNext()) {
				String name = fileIn.next();
				int range = fileIn.nextInt();
				int minDamage = fileIn.nextInt();
				int maxDamage = fileIn.nextInt();
				int weight = fileIn.nextInt();
				int level = fileIn.nextInt();
				int gold = fileIn.nextInt();
				int wood = fileIn.nextInt();
				int ore = fileIn.nextInt();
				int prock = fileIn.nextInt();
				String port = fileIn.next();

				cannonList.add(new Cannon(name, range, minDamage, maxDamage, weight, level, gold, wood, ore, prock, port));
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
	*Get all Cannon objects
	*@return all Cannon objects
	*/
	public ArrayList<Cannon> retrieveAllCannons() {
		return cannonList;
	}
}