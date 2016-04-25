package com.eigthlight.kkotowski.ttt;

import com.eighthlight.kkotowski.ttt.Board;
import com.eighthlight.kkotowski.ttt.Game;
import com.eighthlight.kkotowski.ttt.State;

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
    private static String message = null;
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
        if (menuInput.equals("p")) {
            game.start();
        } else {
            exit = true;
        }
    }

    public static void handleMove(Game game) {
        String gameInput = "";
        showBoard(game);
        gameInput = getMove(game);
        try {
            makeMove(game, gameInput);
        } catch (RuntimeException error) {
            message = error.getMessage();
        };

    }

    public static void handleEndgame(Game game) {
        Game.Winner winner = game.getWinner();
        if (winner != Game.Winner.NONE) {
            switch (winner) {
                case PLAYER1:
                    message = "Player 1 wins!";
                    break;
                case PLAYER2:
                    message = "Player 2 wins!";
                    break;
                case TIE:
                    message = "Neither player wins.";
                    break;
            }
            exit = true;
            showBoard(game);
            showOver(game);
        }
    }

    public static void handleExit() {
        showExit();
    }

    public static String getMenu(Game game) {
        return getFirstCharacter();
    }

    private static String getMove(Game game) {
        return getFirstCharacter();
    }

    private static String getFirstCharacter() {
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

        System.out.println(  "Selected: " + gameInput );
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
        System.out.println( "+-------------------+" );
        System.out.println( "| Menu:             |" );
        System.out.println( "|    'p' to play    |" );
        System.out.println( "|    'q' to quit    |" );
        System.out.println( "|                   |" );
        System.out.println( "+-------------------+" );
    }

    public static void showOver(Game game) {
        System.out.println( "Thanks for playing!" );
    }

    public static void showExit() {
        System.out.println( "Goodbye." );
    }

    public static void showBoard(Game game) {
        Board board = game.getBoard();
        int length = board.get().size();
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
        if ( message != null ) {
            System.out.println( message );
            message = null;
        }
        if ( !exit ) {
            System.out.println( "Enter your square, or 'q' to quit." );
            System.out.println( game.getTurnPlayer() + " move: " );
        }
    }

    public static String showSquare(Game game, int position) {
        return game.showSquare(position);
    }

}
