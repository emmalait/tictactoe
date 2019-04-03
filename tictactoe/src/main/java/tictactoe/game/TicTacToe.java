package tictactoe.game;

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
    private Move latestMove;
    private int moves;

    public TicTacToe(int rows, int cols, int winningStreak) {
        this.rows = rows;
        this.cols = cols;
        this.winningStreak = winningStreak;
        this.board = new char[rows][cols];
        this.player = 'X';
        this.latestMove = new Move();
        this.moves = 0;
    }

    /**
     * Method returns the current player of the game.
     *
     * @return current player
     */
    public char getPlayer() {
        return player;
    }

    /**
     * Method returns the current board.
     *
     * @return current board
     */
    public char[][] getBoard() {
        return board;
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
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if move is legal, false if move is illegal
     */
    public boolean makeMove(int row, int col) {
        if (!isCoordinateWithinBoard(row, col)) {
            System.out.println("Illegal coordinates");
            return false;
        }
        if (!isCoordinateFree(row, col)) {
            System.out.println("That spot is already taken!");
            return false;
        } else {
            board[row][col] = player;
            latestMove.setColCoordinate(col);
            latestMove.setRowCoordinate(row);
            moves++;
            return true;
        }
    }

    /**
     * Method checks whether a move coordinate is within the board.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if coordinate is within board, false if coordinate is out of
     * bounds
     */
    private boolean isCoordinateWithinBoard(int row, int col) {
        if (row < rows && row >= 0 && col < cols && col >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks whether a move coordinate is free/unused.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if coordinate is free, false if coordinate has been used
     */
    private boolean isCoordinateFree(int row, int col) {
        return board[row][col] == '-';
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
     * Method checks the current situation on the board for current player's
     * winning streaks.
     *
     * @param board
     * @param player
     * @return true if a winning streak is found, false if not
     */
    public boolean checkForWin(char[][] board, char player) {
        return (checkRowForWin(board, player, latestMove.getRowCoordinate())
                || checkColumnForWin(board, player, latestMove.getColCoordinate())
                || checkFwdDiagonalForWin(board, player, latestMove.getRowCoordinate(), latestMove.getColCoordinate())
                || checkBwdDiagonalForWin(board, player, latestMove.getRowCoordinate(), latestMove.getColCoordinate()));
    }
    
    /**
     * 
     */
    public boolean isGameOver() {
        if (moves == rows * cols) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks a row for winning streaks by the current player.
     *
     * @param row Row to be checked
     * @return true if a winning streak is found, false if not
     */
    private boolean checkRowForWin(char[][] board, char player, int row) {
        return checkLine(board, player, 0, 0, row, 1, 0);
    }

    /**
     * Method checks a column for winning streaks by the current player.
     *
     * @param col Column to be checked
     * @return true if a winning streak is found, false if not
     */
    private boolean checkColumnForWin(char[][] board, char player, int col) {
        return checkLine(board, player, 0, col, 0, 0, 1);
    }

    /**
     * Method checks a forward diagonal for winning streaks by the current
     * player.
     *
     * @param row Row coordinate for the diagonal
     * @param col Column coordinate for the diagonal
     * @return true if a winning streak is found, false if not
     */
    private boolean checkFwdDiagonalForWin(char[][] board, char player, int row, int col) {
        int smallestDistToEdge = -1;

        if (col < row) {
            smallestDistToEdge = col;
        } else {
            smallestDistToEdge = row;
        }

        return checkLine(board, player, 0, col - smallestDistToEdge, row - smallestDistToEdge, 1, 1);
    }

    /**
     * Method checks a backward diagonal for winning streaks by the current
     * player.
     *
     * @param row Row coordinate for the diagonal
     * @param col Column coordinate for the diagonal
     * @return true if a winning streak is found, false if not
     */
    private boolean checkBwdDiagonalForWin(char[][] board, char player, int row, int col) {
        int smallestDistToEdge = -1;

        int distToRight = cols - 1 - col;

        if (distToRight < row) {
            smallestDistToEdge = distToRight;
        } else {
            smallestDistToEdge = row;
        }

        return checkLine(board, player, 0, col + smallestDistToEdge, row - smallestDistToEdge, -1, 1);
    }

    /**
     * Method recursively checks a series of coordinates on the board for a
     * winning streak by the current player.
     *
     * @param counter Number of subsequent marks found by the current player
     * @param col Column coordinate to be checked
     * @param row Row coordinate to be checked
     * @param colIncrement By how much the column coordinate is incremented for
     * the next coordinate in the series
     * @param rowIncrement By how much the row coordinate is incremented for the
     * next coordinate in the series
     * @return true if a winning streak is found, false if not
     */
    private boolean checkLine(char[][] board, char player, int counter, int col, int row, int colIncrement, int rowIncrement) {
        if (!isCoordinateWithinBoard(row, col)) {
            return false;
        }

        if (board[row][col] == player) {
            counter++;
            if (counter == winningStreak) {
                return true;
            } else {
                return checkLine(board, player, counter, col + colIncrement, row + rowIncrement, colIncrement, rowIncrement);
            }
        } else {
            counter = 0;
            return checkLine(board, player, counter, col + colIncrement, row + rowIncrement, colIncrement, rowIncrement);
        }
    }
    
}
