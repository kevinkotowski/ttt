package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static com.eighthlight.kkotowski.ttt.IOTest.ioInput;
import static com.eighthlight.kkotowski.ttt.IOTest.ioOutput;
import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/28/16.
 */
public class ViewCLITest {
    private InputStream in;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Test
    public void showWelcome() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showWelcome();
        assertTrue( ioOutput(this.out).contains("Tic Tac Toe") );
    }

    @Test
    public void showMenu() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showMenu();
        assertTrue( ioOutput(this.out).contains("Menu") );
    }

    @Test
    public void showBoard() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        game.start();
        Board board = game.getBoard();
        ViewCLI view = new ViewCLI( io,game );

        view.showBoard(board);
        assertTrue( ioOutput(this.out).contains("Enter your square") );
    }

    @Test
    public void setAndGetMessage() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        game.start();
        Board board = game.getBoard();
        ViewCLI view = new ViewCLI( io,game );

        view.setMessage("Rumpus is watching");
        view.showBoard(board);
        assertTrue( ioOutput(this.out).contains("Rumpus is watching") );
    }

    @Test
    public void showMove() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showMove("4");
        assertTrue( ioOutput(this.out).contains("Selected: 4") );
    }

    @Test
    public void showQuit() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showQuit();
        assertTrue( ioOutput(this.out).contains("Quitting...") );
    }

    @Test
    public void showEndgameNotOver() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showEndgame(Game.Winner.NONE);
        assertTrue( ioOutput(this.out).contains("NOT over") );
    }

    @Test
    public void showEndgameOver() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        game.start();
        Board board = game.getBoard();
        ViewCLI view = new ViewCLI( io,game );

        view.showEndgame(Game.Winner.TIE);
        assertTrue( ioOutput(this.out).contains("Neither") );
    }
    @Test
    public void showExit() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        ViewCLI view = new ViewCLI( io,game );

        view.showExit();
        assertTrue( ioOutput(this.out).contains("Goodbye.") );
    }

    @Test
    public void getSquare() throws Exception {
        this.in = ioInput( "" );
        IO io = new IO( this.in, this.out );
        Game game = new Game();
        game.start();
        ViewCLI view = new ViewCLI( io,game );

        assertEquals( "9", view.getSquare(8) );
    }

}