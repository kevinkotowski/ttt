package com.eigthlight.kkotowski.ttt;

import com.eighthlight.kkotowski.ttt.Board;
import com.eighthlight.kkotowski.ttt.Game;
import com.eighthlight.kkotowski.ttt.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

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
        System.out.println( "Enter your square, or 'q' to quit." );

        Game game = new Game();

        // menu loop
        game.start();

        // game loop
        while (game.getState().active()) {
            showBoard(game);
            handleMove(game);
        }
        System.out.println( "Thanks for playing!" );
    }

    public static void showBoard(Game game) {
        Board board = game.getBoard();
        int length = board.getSize();
        List squares = board.get();

        System.out.println( "+-------------------+" );
        System.out.println( "|                   |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + showSquare(game, 0) + "  " +
                "|  "  + showSquare(game, 1) + "  " +
                "|  "  + showSquare(game, 2) + "   " +
                "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|  ----+-----+----  |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + showSquare(game, 3) + "  " +
                "|  "  + showSquare(game, 4) + "  " +
                "|  "  + showSquare(game, 5) + "   " +
                "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|  ----+-----+----  |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + showSquare(game, 6) + "  " +
                "|  "  + showSquare(game, 7) + "  " +
                "|  "  + showSquare(game, 8) + "   " +
                "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|                   |" );
        System.out.println( "+-------------------+" );
    }

    public static String showSquare(Game game, int position) {
        return game.showSquare(position);
    }

    public static void handleMove(Game game) {
        String gameInput = "";
        gameInput = getMove(game);
        makeMove(game, gameInput);
    }

    private static String getMove(Game game) {
        String response = "";

        System.out.println( game.getTurn() + " move: " );
        response = reader.nextLine();

        if (response.length() > 1) {
            System.out.println( "(only the first character will be used.)\n" );
        }
        response = response.substring( 0,1 );
        return response;
    }

    private static void makeMove(Game game, String gameInput) {
        int position;

        System.out.println( game.getTurn() + " selected: " + gameInput );
        if ( gameInput.equals("q") ) {
            game.quit();
            System.out.println( "Quitting..." );
        } else {
            if ( Pattern.matches( "[1-9]", gameInput ) ) {
                position = Integer.parseInt(gameInput) - 1;
                game.move(position);
            } else {
                System.out.println( "Valid square numbers are 1-9" );
            }
        }

    }
}
