
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import tictactoe.game.Move;

public class MoveTest {
    
    public MoveTest() {
    }
    
    @Test
    public void setScoreWorks() {
        Move move1 = new Move(1, 1);
        move1.setScore(5);
        assertEquals(5, move1.getScore());
    }

    @Test
    public void movesWithSameCoordinatesTreatedAsEqual() {
        Move move1 = new Move(1, 1);
        Move move2 = new Move(1, 1);
        boolean same = move1.equals(move2);
        assertEquals(true, same);
    }
    
    @Test
    public void movesWithDifferentCoordinatesNotTreatedAsEqual() {
        Move move1 = new Move(1, 1);
        Move move2 = new Move(1, 2);
        Move move3 = new Move(2, 1);
        Move move4 = new Move(5, 5);
        boolean same1 = move1.equals(move2);
        boolean same2 = move1.equals(move3);
        boolean same3 = move1.equals(move4);
        assertEquals(false, same1);
        assertEquals(false, same2);
        assertEquals(false, same3);
    }
    
    @Test
    public void toStringIsCorrect() {
        Move move1 = new Move(1, 1);
        assertEquals(move1.toString(), "(row: 1, col: 1, score: 0)");
    }
}
