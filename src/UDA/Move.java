
package UDA;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * <p>Move allows us to make a move in the game RabbitStew. It takes a String as its argument.</p>
 * <p>If the move is legal, it decides what the move is based on the contents of the String. It uses
 * the following word types: Subject, Object, Verb, preposition, prepositional object. From these,
 * a valid move can be built and returned.</p>
 * <p>Articles, both definite and indefinite, are ignored</p>
 * <p>Created 23:51PM on 01/27/2012 by Mike Kelley</p>
 * <p>Last modified 10/16/2012 by Mike Kelley</p>
 * 
 * @author Mike Kelley
 */

public class Move{

    private boolean legal, test;
    private int iType = -1, jType = -1;
    private String room, infoList, direction, subject, object, verb, preposition, prepositionObject;
    private Record r;
    private Character actor;
    private String[] move;

    
    public static final String[][] subjectArray = {{"me", "i"},
    										{"you"},
    										{"him", "her", "it"}};
    
    public static final String[][] verbArray = {{"go", "walk", "move", "travel", "run"}, //changeRoom verbs
    										{"talk", "tell", "answer", "say"},// interaction verbs
    										{"fight", "kill", "hit", "stab", "slay", "behead", "wound", "injure"},// fighting verbs
    										{"take", "get", "grab", "nab", "snag", "snatch", "grasp"},// takeItem verbs
    										{"drop", "leave", "put", "set"}};//dropItem verbs
    
    public static final String[][] objectArray = {{"knife", "sword", "axe"}, // weapons
									        {"helmet", "boots", "gloves", "shin-guards"}, // armor
									        {"bag", "backpack", "sack"}, // increased inventory 
									        {"hammer", "wrench"}, // tools
									        {"shelf", "table", "chair"}}; //furniture
    
    public static final String[][] prepositionArray = {{"in", "into", "within"},
											{"on", "onto"},
											{"above"},
											{"below", "under", "underneath", "beneath"}};
									    
    public static final String[][] directionArray ={//corresponding to the directional buttons on the GUI, 0-9
	                                        {"nw","northwest", "north west", "north-west", "nwest", "n-west", "nor'west", "northw", "north-w"},
	                                        {"n", "north", "northe", "northly", "northward-ho"},
	                                        {"ne", "northeast", "north east", "north-east"},
	                                        {"e", "east"},
	                                        {"se", "southeast", "south east", "south-east"},
	                                        {"s", "south"},
	                                        {"sw", "southwest", "south west", "south-west"},
	                                        {"w", "west"},
	                                        {"u", "up"},
	                                        {"d", "down"}};

    
    public static final String[][][] legalSentences = {subjectArray, verbArray, objectArray, prepositionArray, directionArray};
  
    /**
     * <p>the only constructor for Move we will need.</p>
     * <p>First we split the argument String by space chars and determines what each word is.</p>
     * <p>One we know that, we decide what method to use to execute the move.</p>
     * 
     * @param String moveIn
     */
    public Move(String moveIn) {
    	move = moveIn.split(" ");
    	//TODO complete constructor
    }
    private boolean isLegal(){
    	legal = false;
    	//TODO create method body
    	return legal;
    }// end isLegal
    
    /**NOT_YET_TESTED
     * <p>Allows a character to pick up an Item object in the game.</p>
     * <p>First, it checks to see if the Item object is present in the Room's Inventory object. 
     * If not, a message is displayed.</p>
     * <p>If the Item is present, the method checks if the 
     * Character object has space in its Inventory object.</p>
     * <p>If there is sufficient space, the Item object is added to the ArrayList<Item> of 
     * the Character's Inventory object. If there is not sufficient space, a message is displayed instead.</p>
     * 
     * @param String name
     */
    public void takeItem(String name){
    	//TODO create method body
    } // end takeItem
    
    /**NOT_YET_TESTED
     * <p>Allows Character objects to drop an Item object in the game.</p>
     * <p>First, we check to see if the Item object is present in the 
     * Character's inventory. If not, we display a message.</p>
     * <p>If the Item is present, we remove it from the Character Inventory 
     * and add it to the Room Inventory.</p>
     *  
     * @param String name
     */
    public void dropItem(String name){
    	//TODO create method body
    } // end dropItem
    
    /**NOT_YET_TESTED
     * <p>allows a character object to take an exit in a Room object.</p>
     * <p>First, we check to see if the exit exists. If it does, we make the move. 
     * If  not, we display a message.</p>
     * 
     * @param String exit
     */
    public void changeRoom(String exit){
    	//TODO create method body
    }

    /**NOT_YET_TESTED
     * <p>Allows a Character object to use an Item object, if the
     * Item is present in the Character's Inventory.</p>
     * 
     * @param String name
     */
    public void useItem(String name){
    	//TODO create method body
    }

} // end class

