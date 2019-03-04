package entity;

//This represents an instance of a Sail

public class Sail {

	//11 attributes of sail
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
	*Specific constructor of sail
	*@param name 		The name of the sail
	*@param speed 		The speed of the sail
	*@param hp 			The health point of the sail
	*@param capacity 	The capacity of the sail
	*@param weight 		The weight of the sail
	*@param level 		The level that is required to use the sail
	*@param gold 		The gold price of the sail
	*@param wood 		The wood price of the sail
	*@param ore 		The ore price of the sail
	*@param prock 		The plasma rock price of the sail
	*@param port 		The port where the sail is located
	*/

	public Sail(String name, int speed, int hp, int capacity, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the sail
	*@return the name of the sail
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the sail
	*@return the speed of the sail
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the sail
	*@return the health point of the sail
	*/
	public int getHP() {
		return hp;
	}
	
	/**
	*Get the capacity of the sail
	*@return the capacity of the sail
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the weight of the sail
	*@return the weight of the sail
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the sail
	*@return the level that is required to use the sail
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the sail
	*@return the gold price of the sail
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the sail
	*@return the wood price of the sail
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the sail
	*@return the ore price of the sail
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the sail
	*@return the plasma rock price of the sail
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the sail is located
	*@return the port where the sail is located
	*/
	public String getPort() {
		return port;
	}
}