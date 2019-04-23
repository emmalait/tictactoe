
package tictactoe.game;


public class Board {
    
    private char[][] array;
    private int rows;
    private int cols;
    private int winningStreak;
    
    public Board(int rows, int cols) {
        this.array = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.winningStreak = 0;
        initialiseBoard();
    }
    
    public Board(int rows, int cols, int winningStreak) {
        this.array = new char[rows][cols];
        this.rows = rows;
        this.cols = cols;
        this.winningStreak = winningStreak;
        initialiseBoard();
    }
    
    /**
     * Method returns the current array.
     *
     * @return current array
     */
    public char[][] getArray() {
        return array;
    }

    /**
     * Method returns no. of rows on the current board.
     *
     * @return No of rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * Method returns no. of columns on the current board.
     *
     * @return No of columns
     */
    public int getCols() {
        return cols;
    }
    
    /**
     * Method initialises the board with character '-'.
     */
    public void initialiseBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                array[i][j] = '-';
            }
        }
    }

    /**
     * Method prints the board into the console.
     */
    public void printBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    /**
     * Method checks whether a coordinate is within the board.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if coordinate is within board, false if coordinate is out of
     * bounds
     */
    public boolean isLegal(int row, int col) {
        if (row < rows && row >= 0 && col < cols && col >= 0) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Method checks whether a coordinate is free/unused.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if coordinate is free, false if coordinate has been used
     */
    public boolean isFree(int row, int col) {
        return array[row][col] == '-';
    }
    
    /**
     * Method checks the current situation on the board for current player's
     * winning streaks.
     *
     * @param player
     * @param latestMove
     * @return true if a winning streak is found, false if not
     */
    public boolean checkForWin(char player, Move latestMove) {
        return (checkRowForWin(player, latestMove.getRow())
                || checkColumnForWin(player, latestMove.getCol())
                || checkFwdDiagonalForWin(player, latestMove.getRow(), latestMove.getCol())
                || checkBwdDiagonalForWin(player, latestMove.getRow(), latestMove.getCol()));
    }
    
    /**
     * Method checks the current situation on the board for current player's
     * winning streaks.
     *
     * @param player
     * @param latestMove
     * @return true if a winning streak is found, false if not
     */
    public boolean checkForWinWithMove(char player, Move latestMove) {
        return (checkRowForWin(player, latestMove.getRow())
                || checkColumnForWin(player, latestMove.getCol())
                || checkFwdDiagonalForWin(player, latestMove.getRow(), latestMove.getCol())
                || checkBwdDiagonalForWin(player, latestMove.getRow(), latestMove.getCol()));
    }

    /**
     * Method checks a row for winning streaks by the current player.
     *
     * @param row Row to be checked
     * @return true if a winning streak is found, false if not
     */
    private boolean checkRowForWin(char player, int row) {
        return checkLine(player, 0, 0, row, 1, 0);
    }

    /**
     * Method checks a column for winning streaks by the current player.
     *
     * @param col Column to be checked
     * @return true if a winning streak is found, false if not
     */
    private boolean checkColumnForWin(char player, int col) {
        return checkLine(player, 0, col, 0, 0, 1);
    }

    /**
     * Method checks a forward diagonal for winning streaks by the current
     * player.
     *
     * @param row Row coordinate for the diagonal
     * @param col Column coordinate for the diagonal
     * @return true if a winning streak is found, false if not
     */
    private boolean checkFwdDiagonalForWin(char player, int row, int col) {
        int smallestDistToEdge = -1;

        if (col < row) {
            smallestDistToEdge = col;
        } else {
            smallestDistToEdge = row;
        }

        return checkLine(player, 0, col - smallestDistToEdge, row - smallestDistToEdge, 1, 1);
    }

    /**
     * Method checks a backward diagonal for winning streaks by the current
     * player.
     *
     * @param row Row coordinate for the diagonal
     * @param col Column coordinate for the diagonal
     * @return true if a winning streak is found, false if not
     */
    private boolean checkBwdDiagonalForWin(char player, int row, int col) {
        int smallestDistToEdge = -1;

        int distToRight = cols - 1 - col;

        if (distToRight < row) {
            smallestDistToEdge = distToRight;
        } else {
            smallestDistToEdge = row;
        }

        return checkLine(player, 0, col + smallestDistToEdge, row - smallestDistToEdge, -1, 1);
    }

    /**
     * Method recursively checks a series of coordinates on the board for a
     * winning streak by a player.
     *
     * @param player 
     * @param counter Number of subsequent marks found by the current player
     * @param col Column coordinate to be checked
     * @param row Row coordinate to be checked
     * @param colIncrement By how much the column coordinate is incremented for
     * the next coordinate in the series
     * @param rowIncrement By how much the row coordinate is incremented for the
     * next coordinate in the series
     * @return true if a winning streak is found, false if not
     */
    private boolean checkLine(char player, int counter, int col, int row, int colIncrement, int rowIncrement) {
        if (!isLegal(row, col)) {
            return false;
        }

        if (array[row][col] == player) {
            counter++;
            if (counter == winningStreak) {
                return true;
            } else {
                return checkLine(player, counter, col + colIncrement, row + rowIncrement, colIncrement, rowIncrement);
            }
        } else {
            counter = 0;
            return checkLine(player, counter, col + colIncrement, row + rowIncrement, colIncrement, rowIncrement);
        }
    }
    
}
