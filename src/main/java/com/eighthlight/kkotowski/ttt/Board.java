package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/19/16.
 */
public class Board {
    private int size = 9;
    private List<Mark> squares = new ArrayList<Mark>(size);
    public enum Mark {
        AVAILABLE,
        PLAYER1,
        PLAYER2
    }

    public Board() {
        for (int position = 0; position < this.size; position++) {
            this.squares.add(position, Mark.AVAILABLE);
        }
    }

    public List<Mark> get() {
        return this.squares;
    }

    public Mark getSquare(int position) {
        return this.squares.get(position);
    }

    public void setSquare(int position, Mark mark) {
        if ( this.available(position) ) {
            this.squares.set(position, mark);
        } else {
            throw new RuntimeException("Position is not available.");
        }
    }

    private boolean available(int position) {
        if ( (position < -1) || (position > 8) ) {
            throw new RuntimeException("Must place in position 0-8");
        }
        if ( (this.squares.get(position) != Mark.AVAILABLE) ) {
            return false;
        }
        return true;
    }

    public List<Integer> availableSquares () {
        List<Integer> response = new ArrayList<Integer>(this.size);

        for (int x = 0; x < this.size; x++) {
            if (this.getSquare(x) == Board.Mark.AVAILABLE) {
                response.add(x);
            }
        }
        return response;
    }

    public Board copy() {
        Board copyBoard = new Board();

        for (int x = 0; x < this.size; x++) {
            copyBoard.setSquare(x, this.getSquare(x) );
        }
        return copyBoard;
    }

    public Boolean full() {
        Boolean response = false;
        response = ( 0 == this.availableSquares().size() );
        return response;
    }

    public Game.Winner getWinner() {
        Game.Winner winner = Game.Winner.NONE;
        List<String[]> winCombos = winCombinations();
        int winComboLength = winCombos.size();
        int x;
        String[] winCombo = null;
        Board.Mark mark = null;

        for (x = 0; x < winComboLength; x++) {
            winCombo = winCombos.get(x);
            mark = this.getSquare( Integer.parseInt(winCombo[0]) );
            if ( mark != Board.Mark.AVAILABLE &&
                    mark == this.getSquare( Integer.parseInt(winCombo[1]) ) &&
                    mark == this.getSquare( Integer.parseInt(winCombo[2]) )
                    ) {
                switch (mark) {
                    case PLAYER1:
                        winner = Game.Winner.PLAYER1;
                        break;
                    case PLAYER2:
                        winner = Game.Winner.PLAYER2;
                        break;
                }
            }
        }
        if ( winner == Game.Winner.NONE && this.full() ) {
            winner = Game.Winner.TIE;
        }
        return winner;
    }

    public static List<String[]> winCombinations () {
        List<String[]> response = new ArrayList<String[]>(8);

        // horizontal
        response.add( "012".split("") );
        response.add( "345".split("") );
        response.add( "678".split("") );

        // vertical
        response.add( "036".split("") );
        response.add( "147".split("") );
        response.add( "258".split("") );

        // diagonal
        response.add( "048".split("") );
        response.add( "246".split("") );

        return response;
    }
}
