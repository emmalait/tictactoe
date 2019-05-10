
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import tictactoe.game.Game;
import tictactoe.game.Move;

public class GameTest {

    Game game;

    public GameTest() {
    }

    @Before
    public void setUp() {
        game = new Game();
        game.setup(5, 5, 5, true);
    }

    @Test
    public void makingALegalMoveIsPossible() {
        game.getBoard().printBoard();
        boolean ok = game.makeMove(new Move(0, 0));
        game.getBoard().printBoard();
        assertEquals(true, ok);
    }

    @Test
    public void makingAMoveOutsideBoardIsNotPossible() {
        boolean ok = game.makeMove(new Move(-1, 0));
        assertEquals(false, ok);
    }

    @Test
    public void makingAMoveTwiceIsNotPossible() {
        game.makeMove(new Move(1, 1));
        boolean ok = game.makeMove(new Move(1, 1));
        assertEquals(false, ok);
    }

    @Test
    public void switchesPlayerFrom1to2() {
        game.switchPlayer();
        char mark = game.getCurrentPlayer().getMark();
        assertEquals('O', mark);
    }

    @Test
    public void switchesPlayerFrom2to1() {
        game.switchPlayer();
        game.switchPlayer();
        char mark = game.getCurrentPlayer().getMark();
        assertEquals('X', mark);
    }

    @Test
    public void findsWinningRow() {
        game.makeMove(new Move(0, 0));
        game.makeMove(new Move(1, 0));
        game.makeMove(new Move(2, 0));
        game.makeMove(new Move(3, 0));
        game.makeMove(new Move(4, 0));
        boolean win = game.getBoard().checkForWin('X', game.getLatestMove());
        assertEquals(true, win);
    }

    @Test
    public void findsWinningColumn() {
        game.makeMove(new Move(0, 0));
        game.makeMove(new Move(0, 1));
        game.makeMove(new Move(0, 2));
        game.makeMove(new Move(0, 3));
        game.makeMove(new Move(0, 4));
        boolean win = game.getBoard().checkForWin('X', game.getLatestMove());
        assertEquals(true, win);
    }

    @Test
    public void findsWinningFwdDiagonal() {
        game.makeMove(new Move(0, 0));
        game.makeMove(new Move(1, 1));
        game.makeMove(new Move(2, 2));
        game.makeMove(new Move(3, 3));
        game.makeMove(new Move(4, 4));
        boolean win = game.getBoard().checkForWin('X', game.getLatestMove());
        assertEquals(true, win);
    }

    @Test
    public void findsWinningBwdDiagonal() {
        game.makeMove(new Move(0, 4));
        game.makeMove(new Move(1, 3));
        game.makeMove(new Move(2, 2));
        game.makeMove(new Move(3, 1));
        game.makeMove(new Move(4, 0));
        boolean win = game.getBoard().checkForWin('X', game.getLatestMove());
        assertEquals(true, win);
    }

    @Test
    public void registersWin() {
        game.makeMove(new Move(0, 4));
        game.makeMove(new Move(1, 3));
        game.makeMove(new Move(2, 2));
        game.makeMove(new Move(3, 1));
        game.makeMove(new Move(4, 0));
        boolean win = game.isGameWon();
        assertEquals(true, win);
    }

    @Test
    public void registersGameOver() {
        game.makeMove(new Move(0, 0));
        game.makeMove(new Move(0, 1));
        game.makeMove(new Move(0, 2));
        game.makeMove(new Move(0, 3));
        game.makeMove(new Move(0, 4));
        
        game.makeMove(new Move(1, 0));
        game.makeMove(new Move(1, 1));
        game.makeMove(new Move(1, 2));
        game.makeMove(new Move(1, 3));
        game.makeMove(new Move(1, 4));
        
        game.makeMove(new Move(2, 0));
        game.makeMove(new Move(2, 1));
        game.makeMove(new Move(2, 2));
        game.makeMove(new Move(2, 3));
        game.makeMove(new Move(2, 4));
        
        game.makeMove(new Move(3, 0));
        game.makeMove(new Move(3, 1));
        game.makeMove(new Move(3, 2));
        game.makeMove(new Move(3, 3));
        game.makeMove(new Move(3, 4));
        
        game.makeMove(new Move(4, 0));
        game.makeMove(new Move(4, 1));
        game.makeMove(new Move(4, 2));
        game.makeMove(new Move(4, 3));
        game.makeMove(new Move(4, 4));
        boolean over = game.isGameOver();
        assertEquals(true, over);
    }
}
