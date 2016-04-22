package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/22/16.
 */
public class UtilsTest {
    @Test
    public void boardFull() throws Exception {
        Board board = new Board();
        assertEquals( false, Utils.boardFull(board) );

        assertEquals( true, board.setSquare(0, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(1, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(2, Board.Mark.PLAYER1) );

        assertEquals( true, board.setSquare(3, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(4, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(5, Board.Mark.PLAYER1) );

        assertEquals( true, board.setSquare(6, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(7, Board.Mark.PLAYER1) );
        assertEquals( true, board.setSquare(8, Board.Mark.PLAYER1) );

        assertEquals( 0, Utils.openPositions(board).size() );
        assertEquals( true, Utils.boardFull(board) );
    }

    @Test
    public void openPostions() throws Exception {
        Board board = new Board();
        assertEquals( 9, Utils.openPositions(board).size() );

        assertEquals( true, board.setSquare(0, Board.Mark.PLAYER1) );
        assertEquals( 8, Utils.openPositions(board).size() );
    }

}