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
    private MoveList potentialMoves;

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
        
        this.maxDepth = (game.getBoard().getRows() * game.getBoard().getCols() > 5) ? 5 : game.getBoard().getRows() * game.getBoard().getCols();
        
        int maxSize = (int) ((((game.getBoard().getRows() * game.getBoard().getCols())/9)*8)*Math.pow(2, maxDepth));
        
        this.potentialMoves = new MoveList(maxSize);
    }

    public MoveList getPotentialMoves() {
        return this.potentialMoves;
    }

    /**
     * Method calculates the AI's next move.
     *
     * @return AI's calculated move
     */
    public Move makeMove() {
        long timeAtStart = System.currentTimeMillis();
        Board boardCopy = new Board(game.getBoard().getRows(), game.getBoard().getCols(), game.getWinningStreak());

        for (int i = 0; i < game.getBoard().getRows(); i++) {
            for (int j = 0; j < game.getBoard().getCols(); j++) {
                boardCopy.getArray()[i][j] = game.getBoard().getArray()[i][j];
            }
        }

        MoveList potentialMovesCopy = new MoveList(potentialMoves.length());

        for (int i = 0; i < potentialMoves.size(); i++) {
            potentialMovesCopy.add(potentialMoves.get(i));
        }

        Move move = minmaxAB(boardCopy, potentialMovesCopy, 0, maxPlayer, new Move(), Integer.MIN_VALUE, Integer.MAX_VALUE);
        potentialMoves = addPotentialMoves(potentialMoves, move, boardCopy);

        long timeAtEnd = System.currentTimeMillis();
        System.out.println("Operation took: " + (timeAtEnd - timeAtStart) + "ms.");
        return move;
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
     * @param potentialMoves
     * @param depth Recursion depth
     * @param player Player whose turn it is
     * @param latestMove The most recent move on the board
     * @param alpha The current best score for the maximizing player
     * @param beta The current best score for the minimizing player
     * @return Move calculated by the algorithm
     */
    public Move minmaxAB(Board board, MoveList potentialMoves, int depth, char player, Move latestMove, int alpha, int beta) {
        if (board.checkForWinWithMove(maxPlayer, latestMove)) {
            // If maximizing player wins, return score maxDepth-depth.
            return new Move(-1, -1, maxDepth - depth);
        } else if (board.checkForWinWithMove(minPlayer, latestMove)) {
            // If minimizing player wins, return score depth-maxDepth.
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
            return new Move(-1, -1, 0);
        }

        if (availablePlaces.size() == game.getBoard().getRows() * game.getBoard().getCols()) {
            return new Move(1, 1);
        }

        if (player == maxPlayer) {
            Move bestMove = new Move(-1, -1, Integer.MIN_VALUE);

            for (int i = 0; i < potentialMoves.size(); i++) {
                Move move = potentialMoves.get(i);
                
                board.getArray()[move.getRow()][move.getCol()] = player;

                MoveList newPotentialMoves = potentialMoves.copy();
                newPotentialMoves = addPotentialMoves(newPotentialMoves, move, board);
                newPotentialMoves.deleteMoveWithCoordinates(move);

                Move moveWithScore = minmaxAB(board, newPotentialMoves, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRow(move.getRow());
                moveWithScore.setCol(move.getCol());

                board.getArray()[move.getRow()][move.getCol()] = '-';
                

                if (moveWithScore.getScore() > bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() > alpha) {
                    alpha = bestMove.getScore();
                }

                // Alpha-beta pruning
                if (beta <= alpha) {
                    break;
                }
            }

            return bestMove;

        } else {
            Move bestMove = new Move(-1, -1, Integer.MAX_VALUE);
           

            for (int i = 0; i < potentialMoves.size(); i++) {
                Move move = potentialMoves.get(i);

                board.getArray()[move.getRow()][move.getCol()] = player;
                MoveList newPotentialMoves = potentialMoves.copy();
                newPotentialMoves = addPotentialMoves(newPotentialMoves, move, board);
                newPotentialMoves.deleteMoveWithCoordinates(move);

                Move moveWithScore = minmaxAB(board, newPotentialMoves, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRow(move.getRow());
                moveWithScore.setCol(move.getCol());

                board.getArray()[move.getRow()][move.getCol()] = '-';
                

                if (moveWithScore.getScore() < bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() < beta) {
                    beta = bestMove.getScore();
                }

                // Alpha-beta pruning
                if (beta <= alpha) {
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
        int size = (int) ((((game.getBoard().getRows() * game.getBoard().getCols())/9)*8)*Math.pow(2, maxDepth));
        
        MoveList moves = new MoveList(game.getBoard().getRows() * game.getBoard().getCols());

        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getCols(); j++) {
                if (board.getArray()[i][j] == '-') {
                    moves.add(new Move(i, j));
                }
            }
        }

        return moves;
    }

    public MoveList addPotentialMoves(MoveList moves, Move moveMade, Board board) {
        moves.deleteMoveWithCoordinates(moveMade);

        // Directions: N, NE, E, SE, S, SW, W, NW
        // N: -1, 0
        moves = addPotentialMove(moves, moveMade, board, -1, 0);

        // NE: -1, 1
        moves = addPotentialMove(moves, moveMade, board, -1, 1);

        // E: 0, 1
        moves = addPotentialMove(moves, moveMade, board, 0, 1);

        // SE: 1, 1
        moves = addPotentialMove(moves, moveMade, board, 1, 1);

        // S: 1, 0
        moves = addPotentialMove(moves, moveMade, board, 1, 0);

        // SW: 1, -1
        moves = addPotentialMove(moves, moveMade, board, 1, -1);

        // W: 0, -1
        moves = addPotentialMove(moves, moveMade, board, 0, -1);

        // NW: -1, -1
        moves = addPotentialMove(moves, moveMade, board, -1, -1);

        return moves;
    }

    private MoveList addPotentialMove(MoveList moves, Move moveMade, Board board, int rowIncrement, int colIncrement) {
        Move move = new Move(moveMade.getRow() + rowIncrement, moveMade.getCol() + colIncrement);

        if (board.isLegal(move.getRow(), move.getCol())) {
            if (board.isFree(move.getRow(), move.getCol())) {
                if (!moves.moveWithCoordinatesExists(move)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }

}
