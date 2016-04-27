package com.eighthlight.kkotowski.ttt;

import java.util.Scanner;

/**
 * Created by kevinkotowski on 4/27/16.
 */
public class IOTerminal implements IO {
    private Scanner input = null;

    public IOTerminal() {
        this.input = new Scanner(System.in);
    }

    public String nextLine() {
        return this.input.nextLine();
    };

    public void println(String buffer) {
        System.out.println( buffer );
    };
}
