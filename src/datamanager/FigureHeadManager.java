package datamanager;

import java.io.*;
import java.util.*;

import entity.FigureHead;
import exception.*;

//Data Manager class to manage FigureHead objects

public class FigureHeadManager {
	
	//Attributes
	private ArrayList<FigureHead> figureHeadList;
	private final String FILE_NAME = "data/figureHeads.csv";
	private final String CLASS_NAME = "FigureHeadManager";

	/**
	*No-argument constructor
	*@throws LoadingException when unable to load figureHeads' information from file
	*/
	public FigureHeadManager() throws LoadingException {
		figureHeadList = new ArrayList<FigureHead>();
		load();
	}

	/**
	*Loads figureHeads' information from file
	*@throws LoadingException when unable to load figureHeads' information from file
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

				figureHeadList.add(new FigureHead(name, speed, hp, capacity, weight, level, gold, wood, ore, prock, port));
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
	*Get all FigureHead objects
	*@return all FigureHead objects
	*/
	public ArrayList<FigureHead> retrieveAllFigureHeads() {
		return figureHeadList;
	}
}