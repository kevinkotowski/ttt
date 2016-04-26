package com.eighthlight.kkotowski.ttt;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
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
        this.board.setSquare( 9, Board.Mark.PLAYER1 );
    }

    @Test
    public void squares() throws Exception {
        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 0 ) );
        this.board.setSquare( 0, Board.Mark.PLAYER1 );
        assertEquals( Board.Mark.PLAYER1, board.getSquare( 0 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 1 ) );
        this.board.setSquare( 1, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 1 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 2 ) );
        this.board.setSquare( 2, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 2 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 3 ) );
        this.board.setSquare( 3, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 3 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 4 ) );
        this.board.setSquare( 4, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 4 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 5 ) );
        this.board.setSquare( 5, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 5 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 6 ) );
        this.board.setSquare( 6, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 6 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 7 ) );
        this.board.setSquare( 7, Board.Mark.PLAYER2 );
        assertEquals( Board.Mark.PLAYER2, board.getSquare( 7 ) );

        assertEquals( Board.Mark.AVAILABLE, this.board.getSquare( 8 ) );
        this.board.setSquare( 8, Board.Mark.PLAYER2 );
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
        List<String[]> combinations = new ArrayList<String[]>(8);

        combinations = Board.winCombinations();
        assertEquals( 8, combinations.size() );

        // horizontal
        assertEquals( true, Arrays.deepEquals(combinations.get(0), "012".split("") ) );
        assertEquals( true, Arrays.deepEquals(combinations.get(1), "345".split("") ) );
        assertEquals( true, Arrays.deepEquals(combinations.get(2), "678".split("") ) );

        // vertical
        assertEquals( true, Arrays.deepEquals(combinations.get(3), "036".split("") ) );
        assertEquals( true, Arrays.deepEquals(combinations.get(4), "147".split("") ) );
        assertEquals( true, Arrays.deepEquals(combinations.get(5), "258".split("") ) );

        // diagonal
        assertEquals( true, Arrays.deepEquals(combinations.get(6), "048".split("") ) );
        assertEquals( true, Arrays.deepEquals(combinations.get(7), "246".split("") ) );

        assertEquals( "2", String.valueOf( combinations.get(7)[0] ) );
        assertEquals( "4", String.valueOf( combinations.get(7)[1] ) );
        assertEquals( "6", String.valueOf( combinations.get(7)[2] ) );
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
    @Test
    public void getWinnerPlayer2() throws Exception {
        Board board = new Board();
        board.setSquare(0, Board.Mark.PLAYER1);
        board.setSquare(6, Board.Mark.PLAYER2);
        board.setSquare(1, Board.Mark.PLAYER1);

        board.setSquare(7, Board.Mark.PLAYER2);
        board.setSquare(2, Board.Mark.PLAYER1);
        assertEquals( Game.Winner.PLAYER1, board.getWinner() );
    }
}