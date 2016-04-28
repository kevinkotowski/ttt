package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.io.*;

import static java.nio.charset.StandardCharsets.UTF_8;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    private InputStream in;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    private InputStream ioInput(String input) {
        return new ByteArrayInputStream( input.getBytes(UTF_8) );
    }

    private String ioOutput(ByteArrayOutputStream output) {
        try{
            return output.toString("UTF8");
        }catch(IOException ioe){
            return String.valueOf(ioe);
        }
    }

    @Test
    public void runAppAndQuit() throws Exception {
        this.in = ioInput( "q\n" );
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Tic Tac Toe") );
        assertTrue( ioOutput(this.out).contains("Goodbye") );
    }

    @Test
    public void quitFromActiveGame() throws Exception {
        this.in = ioInput("p\nq\nq\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Enter your square") );
        assertFalse( app.isActive() );
        assertTrue( app.isExit() );
    }

    @Test
    public void handleWelcome() throws Exception {
        this.in = ioInput("");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.handleWelcome();
        assertTrue( ioOutput(this.out).contains("Tic Tac Toe") );
    }

    @Test
    public void showMenu() throws Exception {
        this.in = ioInput("\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.handleMenu();
        assertTrue( ioOutput(this.out).contains("Menu") );
        assertFalse( app.isActive() );
    }

    @Test
    public void playFromMenu() throws Exception {
        this.in = ioInput("p\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.handleMenu();
        assertTrue( app.isActive() );
    }

    @Test
    public void playMove() throws Exception {
        this.in = ioInput("p\n1\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Selected: 1") );
    }

    @Test
    public void playInvalidMoveLetter() throws Exception {
        this.in = ioInput("p\nw\nq\nq\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Selected: w") );
        assertTrue( ioOutput(this.out).contains("Enter a number") );
    }

    @Test
    public void playInvalidMoveSymbol() throws Exception {
        this.in = ioInput("p\n&\nq\nq\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Selected: &") );
        assertTrue( ioOutput(this.out).contains("Enter a number") );
    }

    @Test
    public void playInvalidMoveNoCharacter() throws Exception {
        this.in = ioInput("p\n\nq\nq\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Enter a number") );
    }

    @Test
    public void handleEndgame() throws Exception {
        this.in = ioInput("p\n1\n9\n8\n3\n4\nq\n");
        IO io = new IO(this.in, this.out);

        App app = new App(io);
        app.run();
        assertTrue( ioOutput(this.out).contains("Neither player wins") );
        assertTrue( ioOutput(this.out).contains("game is over") );
    }
}

