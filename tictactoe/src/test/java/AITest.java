import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.game.AI;
import tictactoe.game.Move;
import tictactoe.game.TicTacToe;


public class AITest {
    TicTacToe game;
    AI ai;
    
    public AITest() {
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
        game = new TicTacToe(3, 3, 3);
        game.initialiseBoard();
        ai = new AI(game, 3, 3, 'X');
    }
    
//    @After
//    public void tearDown() {
//    }

    @Test
    public void minmaxGeneratesWinningMove() {
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.switchPlayer();
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 2);
        game.switchPlayer();
        Move move = ai.minmax(game.getBoard(), 0, 'X', new Move(2, 2));
        
        assertEquals(move.getRow(), 2);
        assertEquals(move.getCol(), 1);
    }
    
    @Test
    public void minmaxABGeneratesWinningMove() {
        game.makeMove(0, 0);
        game.makeMove(0, 1);
        game.makeMove(1, 1);
        game.switchPlayer();
        game.makeMove(0, 2);
        game.makeMove(1, 0);
        game.makeMove(2, 2);
        game.switchPlayer();
        Move move = ai.minmaxAB(game.getBoard(), 0, 'X', new Move(2, 2), Integer.MIN_VALUE, Integer.MAX_VALUE);
        
        assertEquals(move.getRow(), 2);
        assertEquals(move.getCol(), 1);
    }

}
