package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/28/16.
 */
public class IOTest {
    private InputStream in;
    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    public static InputStream ioInput(String input) {
        return new ByteArrayInputStream( input.getBytes(UTF_8) );
    }

    public static String ioOutput(ByteArrayOutputStream output) {
        try{
            return output.toString("UTF8");
        } catch (IOException ioe) {
            return String.valueOf(ioe);
        }
    }

    @Test
    public void getFirstCharacter() throws Exception {
        this.in = ioInput( "4" );
        IO io = new IO( this.in, this.out );

        assertEquals( "4", io.getFirstCharacter() );
    }

    @Test
    public void getFirstCharacterGetWarning() throws Exception {
        this.in = ioInput( "12" );
        IO io = new IO( this.in, this.out );

        assertEquals( "1", io.getFirstCharacter() );
        assertTrue( ioOutput(this.out).contains("only the first") );
    }

    @Test
    public void println() throws Exception {
        this.in = ioInput("");
        IO io = new IO( this.in, this.out );

        io.println("Rumpus is scary");

        assertTrue( ioOutput(this.out).contains("Rumpus is scary") );
    }

}