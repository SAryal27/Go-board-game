import java.util.Random;

/**
 * Implements certain aspects of the game of "Go"
 */
public class GoFrame extends GoBaseFrame {

    // symbolic constants, representing stones on the board
    public static final int WHITE = 0;
    public static final int BLACK = 1;
    public static final int EMPTY = 2;
    public static final int WHITE_IN_PERIL = 3;
    public static final int BLACK_IN_PERIL = 4;
    
    // Returns true if and only if (row, col) is a valid location on the board
    boolean isValid(int row, int col){
         if ((row >= 0 && row < board.length) && (col >= 0 && col < board[0].length)){
             return true;
         }
         else {
             return false;
         }
    }
    
    /**
     * Clears the board of all stones
     */
    @Override
    protected void clearBoard() {

        // **** YOUR CODE FOR CHECKPOINT 9 OF LAB 6 SHOULD GO HERE ****
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                board[i][j] = GoFrame.EMPTY;
            }
        }
    }
    /**
     * If the given column and row positions are a legal location on the board,
     *  modifies the location by either changing the stone's color,
     *  or by adding or removing a stone.
     *
     * @param col The horizontal position of the spot on the board,
     *            with 0 representing the left of the board.
     * @param row The vertical position of the spot on the board,
     *            with 0 representing the top of the board.
     */
    @Override
    protected void pressedOnSpace(int row, int col) {

         // at this point, col contains the column-coordinate denoting the square
        // that was pressed, and row contains the row-coordinate       
        // **** YOUR CODE FOR CHECKPOINT 10 OF LAB 6 SHOULD GO HERE ****
     
        if ((row >= 0 && row < board.length) &&(col >= 0 && col < board[0].length)){
                    if (board[row][col] == GoFrame.WHITE) {
                        board[row][col] = GoFrame.BLACK;
                    }
                    else if (board[row][col] == GoFrame.BLACK){
                        board[row][col] = GoFrame.EMPTY;
                    }
                    else {
                        board[row][col] = GoFrame.WHITE;
                    }
        }
    }

    /**
     * Places stones randomly on the board, with
     * approximately 20% of the spaces blank, 40% black, 40% white
     */
    @Override
    protected void randomizeBoard() {
        // **** YOUR CODE FOR CHECKPOINT 1 OF LAB 7 SHOULD GO HERE ****
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                Random rand = new Random();
                //random 100 numbers set the first 40 to black, next 40 to white, last 20 to empty
                int randomNumber = rand.nextInt(101) + 0; // picks number 0-100
                if ((randomNumber >= 0) && (randomNumber < 40)){
                    board[row][col] = GoFrame.BLACK;
                }
                else if ((randomNumber >= 40) && (randomNumber < 80)){
                    board[row][col] = GoFrame.WHITE;
                }
                else{
                    board[row][col] = GoFrame.EMPTY;
                }
            }
        }
    }

    /**
     * Determine which stones are captured and remove them from the board
     */
    @Override
    protected void removeCapturedStones() {

        //checkpoint 2
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                if (board[row][col] == GoFrame.WHITE){
                    board[row][col] = GoFrame.WHITE_IN_PERIL;
                }
            }   
        }
        
        //checkpoint 3 AND 4 (while loop) and 5 (while loop until no changes)
        //int y = 0;
        boolean hasChanged = true;
        while (hasChanged){
            hasChanged = false;
            for (int row = 0; row < board.length; row++){ 
                for (int col = 0; col < board[0].length; col++){ 
                    if (board[row][col] == GoFrame.WHITE_IN_PERIL){  
                        boolean mightChange = false;
                        if (row > 0 && (board[row-1][col] == GoFrame.EMPTY || board[row-1][col] == GoFrame.WHITE)) {
                            mightChange = true;
                        } 
                        if (row < board.length - 1 && (board[row + 1][col] == GoFrame.EMPTY || board[row + 1][col] == GoFrame.WHITE)) {
                            mightChange = true;
                        } 
                        if (col > 0 && (board[row][col - 1] == GoFrame.EMPTY || board[row][col - 1] == GoFrame.WHITE)) {
                            mightChange = true;
                        } 
                        if (col < board[0].length - 1 && (board[row][col + 1] == GoFrame.EMPTY || board[row][col + 1] == GoFrame.WHITE)) {
                            mightChange = true;
                        } 
                        if (mightChange){
                            board[row][col] = GoFrame.WHITE;
                            hasChanged = true;
                        }  
                    }
                }
            }
        }
            for (int row = 0; row < board.length; row++){ 
                for (int col = 0; col < board[0].length; col++){
                    if (board[row][col] == GoFrame.WHITE_IN_PERIL){
                        board[row][col] = GoFrame.EMPTY;
                    }
                }
            }
    
    
 
    //checkpoint 7
    
        for (int row = 0; row < board.length; row++){
            for(int col = 0; col < board[0].length; col++){
                if (board[row][col] == GoFrame.BLACK){
                    board[row][col] = GoFrame.BLACK_IN_PERIL;
                }
            }
        }

        hasChanged = true;
        while (hasChanged){
            hasChanged = false;
            for (int row = 0; row < board.length; row++){ 
                for (int col = 0; col < board[0].length; col++){ 
                    if (board[row][col] == GoFrame.BLACK_IN_PERIL){  
                        boolean mightChange = false;
                        if (row > 0 && (board[row-1][col] == GoFrame.EMPTY || board[row-1][col] == GoFrame.BLACK)) {
                            mightChange = true;
                        } 
                        if (row < board.length - 1 && (board[row + 1][col] == GoFrame.EMPTY || board[row + 1][col] == GoFrame.BLACK)) {
                            mightChange = true;
                        } 
                        if (col > 0 && (board[row][col - 1] == GoFrame.EMPTY || board[row][col - 1] == GoFrame.BLACK)) {
                            mightChange = true;
                        } 
                        if (col < board[0].length - 1 && (board[row][col + 1] == GoFrame.EMPTY || board[row][col + 1] == GoFrame.BLACK)) {
                            mightChange = true;
                        } 
                        if (mightChange){
                            board[row][col] = GoFrame.BLACK;
                            hasChanged = true;
                        }  
                    }
                }
            }
            for (int row = 0; row < board.length; row++){ 
                for (int col = 0; col < board[0].length; col++){
                    if (board[row][col] == GoFrame.BLACK_IN_PERIL){
                        board[row][col] = GoFrame.EMPTY;
                    }
                }
            }
    }
}

    ///////////////////////////////////////////////////////////////////
    //*****************************************************************
    //**CS 273 students should not need to modify anything below here**
    //*****************************************************************
    ///////////////////////////////////////////////////////////////////
    /**
     * Creates and displays frame
     * @param args
     */
    public static void main(String[] args) {
        new GoFrame().setVisible(true);
    }
}