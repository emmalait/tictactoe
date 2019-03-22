
package tictactoe.tictactoe;

/**
 * Class is responsible for the basic game mechanics.
 * 
 */

public class TicTacToe {
    private char[][] board;
    private char player;
    private int cols;
    private int rows;
    private int winningStreak;
    
    public TicTacToe() {
        this.cols = 15;
        this.rows = 15;
        this.winningStreak = 5;
        this.board = new char[rows][cols];
        this.player = 'X';
    }
    
    /**
     * Method returns the current player of the game.
     * @return current player
     */
    public char getPlayer() {
        return player;
    }
    
    /**
     * Method initialises the game's board with character '-'.
     */
    public void initialiseBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = '-';
            }
        }
    }
    
    /**
     * Method prints the board into the console.
     */
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Method validates a move and updates the board accordingly.
     * @param x Column coordinate
     * @param y Row coordinate
     * @return true if move is legal, false if move is illegal
     */
    public boolean makeMove(int x, int y) {
        if (x >= cols || x < 0 || y >= rows || y < 0) {
            return false;
        }
        if (isCoordinateFree(x, y)) {
            board[y][x] = player;
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method checks whether a move coordinate is free/unused.
     * @param x Column coordinate
     * @param y Row coordinate
     * @return true if coordinate is free, false if coordinate has been used
     */
    private boolean isCoordinateFree(int x, int y) {
        return board[y][x] == '-';
    }
    
    /**
     * Method switches player (from X to O or from O to X).
     */
    public void switchPlayer() {
        if (player == 'X') {
            player = 'O';
        } else {
            player = 'X';
        }
    }
    
    /**
     * Method checks the board for winning streaks, i.e. a set number of consecutive X's or O's.
     * @return true if winning streak is found, false if not
     */
    public boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }
    
    /**
     * Method checks the board rows for a winning streak, i.e. set number of consecutive X's or O's.
     * @return true if winning streak is found, false if not
     */
    private boolean checkRowsForWin() {
        int counter = 1;
        char last = '-';
        char current;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                current = board[i][j];
                if (current == 'X' || current == 'O') {
                    if (current == last) {
                        counter++;
                        if (counter == winningStreak) {
                            return true;
                        }
                    }
                }
                last = current;
            }
            counter = 1;
        }
        return false;
    }
    
    /**
     * Method checks the board columns for a winning streak, i.e. set number of consecutive X's or O's.
     * @return true if winning streak is found, false if not
     */
    private boolean checkColumnsForWin() {
        int counter = 1;
        char last = '-';
        char current;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                current = board[j][i];
                if (current == 'X' || current == 'O') {
                    if (current == last) {
                        counter++;
                        if (counter == winningStreak) {
                            return true;
                        }
                    }
                }
                last = current;
            }
            counter = 1;
        }
        return false;
    }
    
    private boolean checkDiagonalsForWin() {
        return false;
    }
    
}
