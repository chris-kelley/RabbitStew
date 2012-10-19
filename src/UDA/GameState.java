
package UDA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * GameState tracks the following crucial game state variables:
 * where player is right now
 * how healthy player is right now
 * how many rooms player has found so far
 * how many (and which) treasure items player has scored so far
 * how many items(including treasure) player is carrying right now
 * <p>
 * GameState also saves all of those data to a text file every time 
 * a single datum is changed, allowing the game to be constantly saved. We'll
 * see if that feature lasts or not, depending on now it impacts playability.
 * <p>
 * Created 16:26 PM 01/29/2012
 * Last modified 01/29/2012 by Mike Kelley
 * @author Mike Kelley
 */

public class GameState {
  
    private static String position, hitPoints, item, movesMadeString;
    private static String [] gameInventory;
    private static ArrayList roomsKnown, treasureScored, inventory, itemsEquipped, location;
    private static int health, movesMadeInt;
     
/**
 * Constructor takes two Strings, first is name of directory, second is name of file.
 * File is name of game state file, probably GameState. note the lack of file extension. 
 * Do *not* use a file extension, program will do it for you.
 * <p>
 * Constructor does not return anything.
 * @param directoryIn
 * @param nameIn 
 */
    public GameState(String directoryIn, String nameIn){
        
        setState(directoryIn, nameIn);
    } // end constructor

    private void setState(String directory, String name){
        Record r = new Record(directory, name);
      position = r.getPartialFile(Locator.roomNum, Locator.roomNumEnd);
      hitPoints = r.getPartialFile(Locator.hitPoints, Locator.hitPointsEnd).toString();
      health = Integer.parseInt(hitPoints);
      roomsKnown = r.getPairedInfoFromFile(Locator.roomsKnown, Locator.roomsKnownEnd);
      treasureScored = r.getPairedInfoFromFile(Locator.treasureScored, Locator.treasureScoredEnd);
      inventory = r.getPairedInfoFromFile(Locator.items, Locator.itemsEnd);
      itemsEquipped = r.getPairedInfoFromFile(Locator.itemsEquipped, Locator.itemsEquippedEnd);
      movesMadeString = r.getPartialFile(Locator.moves, Locator.movesEnd);
      movesMadeInt = Integer.parseInt(movesMadeString);
      //gameInventory = r.getStringArrayFromFile(Locator.gameInventory, Locator.gameInventoryEnd);
    } // end setState
    
    /////////////////////////// Getters \\\\\\\\\\\\\\\\\\\\\\\\\\
    
    /**
     * tells us what room the character is in
     * 
     * @return character's current position
     */
    protected static String getPosition(){
        return position;
    }
    /**
     * tells us how many hit points our character has
     * 
     * @return  character's hit points, in an integer value
     */
    protected static int getHealth(){
        return health;
    }
    /**
     * Gives us an ArrayList containing the room numbers our character has visited so far.
     * 
     * @return ArrayList with the room numbers
     */
    protected static ArrayList getRoomsKnown(){
        return roomsKnown;
    }
    /**
     * tells us how many treasures the character has scored, based on the scoring rules we will write for the game
     * 
     * @return ArrayList of treasure numbers (empty if none)
     */
    protected static ArrayList getTreasureScored(){
        return treasureScored;
    }
    /**
     * tells us what the player is actually carrying (minus what he's equipped), *not* what he's found and dropped or put somewhere.
     * If he has a knife on him, this will tell us. but if he's put the knife on a shelf somewhere, or if he has the knife in his hand, this will
     * *not* tell us that.
     * 
     * @return ArrayList of item numbers (blank if none)
     */
    protected static ArrayList getInventory(){
        return inventory;
    }
    /**
     * Tells us what the player has equipped.
     * 
     * @return ArrayList containing items equipped, by their number (blank if none)
     */
    protected static ArrayList getItemsEquipped(){
        return itemsEquipped;
    }
    /**
     * Tells us how many moves the player has made
     * 
     * @return integer value representing the moves made
     */
    protected static int getMoves(){
        return movesMadeInt;
    } 
    /**
     * tells us where an item is by numeric location.
     * 
     * @param String item, a string number value as always.
     * 
     * @return 
     
    protected static ArrayList getLocation(String item){
        location = new ArrayList();
        boolean flag = false;
        for(int i = 0; i < itemLocations.size(); i++ ){
            if (item.equals(itemLocations.get(i))){
                flag = true;
                location.add(itemLocations.get(i));
                location.add(itemLocations.get(i + 1));
                break;
            } // end if
        } // end for
        if(flag == false){
            System.out.println("Invalid Item, Try again");
        } // end if
        return location;
    } // end getLocation*/
    
