package tictactoe.game;

/**
 * Class is responsible for the basic game mechanics.
 *
 */
public class Game {

    private Board board;
    private char player;
    private int winningStreak;
    private Move latestMove;
    private int moves;

    /**
     * 
     * @param rows Rows of the board
     * @param cols Columns of the board
     * @param winningStreak Number of marks in a row required to win
     */
    public Game(int rows, int cols, int winningStreak) {
        this.winningStreak = winningStreak;
        this.board = new Board(rows, cols, winningStreak);
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
     * Method returns the game's board.
     *
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Method returns the game's winning streak.
     *
     * @return winning streak
     */
    public int getWinningStreak() {
        return winningStreak;
    }

    /**
     * Method returns the most recent move in the game.
     *
     * @return most recent move
     */
    public Move getLatestMove() {
        return latestMove;
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
     * Method validates a move and updates the board accordingly.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if move is legal, false if move is illegal
     */
    public boolean makeMove(int row, int col) {
        if (!board.isLegal(row, col)) {
            System.out.println("Illegal coordinates: " + row + ", " + col);
            return false;
        }
        if (!board.isFree(row, col)) {
            System.out.println("That spot is already taken!");
            return false;
        } else {
            board.getArray()[row][col] = player;
            latestMove.setCol(col);
            latestMove.setRow(row);
            moves++;
            return true;
        }
    }
    
    /**
     * Method checks if game is over based on no. of moves made.
     * @return true if no more moves are possible, false if there are possible moves
     */
    public boolean isGameOver() {
        return moves == board.getRows() * board.getRows();
    }
    
}