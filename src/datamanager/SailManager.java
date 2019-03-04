package datamanager;

import java.io.*;
import java.util.*;

import entity.Sail;
import exception.*;

//Data Manager class to manage Sail objects

public class SailManager {
	
	//Attributes
	private ArrayList<Sail> sailList;
	private final String FILE_NAME = "data/sails.csv";
	private final String CLASS_NAME = "SailManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load sails' information from file
	*/
	public SailManager() throws LoadingException {
		sailList = new ArrayList<Sail>();
		load();
	}

	/**
	*Loads sails' information from file
	*@throws LoadingException when unable to load sails' information from file
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

				sailList.add(new Sail(name, speed, hp, capacity, weight, level, gold, wood, ore, prock, port));
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
	*Get all Sail objects
	*@return all Sail objects
	*/
	public ArrayList<Sail> retrieveAllSails() {
		return sailList;
	}
}