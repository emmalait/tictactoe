import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import tictactoe.ai.AI;
import tictactoe.game.Move;
import tictactoe.game.Game;


public class AITest {
    Game game;
    AI ai;
    
    public AITest() {
    }
    
    @Before
    public void setUp() {
        game = new Game();
        game.setup(3, 3, 3, true);
    }
    
    @Test
    public void minmaxABGeneratesWinningMove1() {
        game.makeMove(new Move(1, 1));
        game.switchPlayer();
        game.makeMove(new Move(0, 1)); 
        game.switchPlayer();
        game.makeMove(new Move(1, 2));
        game.switchPlayer();
        Move move = game.getPlayer2().getAi().makeMove();
        assertEquals(move.getRow(), 1);
        assertEquals(move.getCol(), 0);
    }
    
    @Test
    public void minmaxABGeneratesWinningMove2() {
        game.makeMove(new Move(0, 0));
        game.switchPlayer();
        game.makeMove(new Move(0, 1)); 
        game.switchPlayer();
        game.makeMove(new Move(1, 1));
        game.switchPlayer();
        Move move = game.getPlayer2().getAi().makeMove();
        assertEquals(move.getRow(), 2);
        assertEquals(move.getCol(), 2);
    }
    
    @Test
    public void minmaxABGeneratesWinningMove3() {
        game.makeMove(new Move(0, 2));
        game.switchPlayer();
        game.makeMove(new Move(1, 2)); 
        game.switchPlayer();
        game.makeMove(new Move(2, 2));
        game.switchPlayer();
        game.makeMove(new Move(1, 1));
        game.switchPlayer();
        game.makeMove(new Move(0, 0));
        game.switchPlayer();
        Move move = game.getPlayer2().getAi().makeMove();
        assertEquals(move.getRow(), 1);
        assertEquals(move.getCol(), 0);
    }

}
