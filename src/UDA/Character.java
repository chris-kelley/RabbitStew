/**
 * Created 04/13/2012 as a part of the Rabbit Stew project. Don't worry if you 
 * don't understand that. If you don't understand it, you don't need to.
 * 
 * Author: Mike Kelley
 */
package UDA;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mike
 */
public class Character implements Saveable, Killable{
    
    private int score;
    private String name, attributes;
    private ArrayList<Item> items;
    private Inventory characterInventory;
    private Room room;
    private Record r;
    
    
    public Character(String nameIn){
        r = new Record("CharacterFiles", nameIn);
        name = nameIn;
        characterInventory = new Inventory(name, Integer.parseInt(r.getPartialFile("~cIC", "cIC~")), items);
        room = new Room(r.getPartialFile("~cC", "cC~"));
        score = Integer.parseInt(r.getPartialFile("~cSc", "cSc~"));
        } // end constructor

    
    ////////////////////getters\\\\\\\\\\\\\\\\\\\\\\\
    public String getName(){
        return name;
    }
    
    public Inventory getInventory(){
        return characterInventory;
    }

    public int getScore(){
        return score;
    }
   
    private String getItemNames(){
        ArrayList<Item> myItems = getInventory().getItemsContained();
        StringBuilder contents = new StringBuilder();
        String myString;
        
        for (int i = 0; i < myItems.size(); i++){
            contents.append(myItems.get(i).getName());
            contents.append(":");
        }
        contents.deleteCharAt(contents.length()-1);
        myString = contents.toString();
        return myString;
    }

    ////////////////////changers\\\\\\\\\\\\\\\\\\\\\\\
  
    
    public void changeScore(int scoreIn) {
        score += scoreIn;
    }

   /////////////////implementations\\\\\\\\\\\\\\\\\\\\
    
    public void save() {
        
        try {
        	attributes = "~cIC\n" + characterInventory.getTotalCapacity() + "\ncIC~\n\n" + "~cI\n" + 
                    getItemNames() + "\ncI~\n\n" + "~cC\n" + room.getRoom() + 
                    "\ncC~\n\n" + "~cSc\n" + score + "\ncSc~\n\n";
        	
            r.writeStringToFile(attributes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println("File not found");
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            System.out.println("I/O error.");
        }
    }

    public void kill() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
} // end Character
