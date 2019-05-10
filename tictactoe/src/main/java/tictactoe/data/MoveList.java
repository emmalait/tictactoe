package tictactoe.data;

import tictactoe.game.Move;

/**
 * Class implements an ArrayList-type data structure for holding Move-type objects.
 */
public class MoveList {

    private Move[] moves;
    private int index;
    private int indexMax;

    public MoveList(int size) {
        this.moves = new Move[size];
        this.index = -1;
        this.indexMax = -1;
    }

    /**
     * Method adds a move to the MoveList.
     * @param move Move to be added
     */
    public void add(Move move) {
        this.index++;
        this.moves[this.index] = move;
    }

    /**
     * Method returns an item from the MoveList based on index.
     * @param index Index from which an object is retrieved
     * @return Move from MoveList in given index
     */
    public Move get(int index) {
        return this.moves[index];
    }

    /**
     * Methods checks if MoveList is empty.
     * @return true if MoveList is empty, false if not
     */
    public boolean isEmpty() {
        return this.index == -1;
    }

    /**
     * Method returns the number of items on the MoveList.
     * @return No. of items on the MoveList
     */
    public int size() {
        return this.index + 1;
    }
    
    /**
     * Method returns the length of the array behind the MoveList.
     * @return Length of MoveList's array
     */
    public int length() {
        return this.moves.length;
    }
    
    /**
     * Method creates a copy of the MoveList.
     * @return Copy of the MoveList
     */
    public MoveList copy() {
        MoveList newList = new MoveList(this.length());
        for (int i = 0; i < this.index + 1; i++) {
            newList.add(this.moves[i]);
        }
        
        return newList;
    }
    
    /**
     * Method checks if the MoveList holds a move with the given coordinates.
     * @param move Move whose coordinates are used for the check
     * @return true if move exists, false if not
     */
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
    
    /**
     * Method deletes a move with given coordinates from the MoveList.
     * @param move Move whose coordinates are used as the deletion criteria
     */
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
