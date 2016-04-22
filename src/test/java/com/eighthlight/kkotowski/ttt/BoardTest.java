package com.eighthlight.kkotowski.ttt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 4/19/16.
 */
public class BoardTest {

    Board board = new Board();

    @Test
    public void getBoard() throws Exception {
        List<Board.Mark> board = this.board.get();
        int length = board.size();
        assertEquals( 9, length );

        for (int x = 0; x < length; x++) {
            assertEquals( Board.Mark.AVAILABLE, board.get(x) );
        }
    }

    @Test(expected = RuntimeException.class)
    public void badPlacePosition() throws Exception {
        this.board.setSquare( 9, Board.Mark.PLAYER1 );
    }

    @Test(expected = RuntimeException.class)
    public void badPlaceMark() throws Exception {
        this.board.setSquare( 0, Board.Mark.AVAILABLE );
    }

    @Test
    public void squares() throws Exception {
        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 0 ) );
        assertTrue( this.board.setSquare( 0, Board.Mark.PLAYER1 ) );
        assertEquals( Board.Mark.PLAYER1, board.getSquare( 0 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 1 ) );
        assertTrue( this.board.setSquare( 1, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 1 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 2 ) );
        assertTrue( this.board.setSquare( 2, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 2 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 3 ) );
        assertTrue( this.board.setSquare( 3, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 3 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 4 ) );
        assertTrue( this.board.setSquare( 4, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 4 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 5 ) );
        assertTrue( this.board.setSquare( 5, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 5 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 6 ) );
        assertTrue( this.board.setSquare( 6, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 6 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 7 ) );
        assertTrue( this.board.setSquare( 7, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 7 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 8 ) );
        assertTrue( this.board.setSquare( 8, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 8 ) );
    }
}