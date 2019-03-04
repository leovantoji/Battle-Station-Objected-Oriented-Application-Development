package datamanager;

import java.io.*;
import java.util.*;

import entity.Stabilizer;
import exception.*;

//Data Manager class to manage Stabilizer objects

public class StabilizerManager {
	
	//Attributes
	private ArrayList<Stabilizer> stabilizerList;
	private final String FILE_NAME = "data/stabilizers.csv";
	private final String CLASS_NAME = "StabilizerManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load stabilizers' information from file
	*/
	public StabilizerManager() throws LoadingException {
		stabilizerList = new ArrayList<Stabilizer>();
		load();
	}

	/**
	*Loads stabilizers' information from file
	*@throws LoadingException when unable to load stabilizers' information from file
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

				stabilizerList.add(new Stabilizer(name, speed, hp, capacity, weight, level, gold, wood, ore, prock, port));
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
	*Get all Stabilizer objects
	*@return all Stabilizer objects
	*/
	public ArrayList<Stabilizer> retrieveAllStabilizers() {
		return stabilizerList;
	}
}