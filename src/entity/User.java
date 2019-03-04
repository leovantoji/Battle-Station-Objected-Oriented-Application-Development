package entity;

import entity.Inventory;
import entity.Ship;
import entity.Cannon;
import entity.SubCannon;
import entity.Missile;
import entity.Melee;
import entity.Engine;
import entity.Hull;
import entity.FigureHead;
import entity.Stabilizer;
import entity.Sail;

/**
*This represents an instance of user
*A user has his/her username, password, character type and details of his/her accounts
*/

public class User {

	//Attributes of users
	private String username;
	private String password;
	private char character;
	private int level;
	private double stats;
	private int capacity;
	private int fullCapacity;
	private int speed;
	private int ap;
	private int hp;
	private int fullHP;
	private int exp;
	private int slots;
	private double gunnery;
	private double craft;
	private double navigation;
	private int gold;
	private int wood;
	private int ore;
	private int prock;
	private int noWins;
	private int noLosses;
	private Ship ship;
	private BSDate dateJoined;
	private BSDate lastLoggedIn;

	//Constants
	private static final int MAXIMUM_AP = 500;
	private static final int GOLD  = 10000000;
	private static final int WOOD  = 100000;
	private static final int ORE   = 10000;
	private static final int PROCK = 1000;
	private static final double STARTING_GUNNERY = 1.5;
	private static final double STARTING_NAVIGATION = 1.5;
	private static final double STARTING_STATS = 3.0;
	private static final double ADDITIONAL_STATS_PER_LEVEL = 4.5;
	private static final int STARTING_AP = 120;
	private static final int STARTING_LEVEL = 1;
	private static final int STARTING_EXP = (int) ((STARTING_LEVEL*STARTING_LEVEL*STARTING_LEVEL*50-150*STARTING_LEVEL*STARTING_LEVEL+400*STARTING_LEVEL-300)/3.0);
	private static final int MAXIMUM_LVL = 100;
	private static final int MAXIMUM_EXP = 17179900; //Level 100 is the maxium. XP = 50/3.0*(lvl)^3 - 50*(lvl)^2 + 400/3.0*(lvl) - 100

	/**
	*Specific constructor of user
	*@param username 		The username of the user
	*@param password		The password of the user
	*@param characterType	The character type of the user
	*@param level			The level of the user
	*@param stats			The number of stats points of the user
	*@param capacity		The amount of capacity used
	*@param fullCapacity	The full capacity of the user
	*@param speed			The speed of the user
	*@param ap				The number of action points of the user
	*@param hp				The health point of the user
	*@param fullHP			The full health point of the user
	*@param exp				The experience point of the user
	*@param slots			The number of free slots
	*@param gunnery			The gunnery point of the user
	*@param craft			The craft point of the user
	*@param navigation		The navigation point of the user
	*@param gold			The number of gold that the user has
	*@param wood			The number of wood that the user has
	*@param ore				The number of ore that the user has
	*@param prock			The number of plasma rock that the user has
	*@param noWins			The number of wins of the user
	*@param noLosses		The number of losses of the user
	*@param ship 			The ship of the user
	*@param dateJoined		The date that the user first joined BattleStations
	*@param lastLoggedIn	The last time that the user logged in the application
	*/
	public User(String username, String password, char character, int level, double stats, int capacity, int fullCapacity, int speed, 
		int ap, int hp, int fullHP, int exp, int slots, double gunnery, double craft, double navigation, int gold, int wood, int ore, 
		int prock, int noWins, int noLosses, Ship ship, BSDate dateJoined, BSDate lastLoggedIn) {
		this.username = username;
		this.password = password;
		this.character = character;
		this.level = level;
		this.stats = stats;
		this.capacity = capacity;
		this.fullCapacity = fullCapacity;
		this.speed = speed;
		this.ap = ap;
		this.hp = hp;
		this.fullHP = fullHP;
		this.exp = exp;
		this.slots = slots;
		this.gunnery = gunnery;
		this.craft = craft;
		this.navigation = navigation;
		this.gold = gold;
		this.wood = wood;
		this.ore = ore;
		this.prock = prock;
		this.noWins = noWins;
		this.noLosses = noLosses;
		this.ship = ship;
		this.dateJoined = dateJoined;
		this.lastLoggedIn = lastLoggedIn;
	}

	//Constructor for new user upon succesful registration
	public User(String username, String password, char character, BSDate dateJoined, BSDate lastLoggedIn) {
		this(username, password, character, STARTING_LEVEL, STARTING_STATS, 0, 0, 0, 
			STARTING_AP, 0, 0, STARTING_EXP, 0, 0.0, 0.0, 0.0, GOLD, WOOD, ORE, PROCK, 0, 0, null, dateJoined, lastLoggedIn);

		if(character == 'P') {
			addGunnery(STARTING_GUNNERY);
		} else {
			addNavigation(STARTING_NAVIGATION);
		}
	}

