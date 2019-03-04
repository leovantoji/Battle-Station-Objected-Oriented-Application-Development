package entity;

//This represents an instance of a Missile

public class Missile {

	//11 attributes of missile
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
	*Specific constructor of missile
	*@param name 		The name of the missile
	*@param range 		The range of the missile
	*@param minDamage 	The minimum damage of the missile
	*@param maxDamage 	The maximum damage of the missile
	*@param weight 		The weight of the missile
	*@param level 		The level that is required to use the missile
	*@param gold 		The gold price of the missile
	*@param wood 		The wood price of the missile
	*@param ore 		The ore price of the missile
	*@param prock 		The plasma rock price of the missile
	*@param port 		The port where the missile is located
	*/

	public Missile(String name, int range, int minDamage, int maxDamage, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the missile
	*@return the name of the missile
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the range of the missile
	*@return the range of the missile
	*/
	public int getRange() {
		return range;
	}

	/**
	*Get the minimum damage of the missile
	*@return the minimum damage of the missile
	*/
	public int getMinDamage() {
		return minDamage;
	}
	
	/**
	*Get the maximum damage of the missile
	*@return the maximum damage of the missile
	*/
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	*Get the weight of the missile
	*@return the weight of the missile
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the missile
	*@return the level that is required to use the missile
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the missile
	*@return the gold price of the missile
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the missile
	*@return the wood price of the missile
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the missile
	*@return the ore price of the missile
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the missile
	*@return the plasma rock price of the missile
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the missile is located
	*@return the port where the missile is located
	*/
	public String getPort() {
		return port;
	}
}