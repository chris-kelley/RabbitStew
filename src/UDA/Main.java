
 /*
  * This is an adventure game inspired by Zork.
  * Created 11:09 AM on 01/24/2012 by Mike Kelley
  * last modified 01/28/2012 by Mike Kelley
  */

package UDA;

// import java.util.Scanner;
import javax.swing.*;

public class Main extends JFrame {
   
    private static String input;
    private static JFrame rabbit;

    public static void main(String[] args){
        
        rabbit = new GUI();
        
        rabbit.setSize(620,620);
        rabbit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rabbit.setTitle("Rabbit stew is delicious!");
        rabbit.setLocationRelativeTo(null);
        //rabbit.setResizable(false);
        rabbit.setVisible(true);

       // System.out.println(Record.getPairedInfoFromFile("InfoFiles", "GameState", Locator.hitPoints, Locator.hitPointsEnd));
       // System.out.println(Record.getPairedInfoFromFile("InfoFiles", "GameState", Locator.roomsKnown, Locator.roomsKnownEnd));
  /*
       Scanner sc = new Scanner(System.in);
       GameState state = new GameState(); 
       
       System.out.println(Record.getPartialFile("InfoFiles", state.getPosition(), Locator.roomDescription, Locator.roomDescriptionEnd));
       input = sc.nextLine();
       
       System.out.println(input);
    */   
      
       

    } // end main method
} // end class
