

import java.util.*;
public class GameBoard {
    public static final int NUM_ROWS = 4;
    public static final int NUM_COLS = 4;


	
    private char[][] board;
    private boolean[][] visited;
    private Stack<String> path = new Stack<String>();

    public GameBoard(char[][] start) {
        this.board = new char[NUM_ROWS][NUM_COLS];
		board  = start;

        this.visited = new boolean[NUM_ROWS][NUM_COLS];

    }


 
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



    static public void main(String[] args) {
		
    }
	
}
