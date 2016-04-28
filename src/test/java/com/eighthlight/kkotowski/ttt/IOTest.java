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

    }

    @Test
    public void nextLine() throws Exception {

    }

    @Test
    public void println() throws Exception {

    }

}