	/**
	*Get user's username
	*@return user's username
	*/
	public String getUsername() {
		return username;
	}

	/**
	*Get user's password
	*@return user's password
	*/
	public String getPassword() {
		return password;
	}

	/**
	*Get user's character type
	*@return user's character type
	*/
	public char getCharacter() {
		return character;
	}

	/**
	*Get user's level
	*@return user's level
	*/
	public int getLevel() {
		return level;
	}

	/**
	*Get user's number of stats points
	*@return user's number of stats points
	*/
	public double getStats() {
		return stats;
	}

	/**
	*Get current amount of capacity used
	*@return current amount of capacity used
	*/
	public int getCapacity() {
		return capacity;
	}

	/**
	*Get the full capacity
	*@return the full capacity
	*/
	public int getFullCapacity() {
		return fullCapacity;
	}	

	/**
	*Get user's speed
	*@return user's speed
	*/
	public int getSpeed() {
		return speed;
	}

	/**
	*Get user's numer of action points
	*@return user's number of action points
	*/
	public int getAP() {
		return ap;
	}

	/**
	*Get user's health point
	*@return user's health point
	*/
	public int getHP() {
		return hp;
	}

	/**
	*Get user's full health point
	*@return user's full health point
	*/
	public int getFullHP() {
		return fullHP;
	}

	/**
	*Get user's experience point
	*@return user's experience point
	*/
	public int getEXP() {
		return exp;
	}

	/**
	*Get user's number of free slots
	*@return user's number of free slots
	*/
	public int getSlots() {
		return slots;
	}

	/**
	*Get user's gunnery point
	*@return user's gunnery point
	*/
	public double getGunnery() {
		return gunnery;
	}

	/**
	*Get user's craft point
	*@return user's craft point
	*/
	public double getCraft() {
		return craft;
	}

	/**
	*Get user's navigation point
	*@return user's navigation point
	*/
	public double getNavigation() {
		return navigation;
	}

	/**
	*Get the number of gold that the user has
	*@return the number of gold that the user has
	*/
	public int getGold() {
		return gold;
	}

	/**
	*Get the number of wood that the user has
	*@return the number of wood that the user has
	*/
	public int getWood() {
		return wood;
	}

	/**
	*Get the number of ore that the user has
	*@return the number of ore that the user has
	*/
	public int getOre() {
		return ore;
	}

	/**
	*Get the number of plasma rock that the user has
	*@return the number of plasma rock that the user has
	*/
	public int getProck() {
		return prock;
	}

	/**
	*Get the number of wins of the user
	*@return the number of wins of the user
	*/
	public int getNoWins() {
		return noWins;
	}

	/**
	*Get the number of losses of the user
	*@return the number of losses of the user
	*/
	public int getNoLosses() {
		return noLosses;
	}

	/**
	*Get user's ship
	*@return user's ship
	*/
	public Ship getShip() {
		return ship;
	}

	//Get the name of the ship. If user doesn't own anyship yet, return "No Ship"
	public String getShipName() {
		if(ownShip()) {
			return ship.getName();
		} else {
			return "No Ship";
		}
	}

	/**
	*Get the date that the user first joined BattleStations
	*@return the date the user first joined BattleStations
	*/
	public BSDate getDateJoined() {
		return dateJoined;
	}

	/**
	*Get the last time that the user logged in the application
	*@return the last time that the user logged in the application
	*/
	public BSDate getLastLoggedIn() {
		return lastLoggedIn;
	}

	//Change the level of the user when user levels up
	public void setLevel(int level) {
		this.level = level;
	}

	//Change the amount of stats points
	public void setStats(double stats) {
		this.stats = stats;
	}	

	//Update the current amount of capacity used
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	//Update the full capacity
	public void setFullCapacity(int fullCapacity) {
		this.fullCapacity = fullCapacity;
	}	

	//Update the user's speed
	public void setSpeed(int speed) {
		this.speed = speed;
	}	

	//Change the user's number of action point
	public void setAP(int ap) {
		this.ap = ap;
	}

	//Change the user's health point
	public void setHP(int hp) {
		this.hp = hp;
	}

	//Change the user's full health point
	public void setFullHP(int fullHP) {
		this.fullHP = fullHP;
	}

	//Change the user's number of free slots
	public void setSlots(int slots) {
		this.slots = slots;
	}

	//Change the user's total experience point
	public void setEXP(int exp) {
		this.exp = exp;
	}			

	//Change the user's Gunnery point
	public void setGunnery(double gunnery) {
		this.gunnery = gunnery;
	}

