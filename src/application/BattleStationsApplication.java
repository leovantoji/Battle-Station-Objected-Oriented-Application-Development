package application;

import controller.MainController;
import menu.BSLoginMenu;

//The main class of this Battle Stations Application.
//It sets up objects that are required throughout the application and instantiate the starting boundary class BSLoginMenu to interact with user.

public class BattleStationsApplication {
    public static void main(String[] args) {
    	MainController mainController = new MainController();
    	BSLoginMenu loginMenu = new BSLoginMenu(mainController);
    	loginMenu.readOption();
    }
}