import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import tictactoe.game.Game;
import tictactoe.game.Move;
import tictactoe.game.Player;

public class PlayerTest {
    Game game;
    Player human;
    Player ai;
    
    public PlayerTest() {
    }
    
    @Before
    public void setUp() {
        game = new Game();
        game.setup(5, 5, 5, true);
        human = game.getPlayer1();
        ai = game.getPlayer2();
    }
    
    @Test
    public void aiPlayerReturnsMove() {
        Move move = ai.getMove();
        assertNotNull(move);
    }
    
    @Test
    public void isAiPlayerWorks() {
        assertEquals(true, ai.isAiPlayer());
        assertEquals(false, human.isAiPlayer());
        
    }
    
}
