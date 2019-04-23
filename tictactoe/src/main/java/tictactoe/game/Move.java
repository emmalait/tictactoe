package tictactoe.game;

/**
 * Class for the move entity
 * 
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

    /**
     * 
     * @param row Row coordinate
     * @param col Column coordinate
     */
    public Move(int row, int col) {
        this.row = row;
        this.col = col;
        this.score = 0;
    }
    
    /**
     * 
     * @param row Row coordinate
     * @param col Column coordinate
     * @param score Score of the move
     */
    public Move(int row, int col, int score) {
        this.row = row;
        this.col = col;
        this.score = score;
    }

    /**
     * Returns row coordinate
     * @return Row coordinate
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Returns column coordinate
     * @return Column coordinate
     */
    public int getCol() {
        return col;
    }
    
    /**
     * Returns score
     * @return Score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Sets row coordinate
     * @param row Row coordinate
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets column coordinate
     * @param col Column coordinate
     */
    public void setCol(int col) {
        this.col = col;
    }
    
    /**
     * Sets score
     * @param score Score
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "(row: " + row + ", col: " + col + ", score: " + score + ')';
    }
    
}
