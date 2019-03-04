package datamanager;

import java.io.*;
import java.util.*;

import entity.Engine;
import exception.*;

//Data Manager class to manage Engine objects

public class EngineManager {
	
	//Attributes
	private ArrayList<Engine> engineList;
	private final String FILE_NAME = "data/engines.csv";
	private final String CLASS_NAME = "EngineManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load engines' information from file
	*/
	public EngineManager() throws LoadingException {
		engineList = new ArrayList<Engine>();
		load();
	}

	/**
	*Loads engines' information from file
	*@throws LoadingException when unable to load engines' information from file
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

				engineList.add(new Engine(name, speed, hp, capacity, weight, level, gold, wood, ore, prock, port));
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
	*Get all Engine objects
	*@return all Engine objects
	*/
	public ArrayList<Engine> retrieveAllEngines() {
		return engineList;
	}
}