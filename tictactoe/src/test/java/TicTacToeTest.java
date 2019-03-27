import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import tictactoe.tictactoe.TicTacToe;

public class TicTacToeTest {
    TicTacToe game;
    
    public TicTacToeTest() {
    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        game = new TicTacToe();
        game.initialiseBoard();
    }
    
//    @After
//    public void tearDown() {
//    }
    
    @Test
    public void makingALegalMoveIsPossible() {
        game.printBoard();
        boolean ok = game.makeMove(0, 0);
        game.printBoard();
        assertEquals(true, ok);
    }
    
    @Test
    public void makingAMoveOutsideBoardIsNotPossible() {
        boolean ok = game.makeMove(-1, 0);
        assertEquals(false, ok);
    }
    
    @Test
    public void makingAMoveTwiceIsNotPossible() {
        game.makeMove(1, 1);
        boolean ok = game.makeMove(1, 1);
        assertEquals(false, ok);
    }
    
    @Test
    public void switchesPlayerFromXtoO() {
        game.switchPlayer();
        char player = game.getPlayer();
        assertEquals('O', player);
    }
    
    @Test
    public void switchesPlayerFromOtoX() {
        game.switchPlayer();
        game.switchPlayer();
        char player = game.getPlayer();
        assertEquals('X', player);
    }
    
    @Test
    public void findsWinningRow() {
        game.makeMove(0, 0);
        game.makeMove(1, 0);
        game.makeMove(2, 0);
        game.makeMove(3, 0);
        game.makeMove(4, 0);
        
        boolean win = game.checkForWin();
        
        assertEquals(true, win);
    }
    
    @Test
    public void findsWinningColumn() {
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(0, 2);
        game.makeMove(0, 3);
        game.makeMove(0, 4);
        
        boolean win = game.checkForWin();
        
        assertEquals(true, win);
    }
    
    @Test
    public void findsWinningFwdDiagonal() {
        game.makeMove(0, 0);
        game.makeMove(1, 1);
        game.makeMove(2, 2);
        game.makeMove(3, 3);
        game.makeMove(4, 4);
        
        boolean win = game.checkForWin();
        
        assertEquals(true, win);
    }
    
    @Test
    public void findsWinningBwdDiagonal() {
        game.makeMove(0, 4);
        game.makeMove(1, 3);
        game.makeMove(2, 2);
        game.makeMove(3, 1);
        game.makeMove(4, 0);
        
        boolean win = game.checkForWin();
        
        assertEquals(true, win);
    }
}
