package tictactoe.game;

import java.util.ArrayList;
import java.util.List;
import tictactoe.data.MoveList;

/**
 * Class is responsible for the AI.
 * 
 */
public class AI {

    private TicTacToe game;
    private int rows;
    private int cols;
    private char maxPlayer;
    private char minPlayer;
    private int maxDepth;

    /**
     * @param game The instance of TicTacToe the AI is to play in
     * @param rows Number of rows on the board
     * @param cols Number of columns on the board
     * @param maxPlayer The mark the AI is playing with (X/O)
     */
    public AI(TicTacToe game, int rows, int cols, char maxPlayer) {
        this.game = game;
        this.rows = rows;
        this.cols = cols;
        this.maxPlayer = maxPlayer;
        if (maxPlayer == 'X') {
            this.minPlayer = 'O';
        } else {
            this.minPlayer = 'X';
        }
        this.maxDepth = 4;
    }

    /**
     * Method calculates the AI's next move.
     *
     * @return AI's calculated move
     */
    public Move makeMove() {
        char[][] boardCopy = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardCopy[i][j] = game.getBoard()[i][j];
            }
        }

        return minmax(boardCopy, 0, maxPlayer, new Move());
        //return minmaxAB(boardCopy, 0, maxPlayer, new Move(), Integer.MIN_VALUE, Integer.MAX_VALUE);
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
    public Move minmax(char[][] board, int depth, char player, Move latestMove) {
        char[][] boardCopy = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardCopy[i][j] = board[i][j];
            }
        }

        if (game.checkForWinWithMove(boardCopy, maxPlayer, latestMove)) {
            // If maximizing player wins, return score maxdepth-d.
            return new Move(-1, -1, maxDepth - depth);
        } else if (game.checkForWinWithMove(boardCopy, minPlayer, latestMove)) {
            // If minimizing player wins, return score maxdepth-10.
            return new Move(-1, -1, depth - maxDepth);
        }

        // Check passed board for empty spaces.
        MoveList availablePlaces = getAvailablePlaces(boardCopy);
        
        if (availablePlaces.isEmpty()) {
            return new Move(-1, -1, 0);
        }

        MoveList scores = new MoveList(rows*cols);
        
        for (int i = 0; i < availablePlaces.size(); i++) {
            Move move = availablePlaces.get(i);
            boardCopy[move.getRowCoordinate()][move.getColCoordinate()] = player;
            Move moveWithScore = minmax(boardCopy, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move);
            moveWithScore.setRowCoordinate(move.getRowCoordinate());
            moveWithScore.setColCoordinate(move.getColCoordinate());
            scores.add(moveWithScore);
            boardCopy[move.getRowCoordinate()][move.getColCoordinate()] = '-';
        }

        System.out.println("Scores size: " + scores.size());
        
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
    public Move minmaxAB(char[][] board, int depth, char player, Move latestMove, int alpha, int beta) {
        if (game.checkForWinWithMove(board, maxPlayer, latestMove)) {
            // If maximizing player wins, return score maxdepth-d.
            return new Move(-1, -1, maxDepth - depth);
        } else if (game.checkForWinWithMove(board, minPlayer, latestMove)) {
            // If minimizing player wins, return score maxdepth-10.
            return new Move(-1, -1, depth - maxDepth);
        }

        // Check passed board for empty spaces.
        List<Move> availablePlaces = getAvailablePlaces(board);

        if (availablePlaces.isEmpty()) {
            return new Move(-1, -1, 0);
        }

        if (player == maxPlayer) {
            Move bestMove = new Move(-1, -1, Integer.MIN_VALUE);

            for (Move move : availablePlaces) {
                board[move.getRowCoordinate()][move.getColCoordinate()] = player;
                Move moveWithScore = minmaxAB(board, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRowCoordinate(move.getRowCoordinate());
                moveWithScore.setColCoordinate(move.getColCoordinate());

                //System.out.println("Move (max): " + move.getRowCoordinate() + ", " + move.getColCoordinate() + "; alpha: " + alpha + ", beta: " + beta);
                if (moveWithScore.getScore() > bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() > alpha) {
                    alpha = bestMove.getScore();
                }

                if (beta <= alpha) {
                    //System.out.println("BREAK: Move (max): " + move.getRowCoordinate() + ", " + move.getColCoordinate() + "; alpha: " + alpha + ", beta: " + beta);

                    break;
                }

                board[move.getRowCoordinate()][move.getColCoordinate()] = '-';
            }

            return bestMove;

        } else {
            Move bestMove = new Move(-1, -1, Integer.MAX_VALUE);

            for (Move move : availablePlaces) {
                board[move.getRowCoordinate()][move.getColCoordinate()] = player;
                Move moveWithScore = minmaxAB(board, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRowCoordinate(move.getRowCoordinate());
                moveWithScore.setColCoordinate(move.getColCoordinate());

                //System.out.println("Move (min): " + move.getRowCoordinate() + ", " + move.getColCoordinate() + "; alpha: " + alpha + ", beta: " + beta);
                if (moveWithScore.getScore() < bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() < beta) {
                    beta = bestMove.getScore();
                }

                if (beta <= alpha) {
                    //System.out.println("BREAK: Move (min): " + move.getRowCoordinate() + ", " + move.getColCoordinate() + "; alpha: " + alpha + ", beta: " + beta);

                    break;
                }

                board[move.getRowCoordinate()][move.getColCoordinate()] = '-';
            }

            return bestMove;
        }

    }

    /**
     * Method for finding free places on the board ('-').
     * 
     * @param board Current board
     * @return List of possible moves
     */
    private MoveList getAvailablePlaces(char[][] board) {
        List<Move> availablePlaces2 = new ArrayList<>();
        MoveList availablePlaces = new MoveList(rows * cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') {
                    availablePlaces.add(new Move(i, j));
                }
            }
        }

        return availablePlaces;
    }

}
