/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package UDA;

/**
 *
 * @author Mike
 */
public class Message {
	
    // Error messages
	
	/** 
	 * Message telling the player that the verb is illegal
	 * 
	 * @param String move
	 * 
	 * @return String
	 */
    public static String illegalMove(String move){
        return "I don't know what " + move + "means in this context.";
    }// end illegalMove
    
    /**
     * MEssage to user about a non-existent exit
     * 
     * @return String
     */
    public static String illegalExitChoice(){
        return "I don't see an exit there.";
    }// end illegalExitChoice
    
    /**
     * message to user about non-existent Item
     * 
     * @param String name
     * 
     * @return String
     */
    public static String illegalItemChoice(String name){
        return "I don't see a " + name + " here.";
    }// end illegalItemChoice
    
    /**
     * Message to user that the move succeeded.
     * 
     * @return String
     */
    public static String done(){
    	return "Done.";
    }
    
} // end Message
