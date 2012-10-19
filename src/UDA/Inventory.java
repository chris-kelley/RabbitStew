/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDA;

import java.util.ArrayList;



/**
 *
 * @author Mike Kelley
 * 
 */
public class Inventory {

    private int totalCapacity, remainingCapacity;
    private String owner;
    private ArrayList<Item> items;
    private Item returnItem;

    
    public Inventory(String ownerIn, int totalCapacityIn, ArrayList<Item> itemsIn){
        
    	owner = ownerIn;
        totalCapacity = totalCapacityIn;
        items = itemsIn;
    
    }// end constructor


    /**NOT_YET_TESTED
     * Changes the total capacity of this Inventory object by the int amount specified
     * @param extraCapacity
     */
    public void changeTotalCapacity(int extraCapacity) {
    	totalCapacity += extraCapacity;
    } // end changeTotalCapacity

    /**NOT_YET_TESTED
     * adds the specified Item object to the ArrayList<Item> of this Inventory object
     * 
     * @param item
     */
    public void addItem(Item item){
    	//TODO create method body
    }// end addItem
    
    /**NOT_YET_TESTED
     * removes the specified Item object from the ArrayList<Item> of this Inventory object
     * 
     * @param item
     */
    public void removeItem(Item item) {
    	//TODO create method body
    }
    ///////////////////getters\\\\\\\\\\\\\\\\\\\\\\\\\\
    
    /**NOT_YET_TESTED
     * returns the totalCapacity value of this Inventory object
     * 
     * @return int totalCapacity
     */
    public int getTotalCapacity(){
       return totalCapacity;
    }
    
    /**NOT_YET_TESTED
     * returns the String representing the owner of this Inventory object.
     * Note that an owner may be a Character or a Room object
     * 
     * @return String owner
     */
    public String getOwner(){
        return owner;
    }
    
    /**NOT_YET_TESTED
     * returns the ArrayList<Item> of this Inventory object. 
     * Does not return the item names, nor does it return the individual items.
     * 
     * @return ArrayList<Item> items
     */
    public ArrayList<Item> getItemsContained(){
        return items;
    }
    
    /**NOT_YET_TESTED
     * calculates and returns the remaining capacity of this Inventory object.
     * 
     * @return int remainingCapacity
     */
    public int getRemainingCapacity(){
        int remainingCapacity = totalCapacity;
        //TODO create method body;
        return remainingCapacity;
    } // end getRemainingCapacity


    /**NOT_YET_TESTED
     * Tests to see if an Item object is present in this Inventory object.
     * If present, the Item is returned. If not, a message is displayed.
     * Takes the String representation of the Item name as its argument.
     * 
     * @param String name
     * 
     * @return Item item
     */
	public Item getItem(String name){
	    Item item = null;
	        return item;
	}

	/**NOT_YET_TESTED
	 * returns a colon delineated String containing the String representation of
	 * the name of each Item object in the ArrayList<Item> of this Inventory object
	 * 
	 * @return String itemString
	 */
	String getItemNames(){
	    String itemString;
	    StringBuilder contents = new StringBuilder();
	    
	    for (int i = 0; i < items.size(); i++){
	        contents.append(items.get(i).getName());
	        contents.append(":");
	    }
	    contents.deleteCharAt(contents.length()-1);
	    itemString = contents.toString();
	    return itemString;
	}

	/**NOT_YET_TESTED
	 * checks to see if this Inventory object contains a specific Item object.
	 * Takes a String argument representing the name value of the Item
	 * 
	 * @param String name
	 * 
	 * @return Boolean flag
	 */
    public boolean contains(String name){
        Boolean flag = false;
        here:
        for(int i = 0; i < items.size(); i++){
            if (name.equals(items.get(i).getName())){
                flag = true;
                returnItem = items.get(i);
                break here;
            }// end if
        }// end for i
        
        return flag;
    }// end contains

    /**NOT_YET_TESTED
     * uses contains() to see if a specific Item object is present in this Inventory object.
     * If so, that Item object is removed from the ArrayList<Item> of this Inventory object.
     * 
     * @param String name
     */
    public void removeItem(String name){
        if(contains(name)){
            for(int i = 0; i < items.size() - 1; i++){
                if (name.equals(items.get(i).getName())){
                    items.remove(items.get(i));
                }// end if
            }// end for
            
        }// end if
        if(!contains(name)){
            Message.illegalItemChoice(name);
        }
    }// end removeItem
    /////////////////////////changers\\\\\\\\\\\\\\\\\\\\\\\\
    

    /**NOT_YET_TESTED
     * Changes the capacity of the player's inventory by the specified size (delta).
     * Note that delta can be positive or negative. 
     * If the new inventory is too small to hold all the Items the player is carrying, the player
     * is forced to drop the extra Items. Returns an Item array with the dropped Items, if any.
     * 
     * @param int delta
     * @param Character c 
     * 
     * @return Item[] itemsDropped
     */

} // end Inventory
