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
        START,
        MOVE,
        ENDGAME,
        QUIT
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

//    public Boolean getEndgame () {
//        if ( this.state.winner() != Winner.NONE ) {
//            Game.Winner winner;
//            winner = this.checkWinner();
//        }

//        if ( !this.state.winner() && this.board.full() ) {
//            this.state.action(Action.ENDGAME);

//        }
//        return this.state.endgame();
//    }

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
            winner = this.getWinner();
            if (winner != Winner.NONE) {
                this.state.setWinner(winner);
            } else if ( this.board.full() ) {
                this.state.setWinner(Winner.TIE);
            }
        } else {
            throw new RuntimeException("Invalid move position: " + position);
        }
    }

    public void quit() {
        this.state.action(Action.QUIT);
    }

    public Winner getWinner() {
        Winner winner = Winner.NONE;
        List<String[]> winCombos = winCombinations();
        int winComboLength = winCombos.size();
        int x;
        String[] winCombo = null;
        Board.Mark mark = null;

        for (x = 0; x < winComboLength; x++) {
            winCombo = winCombos.get(x);
            mark = this.board.getSquare( Integer.parseInt(winCombo[0]) );
            if ( mark != Board.Mark.AVAILABLE &&
                 mark == this.board.getSquare( Integer.parseInt(winCombo[1]) ) &&
                 mark == this.board.getSquare( Integer.parseInt(winCombo[2]) )
                ) {
                this.state.action(Action.ENDGAME);
                if ( mark == Board.Mark.PLAYER1 ) {
                    winner = Winner.PLAYER1;
                    this.state.setWinner(winner);
                } else {
                    winner = Winner.PLAYER2;
                    this.state.setWinner(winner);
                }
            }
        }

        return winner;
    }

    public static List<String[]> winCombinations () {
        List<String[]> response = new ArrayList<String[]>(8);

        // horizontal
        response.add( "012".split("") );
        response.add( "345".split("") );
        response.add( "678".split("") );

//        // vertical
        response.add( "036".split("") );
        response.add( "147".split("") );
        response.add( "258".split("") );

//        // diagonal
        response.add( "048".split("") );
        response.add( "246".split("") );

        return response;
    }
}
