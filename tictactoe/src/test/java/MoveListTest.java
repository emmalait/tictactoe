
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import tictactoe.data.MoveList;
import tictactoe.game.Move;

public class MoveListTest {
    private MoveList list;
    
    public MoveListTest() {
    }
    
    @Before
    public void setUp() {
         list = new MoveList(5);
         list.add(new Move(1, 0));
         list.add(new Move(1, 2));
         list.add(new Move(3, 3));
    }
    
    @Test
    public void addingToMoveListWorks() {
        list.add(new Move(4, 4));
        assertEquals(4, list.size());
    }

    @Test
    public void deletingFromMoveListWorks() {
        list.deleteMoveWithCoordinates(new Move(3, 3));
        assertEquals(2, list.size());
        assertEquals(list.moveWithCoordinatesExists(new Move(3, 3)), false);
    }
    
    @Test
    public void gettingFromMoveListWorks() {
        Move move = list.get(0);
        assertEquals(1, move.getRow());
        assertEquals(0, move.getCol());
    }
    
    @Test
    public void isEmptyWorksWhenListIsEmpty() {
        MoveList list1 = new MoveList(5);
        assertEquals(true, list1.isEmpty());
    }
    
    @Test
    public void isEmptyWorksWhenListIsNotEmpty() {
        assertEquals(false, list.isEmpty());
    }
    
    @Test
    public void moveListSizeWorks() {
        MoveList list1 = new MoveList(1);
        list1.add(new Move(1, 1));
        assertEquals(1, list1.size());
    }
    
    @Test
    public void moveListLengthWorks() {
        MoveList list1 = new MoveList(10);
        list1.add(new Move(1, 1));
        assertEquals(10, list1.length());
    }
    
    @Test
    public void moveListCopyWorks() {
        MoveList list1 = new MoveList(5);
        list1.add(new Move(1, 1));
        list1.add(new Move(2, 2));
        MoveList list2 = list1.copy();
        assertEquals(list1.get(0), list2.get(0));
        assertEquals(list1.get(1), list2.get(1));
    }
    
    @Test
    public void toStringIsCorrect() {
        assertEquals(list.toString(), "(row: 1, col: 0, score: 0), (row: 1, col: 2, score: 0), (row: 3, col: 3, score: 0)");
    }
}
