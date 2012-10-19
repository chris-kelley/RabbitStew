/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDA;

/**
 *
 * @author Mike
 */
public class Test {

    public static void main (String[] args){
        /*Room room = new Room("101");
        System.out.println(room.getItems().length);
        room.removeItem("knife");
        System.out.println(room.getItems().length);
        */
        Character mike = new Character("Mike");
        /*Item bag = new Item("bag");
        Move move = new Move("get bag");
        Room room = new Room("101");*/
        System.out.println(mike.getInventory().getItemsContained().size());
        
        System.out.println(mike.getInventory().getItemsContained().get(2).getName());
        
    } // end main method
}// end class
