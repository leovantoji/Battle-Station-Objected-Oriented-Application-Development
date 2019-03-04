package entity;

//This represents an instance of a Melee

public class Melee {

	//11 attributes of melee
	//All the attributes correspond to the CSV file
	private String name;
	private int range;
	private int minDamage;
	private int maxDamage;
	private int weight;
	private int level;
	private int gold;
	private int wood;
	private int ore;
	private int prock;
	private String port;

	/**
	*Specific constructor of melee
	*@param name 		The name of the melee
	*@param range 		The range of the melee
	*@param minDamage 	The minimum damage of the melee
	*@param maxDamage 	The maximum damage of the melee
	*@param weight 		The weight of the melee
	*@param level 		The level that is required to use the melee
	*@param gold 		The gold price of the melee
	*@param wood 		The wood price of the melee
	*@param ore 		The ore price of the melee
	*@param prock 		The plasma rock price of the melee
	*@param port 		The port where the melee is located
	*/

	public Melee(String name, int range, int minDamage, int maxDamage, int weight, int level, int gold, int wood, int ore, int prock, String port) {
		this.name = name;
		this.range = range;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.weight = weight;
		this.level = level;
		this.gold = gold;
		this.wood = wood;
		this.ore = ore;
		this.prock = prock;
		this.port = port;
	}

	/**
	*Get the name of the melee
	*@return the name of the melee
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the range of the melee
	*@return the range of the melee
	*/
	public int getRange() {
		return range;
	}

	/**
	*Get the minimum damage of the melee
	*@return the minimum damage of the melee
	*/
	public int getMinDamage() {
		return minDamage;
	}
	
	/**
	*Get the maximum damage of the melee
	*@return the maximum damage of the melee
	*/
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	*Get the weight of the melee
	*@return the weight of the melee
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the melee
	*@return the level that is required to use the melee
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the melee
	*@return the gold price of the melee
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the melee
	*@return the wood price of the melee
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the melee
	*@return the ore price of the melee
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the melee
	*@return the plasma rock price of the melee
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the melee is located
	*@return the port where the melee is located
	*/
	public String getPort() {
		return port;
	}
}