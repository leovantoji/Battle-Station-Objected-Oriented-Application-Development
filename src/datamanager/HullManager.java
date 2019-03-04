package datamanager;

import java.io.*;
import java.util.*;

import entity.Hull;
import exception.*;

//Data Manager class to manage Hull objects

public class HullManager {
	
	//Attributes
	private ArrayList<Hull> hullList;
	private final String FILE_NAME = "data/hulls.csv";
	private final String CLASS_NAME = "HullManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load hulls' information from file
	*/
	public HullManager() throws LoadingException {
		hullList = new ArrayList<Hull>();
		load();
	}

	/**
	*Loads hulls' information from file
	*@throws LoadingException when unable to load hulls' information from file
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
				int capacity = fileIn.nextInt();
				int weight = fileIn.nextInt();
				int level = fileIn.nextInt();
				int gold = fileIn.nextInt();
				int wood = fileIn.nextInt();
				int ore = fileIn.nextInt();
				int prock = fileIn.nextInt();
				String port = fileIn.next();

				hullList.add(new Hull(name, speed, hp, capacity, weight, level, gold, wood, ore, prock, port));
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
	*Get all Hull objects
	*@return all Hull objects
	*/
	public ArrayList<Hull> retrieveAllHulls() {
		return hullList;
	}
}