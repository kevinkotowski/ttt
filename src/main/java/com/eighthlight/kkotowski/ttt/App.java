package com.eigthlight.kkotowski.ttt;

import com.eighthlight.kkotowski.ttt.*;

import java.util.regex.Pattern;

public class App
{
    private IO io = null;
    private Game game = null;
    private View view = null;
    private Boolean exit = false;

    public static void main( String[] args ) {
        App app = new App();
        app.run();
    }

    public App() {
        this.io = new IOTerminal();
        this.game = new Game();
        this.view = new ViewCLI( io, this.game );
    }

    public App(IO io) {
        this.io = io;
        this.game = new Game();
        this.view = new ViewCLI( io, this.game );
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
        this.view.showWelcome();
    }

    public void handleMenu() {
        String menuInput;
        this.view.showMenu();
        menuInput = this.getMenu();
        if (menuInput.equals("p")) {
            this.game.start();
        } else {
            this.exit = true;
        }
    }

    public void handleMove() {
        String gameInput;
        this.view.showBoard(this.game.getBoard());

        gameInput = this.getMove();
        try {
            this.makeMove(gameInput);
        } catch (RuntimeException error) {
            this.view.setMessage( error.getMessage() );
        };

    }

    public void handleEndgame() {
        Game.Winner winner = this.game.getWinner();
        if (winner != Game.Winner.NONE) {
            switch (winner) {
                case PLAYER1:
                    this.view.setMessage ("Player 1, " +
                           this.game.getPlayers().get(0).getName() + ", wins!");
                    break;
                case PLAYER2:
                    this.view.setMessage("Player 2, " +
                           this.game.getPlayers().get(1).getName() + ", wins!");
                    break;
                case TIE:
                    this.view.setMessage("Neither player wins.");
                    break;
            }
            this.view.showBoard(this.game.getBoard());
            this.view.showOver();
        }
    }

    public void handleExit() {
        this.view.showExit();
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

        response = this.io.nextLine();

        if (response.length() > 1) {
            this.io.println( "(only the first character will be used.)\n" );
        }
        response = response.substring( 0,1 );
        return response;
    }

    public void makeMove(String gameInput) {
        int position;

        this.io.println( "Selected: " + gameInput );
        if ( gameInput.equals("q") ) {
            game.setActive(false);

            this.io.println( "Quitting..." );
        } else {
            if ( Pattern.matches( "[1-9]", gameInput ) ) {
                position = Integer.parseInt(gameInput) - 1;
                this.game.move(position);
            } else {
                this.io.println( "Valid square numbers are 1-9" );
            }
        }
    }
}
