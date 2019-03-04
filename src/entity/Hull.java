package entity;

//This represents an instance of a Hull

public class Hull {

	//11 attributes of hull
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
	*Specific constructor of hull
	*@param name 		The name of the hull
	*@param speed 		The speed of the hull
	*@param hp 			The health point of the hull
	*@param capacity 	The capacity of the hull
	*@param weight 		The weight of the hull
	*@param level 		The level that is required to use the hull
	*@param gold 		The gold price of the hull
	*@param wood 		The wood price of the hull
	*@param ore 		The ore price of the hull
	*@param prock 		The plasma rock price of the hull
	*@param port 		The port where the hull is located
	*/

	public Hull(String name, int speed, int hp, int capacity, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the hull
	*@return the name of the hull
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the hull
	*@return the speed of the hull
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the hull
	*@return the health point of the hull
	*/
	public int getHP() {
		return hp;
	}
	
	/**
	*Get the capacity of the hull
	*@return the capacity of the hull
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the weight of the hull
	*@return the weight of the hull
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the hull
	*@return the level that is required to use the hull
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the hull
	*@return the gold price of the hull
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the hull
	*@return the wood price of the hull
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the hull
	*@return the ore price of the hull
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the hull
	*@return the plasma rock price of the hull
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the hull is located
	*@return the port where the hull is located
	*/
	public String getPort() {
		return port;
	}
}