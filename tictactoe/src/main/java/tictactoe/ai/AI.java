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
        this.maxDepth = (game.getBoard().getRows() * game.getBoard().getCols() > 7) ? 7 : game.getBoard().getRows() * game.getBoard().getCols();
        this.potentialMoves = new MoveList(game.getBoard().getRows() * game.getBoard().getCols());
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
        Board boardCopy = new Board(game.getBoard().getRows(), game.getBoard().getCols(), game.getWinningStreak());

        for (int i = 0; i < game.getBoard().getRows(); i++) {
            for (int j = 0; j < game.getBoard().getCols(); j++) {
                boardCopy.getArray()[i][j] = game.getBoard().getArray()[i][j];
            }
        }

        MoveList potentialMovesCopy = new MoveList(potentialMoves.size());

        for (int i = 0; i < potentialMoves.size(); i++) {
            potentialMovesCopy.add(potentialMoves.get(i));
        }

        //Move move = minmax(boardCopy, 0, maxPlayer, new Move());
        Move move = minmaxAB(boardCopy, potentialMovesCopy, 0, maxPlayer, new Move(), Integer.MIN_VALUE, Integer.MAX_VALUE);

        potentialMoves = addPotentialMoves(potentialMoves, move, boardCopy);

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

            /*System.out.println("d: " + depth + ", player: " + player + ", move: " + latestMove);
            System.out.println("Max wins, score: " + String.valueOf(maxDepth - depth));*/
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

        if (availablePlaces.size() == game.getBoard().getRows() * game.getBoard().getCols()) {
            return new Move(1, 1);
        }

        if (player == maxPlayer) {
            Move bestMove = new Move(-1, -1, Integer.MIN_VALUE);

            System.out.println("size: " + potentialMoves.size());

            for (int i = 0; i < potentialMoves.size(); i++) {
                Move move = potentialMoves.get(i);
                //System.out.println("Player " + player + " makes move: " + move);

                board.getArray()[move.getRow()][move.getCol()] = player;
                potentialMoves.deleteMoveWithCoordinates(move);
                potentialMoves = addPotentialMoves(potentialMoves, move, board);

                Move moveWithScore = minmaxAB(board, potentialMoves, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
                moveWithScore.setRow(move.getRow());
                moveWithScore.setCol(move.getCol());

                board.getArray()[move.getRow()][move.getCol()] = '-';

                //System.out.println("Move (max): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);
                if (moveWithScore.getScore() > bestMove.getScore()) {
                    bestMove = moveWithScore;
                }

                if (bestMove.getScore() > alpha) {
                    alpha = bestMove.getScore();
                }

                // Alpha-beta pruning
                if (beta <= alpha) {
                    //System.out.println("BREAK: Move (max): " + move.getRow() + ", " + move.getCol() + "; alpha: " + alpha + ", beta: " + beta);
                    break;
                }
            }

            return bestMove;

        } else {
            Move bestMove = new Move(-1, -1, Integer.MAX_VALUE);

            for (int i = 0; i < potentialMoves.size(); i++) {
                Move move = potentialMoves.get(i);
                //System.out.println("Player " + player + " makes move: " + move);

                board.getArray()[move.getRow()][move.getCol()] = player;
                potentialMoves.deleteMoveWithCoordinates(move);
                addPotentialMoves(potentialMoves, move, board);

                Move moveWithScore = minmaxAB(board, potentialMoves, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move, alpha, beta);
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

    public MoveList addPotentialMoves(MoveList moves, Move moveMade, Board board) {
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
