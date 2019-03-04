package datamanager;

import java.io.*;
import java.util.*;

import entity.SubCannon;
import exception.*;

//Data Manager class to manage SubCannon objects

public class SubCannonManager {
	
	//Attributes
	private ArrayList<SubCannon> subCannonList;
	private final String FILE_NAME = "data/subCannons.csv";
	private final String CLASS_NAME = "SubCannonManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load subCannons' information from file
	*/
	public SubCannonManager() throws LoadingException {
		subCannonList = new ArrayList<SubCannon>();
		load();
	}

	/**
	*Loads subCannons' information from file
	*@throws LoadingException when unable to load subCannons' information from file
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

				subCannonList.add(new SubCannon(name, range, minDamage, maxDamage, weight, level, gold, wood, ore, prock, port));
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
	*Get all SubCannon objects
	*@return all SubCannon objects
	*/
	public ArrayList<SubCannon> retrieveAllSubCannons() {
		return subCannonList;
	}
}