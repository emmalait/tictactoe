package tictactoe.game;

public class Move {
    
    private int rowCoordinate;
    private int colCoordinate;
    private int score;
    
    public Move() {
        this.rowCoordinate = -1;
        this.colCoordinate = -1;
        this.score = 0;
    }

    public Move(int row, int col) {
        this.rowCoordinate = row;
        this.colCoordinate = col;
        this.score = 0;
    }

    public int getRowCoordinate() {
        return rowCoordinate;
    }

    public int getColCoordinate() {
        return colCoordinate;
    }
    
    public int getScore() {
        return this.score;
    }

    public void setRowCoordinate(int rowCoordinate) {
        this.rowCoordinate = rowCoordinate;
    }

    public void setColCoordinate(int colCoordinate) {
        this.colCoordinate = colCoordinate;
    }
    
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Move{" + "rowCoordinate=" + rowCoordinate + ", colCoordinate=" + colCoordinate + ", score=" + score + '}';
    }
    
}