    /**
     * This one tells us where an item is in the game. We're using a String[] array
     * read from a text file and maintained in RAM in a static context. The 
     * item number is its array position, and the value stored in that position 
     * is the location of that item. If array position 0 holds value 101, then 
     * item 0 is in room 101.
     * <p>
     * All rooms are 3 digit values. Personal inventory is represented by value 1, 
     * equipped items are in positions 2-9, which have not yet been assigned to 
     * equipment positions. 
     * <p>
     * There are special 2 digit suffixes to the 3 digit room numbers that represent 
     * special positions in the room. For instance, all tables might be represented by the number 21. 
     * In that case, an item located on a table in room 105 would have a position value of 10521.
     * 
     * @param int itemNumber - the item number we're looking at, *as an int*
     * 
     * @return item's location in the game, *as a String*
     */
    protected static String getItemPosition(int itemNumber){
        String itemLocation = gameInventory[itemNumber].toString();
        return itemLocation;
    }
    /**
     * returns the entire String array of item positions within the game
     * 
     * @return String[] array containing item position in order of item number.
     */
    protected static String[] getGameInventory(){
        return gameInventory;
    }
    
    //////////////////////// Changers \\\\\\\\\\\\\\\\\\\\\\\\\\\
 /**
  * Moves the player character from one room to the next.
  * takes a String representing the filename associated with
  * the next room as the argument, but without the file extension.
  * For instance, if you want the character to move to room 103, you would 
  * use "103" as the argument, *not* "103.txt". We automagically add the ".txt"
  * for you.
  * <p>
  * After moving, the method updates rooms known and saves current state to text file.
  * 
  * @param String newPosition the number of the room we're moving to, corresponds to the name of the text file describing that room
  * 
  * @return on success, the room we moved to. on failure, an error message.
  * 
  * @throws FileNotFoundException
  * 
  * @throws IOException 
  */
    
