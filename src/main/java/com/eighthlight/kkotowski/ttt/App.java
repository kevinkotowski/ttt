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
    private static Boolean exit = false;

    public static void main( String[] args )
    {
        System.out.println( "Java Tic Tac Toe" );

        Game game = new Game();

        // menu loop
        while (!exit) {
            handleMenu(game);

            // game loop
            while (!exit && game.getState().active()) {
                handleMove(game);
                handleEndgame(game);
            }
        }
        handleExit();
    }

    public static void handleMenu(Game game) {
        String menuInput = "";
        showMenu(game);
        menuInput = getMenu(game);
        if (menuInput.equals("y")) {
            game.start();
        } else {
            exit = true;
        }
    }

    public static void handleMove(Game game) {
        String gameInput = "";
        showBoard(game);
        gameInput = getMove(game);
        makeMove(game, gameInput);
    }

    public static void handleEndgame(Game game) {
        if (game.getEndgame()) {
            showOver(game);
        }
    }

    public static void handleExit() {
        showExit();
    }

    public static String getMenu(Game game) {
        System.out.println( "Enter 'y' to play: " );
        return getLetter();
    }

    private static String getMove(Game game) {
        System.out.println( game.getTurnPlayer() + " move: " );
        return getLetter();
    }

    private static String getLetter() {
        String response = "";

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

    public static void showMenu(Game game) {
        System.out.println( "I am a menu!" );
    }

    public static void showOver(Game game) {
        System.out.println( "Thanks for playing!" );
    }

    public static void showExit() {
        System.out.println( "Goodbye." );
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
        System.out.println( "Enter your square, or 'q' to quit." );
    }

    public static String showSquare(Game game, int position) {
        return game.showSquare(position);
    }

}
