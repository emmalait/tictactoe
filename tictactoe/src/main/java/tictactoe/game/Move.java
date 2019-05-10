package tictactoe.game;

/**
 * Class for implementing the move object in the game.
 */
public class Move {
    
    private int row;
    private int col;
    private int score;
    
    public Move() {
        this.row = -1;
        this.col = -1;
        this.score = 0;
    }

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
        this.score = 0;
    }
    
    public Move(int row, int col, int score) {
        this.row = row;
        this.col = col;
        this.score = score;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
    
    public int getScore() {
        return this.score;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "(row: " + row + ", col: " + col + ", score: " + score + ')';
    }

    @Override
    public boolean equals(Object object) {
        boolean equals = false;
        Move other = (Move) object;
        if (row == other.getRow() && col == other.getCol() && score == other.getScore()) {
            equals = true;
        }
        return equals;
    }
    
}