    protected static String changePosition(String newPosition) throws FileNotFoundException, IOException{
        position = newPosition;
        changeRoomsKnown(newPosition);
        saveState();
        return position;    
    }
    
    
    /**updates character health, in case we want to smite or heal him.
     * Takes a simple int as argument. We might think about adding code that prevents too large of a change.
     * 
     * @param int newHealth
     * 
     * @return on success, an int representation of our intrepid character's updated health
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    protected static int changeHealth(int newHealth) throws FileNotFoundException, IOException{
       health = health + newHealth;
       saveState();
       return health;
   }
    
    
   /**
    * changeRoomsKnown tells us when the player encounters a new room. It adds the new room to the 
    * ArrayList of rooms the player has seen so far. As usual, all rooms are referenced by their number, 
    * not their name. All room numbers are of type String.
    * 
    * @param ArrayList newRoom
    *
    * @return on success, an ArrayList containing all rooms known by player.
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static ArrayList changeRoomsKnown(String newRoom) throws FileNotFoundException, IOException{
       boolean flag = false;
       for (int i = 0; i < roomsKnown.size(); i++){
           if (newRoom.equals(roomsKnown.get(i).toString())){
               flag = true;
               break;
           } // end if
       } // end for i
       if (flag == false){
               roomsKnown.add(newRoom);
           } // end if
       saveState();
       return roomsKnown;
   } // end changeRoomsKnown
   
   /**
    * tests to see if new treasure has been scored, and if not, scores it. As usual, all treasures are 
    * referenced by their number, *not* their name.
    * 
    * @param ArrayList newTreasure
    * 
    * @return on success, the updated ArrayList of scored treasures.
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static ArrayList changeTreasureScored(String newTreasure) throws FileNotFoundException, IOException{
       boolean flag = false;
       for (int i = 0; i < treasureScored.size(); i++){
           if (newTreasure.equals(treasureScored.get(i))){
               flag = true;
               break;
           } // end if
       } // end for
       if (flag == false){
           treasureScored.add(newTreasure);
       } // end if
       saveState();
       return treasureScored;
   } // end changeTreasureScored
   
   /**
    * takes a string as an argument (which means we can only add one item at a time, does that need to change?)
    * and adds it to an ArrayList representing player inventory. Items are, as always, referred to by their
    * number, not their name. Saves state and returns the ArrayList.
    * 
    * @param String newItem
    * 
    * @return on success, whatever Mr. Intrepid has on him at present.
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static ArrayList addItemToInventory(String newItem) throws FileNotFoundException, IOException{
       boolean flag = false;
       for (int i = 0; i < inventory.size(); i++){
           if (newItem.equals(inventory.get(i))){
               flag = true;
               break;
           } // end if
       }// end for
       if (flag == false){
           inventory.add(newItem);
           moveItem(newItem, "1");
           saveState();
       }// end if
       return inventory;
   }// end addItemToInventory
   
   /**
    * removes a specified item from inventory, *after* checking to see if that item 
    * is in inventory to begin with.
    * 
    * @param String oldItem
    * 
    * @return updated inventory, plus String "You're not carrying " + oldItem
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static ArrayList dropItemFromInventory(String oldItem) throws FileNotFoundException, IOException{
       boolean flag = false;
       for (int i = 0; i < inventory.size(); i++){
           if (oldItem.equals(inventory.get(i))){
               flag = true;
               break;
           } // end if
       }// end for
       if (flag == true){
           inventory.remove(oldItem);
           moveItem(item, getPosition());
       }// end if
       else{
           System.out.println("You're not carrying " + oldItem);
           saveState();
       }
       
       return inventory;
   }//end dropItemFromInventory
   
   /**
    * Changes the location of an item in the itemLocations ArrayList.
    * 
    * @param String item
    * 
    * @param String place
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static void moveItem(String item, String place) throws FileNotFoundException, IOException{
       int i = Integer.parseInt(item);
       gameInventory[i] = place;
       saveState();
   } // end moveItem
   
   /**
    * if player has an item, euipItem allows us to equip that item for the player.
    * <p>
    * ********************KNOWN DESIGN FLAW:**********************
    * <p>
    * WE HAVEN'T DESIGNED A METHOD TO DETERMINE IF THE ITEM CAN BE 
    * EQUIPPED, AND IF SO, WHERE IT CAN BE EQUIPPED. AS IT STANDS, THE PLAYER 
    * COULD CONCEIVABLY EQUIP THE BENT SPOON OR SOMETHING EQUALLY RIDICULOUS. 
    * OF COURSE, THAT MIGHT BE KIND OF FUN.....
    * 
    * @param String itemToEquip
    * 
    * @return
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static ArrayList equipItem(String itemToEquip) throws FileNotFoundException, IOException{
       if(isInInventory(itemToEquip)){
         boolean flag = false;
            for (int i = 0; i < itemsEquipped.size(); i++){
                if (itemToEquip.equals(itemsEquipped.get(i))){
                    flag = true;
                    break;
                } // end if
            }// end for
            if (flag == false){
                itemsEquipped.add(itemToEquip);
                System.out.println(itemToEquip + " equipped.");
            }// end if
            if (flag == true){
                System.out.println("You've already equipped that.");
            } // end else
            saveState();
      }/// end if(this.
       else{
           System.out.println("You don't have one of those.");
       } // end else
      return itemsEquipped;
   } // end equipItem
   
   /**
    * increments the number of moves made by our Intrepid Adventurer.
    * 
    * @throws FileNotFoundException
    * 
    * @throws IOException 
    */
   protected static void addMove() throws FileNotFoundException, IOException{
       movesMadeInt ++;
       saveState();
   }
    /**
     * saves the state of the game to a text file called GameState
     * 
     * @throws FileNotFoundException
     * 
     * @throws IOException 
     */
    private static void saveState() throws FileNotFoundException, IOException{
        Record r = new Record("InfoFiles", "GameState");
       String roomsKnownString = r.formatArrayListToString(roomsKnown);
       String treasureScoredString = r.formatArrayListToString(treasureScored);
       String inventoryString = r.formatArrayListToString(inventory);
       String itemsEquippedString = r.formatArrayListToString(itemsEquipped);
       String gameInventoryString = r.formatStringArrayToString(gameInventory);
       ArrayList contents = new ArrayList();
                  contents.add(Locator.roomNum + "\n" +
                              position + "\n" +
                              Locator.roomNumEnd + "\n\n" +
                              Locator.hitPoints + "\n" +
                              health + "\n" +
                              Locator.hitPointsEnd + "\n\n" +
                              Locator.roomsKnown + "\n" +
                              roomsKnownString + "\n" +
                              Locator.roomsKnownEnd + "\n\n" +
                              Locator.treasureScored + "\n" +
                              treasureScoredString + "\n" +
                              Locator.treasureScoredEnd + "\n\n" +
                              Locator.items + "\n" +
                              inventoryString + "\n" +
                              Locator.itemsEnd + "\n\n" +
                              Locator.itemsEquipped + "\n" +
                              itemsEquippedString + "\n" +
                              Locator.itemsEquippedEnd + "\n\n" + 
                              Locator.moves + "\n" +
                              movesMadeInt + "\n" +
                              Locator.movesEnd + "\n\n" + 
                              Locator.gameInventory + "\n" +
                              gameInventoryString + "\n" +
                              Locator.gameInventoryEnd + "\n\n"
                                );
                              
        
       // Record.writeToFile(contents);
    } // end saveState
    
    /**
     * Tells us if the player has a given item in inventory.
     * 
     * @param String item
     * 
     * @return boolean value
     */
    private static boolean isInInventory(String item){
        boolean flag = false;
        for(int i = 0; i < inventory.size(); i++){
            if (item.equals(inventory.get(i))){
                flag = true;
            } // end if
        } // end for
        return flag;
    } // end isInInventory
} // end GameState
