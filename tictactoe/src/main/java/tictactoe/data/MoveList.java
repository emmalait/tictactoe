
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
    
    public Move get(int index) {
        return this.moves[index];
    }
    
    public boolean isEmpty() {
        return this.index == 0;
    }
    
    public int size() {
        return this.index + 1;
    }
    
}
