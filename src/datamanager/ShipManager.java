package datamanager;

import java.io.*;
import java.util.*;

import entity.Ship;
import exception.*;

//Data Manager class to manage Ship objects

public class ShipManager {
	
	//Attributes
	private ArrayList<Ship> shipList;
	private final String FILE_NAME = "data/ships.csv";
	private final String CLASS_NAME = "ShipManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load ships' information from file
	*/
	public ShipManager() throws LoadingException {
		shipList = new ArrayList<Ship>();
		load();
	}

	/**
	*Loads ships' information from file
	*@throws LoadingException when unable to load ships' information from file
	*/
	public void load() throws LoadingException {
		Scanner fileIn = null;
		
		try {
			fileIn = new Scanner(new File(FILE_NAME));
			fileIn.useDelimiter(",|\r\n");
			fileIn.nextLine();

			while(fileIn.hasNext()) {
				String name = fileIn.next();
				int speed = fileIn.nextInt();
				int hp = fileIn.nextInt();
				int slots = fileIn.nextInt();
				int capacity = fileIn.nextInt();
				int level = fileIn.nextInt();
				int gold = fileIn.nextInt();
				int wood = fileIn.nextInt();
				int ore = fileIn.nextInt();
				int prock = fileIn.nextInt();
				String port = fileIn.next();

				shipList.add(new Ship(name, speed, hp, slots, capacity, level, gold, wood, ore, prock, port));
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
	*Get all Ship objects
	*@return all Ship objects
	*/
	public ArrayList<Ship> retrieveAllShips() {
		return shipList;
	}

	/**
	*Retrieve the ship given the name of the ship
	*@return the ship with the matching name. Otherwise, return null
	*/
	public Ship retrieveShipByName(String name) {
		for(int i=0; i<shipList.size(); ++i) {
			if(shipList.get(i).getName().equals(name)) {
				return shipList.get(i);
			}
		}

		return null;
	}
}