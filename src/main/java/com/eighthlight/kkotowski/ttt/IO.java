package com.eighthlight.kkotowski.ttt;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by kevinkotowski on 4/27/16.
 */
public class IO {
    private Scanner input = null;
    private PrintStream output = null;

    public IO() {
        this.input = new Scanner(System.in);
        this.output = new PrintStream(System.out);
    }

    public IO(InputStream in, ByteArrayOutputStream out) {
        this.input = new Scanner(in);
        this.output = new PrintStream(out);
    }

    public String getFirstCharacter() {
        String response = null;

        response = this.input.nextLine();

        if (response.length() > 1) {
            this.output.println( "(only the first character will be used.)\n" );
            response = response.substring(0, 1);
        }
        return response;
    }

    public void println(String buffer) {
        this.output.println( buffer );
    };
}