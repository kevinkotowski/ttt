package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Game {
    private Board board = null;
    private List<Player> players = new ArrayList<Player>(2);
    private Turn turn = null;
    private Boolean active = false;

    public enum Action {
        START,
        MOVE,
        ENDGAME,
        QUIT
    }

    public enum Strategy {
        HUMAN,
        EASY,
        MEDIUM,
        HARD
    }

    public enum Turn {
        PLAYER1,
        PLAYER2
    }

    public enum Winner {
        PLAYER1,
        PLAYER2,
        TIE,
        NONE
    }

    public Game() {
        this.players.add( new Player("Homer", "X", Player.Mode.HUMAN,
                Strategy.HUMAN) );
        this.players.add( new Player("Joshua", "O", Player.Mode.COMPUTER,
                Strategy.HARD) );
    }

    public Boolean isActive() { return this.active; };

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Board getBoard() { return this.board; };

    public List<Player> getPlayers() {
        return this.players;
    }

    public Turn getTurn() {
        return this.turn;
    }

    public Player getTurnPlayer() {
        Player response;
        if (this.turn == Turn.PLAYER1) {;
            response = this.players.get(0);
        } else {
            response = this.players.get(1);
        }
        return response;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public String getTurnRecommendation() {
        Player player = this.getTurnPlayer();
        String move;

        if ( player.getMode() == Player.Mode.HUMAN ) {
            throw new RuntimeException("No recommendations for HUMAN players");
        } else {
            move = Integer.toString( player.getStrategy().recommend(this.board) );
        }
        return move;
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
        this.setActive(true);
        this.board = null;
        this.board = new Board();
        this.turn = Turn.PLAYER1;
    }

    public void move(int position) {
        Game.Winner winner;

        if ( (position > -1) && (position < 9) ) {
            switch (this.turn) {
                case PLAYER1:
                    this.board.setSquare(position, Board.Mark.PLAYER1);
                    this.setTurn(Turn.PLAYER2);
                    break;
                case PLAYER2:
                    this.board.setSquare(position, Board.Mark.PLAYER2);
                    this.setTurn(Turn.PLAYER1);
                    break;
                default:
                    throw new RuntimeException("Invalid game.turn state.");
            }
        } else {
            throw new RuntimeException("Invalid move position: " + position);
        }
    }

    public void quit() {
        this.setActive(false);
    }

    public Winner getWinner() {
        Winner winner;
        winner = this.board.getWinner();

        if (winner != Winner.NONE) {
            this.setActive(false);
        }
        return winner;
    }
}
