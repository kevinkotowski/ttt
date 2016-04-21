package com.eigthlight.kkotowski.ttt;

import com.eighthlight.kkotowski.ttt.Game;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Scanner reader = new Scanner(System.in);

    public static void main( String[] args )
    {
        System.out.println( "Java Tic Tac Toe" );

        Game game = new Game();

        while (!game.getState().over()) {
            // show board
            // show options
            System.out.println( game.getTurn() + " turn:" );
            int test = reader.nextInt();
            System.out.println( game.getTurn() + " selected: " + test );

            // check if quit
            game.quit();
            // get player move
            game.move(0);
        }
        System.out.println( "Thanks for playing!" );
    }
}
