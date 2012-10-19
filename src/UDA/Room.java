/*
 * This class describes the basic Room. Things like contents, exits, NPCs, etc.
 * Created 10:21 01/23/2012 by Mike Kelley
 * Last Modified 01/23/2012
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
public class Room implements Saveable{

    private String[] normalExits, secretExits, furniture;
    private Character[] NPCs;
    ArrayList<Item> items;
    private int capacity = 1000000;
    private Inventory inventory;
    private Item returnItem;
    private static String room, hint;
    private String description, attributes;
    private Record r;

    /**
     * reads info from a text file to create a room object. As always, do not use a file extension, just the file name.
     * i.e., use "101" to build an object for room 101, *not* "101.txt". We automagically add the ".txt" for you.
     * 
     * @param String directory
     * 
     * @param String name 
     */
    public Room(String roomIn){
        
        r = new Record("RoomFiles", roomIn);
        room = roomIn;
        hint = r.getPartialFile("~rH", "rH~");
        description = r.getPartialFile("~rD", "rD~");
        setNormalExits(r.getPairedInfoFromFile("~rE", "rE~"));
        setSecretExits(r.getPairedInfoFromFile("~rSE", "rSE~"));
        setNPCs(r.getPairedInfoFromFile("~rNPC", "rNPC~"));
        setInventory(r.getPairedInfoFromFile("~rI", "rI~"));
    } // end constructor
    
///////////////setters\\\\\\\\\\\\\\\\\\\\
    private void setNormalExits(ArrayList<String> normalExitsIn) {
        normalExits = new String[normalExitsIn.size()];
        for (int i = 0; i < normalExits.length; i++){
            normalExits[i] = normalExitsIn.get(i);
        }// end for
    }

    private void setSecretExits(ArrayList<String> secretExitsIn) {
        secretExits = new String[secretExitsIn.size()];
        for (int i = 0; i < secretExits.length; i++){
            secretExits[i] = secretExitsIn.get(i);
        }
    }

    private void setNPCs(ArrayList<String> NPCNames) {
        NPCs = new Character[NPCNames.size()];
        for(int i =0; i < NPCNames.size(); i++){
            NPCs[i] = new Character(NPCNames.get(i));
        }// end for i
    }
    
    private void setInventory(ArrayList<String> itemNames){
        items = new ArrayList<Item>();
        for (int i = 0; i < itemNames.size(); i++){
            items.add(new Item(itemNames.get(i)));
        }// end for i
        inventory = new Inventory(room, capacity, items);
    }
    
//////////////getters\\\\\\\\\\\\\\\\\   
    public String getRoom(){
        return room;
    }

    public String getDescription(){
        return description;
    }
    
    public String[] getNormalExits(){
        return normalExits;
    }
    
    public Character[] getNPCs(){
        return NPCs;
    }
    
    public String[] getSecretExits(){
        return secretExits;
    }
    
    public String getHint(){
        return hint;
    }
    
   private String getNPCNames(){
        String itemString;
        StringBuilder contents = new StringBuilder();
        
        for (int i = 0; i < NPCs.length; i++){
            contents.append(NPCs[i].getName());
            contents.append(":");
        }
        contents.deleteCharAt(contents.length()-1);
        itemString = contents.toString();
        return itemString;
    }
    
 
////////////////////////implementations\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void save() {
        attributes = "~rD\n" + description + "\nrD~\n\n" + "~rE\n" + normalExits + "\nrE~\n\n" + "~rSE\n" + secretExits +
        "\nrSE~\n\n" + "~rH\n" + hint + "\nrH~\n\n" + "~rI\n" + inventory.getItemNames() + "rI~\n\n" + "~rNPC\n" + getNPCNames() + "\nrNPC~\n\n";
        
        try {
            r.writeStringToFile(attributes);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// end save
    
} // end Room
