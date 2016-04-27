package com.eighthlight.kkotowski.ttt;

import java.util.List;

/**
 * Created by kevinkotowski on 4/27/16.
 */
public class ViewCLI implements View{
    private IO io;
    private Game game;
    private String message;

    public ViewCLI(IO io, Game game) {
        this.io = io;
        this.game = game;
    }

    public void setMessage(String message) {
        this.message = message;
    };

    public String getMessage() {
        return this.message;
    };

    public void showWelcome() {
        this.io.println( "Java Tic Tac Toe" );
    };

    public void showMenu() {
        this.io.println( "+-------------------+" );
        this.io.println( "| Menu:             |" );
        this.io.println( "|    'p' to play    |" );
        this.io.println( "|    'q' to quit    |" );
        this.io.println( "|                   |" );
        this.io.println( "+-------------------+" );
    };

    public void showOver() {
        this.io.println( "The game is over." );
    };

    public void showExit() {
        this.io.println( "Goodbye." );
    };

    public void showBoard(Board board) {
        int length = board.get().size();
        List squares = board.get();
        Player player;

        this.io.println( "+-------------------+" );
        this.io.println( "|                   |" );
        this.io.println( "|      |     |      |" );
        this.io.println(
                "|   "  + this.getSquare(0) + "  " +
                "|  "  + this.getSquare(1) + "  " +
                "|  "  + this.getSquare(2) + "   " +
                "|"
        );
        this.io.println( "|      |     |      |" );
        this.io.println( "|  ----+-----+----  |" );
        this.io.println( "|      |     |      |" );
        this.io.println(
                "|   "  + this.getSquare(3) + "  " +
                "|  "  + this.getSquare(4) + "  " +
                "|  "  + this.getSquare(5) + "   " +
                "|"
        );
        this.io.println( "|      |     |      |" );
        this.io.println( "|  ----+-----+----  |" );
        this.io.println( "|      |     |      |" );
        this.io.println(
                "|   "  + this.getSquare(6) + "  " +
                "|  "  + this.getSquare(7) + "  " +
                "|  "  + this.getSquare(8) + "   " +
                "|"
        );
        this.io.println( "|      |     |      |" );
        this.io.println( "|                   |" );
        this.io.println( "+-------------------+" );
        if ( this.message != null ) {
            this.io.println( this.message );
            this.message = null;
        }
        if ( game.isActive() ) {
            this.io.println( "Enter your square, or 'q' to quit." );
            player = this.game.getTurnPlayer();
            this.io.println( player.getName() + " (" + player.getSymbol() +
                    ") move: " );
        }
    };

    public String getSquare(int position) {
        return this.game.getSquare(position);
    };
}
