/*
 * Locator exists to store and retrieve static final variables within the game.
 * Created 12:13 01/27/2012 by Mike Kelley
 * Last modified 01/29/2012 by Mike Kelley
 */
package UDA;

import java.io.File;
/**
 * Locator holds a series of static variables that help us locate segments in a text file.
 * It's important to know each of these variables, we'll be using them lots and lots.
 * 
 * @author Mike
 */
public class Locator {

    
    protected static final String roomNum = "~r";
    protected static final String roomNumEnd = "r~";
    protected static final String roomDescription = "~rD";
    protected static final String roomDescriptionEnd = "rD~";    
    protected static final String exits = "~e";
    protected static final String exitsEnd = "e~";
    protected static final String exitDescription = "~eD";
    protected static final String exitDescriptionEnd = "eD~";
    protected static final String secretExits = "~sE";
    protected static final String secretExitsEnd = "sE~";
    protected static final String secretExitDescription = "~sED";
    protected static final String secretExitDescriptionEnd = "sED~";
    protected static final String items = "~i";
    protected static final String itemsEnd = "i~";
    protected static final String itemDescription = "~iD";
    protected static final String itemDescriptionEnd = "iD~";
    protected static final String itemsEquipped = "~iE";
    protected static final String itemsEquippedEnd = "iE~";
    protected static final String npcs = "~n";
    protected static final String npcsEnd = "n~";
    protected static final String npcDescription = "~nD";
    protected static final String npcDescriptionEnd = "nD~";
    protected static final String treasures = "~t";
    protected static final String treasuresEnd = "t~";
    protected static final String treasureDescription = "~tD";
    protected static final String treasureDescriptionEnd = "tD~";
    protected static final String characterPosition = "~cP";
    protected static final String characterPositionEnd = "cP~";
    protected static final String hint = "~h";        
    protected static final String hintEnd = "h~";
    protected static final String hitPoints = "~hP";
    protected static final String hitPointsEnd = "hP~";
    protected static final String roomsKnown = "~rK";
    protected static final String roomsKnownEnd = "rK~";
    protected static final String treasureScored = "~tS";
    protected static final String treasureScoredEnd = "tS~";
    protected static final String score = "~s";
    protected static final String scoreEnd = "s~";
    protected static final String moves = "~m";
    protected static final String movesEnd = "m~";
    protected static final String itemLocations = "~iL";
    protected static final String itemLocationsEnd = "iL~";
    protected static final String gameInventory = "~gI";
    protected static final String gameInventoryEnd = "gI~";
    
    private String path;
    protected Locator(){}
    
    protected String getPath(String d, String n){
    
        //This is broken, because we are now using a relative path. we need to make sure we put the files in the right places.
        path = new File("RoomFiles/" + n + ".txt" ).toURI().getPath();
        
        return path;
    
    }
    
}// end Locator
