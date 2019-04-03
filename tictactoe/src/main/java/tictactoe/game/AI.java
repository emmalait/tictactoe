package tictactoe.game;

import java.util.ArrayList;
import java.util.List;

public class AI {
    
    private TicTacToe game;
    private char[][] board;
    private int rows;
    private int cols;
    private char maxPlayer;
    private char minPlayer;
    private boolean isMax;

    public AI(TicTacToe game, int rows, int cols, char maxPlayer, char minPlayer) {
        this.game = game;
        this.rows = rows;
        this.cols = cols;
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
    }

    public Move minimax(char[][] board, char maxPlayer, char minPlayer, boolean isMax) {
        List<Move> availablePlaces = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == '-') {
                    availablePlaces.add(new Move(i, j));
                }
            }
        }

        if (game.checkForWin(board, maxPlayer)) {
            Move move = new Move();
            move.setScore(10);
            return move;
        } else if (game.checkForWin(board, minPlayer)) {
            Move move = new Move();
            move.setScore(-10);
            return move;
        } else if (availablePlaces.isEmpty()) {
            Move move = new Move();
            move.setScore(0);
            return move;
        }

        List<Move> scores = new ArrayList<>();

        for (Move move : availablePlaces) {
            char[][] newBoard = board;

            if (isMax) {
                newBoard[move.getRowCoordinate()][move.getColCoordinate()] = maxPlayer;
            } else {
                newBoard[move.getRowCoordinate()][move.getColCoordinate()] = minPlayer;
            }

            if (isMax) {
                Move result = minimax(newBoard, maxPlayer, minPlayer, false);
                move.setScore(result.getScore());
            } else {
                Move result = minimax(newBoard, maxPlayer, minPlayer, true);
                move.setScore(result.getScore());
            }

            newBoard[move.getRowCoordinate()][move.getColCoordinate()] = '-';

            scores.add(move);
        }

        int bestMove = -1;

        if (isMax) {
            int bestScore = -100000;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i).getScore() > bestScore) {
                    bestScore = scores.get(i).getScore();
                    bestMove = i;
                }
            }
        } else {
            int bestScore = 100000;
            for (int i = 0; i < scores.size(); i++) {
                if (scores.get(i).getScore() < bestScore) {
                    bestScore = scores.get(i).getScore();
                    bestMove = i;
                }
            }
        }

        return scores.get(bestMove);
    }

}
