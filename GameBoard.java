/**
 * GameBoard.java
 * @author Mike Zastre
 *
 * For use with Assignment #3, UVic CSC 115 (Spring 2018)
 * University of Victoria.
 */

import java.util.*;

/**
 * GameBoard creates a representation of the word-search game
 * board. Some of the methods to be completed by students
 * for assignment #3 can be found here.
 */
public class GameBoard {
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 4;


	
    private char[][] board;
    private boolean[][] visited;
    private Stack<String> path = new Stack<String>();

    /**
     * Accepts a string-array representation of the game
     * board, and initializes the board to this value.
     *
     * @param start A one-dimensional array of strings. Each row
     *              corresponds to a row in the gameboard. If there are
     *              too few characters in a row for the board, or too few
     *              rows for the whole board, then the '.' character is
     *              substituted for that game board position.
     */
    public GameBoard(char[][] start) {
        this.board = new char[NUM_ROWS][NUM_COLS];
		board  = start;

        this.visited = new boolean[NUM_ROWS][NUM_COLS];

    }


 




    /**
     * Creates a printable representation of the gameboard.
     * For examples of output from this method, please read
     * the assignment description.
     *
     * @return Single string with representation of gameboard.
     */
    public String toString() {
        String result = "     0 1 2 3\n"
                + "     -------\n";

        for (int row = 0; row < NUM_ROWS; row++) {
            result += "  " + row + ":";
            for (int col = 0; col < NUM_COLS; col++) {
                result += " " + board[row][col];
            }
            result += "\n";
        }

        return result;
    }


    /**
     * Given the state of the given GameBoard instance, determine
     * whether or not the the given word is on the board.
     *
     * @param word The word for which a search of the board
     * must be performed (i.e., recursive backtracking).
     * @return A string with the letter-square path through the
     * board the, when followed, will equal the letters in the
     * word. If the word *cannot* be found, then null is
     * returned.
     */

    public String isWord(String word) {
        word = word.toUpperCase();
        path.removeAllElements();
        clearflags();
         for (int rows = 0; rows < NUM_ROWS; rows++) {      //for loops used to travers all the letters in the word search array
             for (int cols = 0; cols< NUM_COLS; cols++) {
                if (board[rows][cols] == word.charAt(0)) {   //the letter to start at
                    visited[rows][cols] = true;
                    path.push("(" + rows + "," + cols + ")");

                     if (findPath(word, path, rows, cols)) {  //returns true of the word can be found at the starting point and prints it
                                return (path.toString());

                            } else {      //incase there are multiple of one letter, pick the next letter as the starting point
                                path.pop();
                                clearflags();
                            }
                        }
                    }
                }

      return null; //returns null if word is not found
    }
    /**
     * Given the GameBoard instance's letter square, find
     * path/sequence the spells out wordToFind starting from
     * square (row, col), with the pathSoFar including this
     * current (row, col) if a path can be found. This is
     * meant to be a recursive method, therefore will require
     * one or more base cases and one or more recursive
     * cases.
     *
     * @param wordtoFind The word for which a sequence must
     *                   be found on the board. Note could possible be a single
     *                   character (which should be a base case). Also note
     *                   that sequence of calls to findPath should occur with
     *                   short and short strings in wordToFind.
     * @param pathSoFar  The stack is use to keep track of
     *                   (row, col) lettersquares on the path. Any call of this
     *                   method should directly perform at most one push and
     *                   at most one pop.
     * @param row        Row index of square from which to start
     *                   looking for a sequence. (The assumption is that this
     *                   function is only ever called if the first letter in
     *                   word corrsponds to the (row, col) lettersquare).
     * @param col        Column index of square from which to
     *                   start looking for a sequence.
     * @return true if a sequence can be found, false
     * otherwise.
     */
    private boolean findPath(String wordToFind, Stack<String> pathSoFar, int row, int col) {
        //Base Case, if every letter is found
        if (pathSoFar.size() == wordToFind.length())
            return true;

        //for loops used to travers the 8 spots around a starting point
        for (int newr = row-1; newr <= row + 1; newr++) {
            for (int newc = col-1; newc<= col + 1; newc++) {
                if (newr <= 3 && newc <=3 && newr >=0 && newc >=0 ) {        //checks if the point is inbounds
                    if (board[newr][newc] == wordToFind.charAt(pathSoFar.size()) && !(visited[newr][newc])) {     //if the letter is at the point
                        pathSoFar.push("(" + newr + ", " + newc + ")");
                        visited[newr][newc] = true;
                        if (findPath(wordToFind, pathSoFar, newr, newc)) {   //recursive case if the next letter can be found and down the rabbit hole it goes
                            return true;
                        }
                        if (!pathSoFar.isEmpty()) {      // backtracking recursive case, which pops the last letter and trys again from the previous letter
                            pathSoFar.pop();
                            return findPath(wordToFind, pathSoFar, row, col);
                        }
                    }
                }
            }
        }
        return false;  //if it runs out of backtracking( word doesn't exist in board) it reaches here  and returns false
    }

    /**
     * Clears the boolean 2-D Array "Visited" to all false
     * Inputs: Null
     * Returns: void
     */
    private void clearflags() {

        for (int rows = 0; rows < NUM_ROWS; rows++) {
            for (int cols = 0; cols < NUM_COLS; cols++) {
                visited[rows][cols] = false;
            }
        }
    }


    /**
     * Creates a few GameBoard instances and causes their
     * contents to be printed. This method should be used for
     * tests written by the student as they develop their
     * solution.
     * The main is used for internal testing only
     */

    static public void main(String[] args) {
		/*
        String testBoard[] = {"wurg", "hsor", "heei", "isen"};
        String testBoard2[] = {"lite", "rnhs", "yjed", "iaye"};

        Scanner keyboard = new Scanner(System.in);

        GameBoard test01 = new GameBoard(testBoard2);
        System.out.println(test01);

        //WORD TO FIND
        String word = "e";

        System.out.println("Word to search (enter ! to exit)? " + word);
        //String word = keyboard.next();


        String path = test01.isWord(word);
        if (path == null) {
            System.out.println("no path");
        } else {
            System.out.println(path);
        }

        //WORD TO FIND
        String word2 = "ee";

        System.out.println("Word to search (enter ! to exit)? " + word2);
        //String word = keyboard.next();

        String path2 = test01.isWord(word2);
        if (path2 == null) {
            System.out.println("no path");
        } else {
            System.out.println(path2);
        }

         //WORD TO FIND
         String word3 = "aye";

         System.out.println("Word to search (enter ! to exit)? " + word3);
         //String word = keyboard.next();

         String path3 = test01.isWord(word3);
         if (path3 == null) {
             System.out.println("no path");
         } else {
             System.out.println(path3);
         }


         //WORD TO FIND
         String word4 = "lit";

         System.out.println("Word to search (enter ! to exit)? " + word4);
         //String word = keyboard.next();

         String path4 = test01.isWord(word4);
         if (path4 == null) {
             System.out.println("no path");
         } else {
             System.out.println(path4);
         }



          //WORD TO FIND
          String word5 = "LITE";

          System.out.println("Word to search (enter ! to exit)? " + word5);
          //String word = keyboard.next();

          String path5 = test01.isWord(word5);
          if (path5 == null) {
              System.out.println("no path");
          } else {
              System.out.println(path5);
          }
		  */

    }
	
}
