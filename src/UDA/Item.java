/*
 * This class creates objects to track items within the game. created on 4/11/2012 
 * as a part of Rabbit Stew.
 * Mike Kelley
 */
package UDA;

/**
 *
 * @author Mike Kelley
 */
public class Item {
    
    private int score, size;
    private String name;
    private boolean consumable;
    
    /**
     * This constructor allows us to build a custom Item without using a Record object
     * or an Item file.
     * 
     * @param String nameIn
     * 
     * @param int sizeIn
     * 
     * @param int scoreIn
     * 
     * @param boolean consumableIn
     */
    public Item(String nameIn, int sizeIn, int scoreIn, boolean consumableIn){
    
    	consumable = consumableIn;
        name=nameIn;
        size = sizeIn;
        score = scoreIn;
        
    } // end constructor

    /**
     * This constructor allows us to build an Item object form an Item file
     *  via a Record object. We only need the String name of the Item file to read
     *  from. For simplicity's sake, all Item objects have the same 
     *  name value as their associated Item file.
     *   
     * @param nameIn
     */
    public Item(String nameIn){
        name = nameIn;
        
        Record r = new Record("ItemFiles", name);
        
        size = Integer.parseInt(r.getPartialFile("~iS", "iS~"));
        score = Integer.parseInt(r.getPartialFile("~iSV", "iSV~"));
        
        if (r.getPartialFile("~iC", "iC~").equals("1"))
        	consumable = true;
        else
        	consumable = false;
    }// end constructor
    

    
    ////////////////getters\\\\\\\\\\\\\\\\\\\
    
    /**
     * returns the String name of this Item object
     * 
     * @return String name
     */
    public String getName(){
        return name;
    }
    
    /** 
     * returns the int size of this Item object
     * 
     * @return int size
     */
    public int getSize(){
        return size;
    }
    
    /**
     * returns the int score of this Item object.
     * 
     * @return int score
     */
    public int getScore(){
        return score;
    }
 
    /**
     * Tells us if this Item object is consumable.
     * 
     * @return boolean consumable
     */
    public Boolean isConsumable(){
    	return consumable;
    }// end isConsummable
    
} // end Item
