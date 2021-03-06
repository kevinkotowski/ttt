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

    public enum Strategy {
        HUMAN,
        EASY,
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

    public Boolean isActive() {
        Winner winner = this.getWinner();
        if (winner != Winner.NONE) {
            this.setActive(false);
        }
        return this.active;
    };

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Board getBoard() { return this.board; };

    public String getBoardData() {
        String values = "";
        List<Integer> data = this.board.getData();
        for (Integer value : data) {
            values += value.toString() + ",";
        }
        values = values.substring(0, values.length()-1);
        return values;
    }

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

    public String getTurnPlayerName() {
        String name;
        if (this.turn == null) {
            name = "NONE";
        } else {
            if (this.turn == Turn.PLAYER1) {
                name = this.players.get(0).getName();
            } else {
                name = this.players.get(1).getName();
            }
        }
        return name;
    }

    public void setTurn(Turn turn) {
        this.turn = turn;
    }

    public String getTurnRecommendation() {
        Player player = this.getTurnPlayer();
        String move;

        if ( player.getMode() == Player.Mode.HUMAN ) {
            move = "";
        } else {
            move = Integer.toString( player.getStrategy().recommend(this.board) );
        }
        return move;
    }

    public String getSquare(int position) {
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
        if (this.board != null) {
            winner = this.board.getWinner();
        } else {
            winner = Winner.NONE;
        }
        return winner;
    }

    public String getWinnerPlayerName() {
        String name;
        switch (this.getWinner()) {
            case NONE:
                name = "No winner, yet.";
                break;
            case PLAYER1:
                name = this.players.get(0).getName();
                break;
            case PLAYER2:
                name = this.players.get(1).getName();
                break;
            default:
                name = "Draw, no winner!";
        }
        return name;
    }

}
