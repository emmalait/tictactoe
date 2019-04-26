package tictactoe.data;

import tictactoe.game.Move;

public class MoveList {

    private Move[] moves;
    private int index;

    public MoveList(int size) {
        this.moves = new Move[size];
        this.index = -1;
    }

    public void add(Move move) {
        this.index++;
        this.moves[this.index] = move;
    }

    public void delete(Move move) {
        int deleteIndex = -1;
        for (int i = 0; i <= index; i++) {
            if (moves[i].equals(move)) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            for (int i = deleteIndex; i < index; i++) {
                moves[i] = moves[i + 1];
            }
            index--;
        }
 
    }

    public Move get(int index) {
        return this.moves[index];
    }

    public boolean isEmpty() {
        return this.index == -1;
    }

    public int size() {
        return this.index + 1;
    }
    
    public boolean moveWithCoordinatesExists(Move move) {
        boolean exists = false;
        
        for (int i = 0; i <= index; i++) {
            if (moves[i].getRow() == move.getRow() && moves[i].getCol() == move.getCol()) {
                exists = true;
                break;
            }
        }
        
        return exists;
    }
    
    public void deleteMoveWithCoordinates(Move move) {
        int deleteIndex = -1;
        for (int i = 0; i <= index; i++) {
            if (moves[i].getRow() == move.getRow() && moves[i].getCol() == move.getCol()) {
                deleteIndex = i;
                break;
            }
        }

        if (deleteIndex != -1) {
            for (int i = deleteIndex; i < index; i++) {
                moves[i] = moves[i + 1];
            }
            index--;
        }
    }

    @Override
    public String toString() {
        String movesString = "";
        
        for (int i = 0; i <= index; i++) {
            movesString += moves[i];
            if (i != index) {
                movesString += ", ";
            }
        }
        
        return movesString;
    }

}
