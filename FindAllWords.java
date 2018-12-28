

import java.util.*;
import java.io.*;

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
