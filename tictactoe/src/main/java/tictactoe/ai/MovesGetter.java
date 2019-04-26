package tictactoe.ai;

import tictactoe.data.MoveList;
import tictactoe.game.Board;
import tictactoe.game.Move;

public class MovesGetter {

    private MoveList moves;
    private Board board;
    private Move latestMove;

    public MovesGetter(Board board, Move latestMove) {
        this.moves = new MoveList(board.getRows() * board.getCols());
        this.board = board;
        this.latestMove = latestMove;
    }

    public MoveList getMoves() {
        findMoves(latestMove, true, true, true, true, true, true, true, true);
        return moves;
    }

    public void findMoves(Move move, boolean N, boolean NE, boolean E, boolean SE, boolean S, boolean SW, boolean W, boolean NW) {
        if (N) {
            boolean legal = board.isLegal(move.getRow() - 1, move.getCol());
            if (legal) {
                boolean free = board.isFree(move.getRow() - 1, move.getCol());
                if (free) {
                    moves.add(new Move(move.getRow() - 1, move.getCol()));
                } else {
                    findMoves(new Move(move.getRow() - 1, move.getCol()), true, true, false, false, false, false, false, true);
                }
            }
        }
        
        if (NE) {
            boolean legal = board.isLegal(move.getRow() - 1, move.getCol() - 1);
            if (legal) {
                boolean free = board.isFree(move.getRow() - 1, move.getCol() - 1);
                if (free) {
                    moves.add(new Move(move.getRow() - 1, move.getCol() - 1));
                } else {
                    findMoves(new Move(move.getRow() - 1, move.getCol() - 1), true, true, true, true, false, false, false, true);
                }
            }
        }
        
        if (E) {
            boolean legal = board.isLegal(move.getRow(), move.getCol() + 1);
            if (legal) {
                boolean free = board.isFree(move.getRow(), move.getCol() + 1);
                if (free) {
                    moves.add(new Move(move.getRow(), move.getCol() + 1));
                } else {
                    findMoves(new Move(move.getRow(), move.getCol() + 1), false, true, true, true, false, false, false, false);
                }
            }
        }
        
        if (SE) {
            boolean legal = board.isLegal(move.getRow() + 1, move.getCol() + 1);
            if (legal) {
                boolean free = board.isFree(move.getRow() + 1, move.getCol() + 1);
                if (free) {
                    moves.add(new Move(move.getRow() + 1, move.getCol() + 1));
                } else {
                    findMoves(new Move(move.getRow() + 1, move.getCol() + 1), false, true, true, true, true, true, false, false);
                }
            }
        }
        
        if (S) {
            boolean legal = board.isLegal(move.getRow() + 1, move.getCol());
            if (legal) {
                boolean free = board.isFree(move.getRow() + 1, move.getCol());
                if (free) {
                    moves.add(new Move(move.getRow() + 1, move.getCol()));
                } else {
                    findMoves(new Move(move.getRow() + 1, move.getCol()), false, false, false, true, true, true, false, false);
                }
            }
        }
        
        if (SW) {
            boolean legal = board.isLegal(move.getRow() + 1, move.getCol() - 1);
            if (legal) {
                boolean free = board.isFree(move.getRow() + 1, move.getCol() - 1);
                if (free) {
                    moves.add(new Move(move.getRow() + 1, move.getCol() - 1));
                } else {
                    findMoves(new Move(move.getRow() + 1, move.getCol() - 1), false, false, false, true, true, true, true, true);
                }
            }
        }
        
        if (W) {
            boolean legal = board.isLegal(move.getRow(), move.getCol() - 1);
            if (legal) {
                boolean free = board.isFree(move.getRow(), move.getCol() - 1);
                if (free) {
                    moves.add(new Move(move.getRow(), move.getCol() - 1));
                } else {
                    findMoves(new Move(move.getRow(), move.getCol() - 1), false, false, false, false, false, true, true, true);
                }
            }
        }
        
        if (NW) {
            boolean legal = board.isLegal(move.getRow() - 1, move.getCol() - 1);
            if (legal) {
                boolean free = board.isFree(move.getRow() - 1, move.getCol() - 1);
                if (free) {
                    moves.add(new Move(move.getRow() - 1, move.getCol() - 1));
                } else {
                    findMoves(new Move(move.getRow() - 1, move.getCol() - 1), true, true, false, false, false, true, true, true);
                }
            }
        }
    }

}
