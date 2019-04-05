package tictactoe.game;

/**
 * Class for the move entity
 * 
 */
public class Move {
    
    private int rowCoordinate;
    private int colCoordinate;
    private int score;
    
    public Move() {
        this.rowCoordinate = -1;
        this.colCoordinate = -1;
        this.score = 0;
    }

    /**
     * 
     * @param row Row coordinate
     * @param col Column coordinate
     */
    public Move(int row, int col) {
        this.rowCoordinate = row;
        this.colCoordinate = col;
        this.score = 0;
    }
    
    /**
     * 
     * @param row Row coordinate
     * @param col Column coordinate
     * @param score Score of the move
     */
    public Move(int row, int col, int score) {
        this.rowCoordinate = row;
        this.colCoordinate = col;
        this.score = score;
    }

    /**
     * Returns row coordinate
     * @return Row coordinate
     */
    public int getRowCoordinate() {
        return rowCoordinate;
    }
    
    /**
     * Returns column coordinate
     * @return Column coordinate
     */
    public int getColCoordinate() {
        return colCoordinate;
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
     * @param rowCoordinate Row coordinate
     */
    public void setRowCoordinate(int rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    /**
     * Sets column coordinate
     * @param colCoordinate Column coordinate
     */
    public void setColCoordinate(int colCoordinate) {
        this.colCoordinate = colCoordinate;
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
        return "(row: " + rowCoordinate + ", col: " + colCoordinate + ", score: " + score + ')';
    }
    
}
