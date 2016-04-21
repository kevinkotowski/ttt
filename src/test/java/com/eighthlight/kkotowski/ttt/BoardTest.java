package com.eighthlight.kkotowski.ttt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 4/19/16.
 */
public class BoardTest {

    Board board = new Board();

    @Before
    public void setUp() throws Exception {
        System.out.println( "...BoardTest before starting" );
    }

    @After
    public void tearDown() throws Exception {
        System.out.println( "...BoardTest ending" );
    }

    @Test
    public void getSize() throws Exception {
        assertEquals( 9, this.board.getSize() );
    }

    @Test
    public void getBoard() throws Exception {
        int length = this.board.getSize();
        List squares = this.board.get();

        for ( int x = 0; x < length; x++ ) {
            System.out.println( "...BoardTest get: " + x + ", " + squares.get(x) );
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
    public void setSquare() throws Exception {
        assertTrue( this.board.setSquare( 0, Board.Mark.PLAYER1 ) );
        assertTrue( this.board.setSquare( 1, Board.Mark.PLAYER2 ) );
        assertTrue( this.board.setSquare( 2, Board.Mark.PLAYER1 ) );
        assertTrue( this.board.setSquare( 3, Board.Mark.PLAYER2 ) );
        assertTrue( this.board.setSquare( 4, Board.Mark.PLAYER1 ) );
        assertTrue( this.board.setSquare( 5, Board.Mark.PLAYER2 ) );
        assertTrue( this.board.setSquare( 6, Board.Mark.PLAYER1 ) );

        int length = this.board.getSize();
        List squares = this.board.get();

        for ( int x = 0; x < length; x++ ) {
            System.out.println( "...BoardTest setSquare: " + x + ", " + squares.get(x) );
        }
    }

    @Test
    public void getSquare() throws Exception {
        assertTrue( this.board.setSquare( 0, Board.Mark.PLAYER1 ) );
        assertEquals( Board.Mark.PLAYER1, board.getSquare( 0 ) );
        assertTrue( this.board.setSquare( 0, Board.Mark.PLAYER2 ) );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 0 ) );
    }
}