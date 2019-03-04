package entity;

//This represents an instance of a SubCannon

public class SubCannon {

	//11 attributes of subCannon
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
	*Specific constructor of subCannon
	*@param name 		The name of the subCannon
	*@param range 		The range of the subCannon
	*@param minDamage 	The minimum damage of the subCannon
	*@param maxDamage 	The maximum damage of the subCannon
	*@param weight 		The weight of the subCannon
	*@param level 		The level that is required to use the subCannon
	*@param gold 		The gold price of the subCannon
	*@param wood 		The wood price of the subCannon
	*@param ore 		The ore price of the subCannon
	*@param prock 		The plasma rock price of the subCannon
	*@param port 		The port where the subCannon is located
	*/

	public SubCannon(String name, int range, int minDamage, int maxDamage, int weight, int level, int gold, int wood, int ore, int prock, String port) {
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
	*Get the name of the subCannon
	*@return the name of the subCannon
	*/
	public String getName() {
		return name;
	}

	/**
	*Get the range of the subCannon
	*@return the range of the subCannon
	*/
	public int getRange() {
		return range;
	}

	/**
	*Get the minimum damage of the subCannon
	*@return the minimum damage of the subCannon
	*/
	public int getMinDamage() {
		return minDamage;
	}
	
	/**
	*Get the maximum damage of the subCannon
	*@return the maximum damage of the subCannon
	*/
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	*Get the weight of the subCannon
	*@return the weight of the subCannon
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the level that is required to use the subCannon
	*@return the level that is required to use the subCannon
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the gold price of the subCannon
	*@return the gold price of the subCannon
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the wood price of the subCannon
	*@return the wood price of the subCannon
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the ore price of the subCannon
	*@return the ore price of the subCannon
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the plasma rock price of the subCannon
	*@return the plasma rock price of the subCannon
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the port where the subCannon is located
	*@return the port where the subCannon is located
	*/
	public String getPort() {
		return port;
	}
}