package com.eighthlight.kkotowski.ttt;

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
        this.io = new IO();
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
        while (!this.isExit()) {
            this.handleMenu();

            // game loop
            while (!this.isExit() && this.isActive()) {
                this.handleMove();
                this.handleEndgame();
            }
        }
        this.view.showExit();
    }

    public Boolean isActive() {
        return game.isActive();
    }

    public Boolean isExit() {
        return this.exit;
    }

    public void setExit(Boolean exit) {
        this.exit = exit;
    }

    public void handleWelcome() {
        this.view.showWelcome();
    }

    public void handleMenu() {
        String menuInput;
        this.view.showMenu();
        menuInput = this.getInput();
        if (menuInput.equals("p")) {
            this.game.start();
        } else {
            this.setExit(true);
        }
    }

    public void handleMove() {
        String gameInput;
        this.view.showBoard(this.game.getBoard());

        gameInput = this.getMove();
        if ( !gameInput.matches("[1-9]") ) {
            this.view.setMessage( "Enter a number, 1-9." );
        } else {
            try {
                this.game.move( Integer.parseInt(gameInput) - 1 );
            } catch (RuntimeException error) {
                this.view.setMessage( error.getMessage() );
            };
        }
    }

    public void handleEndgame() {
        Game.Winner winner = this.game.getWinner();
        if (winner != Game.Winner.NONE) {
            this.view.showEndgame(winner);
        }
    }

    private String getMove() {
        Player player = this.game.getTurnPlayer();
        String move = null;

        if (player.getMode() == Player.Mode.HUMAN) {
            move = this.getInput();
            if ( move.equals("q") ) {
                game.setActive(false);
                this.view.showQuit();
            }
        } else {
            move = this.game.getTurnRecommendation();
        }
        this.view.showMove(move);
        return move;
    }

    private String getInput() {
        return this.io.getFirstCharacter();
    }

}
