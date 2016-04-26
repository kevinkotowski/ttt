package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 4/19/16.
 */
public class BoardTest {
    @Test
    public void getBoard() throws Exception {
        Board board = new Board();
        List<Board.Mark> boardMarks = board.get();
        int length = boardMarks.size();
        assertEquals( 9, length );

        for (int x = 0; x < length; x++) {
            assertEquals( Board.Mark.AVAILABLE, boardMarks.get(x) );
        }
    }

    @Test
    public void copy() throws Exception {
        Board origBoard = new Board();
        Board copyBoard;

        origBoard.setSquare( 0, Board.Mark.PLAYER1 );
        origBoard.setSquare( 1, Board.Mark.PLAYER1 );
        origBoard.setSquare( 8, Board.Mark.PLAYER1 );

        origBoard.setSquare( 6, Board.Mark.PLAYER2 );
        origBoard.setSquare( 7, Board.Mark.PLAYER2 );
        origBoard.setSquare( 2, Board.Mark.PLAYER2 );

        copyBoard = origBoard.copy();
        int length = origBoard.get().size();

        for (int x = 0; x < length; x++) {
            assertEquals( origBoard.getSquare(x), copyBoard.getSquare(x) );
//            System.out.println("...BoardTest.copy origBoard(" + x + "): " + origBoard.getSquare(x) );
//            System.out.println("...BoardTest.copy copyBoard(" + x + "): " + copyBoard.getSquare(x) );
        }
    }

    @Test(expected = RuntimeException.class)
    public void badPlacePosition() throws Exception {
        Board board = new Board();
        board.setSquare( 9, Board.Mark.PLAYER1 );
    }

    @Test
    public void squares() throws Exception {
        Board board = new Board();
        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 0 ) );
        board.setSquare( 0, Board.Mark.PLAYER1 );
        assertEquals( Board.Mark.PLAYER1, board.getSquare( 0 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 1 ) );
        board.setSquare( 1, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 1 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 2 ) );
        board.setSquare( 2, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 2 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 3 ) );
        board.setSquare( 3, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 3 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 4 ) );
        board.setSquare( 4, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 4 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 5 ) );
        board.setSquare( 5, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 5 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 6 ) );
        board.setSquare( 6, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 6 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 7 ) );
        board.setSquare( 7, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 7 ) );

        assertEquals( Board.Mark.AVAILABLE, board.getSquare( 8 ) );
        board.setSquare( 8, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 8 ) );
    }

    @Test
    public void availableSquares() throws Exception {
        Board board = new Board();
        assertEquals( 9, board.availableSquares().size() );

        board.setSquare(0, Board.Mark.PLAYER1);
        assertEquals( 8, board.availableSquares().size() );
    }

    @Test
    public void full() throws Exception {
        Board board = new Board();
        assertEquals( false, board.full() );

        board.setSquare(0, Board.Mark.PLAYER1);
        board.setSquare(1, Board.Mark.PLAYER1);
        board.setSquare(2, Board.Mark.PLAYER1);

        board.setSquare(3, Board.Mark.PLAYER1);
        board.setSquare(4, Board.Mark.PLAYER1);
        board.setSquare(5, Board.Mark.PLAYER1);

        board.setSquare(6, Board.Mark.PLAYER1);
        board.setSquare(7, Board.Mark.PLAYER1);
        board.setSquare(8, Board.Mark.PLAYER1);

        assertEquals( 0, board.availableSquares().size() );
        assertEquals( true, board.full() );
    }

    @Test
    public void winCombinations() throws Exception {
        Game game = new Game();
        int[][] combinations = new int[8][3];

        combinations = Board.winCombinations();
        assertEquals( 8, combinations.length );

        // horizontal
        assertArrayEquals( combinations[0], new int[]{ 0, 1, 2 } );
        assertArrayEquals( combinations[1], new int[]{ 3, 4, 5 } );
        assertArrayEquals( combinations[2], new int[]{ 6, 7, 8 } );

        // vertical
        assertArrayEquals( combinations[3], new int[]{ 0, 3, 6 } );
        assertArrayEquals( combinations[4], new int[]{ 1, 4, 7 } );
        assertArrayEquals( combinations[5], new int[]{ 2, 5, 8 } );

        // diagonal
        assertArrayEquals( combinations[6], new int[]{ 0, 4, 8 } );
        assertArrayEquals( combinations[7], new int[]{ 2, 4, 6 } );

        assertEquals( "2", String.valueOf( combinations[7][0] ) );
        assertEquals( "4", String.valueOf( combinations[7][1] ) );
        assertEquals( "6", String.valueOf( combinations[7][2] ) );
    }

    @Test
    public void getWinnerAvailable() throws Exception {
        Board board = new Board();
        assertEquals( Game.Winner.NONE, board.getWinner() );
    }

    @Test
    public void getWinnerTie() throws Exception {
        Board board = new Board();
        board.setSquare(0, Board.Mark.PLAYER1);
        board.setSquare(4, Board.Mark.PLAYER2);
        board.setSquare(2, Board.Mark.PLAYER1);
        assertEquals( Game.Winner.NONE, board.getWinner() );

        board.setSquare(1, Board.Mark.PLAYER2);
        board.setSquare(7, Board.Mark.PLAYER1);
        board.setSquare(3, Board.Mark.PLAYER2);
        assertEquals( Game.Winner.NONE, board.getWinner() );

        board.setSquare(5, Board.Mark.PLAYER1);
        board.setSquare(8, Board.Mark.PLAYER2);
        board.setSquare(6, Board.Mark.PLAYER1);
        assertEquals( Game.Winner.TIE, board.getWinner() );
    }

    @Test
    public void getWinnerPlayer1() throws Exception {
        Board board = new Board();
        board.setSquare(0, Board.Mark.PLAYER1);
        board.setSquare(1, Board.Mark.PLAYER2);
        board.setSquare(2, Board.Mark.PLAYER1);

        board.setSquare(3, Board.Mark.PLAYER2);
        board.setSquare(4, Board.Mark.PLAYER1);
        board.setSquare(5, Board.Mark.PLAYER2);

        board.setSquare(6, Board.Mark.PLAYER1);
        assertEquals( Game.Winner.PLAYER1, board.getWinner() );
    }
}