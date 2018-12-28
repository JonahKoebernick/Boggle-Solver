/**
 * FindAllWords.java
 * @author Mike Zastre
 *
 * For use with Assignment #3, UVic CSC 115 (Spring 2018)
 * University of Victoria.
 */

import java.util.*;
import java.io.*;

/**
 * When run as a program, FindAllWords creates a GameBoard
 * using the first parameter as a seed, and reads the
 * word file (whose named is provided as the second parameter)
 * line-by-line. For each word, this program determines if
 * the word can be found in the GameBoard instance created.
 */
public class FindAllWords {
    public static void main(String args[] ) {
        if (args.length < 1) {
            System.err.println(
                    "usage: java ValidateWord <seed> <filename>"
            );
            System.exit(1);
        }

        try {
            long seed = Long.parseLong(args[0]);
            String file = "Allwords.txt";

            GameBoard gb = new GameBoard(seed);
            System.out.println(gb.toString());

            /* Student code here for opening the file,
             * then reading it line by line, and for each
             * line (which is a single word) determining
             * whether or not the word appears on
             * the GameBoard instance.
             *
             * Note: The name of the file is in argv[1].
             */

            try {

                FileInputStream fs = new FileInputStream(file);
                BufferedReader br = new BufferedReader(new InputStreamReader(fs));

                String strLine;
				String thestring ="";

                while ((strLine = br.readLine()) != null) {
                    String path = gb.isWord(strLine);
                    if(path != null){
						thestring = thestring+strLine+":"+path+"\n";
                    }

                }
				 System.out.print(thestring);

                br.close();
            }catch (Exception e){//Catch exception if any
                System.err.println("Error: " + e.getMessage());
            }


        }
        catch (NumberFormatException nfe) {
            System.err.println(
                    "'" + args[0] + "' " +
                            "given for <seed>; <seed> needs to be a valid integer"
            );
            System.exit(1);
        }
    }
}
