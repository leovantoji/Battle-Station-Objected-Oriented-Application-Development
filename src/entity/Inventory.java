package entity;
import datamanager.InventoryManager;

//This represents an instance of inventory
//An inventory is created whenever the user buys/finds a ship part or a weapon

public class Inventory {

	//Attributes of inventory
	private int id;
	private String inventory;
	private String description;
	private String name;
	private boolean status;
	private int level;
	private int capacity;
	private int hp;
	private int range;
	private int minDamage;
	private int maxDamage;
	private int weight;
	private int speed;
	private User owner;

	//original damge
	private int originalMinDamage;
	private int originalMaxDamage;

	/**
	*Specific constructor of inventory
	*@param id 						The id of the inventory (a nominal number starting from 1 to uniquely identify the inventory)
	*@param inventory 				The type of the inventory (Weapon or Part)
	*@param description				The specific description of the inventory (e.g. cannon, engine, etc.)
	*@param name					The name of the inventory
	*@param status					true if the inventory is equipped, false if not
	*@param level 					The level of the inventory
	*@param capacity				The additional capacity provided by the inventory Part
	*@param hp						The additional hp provided by the inventory Part
	*@param range					The range of the inventory Weapon
	*@param minDamage				The minimum damage of inventory Weapon
	*@param maxDamage				The maximum damage of the inventory Weapon
	*@param originalMinDamage		The minimum damage of inventory Weapon
	*@param originalMaxDamage		The maximum damage of the inventory Weapon
	*@param weight					The weight of the inventory
	*@param speed					The additional speed provided by the inventory Part
	*@param owner					The owner of the inventory
	*/
	public Inventory(int id, String inventory, String description, String name, boolean status, int level, int capacity, int hp, int range, 
		int minDamage, int maxDamage, int originalMinDamage, int originalMaxDamage, int weight, int speed, User owner) {
		this.id = id;
		this.inventory = inventory;
		this.description = description;
		this.name = name;
		this.status = status;
		this.level = level;
		this.capacity = capacity;
		this.hp = hp;
		this.range = range;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.originalMinDamage = originalMinDamage;
		this.originalMaxDamage = originalMaxDamage;
		this.weight = weight;
		this.speed = speed;
		this.owner = owner;
	}

	/**
	*Get the id of the inventory
	*@return the id of the inventory
	*/
	public int getID() {
		return id;
	}

	/**
	*Get the type of the inventory
	*@return the type of the inventory
	*/
	public String getInventory() {
		return inventory;
	}

	/**
	*Get the specific description of the inventory
	*@return the specific description of the inventory
	*/
	public String getDescription() {
		return description;
	}

	/**
	*Get the name of the inventory
	*@return the name of the inventory
	*/
	public String getName() {
		return name;
	}

	/**
	*Check if the inventory is equipped or not
	*@return 
	*/
	public boolean getStatus() {
		return status;
	}

	/**
	*Get the level of the inventory
	*@return the level of the inventory
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get the capacity of the inventory
	*@return the capacity of the inventory
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the HP of the inventory
	*@return the HP of the inventory
	*/
	public int getHP() {
		return hp;
	}

	/**
	*Get the Range of the inventory
	*@return the Range of the inventory
	*/
	public int getRange() {
		return range;
	}

	/**
	*Get the maximum damage of the inventory
	*@return the maximum damage of the inventory
	*/
	public int getMaxDamage() {
		return maxDamage;
	}

	/**
	*Get the mininum damage of the inventory
	*@return the minimum damage of the inventory
	*/
	public int getMinDamage() {
		return minDamage;
	}

	/**
	*Get the original maximum damage of the inventory
	*@return the original maximum damage of the inventory
	*/
	public int getOriginalMaxDamage() {
		return originalMaxDamage;
	}

	/**
	*Get the original mininum damage of the inventory
	*@return the original minimum damage of the inventory
	*/
	public int getOriginalMinDamage() {
		return originalMinDamage;
	}

	/**
	*Get the weight of the inventory
	*@return the weight of the inventory
	*/
	public int getWeight() {
		return weight;
	}

	/**
	*Get the additional speed provided by the inventory
	*@return the additional speed provided the inventory
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get the owner of the inventory
	*@return the owner of the inventory
	*/
	public User getOwner() {
		return owner;
	}

	//Set the minDamage of the inventory
	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	//Set the maxDamage of the inventory
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	//Set the status of the inventory
	public void setStatus(boolean status) {
		this.status = status;
	}
}