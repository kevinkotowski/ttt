package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Game {
    private Board board = new Board();
    private List<Player> players = new ArrayList<Player>(2);
    private State state = new State();
    private Turn turn;

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
        PLAY,
        ENGAME,
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
        this.turn = Turn.PLAYER1;
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

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public void move(int position) {
        Board.Mark symbol;
        if (this.turn == Turn.PLAYER1) {
            symbol = Board.Mark.PLAYER1;
        } else {
            symbol = Board.Mark.PLAYER2;
        }
        this.board.setSquare(position, symbol);
    }

    public void quit() {
        this.state.action(Action.QUIT);
    }
//    public void run() {
//        while (!state.quit()) {
//            System.out.println( "...game.run loop" );
//            state.action(Action.QUIT);
//        }
//    }
}
