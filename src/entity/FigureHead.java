package entity;

//This represents an instance of a FigureHead

public class FigureHead {

	//11 attributes of figure head
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
	*Specific constructor of figure head
	*@param name 		The name of the figure head
	*@param speed 		The speed of the figure head
	*@param hp 			The health point of the figure head
	*@param capacity 	The capacity of the figure head
	*@param weight 		The weight of the figure head
	*@param level 		The level that is required to use the figure head
	*@param gold 		The gold price of the figure head
	*@param wood 		The wood price of the figure head
	*@param ore 		The ore price of the figure head
	*@param prock 		The plasma rock price of the figure head
	*@param port 		The port where the figure head is located
	*/

	public FigureHead(String name, int speed, int hp, int capacity, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the figure head
	*@return the name of the figure head
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the speed of the figure head
	*@return the speed of the figure head
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the health point of the figure head
	*@return the health point of the figure head
	*/
	public int getHP() {
		return hp;
	}
	
	/**
	*Get the capacity of the figure head
	*@return the capacity of the figure head
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the weight of the figure head
	*@return the weight of the figure head
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the figure head
	*@return the level that is required to use the figure head
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the figure head
	*@return the gold price of the figure head
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the figure head
	*@return the wood price of the figure head
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the figure head
	*@return the ore price of the figure head
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the figure head
	*@return the plasma rock price of the figure head
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the figure head is located
	*@return the port where the figure head is located
	*/
	public String getPort() {
		return port;
	}
}