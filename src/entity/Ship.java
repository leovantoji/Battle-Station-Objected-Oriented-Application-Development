package entity;

//This represents an instance of a Ship

public class Ship {

	//11 attributes of ship
	//All the attributes correspond to the CSV file
	private String name;
	private int speed;
	private int hp;
	private int slots;
	private int capacity;
	private int level;
	private int gold;
	private int wood;
	private int ore;
	private int prock;
	private String port;

	/**
	*Specific constructor of ship
	*@param name 		The name of the ship
	*@param speed 		The speed of the ship
	*@param hp 			The health point of the ship
	*@param slots		The number of slots in the ship
	*@param capacity 	The capacity of the ship
	*@param level 		The level that is required to use the ship
	*@param gold 		The gold price of the ship
	*@param wood 		The wood price of the ship
	*@param ore 		The ore price of the ship
	*@param prock 		The plasma rock price of the ship
	*@param port 		The port where the ship is located
	*/

	public Ship(String name, int speed, int hp, int slots, int capacity, int level, int gold, int wood, int ore, int prock, String port) {
		this.name = name;
		this.speed = speed;
		this.hp = hp;
		this.slots = slots;
		this.capacity = capacity;
		this.level = level;
		this.gold = gold;
		this.wood = wood;
		this.ore = ore;
		this.prock = prock;
		this.port = port;
	}

	/**
	*Get the name of the ship
	*@return the name of the ship
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the ship
	*@return the speed of the ship
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the ship
	*@return the health point of the ship
	*/
	public int getHP() {
		return hp;
	}

	/**
	*Get the number of slots in the ship
	*@return the number of slots in the ship
	*/
	public int getSlots() {
		return slots;
	}
	
	/**
	*Get the capacity of the ship
	*@return the capacity of the ship
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the level that is required to use the ship
	*@return the level that is required to use the ship
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the ship
	*@return the gold price of the ship
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the ship
	*@return the wood price of the ship
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the ship
	*@return the ore price of the ship
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the ship
	*@return the plasma rock price of the ship
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the ship is located
	*@return the port where the ship is located
	*/
	public String getPort() {
		return port;
	}
}