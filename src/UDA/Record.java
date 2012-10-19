/*
 * Record reads and writes text files to supply variables
 * for the rest of the game.
 * Created 20:49 PM 01/24/2012 by Mike Kelley.
 * Last modified 01/29/2012 by Mike Kelley.
 */

package UDA;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class Record {

    private  String line, item, path, directory, name;
    private  ArrayList<String> parse, inventory;
    private  StringBuilder contents;
    private  BufferedReader input;
    private  StringTokenizer tokenizer;


    public Record(String directoryIn, String nameIn){
        directory = directoryIn;
        name = nameIn;
    
    } // end constructor

    protected String getPath(){
    
        
        path = directory + File.separatorChar + name + ".txt";
       
        return path;
    
    }
    /**
     * converts a colon-separated string to an ArrayList
     * 
     * @param String input
     * 
     * @return ArrayList 
     */
    public ArrayList<String> parseInput(String input){
        
         parse = new ArrayList<String>();
         tokenizer = new StringTokenizer(input);
                while(tokenizer.hasMoreTokens()){
                item = tokenizer.nextToken(":");
                    parse.add(item);
                 }
                   // parse.trimToSize();
        return parse;
    } // end parseInput
    
    /**
     * Pulls info from a file in String format between a given start and stop point.
     * Start and stop points are character sequences in the file, and are *not* 
     * included in the returned ArrayList.
     * 
     * @param String start (The beginning of the information we want to read)
     * 
     * @param String stop (End of same)
     * 
     * @return The info we read, as a single String
     */
    public String getPartialFile(String start, String stop){ 
        contents = new StringBuilder();

        // path to our file
        path = getPath();
        
        try{

            input = new BufferedReader(new FileReader(path)); // assign the filepath and file to a FileReader object wrapped in a BufferedReader object
            
            try{
              line = null;
                 
              while((line = input.readLine()) != null){
                     if(start.matches(line)){
                        while(!stop.matches(line = input.readLine())){
                            if ("\n".matches(line)){
                            line = System.getProperty("line.separator");
                            }
                                contents.append(line);                             
                        } // end while
                    } // end if
                } // end while
                item = contents.toString();
            } // end try block
            finally{
                input.close();
            } // end finally
        } // end try block
        catch(IOException ex){
            ex.fillInStackTrace();
        } // end catch
        
        return item;
    } // end readWholeFile
   
    /**
     * Reads a sequence of colon-separated pairs from a file and converts 
     * it to an ArrayList, minus the colons, based on user-specified 
     * start and stop sequences. The start and stop sequences are *not* included in the returned ArrayList.
     * 
     * @param String start (The beginning of the set of pairs to be read)
     * 
     * @param String stop (The end of same)
     * 
     * @return an ArrayList containing the pairs we read from the file.
     */
    public ArrayList<String> getPairedInfoFromFile(String start, String stop){
        
        contents = new StringBuilder();
        inventory = new ArrayList<String>();
        item = new String();
        path = getPath();
        
        try{

            input = new BufferedReader(new FileReader(path));
            
            try{
                 line = null;
                 
                while((line = input.readLine()) != null){
                     if(start.equals(line)){
                        while(!stop.equals(line = input.readLine())){
                            if ("\n".matches(line)){
                            line = System.getProperty("line.separator");
                            }
                                contents.append(line);
                                contents.append(":");
                                
                        } // end while
                    } // end if
                } // end while
                item = contents.toString();
                tokenizer = new StringTokenizer(item, ":");
                while(tokenizer.hasMoreTokens()){
                    String token = tokenizer.nextToken();
                    inventory.add(token);
                 }
                   //inventory.trimToSize();
                   //inventory.remove(inventory.get(0));
                          //inventory.add(inventory.size());

            } // end try block
            finally{
                input.close();
            } // end finally
        } // end try block
        catch(IOException ex){
            ex.printStackTrace();
        } // end catch

    return inventory;

    } // end getInventory



    /**
     * Takes a String and writes it to a file, after checking to see if 
     * a) the filename is not null, b) the file exists, c) the filename is not, in fact, a directory, 
     * and d), we have permission to write to the file.
     * 
     * @param String contents
     * 
     * @throws FileNotFoundException if the file can't be found (in which case, 
     * check the filename and directory you passed to the constructor)
     * 
     * @throws IOException if the filename is null, if it's a directory, or if we don't have permission to write 
     * to the file. 
     */
    public void writeStringToFile(String contents)
                            throws FileNotFoundException, IOException{
        
        path = getPath();
        
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("File " + file + "does not exist");
        if (!file.isFile())
            throw new IllegalArgumentException(file + " should not be a directory.");
        if(!file.canWrite())
            throw new IllegalArgumentException(file + " cannot be written to.");
        Writer output = new BufferedWriter(new FileWriter(file));
        try{
            output.write(contents);
        }
        finally{
            output.close();
        }
    } // end writeStringToFile
    
    /**
     * Writes an ArrayList to a file, after checking to see if 
     * a) the filename is not null, b) the file exists, c) the filename is not, in fact, a directory, 
     * and d), we have permission to write to the file.
     * 
     * @param ArrayList contents
     * 
     * @throws FileNotFoundException if the file can't be found (in which case, 
     * check the filename and directory you passed to the constructor)
     * 
     * @throws IOException if the filename is null, if it's a directory, or if we don't have permission to write 
     * to the file.
     */
    public void writeToFile(ArrayList<?> contents)
                            throws FileNotFoundException, IOException{
        
       path = getPath();
        
        File file = new File(path);
        if (!file.exists())
            throw new FileNotFoundException("File " + file + "does not exist");
        if (!file.isFile())
            throw new IllegalArgumentException(file + " should not be a directory.");
        if(!file.canWrite())
            throw new IllegalArgumentException(file + " cannot be written to.");
        Writer output = new BufferedWriter(new FileWriter(file));
        try{
            for (int i = 0; i < contents.size(); i++){
            output.write(contents.get(i).toString());
            }
        }
        finally{
            output.close();
        }
    } // end writeStringToFile
    
    /**
     * inserts colons between all elements in an ArrayList, then converts it to a String
     * 
     * @param ArrayList list
     * 
     * @return String version of formatted ArrayList
     */
    public String formatArrayListToString(ArrayList<?> list){
        contents = new StringBuilder();
        String myString;
        
        for (int i = 0; i < list.size(); i++){
            contents.append(list.get(i));
            contents.append(":");
        }
        contents.deleteCharAt(contents.length()-1);
        myString = contents.toString();
        return myString;
    } // end formatArrayListToString
    
    /**
     * takes a string array and formats it as a string. The string is composed of newline delineated pairs, 
     * the elements of which are colon separated.
     * 
     * @param String array
     * 
     * @return String myString,formatted for use in the GameState text file.
     */
    protected String formatStringArrayToString(String[] array){
        contents = new StringBuilder();
        String myString;
        
        for (int i = 0; i < array.length; i++){
                contents.append(i).toString();
                contents.append(":");
                contents.append(array[i]);
                contents.append("\n");
        }// end for
        contents.deleteCharAt(contents.length()-1);
        myString = contents.toString();
        return myString;
    }// end formatStringArrayToString
    
    /**
     * inserts a colons between all elements of an ArrayList.
     * 
     * @param ArrayList list
     * 
     * @return formatted ArrayList list
     */
    public ArrayList<String> formatArrayList(ArrayList<String> list){
       
        for (int i = 0; i < list.size(); i++){
            list.add(i + 1, ":");
            i++;
        }
        list.remove(list.size() - 1);
        return list;
    }
    public void createFile(String contents) throws IOException{
        //FileWriter writer = new FileWriter(getPath(), true);
        BufferedWriter outputFile = new BufferedWriter(new FileWriter(getPath(), true)); 
        outputFile.write(contents);
        outputFile.close();
    }
}// end Record
