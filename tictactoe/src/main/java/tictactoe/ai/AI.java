package tictactoe.ai;

import tictactoe.data.MoveList;
import tictactoe.game.Board;
import tictactoe.game.Move;
import tictactoe.game.Game;

/**
 * Class is responsible for the AI.
 *
 */
public class AI {

    private Game game;
    private char maxPlayer;
    private char minPlayer;
    private int maxDepth;

    /**
     * @param game The instance of TicTacToe the AI is to play in
     * @param maxPlayer The mark the AI is playing with (X/O)
     */
    public AI(Game game, char maxPlayer) {
        this.game = game;
        this.maxPlayer = maxPlayer;
        if (maxPlayer == 'X') {
            this.minPlayer = 'O';
        } else {
            this.minPlayer = 'X';
        }
        this.maxDepth = (game.getBoard().getRows() * game.getBoard().getCols() > 7) ? 7 : game.getBoard().getRows() * game.getBoard().getCols();
    }

    /**
     * Method calculates the AI's next move.
     *
     * @return AI's calculated move
     */
    public Move makeMove() {        
        Board boardCopy = new Board(game.getBoard().getRows(), game.getBoard().getCols(), game.getWinningStreak());
        
        for (int i = 0; i < game.getBoard().getRows(); i++) {
            for (int j = 0; j < game.getBoard().getCols(); j++) {
                boardCopy.getArray()[i][j] = game.getBoard().getArray()[i][j];
            }
        }

        //return minmax(boardCopy, 0, maxPlayer, new Move());
        return minmaxAB(boardCopy, 0, maxPlayer, new Move(), Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * A basic minmax algorithm for calculating the next move.
     *
     * @param board Current board
     * @param depth Recursion depth
     * @param player Player whose turn it is
     * @param latestMove The most recent move on the board
     * @return Move calculated by the algorithm
     */
    public Move minmax(Board board, int depth, char player, Move latestMove) {
        if (board.checkForWinWithMove(maxPlayer, latestMove)) {
            // If maximizing player wins, return score maxDepth-depth.
            return new Move(-1, -1, maxDepth - depth);
        } else if (board.checkForWinWithMove(minPlayer, latestMove)) {
            // If minimizing player wins, return score depth-maxDepth.
            return new Move(-1, -1, depth - maxDepth);
        }

        // Check passed board for empty spaces.
        MoveList availablePlaces = getAvailableMoves(board);

        // If there are no empty spaces, game is a draw: return score 0.
        if (availablePlaces.isEmpty()) {
            return new Move(-1, -1, 0);
        }

        MoveList scores = new MoveList(board.getRows() * board.getCols());

        for (int i = 0; i < availablePlaces.size(); i++) {
            Move move = availablePlaces.get(i);
            board.getArray()[move.getRow()][move.getCol()] = player;
            Move moveWithScore = minmax(board, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move);
            moveWithScore.setRow(move.getRow());
            moveWithScore.setCol(move.getCol());
            scores.add(moveWithScore);
            board.getArray()[move.getRow()][move.getCol()] = '-';
        }

        if (player == maxPlayer) {
            Move max = new Move(-1, -1, Integer.MIN_VALUE);

            for (int i = 0; i < scores.size(); i++) {
                Move value = scores.get(i);
                if (value.getScore() > max.getScore()) {
                    max = value;
                }
            }

            return max;

        } else {
            Move min = new Move(-1, -1, Integer.MAX_VALUE);

            for (int i = 0; i < scores.size(); i++) {
                Move value = scores.get(i);
                if (value.getScore() < min.getScore()) {
                    min = value;
                }
            }

            return min;
        }

    }

    /**
     * A minmax algorithm with alpha-beta pruning for calculating the next move.
     *
     * @param board Current board
     * @param depth Recursion depth
     * @param player Player whose turn it is
     * @param latestMove The most recent move on the board
     * @param alpha The current best score for the maximizing player
     * @param beta The current best score for the minimizing player
     * @return Move calculated by the algorithm
     */
    public Move minmaxAB(Board board, int depth, char player, Move latestMove, int alpha, int beta) {
        if (board.checkForWinWithMove(maxPlayer, latestMove)) {
            // If maximizing player wins, return score maxDepth-depth.

            System.out.println("d: " + depth + ", player: " + player + ", move: " + latestMove);
            System.out.println("Max wins, score: " + String.valueOf(maxDepth - depth));
            return new Move(-1, -1, maxDepth - depth);
        } else if (board.checkForWinWithMove(minPlayer, latestMove)) {
            // If minimizing player wins, return score depth-maxDepth.

            /*System.out.println("d: " + depth + ", player: " + player + ", move: " + latestMove);
            System.out.println("Min wins, score: " + String.valueOf(depth - maxDepth));*/
            return new Move(-1, -1, depth - maxDepth);
        }

        // If recursion has reached maxDepth & no winner has been found, return score 0.
        if (depth == maxDepth) {
            return new Move(-1, -1, 0);
        }

        // Check passed board for empty spaces.
        MoveList availablePlaces = getAvailableMoves(board);

        // If there are no empty spaces, game is a draw: return score 0.
        if (availablePlaces.isEmpty()) {
            /*System.out.println("d: " + depth + ", player: " + player + ", move: " + latestMove);
            System.out.println("Draw");*/
            return new Move(-1, -1, 0);
        }

        if (player == maxPlayer) {
            Move bestMove = new Move(-1, -1, Integer.MIN_VALUE);

            for (int i = 0; i < availablePlaces.size(); i++) {
                Move move = availablePlaces.get(i);
                System.out.println("Player " + player + " makes move: " + move);

                board.getArray()[move.getRow()][move.getCol()] = player;
                Move moveWithScore = minmaxAB(board, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRow(move.getRow());
                moveWithScore.setCol(move.getCol());

                board.getArray()[move.getRow()][move.getCol()] = '-';

                System.out.println("Move (max): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);
                if (moveWithScore.getScore() > bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() > alpha) {
                    alpha = bestMove.getScore();
                }

                // Alpha-beta pruning
                if (beta <= alpha) {
                    System.out.println("BREAK: Move (max): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);

                    break;
                }

            }

            return bestMove;

        } else {
            Move bestMove = new Move(-1, -1, Integer.MAX_VALUE);

            for (int i = 0; i < availablePlaces.size(); i++) {
                Move move = availablePlaces.get(i);
                //System.out.println("Player " + player + " makes move: " + move);

                board.getArray()[move.getRow()][move.getCol()] = player;
                Move moveWithScore = minmaxAB(board, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRow(move.getRow());
                moveWithScore.setCol(move.getCol());

                board.getArray()[move.getRow()][move.getCol()] = '-';

                //System.out.println("Move (min): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);
                if (moveWithScore.getScore() < bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() < beta) {
                    beta = bestMove.getScore();
                }

                // Alpha-beta pruning
                if (beta <= alpha) {
                    //System.out.println("BREAK: Move (min): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);

                    break;
                }
            }

            return bestMove;
        }
    }

    /**
     * Method for finding free places on the board ('-').
     *
     * @param board Current board
     * @return List of available moves
     */
    private MoveList getAvailableMoves(Board board) {
        MoveList moves = new MoveList(board.getRows() * board.getCols());

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if (board.getArray()[i][j] == '-') {
                    moves.add(new Move(i, j));
                }
            }
        }

        return moves;
    }

    /**
     * Method for finding free places on the board near existing moves.
     *
     * @param board Current board
     * @param latestMove The most recent move on the board
     * @return List of potential moves
     */
    private MoveList getPotentialMoves(Board board, Move latestMove) {
        MoveList moves = new MoveList(board.getRows() * board.getCols());

        // Tarkista kaikki suunnat ympäriltä (8).
        // Jos jossain suunnassa ruutu =/= '-', tarkista tämän ympäriltä 
        // kaikki muut suunnat, paitsi se suunta, josta tämä ruutu saatiin.
        // All directions (8): N, NE, E, SE, S, SW, W, NW
        return moves;
    }

    private boolean checkDirection(Board board, Move move, boolean N, boolean NE, boolean E, boolean SE, boolean S, boolean SW, boolean W, boolean NW) {
        if (N) {
            board.isFree(move.getRow() - 1, move.getCol());
        } else if (NE) {
            
        } else if (E) {
            
        } else if (SE) {
            
        } else if (S) {
            
        } else if (SW) {
            
        } else if (W) {
            
        } else if (NW) {
            
        }

        return false;
    }
    
}
