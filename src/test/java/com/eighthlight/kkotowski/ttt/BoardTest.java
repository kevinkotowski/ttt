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
        assertEquals( this.board.getSize(), 9 );
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
        board.setSquare( 9, Mark.PLAYER1 );
    }

    @Test(expected = RuntimeException.class)
    public void badPlaceMark() throws Exception {
        board.setSquare( 0, Mark.AVAILABLE );
    }

    @Test
    public void setSquare() throws Exception {
        assertTrue( board.setSquare( 0, Mark.PLAYER1 ) );
        assertTrue( board.setSquare( 1, Mark.PLAYER2 ) );
        assertTrue( board.setSquare( 2, Mark.PLAYER1 ) );
        assertTrue( board.setSquare( 3, Mark.PLAYER2 ) );
        assertTrue( board.setSquare( 4, Mark.PLAYER1 ) );
        assertTrue( board.setSquare( 5, Mark.PLAYER2 ) );
        assertTrue( board.setSquare( 6, Mark.PLAYER1 ) );

        int length = this.board.getSize();
        List squares = this.board.get();

        for ( int x = 0; x < length; x++ ) {
            System.out.println( "...BoardTest setSquare: " + x + ", " + squares.get(x) );
        }
    }

    @Test
    public void getSquare() throws Exception {
        assertTrue( board.setSquare( 0, Mark.PLAYER1 ) );


        int length = this.board.getSize();
        List squares = this.board.get();

        for ( int x = 0; x < length; x++ ) {
            System.out.println( "...BoardTest setSquare: " + x + ", " + squares.get(x) );
        }
    }
}