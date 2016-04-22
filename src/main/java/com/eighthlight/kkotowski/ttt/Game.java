package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Game {
    private Board board = null;
    private List<Player> players = new ArrayList<Player>(2);
    private State state = new State();
    private Turn turn = null;

    public enum Strategy {
        EASY,
        MEDIUM,
        HARD,
        HUMAN
    }

    public enum Action {
        MENU,
        EDIT,
        SWAP,
        START,
        MOVE,
        ENDGAME,
        QUIT
    }

    public enum Turn {
        PLAYER1,
        PLAYER2
    }

    public Game() {
        this.players.add( new Player("Homer", "X", Player.Mode.HUMAN,
                Game.Strategy.HUMAN) );
        this.players.add( new Player("Joshua", "O", Player.Mode.COMPUTER,
                Game.Strategy.HARD) );
    }

    public Board getBoard() { return this.board; };

    public List<Player> getPlayers() {
        return this.players;
    }

    public State getState() {
        return this.state;
    }

    public Turn getTurn() {
        return this.turn;
    }

    public String getTurnPlayer() {
        String response = "";
        if (this.turn == Turn.PLAYER1) {;
            response = this.players.get(0).getName() + " (" +
                    this.players.get(0).getSymbol() + ")";
        } else {
            response = this.players.get(1).getName() + " (" +
                    this.players.get(1).getSymbol() + ")";
        }
        return response;
    }


    public Boolean getEndgame () {
        // TODO: if winning combination

        if ( !this.state.endgame() && Utils.boardFull(this.board) ) {
            this.state.action(Action.ENDGAME);

        }
        return this.state.endgame();
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public String showSquare(int position) {
        String response = "";
        switch ( this.board.getSquare(position) ) {
            case AVAILABLE:
                response = Integer.toString(position + 1);
                break;
            case PLAYER1:
                response = this.players.get(0).getSymbol();
                break;
            case PLAYER2:
                response = this.players.get(1).getSymbol();
                break;
        }
        return response;
    }

    public void start() {
        this.state.action(Action.START);
        this.board = null;
        this.board = new Board();
        this.turn = Turn.PLAYER1;
    }

    public Boolean move(int position) {
        Boolean response = false;
        if ( (position > -1) && (position < 9) ) {
            switch (this.turn) {
                case PLAYER1:
                    response = this.board.setSquare(position, Board.Mark.PLAYER1);
                    this.setTurn(Turn.PLAYER2);
                    break;
                case PLAYER2:
                    response = this.board.setSquare(position, Board.Mark.PLAYER2);
                    this.setTurn(Turn.PLAYER1);
                    break;
                default:
                    throw new RuntimeException("Invalid game.turn state.");
            }
        } else {
            throw new RuntimeException("Invalid move position: " + position);
        }
        return response;
    }

    public void quit() {
        this.state.action(Action.QUIT);
    }
}
