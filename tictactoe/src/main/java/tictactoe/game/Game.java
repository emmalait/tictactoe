package tictactoe.game;

import tictactoe.ai.AI;

/**
 * Class is responsible for the basic game mechanics.
 *
 */
public class Game {

    private UI ui;
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private boolean isHumanMode;
    private int winningStreak;
    private Move latestMove;
    private int moves;

    /**
     *
     * @param rows Rows of the board
     * @param cols Columns of the board
     * @param winningStreak Number of marks in a row required to win
     */
    public Game() {
        this.latestMove = new Move();
        this.moves = 0;
        this.ui = new UI(this);
    }

    public void setup(int rows, int cols, int winningStreak, boolean isHumanMode) {
        this.winningStreak = winningStreak;
        this.board = new Board(rows, cols, winningStreak);
        this.isHumanMode = isHumanMode;

        if (isHumanMode) {
            this.player1 = new Player('X');
            this.player2 = new Player('O', new AI(this, 'O'));
        } else {
            this.player1 = new Player('X', new AI(this, 'X'));
            this.player2 = new Player('O', new AI(this, 'O'));
        }

        this.currentPlayer = this.player1;
    }

    /**
     *
     */
    public void start() {
        ui.printStart();
        ui.printBoard();
        
        while (true) {
            ui.printTurn();
            ui.printBoard();
            
            if (isGameWon()) {
                ui.printWin(currentPlayer);
                break;
            } else if (isGameOver()) {
                ui.printDraw();
                break;
            } else {
                switchPlayer();
            }
        }
    }

    /**
     * Method returns the current player of the game.
     *
     * @return Player current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
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
        if (this.currentPlayer == this.player1) {
            this.currentPlayer = this.player2;
        } else {
            this.currentPlayer = this.player1;
        }
    }

    /**
     * Method validates a move and updates the board accordingly.
     *
     * @param row Row coordinate
     * @param col Column coordinate
     * @return true if move is legal, false if move is illegal
     */
    public boolean makeMove(Move move) {
        if (!board.isLegal(move.getRow(), move.getCol())) {
            System.out.println("Illegal coordinates!");
            return false;
        }
        if (!board.isFree(move.getRow(), move.getCol())) {
            System.out.println("That spot is already taken!");
            return false;
        } else {
            board.getArray()[move.getRow()][move.getCol()] = currentPlayer.getMark();
            latestMove.setCol(move.getCol());
            latestMove.setRow(move.getRow());
            moves++;

            if (isHumanMode) {
                player2.getAi().addPotentialMoves(player2.getAi().getPotentialMoves(), move, board);
            } else {
                player1.getAi().addPotentialMoves(player1.getAi().getPotentialMoves(), move, board);
                player2.getAi().addPotentialMoves(player2.getAi().getPotentialMoves(), move, board);
            }

            return true;
        }
    }

    public boolean isGameWon() {
        return board.checkForWin(getCurrentPlayer().getMark(), getLatestMove());
    }

    /**
     * Method checks if game is over based on no. of moves made.
     *
     * @return true if no more moves are possible, false if there are possible
     * moves
     */
    public boolean isGameOver() {
        return moves == board.getRows() * board.getRows();
    }

}
