package entity;

//This represents an instance of a Stabilizer

public class Stabilizer {

	//11 attributes of stabilizer
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
	*Specific constructor of stabilizer
	*@param name 		The name of the stabilizer
	*@param speed 		The speed of the stabilizer
	*@param hp 			The health point of the stabilizer
	*@param capacity 	The capacity of the stabilizer
	*@param weight 		The weight of the stabilizer
	*@param level 		The level that is required to use the stabilizer
	*@param gold 		The gold price of the stabilizer
	*@param wood 		The wood price of the stabilizer
	*@param ore 		The ore price of the stabilizer
	*@param prock 		The plasma rock price of the stabilizer
	*@param port 		The port where the stabilizer is located
	*/

	public Stabilizer(String name, int speed, int hp, int capacity, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the stabilizer
	*@return the name of the stabilizer
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the stabilizer
	*@return the speed of the stabilizer
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the stabilizer
	*@return the health point of the stabilizer
	*/
	public int getHP() {
		return hp;
	}
	
	/**
	*Get the capacity of the stabilizer
	*@return the capacity of the stabilizer
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the weight of the stabilizer
	*@return the weight of the stabilizer
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the stabilizer
	*@return the level that is required to use the stabilizer
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the stabilizer
	*@return the gold price of the stabilizer
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the stabilizer
	*@return the wood price of the stabilizer
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the stabilizer
	*@return the ore price of the stabilizer
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the stabilizer
	*@return the plasma rock price of the stabilizer
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the stabilizer is located
	*@return the port where the stabilizer is located
	*/
	public String getPort() {
		return port;
	}
}