	//Change the user's Craft point
	public void setCraft(double craft) {
		this.craft = craft;
	}

	//Change the user's Navigation point
	public void setNavigation(double navigation) {
		this.navigation = navigation;
	}	

	//Update user's amount of resources
	public void setResources(int gold, int wood, int ore, int prock) {
		this.gold = gold;
		this.wood = wood;
		this.ore = ore;
		this.prock = prock;
	}

	//Set the user's ship to ship
	public void setShip(Ship ship) {
		this.ship = ship;
	}

	//Update user's last logged in time
	public void setLastLoggedIn(BSDate lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	//Increase the user's number of stats points by additionalStats
	public void addStats(double additionalStats) {
		stats += additionalStats;
	}

	//Check if additionalCapacity can be equipped. If yes, add additionalCapacity to capacity
	public boolean addCapacity(int additionalCapacity) {
		if(capacity + additionalCapacity <= fullCapacity) {
			capacity += additionalCapacity;
			return true;	
		}
		
		return false;
	}

	//Increase the user's number of action points by additionalAP
	public void addAP(int additionalAP) {
		ap += additionalAP;
		if(ap > MAXIMUM_AP) {
			ap = MAXIMUM_AP;
		}
	}

	//Increase the user's health point by additionalHP
	public void addHP(int additionalHP) {
		hp += additionalHP;
		if(hp > fullHP) {
			hp = fullHP;
		}
	}

	//Increase the user's exp by additionalExp
	public void addEXP(int additionalExp) {
		exp += additionalExp;
	}

	//Increase the user's gunnery point by additionalGunnery
	public void addGunnery(double additionalGunnery) {
		gunnery += additionalGunnery;
	}

	//Increase the user's craft point by additionalCraft
	public void addCraft(double additionalCraft) {
		craft += additionalCraft;
	}

	//Increase the user's navigation point by additionalNavigation
	public void addNavigation(double additionalNavigation) {
		navigation += additionalNavigation;
	}

	//Add user's resources when user buys an inventory
	public void addResources(int gold, int wood, int ore, int prock) {
		this.gold += gold;
		this.wood += wood;
		this.ore += ore;
		this.prock += prock;
	}

	//Deduct user's resources when user buys an inventory
	public void deductResources(int gold, int wood, int ore, int prock) {
		this.gold -= gold;
		this.wood -= wood;
		this.ore -= ore;
		this.prock -= prock;
	}

	//Increase the number of wins when user wins PVP
	public void addNoWins() {
		++noWins;
	}

	//Increase the number of losses when user loses PVP
	public void addNoLosses() {
		++noLosses;
	}

	//Check whether user owns a ship
	public boolean ownShip() {
		if(ship != null) {
			return true;
		}

		return false;
	}

	//Check if user can buy a particular ship
	public boolean isAbleToBuy(Ship aShip) {
		if(getGold() >= aShip.getGold() && getWood() >= aShip.getWood() && getOre() >= aShip.getOre() 
			&& getProck() >= aShip.getProck() && getLevel() >= aShip.getLevel()) {
			
			deductResources(aShip.getGold(), aShip.getWood(), aShip.getOre(), aShip.getProck());
			int newFullHP = (int) (aShip.getHP()*(1+0.01*craft));
			int newSpeed = (int) (aShip.getSpeed() + getNavigation());
			int newFullCapacity = aShip.getCapacity();
			
			setSpeed(newSpeed);
			setHP(newFullHP);
			setFullHP(newFullHP);
			setFullCapacity(newFullCapacity);
			setShip(aShip);
			setSlots(aShip.getSlots());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular cannon
	public boolean isAbleToBuy(Cannon cannon) {
		if(getGold() >= cannon.getGold() && getWood() >= cannon.getWood() && getOre() >= cannon.getOre() 
			&& getProck() >= cannon.getProck() && getLevel() >= cannon.getLevel()) {
			
			deductResources(cannon.getGold(), cannon.getWood(), cannon.getOre(), cannon.getProck());
			return true;
		}

		return false;	
	}

	//Check if user can buy a particular subCannon
	public boolean isAbleToBuy(SubCannon subCannon) {
		if(getGold() >= subCannon.getGold() && getWood() >= subCannon.getWood() && getOre() >= subCannon.getOre() 
			&& getProck() >= subCannon.getProck() && getLevel() >= subCannon.getLevel()) {
			
			deductResources(subCannon.getGold(), subCannon.getWood(), subCannon.getOre(), subCannon.getProck());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular missile
	public boolean isAbleToBuy(Missile missile) {
		if(getGold() >= missile.getGold() && getWood() >= missile.getWood() && getOre() >= missile.getOre() 
			&& getProck() >= missile.getProck() && getLevel() >= missile.getLevel()) {
			
			deductResources(missile.getGold(), missile.getWood(), missile.getOre(), missile.getProck());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular melee
	public boolean isAbleToBuy(Melee melee) {
		if(getGold() >= melee.getGold() && getWood() >= melee.getWood() && getOre() >= melee.getOre() 
			&& getProck() >= melee.getProck() && getLevel() >= melee.getLevel()) {
			
			deductResources(melee.getGold(), melee.getWood(), melee.getOre(), melee.getProck());
			return true;
		}

		return false;	
	}

	//Check if user can buy a particular figureHead
	public boolean isAbleToBuy(FigureHead figureHead) {
		if(getGold() >= figureHead.getGold() && getWood() >= figureHead.getWood() && getOre() >= figureHead.getOre() 
			&& getProck() >= figureHead.getProck() && getLevel() >= figureHead.getLevel()) {
			
			deductResources(figureHead.getGold(), figureHead.getWood(), figureHead.getOre(), figureHead.getProck());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular stabilizer
	public boolean isAbleToBuy(Stabilizer stabilizer) {
		if(getGold() >= stabilizer.getGold() && getWood() >= stabilizer.getWood() && getOre() >= stabilizer.getOre() 
			&& getProck() >= stabilizer.getProck() && getLevel() >= stabilizer.getLevel()) {
			
			deductResources(stabilizer.getGold(), stabilizer.getWood(), stabilizer.getOre(), stabilizer.getProck());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular engine
	public boolean isAbleToBuy(Engine engine) {
		if(getGold() >= engine.getGold() && getWood() >= engine.getWood() && getOre() >= engine.getOre() 
			&& getProck() >= engine.getProck() && getLevel() >= engine.getLevel()) {
			
			deductResources(engine.getGold(), engine.getWood(), engine.getOre(), engine.getProck());
			return true;
		}

		return false;
	}

	//Check if user can buy a particular hull
	public boolean isAbleToBuy(Hull hull) {
		if(getGold() >= hull.getGold() && getWood() >= hull.getWood() && getOre() >= hull.getOre() 
			&& getProck() >= hull.getProck() && getLevel() >= hull.getLevel()) {
			
			deductResources(hull.getGold(), hull.getWood(), hull.getOre(), hull.getProck());
			return true;
		}

		return false;
	}	

	//Check if user can buy a particular sail
	public boolean isAbleToBuy(Sail sail) {
		if(getGold() >= sail.getGold() && getWood() >= sail.getWood() && getOre() >= sail.getOre() 
			&& getProck() >= sail.getProck() && getLevel() >= sail.getLevel()) {
			
			deductResources(sail.getGold(), sail.getWood(), sail.getOre(), sail.getProck());
			return true;
		}

		return false;
	}

	//Equip an inventory
	public boolean equip(Inventory i) {
		if(i.getInventory().equalsIgnoreCase("Weapon")) {
			if((fullCapacity-capacity) >= i.getWeight() && (slots>0) && (!i.getStatus())) {
				capacity += i.getWeight();
				--slots;
				return true;
			} 
		} else if(i.getInventory().equalsIgnoreCase("Part")) {
			if((fullCapacity-capacity) >= i.getWeight() && (!i.getStatus())) {
				capacity += i.getWeight();
				fullCapacity += i.getCapacity();
				speed += i.getSpeed();
				hp += i.getHP();
				fullHP += i.getHP();
				return true;
			}
		}

		return false;
	}

	//Unequip an inventory
	public void unequip(Inventory i) {
		if(i.getInventory().equalsIgnoreCase("Weapon")) {
			++slots;
			capacity -= i.getWeight();
		} else if(i.getInventory().equalsIgnoreCase("Part")) {
			capacity -= i.getWeight();
			fullCapacity -= i.getCapacity();
			speed -= i.getSpeed();
			hp -= i.getHP();
			fullHP -= i.getHP();
		}
	}

	//Compute the user level given the exp
	public void computeLevel(int exp) {
		if(exp >= MAXIMUM_EXP) {
			setLevel(MAXIMUM_LVL);
			setEXP(MAXIMUM_EXP);
		} else {
			for(int i=1; i<MAXIMUM_LVL; ++i) {
				if(computeEXP(i) <= exp && computeEXP(i+1) > exp) {
					addStats(ADDITIONAL_STATS_PER_LEVEL*(i-getLevel()));
					setLevel(i);
				}
			}
		}
	}

	//Compute the exp check point given the level
	public int computeEXP(int lvl) {
		int computedEXP = Math.round((float) (50/3.0*lvl*lvl*lvl - 50*lvl*lvl + 400/3.0*lvl - 100));
		return computedEXP;
	}
}