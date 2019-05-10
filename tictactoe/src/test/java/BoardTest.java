/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;
import tictactoe.game.Board;

/**
 *
 * @author emmalait
 */
public class BoardTest {
    Board board;
    
    public BoardTest() {
    }

    
    @Before
    public void setUp() {
        board = new Board(3, 3, 3);
    }

    @Test
    public void constructorInitialisesBoard() {
        assertEquals('-', board.getArray()[0][0]);
        assertEquals('-', board.getArray()[0][1]);
        assertEquals('-', board.getArray()[0][2]);
        assertEquals('-', board.getArray()[1][0]);
        assertEquals('-', board.getArray()[1][1]);
        assertEquals('-', board.getArray()[1][2]);
        assertEquals('-', board.getArray()[2][0]);
        assertEquals('-', board.getArray()[2][1]);
        assertEquals('-', board.getArray()[2][2]);
    }
    
    @Test
    public void boardCanBeCreatedWithoutWinningStreak() {
        Board board1 = new Board(1, 1);
        assertNotNull(board1);
    }
    
    @Test
    public void copyWorks() {
        board.getArray()[0][0] = 'X';
        board.getArray()[2][1] = 'O';
        Board board1 = board.copy();
        assertEquals('X', board1.getArray()[0][0]);
        assertEquals('O', board1.getArray()[2][1]);
    }
}
