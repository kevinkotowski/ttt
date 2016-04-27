package com.eigthlight.kkotowski.ttt;

import com.eighthlight.kkotowski.ttt.*;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App
{
    private Game game = null;
    private Boolean exit = false;

    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public App() {
        this.game = new Game();
        this.input = new Scanner(System.in);
    }

    public void run()
    {
        this.handleWelcome();

        // menu loop
        while (!this.exit) {
            this.handleMenu();

            // game loop
            while (!this.exit && game.isActive()) {
                this.handleMove();
                this.handleEndgame();
            }
        }
        this.handleExit();
    }

    public void handleWelcome() {
        this.showWelcome();
    }

    public void handleMenu() {
        String menuInput;
        this.showMenu();
        menuInput = this.getMenu();
        if (menuInput.equals("p")) {
            this.game.start();
        } else {
            this.exit = true;
        }
    }

    public void handleMove() {
        String gameInput;
        this.showBoard();

        gameInput = this.getMove();
        try {
            this.makeMove(gameInput);
        } catch (RuntimeException error) {
            this.message = error.getMessage();
        };

    }

    public void handleEndgame() {
        Game.Winner winner = this.game.getWinner();
        if (winner != Game.Winner.NONE) {
            switch (winner) {
                case PLAYER1:
                    this.message = "Player 1, " +
                            this.game.getPlayers().get(0).getName() + ", wins!";
                    break;
                case PLAYER2:
                    this.message = "Player 2, " +
                            this.game.getPlayers().get(1).getName() + ", wins!";
                    break;
                case TIE:
                    this.message = "Neither player wins.";
                    break;
            }
            this.showBoard();
            this.showOver();
        }
    }

    public void handleExit() {
        this.showExit();
    }

    private String getMenu() {
        return this.getFirstCharacter();
    }

    private String getMove() {
        Player player = this.game.getTurnPlayer();
        String move = null;

        if (player.getMode() == Player.Mode.HUMAN) {
            move = this.getFirstCharacter();
        } else {
            move = this.game.getTurnRecommendation();
        }
        return move;
    }

    private String getFirstCharacter() {
        String response;

        response = this.input.nextLine();

        if (response.length() > 1) {
            System.out.println( "(only the first character will be used.)\n" );
        }
        response = response.substring( 0,1 );
        return response;
    }

    public void makeMove(String gameInput) {
        int position;

        System.out.println( "Selected: " + gameInput );
        if ( gameInput.equals("q") ) {
            this.exit = true;

            System.out.println( "Quitting..." );
        } else {
            if ( Pattern.matches( "[1-9]", gameInput ) ) {
                position = Integer.parseInt(gameInput) - 1;
                this.game.move(position);
            } else {
                System.out.println( "Valid square numbers are 1-9" );
            }
        }
    }

    // UI starts here
    private Scanner input = null;
    private String message = null;

    public void showWelcome() {
        System.out.println( "Java Tic Tac Toe" );
    }

    public void showMenu() {
        System.out.println( "+-------------------+" );
        System.out.println( "| Menu:             |" );
        System.out.println( "|    'p' to play    |" );
        System.out.println( "|    'q' to quit    |" );
        System.out.println( "|                   |" );
        System.out.println( "+-------------------+" );
    }

    public void showOver() {
        System.out.println( "The game is over." );
    }

    public void showExit() {
        System.out.println( "Goodbye." );
    }

    public void showBoard() {
        Board board = this.game.getBoard();
        int length = board.get().size();
        List squares = board.get();
        Player player;

        System.out.println( "+-------------------+" );
        System.out.println( "|                   |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + this.showSquare(0) + "  " +
                        "|  "  + this.showSquare(1) + "  " +
                        "|  "  + this.showSquare(2) + "   " +
                        "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|  ----+-----+----  |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + this.showSquare(3) + "  " +
                        "|  "  + this.showSquare(4) + "  " +
                        "|  "  + this.showSquare(5) + "   " +
                        "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|  ----+-----+----  |" );
        System.out.println( "|      |     |      |" );
        System.out.println(
                "|   "  + this.showSquare(6) + "  " +
                        "|  "  + this.showSquare(7) + "  " +
                        "|  "  + this.showSquare(8) + "   " +
                        "|"
        );
        System.out.println( "|      |     |      |" );
        System.out.println( "|                   |" );
        System.out.println( "+-------------------+" );
        if ( this.message != null ) {
            System.out.println( message );
            this.message = null;
        }
        if ( game.isActive() ) {
            System.out.println( "Enter your square, or 'q' to quit." );
            player = this.game.getTurnPlayer();
            System.out.println( player.getName() + " (" + player.getSymbol() +
                    ") move: " );
        }
    }

    public String showSquare(int position) {
        return this.game.showSquare(position);
    }
}
