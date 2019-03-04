package entity;

//This represents an instance of a Cannon

public class Cannon {

	//11 attributes of cannon
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
	*Specific constructor of cannon
	*@param name 		The name of the cannon
	*@param range 		The range of the cannon
	*@param minDamage 	The minimum damage of the cannon
	*@param maxDamage 	The maximum damage of the cannon
	*@param weight 		The weight of the cannon
	*@param level 		The level that is required to use the cannon
	*@param gold 		The gold price of the cannon
	*@param wood 		The wood price of the cannon
	*@param ore 		The ore price of the cannon
	*@param prock 		The plasma rock price of the cannon
	*@param port 		The port where the cannon is located
	*/

	public Cannon(String name, int range, int minDamage, int maxDamage, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the cannon
	*@return the name of the cannon
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the range of the cannon
	*@return the range of the cannon
	*/
	public int getRange() {
		return range;
	}

	/**
	*Get the minimum damage of the cannon
	*@return the minimum damage of the cannon
	*/
	public int getMinDamage() {
		return minDamage;
	}
	
	/**
	*Get the maximum damage of the cannon
	*@return the maximum damage of the cannon
	*/
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	*Get the weight of the cannon
	*@return the weight of the cannon
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the cannon
	*@return the level that is required to use the cannon
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the cannon
	*@return the gold price of the cannon
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the cannon
	*@return the wood price of the cannon
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the cannon
	*@return the ore price of the cannon
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the cannon
	*@return the plasma rock price of the cannon
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the cannon is located
	*@return the port where the cannon is located
	*/
	public String getPort() {
		return port;
	}
}