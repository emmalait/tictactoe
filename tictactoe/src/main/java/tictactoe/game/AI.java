package tictactoe.game;

import java.util.ArrayList;
import java.util.List;

public class AI {

    private TicTacToe game;
    private int rows;
    private int cols;
    private char maxPlayer;
    private char minPlayer;
    private int maxDepth;

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
        this.maxDepth = rows * cols + 1;
    }

    public Move moveAI() {
        char[][] boardCopy = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                boardCopy[i][j] = game.getBoard()[i][j];
            }
        }

        return minmax(boardCopy, 0, maxPlayer, new Move());
    }

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
        List<Move> availablePlaces = getAvailablePlaces(boardCopy);

        if (availablePlaces.isEmpty()) {            
            return new Move(-1, -1, 0);
        }

        List<Move> values = new ArrayList<>();

        for (Move move : availablePlaces) {
            boardCopy[move.getRowCoordinate()][move.getColCoordinate()] = player;
            Move moveWithScore = minmax(boardCopy, depth + 1, (player == maxPlayer) ? minPlayer : maxPlayer, move);
            moveWithScore.setRowCoordinate(move.getRowCoordinate());
            moveWithScore.setColCoordinate(move.getColCoordinate());
            values.add(moveWithScore);
            boardCopy[move.getRowCoordinate()][move.getColCoordinate()] = '-';
        }

        if (depth == 0) {
            for (Move value : values) {
                System.out.println("Row: " + String.valueOf(value.getRowCoordinate()) + ", col: " + String.valueOf(value.getColCoordinate())+ ", score: " + String.valueOf(value.getScore()));
            }
        }
        
        if (player == maxPlayer) {
            Move max = new Move(-1, -1, Integer.MIN_VALUE);

            for (Move value : values) {
                if (value.getScore() > max.getScore()) {
                    max = value;
                }
            }

            return max;

        } else {
            Move min = new Move(-1, -1, Integer.MAX_VALUE);

            for (Move value : values) {
                if (value.getScore() < min.getScore()) {
                    min = value;
                }
            }

            return min;
        }

    }

    public List<Move> getAvailablePlaces(char[][] board) {
        List<Move> availablePlaces = new ArrayList<>();

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
