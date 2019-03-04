package entity;

//This represents an instance of a Engine

public class Engine {

	//11 attributes of engine
	//All the attributes correspond to the CSV file
	private String name;
	private int speed;
	private int hp;
	private int capacity;
	private int weight;
	private int level;
	private int gold;
	private int wood;
	private int ore;
	private int prock;
	private String port;

	/**
	*Specific constructor of engine
	*@param name 		The name of the engine
	*@param speed 		The speed of the engine
	*@param hp 			The health point of the engine
	*@param capacity 	The capacity of the engine
	*@param weight 		The weight of the engine
	*@param level 		The level that is required to use the engine
	*@param gold 		The gold price of the engine
	*@param wood 		The wood price of the engine
	*@param ore 		The ore price of the engine
	*@param prock 		The plasma rock price of the engine
	*@param port 		The port where the engine is located
	*/

	public Engine(String name, int speed, int hp, int capacity, int weight, int level, int gold, int wood, int ore, int prock, String port) {
		this.name = name;
		this.speed = speed;
		this.hp = hp;
		this.capacity = capacity;
		this.weight = weight;
		this.level = level;
		this.gold = gold;
		this.wood = wood;
		this.ore = ore;
		this.prock = prock;
		this.port = port;
	}

	/**
	*Get the name of the engine
	*@return the name of the engine
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the engine
	*@return the speed of the engine
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the engine
	*@return the health point of the engine
	*/
	public int getHP() {
		return hp;
	}
	
	/**
	*Get the capacity of the engine
	*@return the capacity of the engine
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the weight of the engine
	*@return the weight of the engine
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the engine
	*@return the level that is required to use the engine
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the engine
	*@return the gold price of the engine
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the engine
	*@return the wood price of the engine
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the engine
	*@return the ore price of the engine
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the engine
	*@return the plasma rock price of the engine
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the engine is located
	*@return the port where the engine is located
	*/
	public String getPort() {
		return port;
	}
}