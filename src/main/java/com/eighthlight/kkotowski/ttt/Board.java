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
            if ((mark != Mark.PLAYER1) && (mark != Mark.PLAYER2)) {
                throw new RuntimeException("Must place PLAYER1 or PLAYER2.");
            }
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
        Integer size = this.squares.size();
        List<Integer> response = new ArrayList<Integer>(size);

        for (int x = 0; x < size; x++) {
            if (this.getSquare(x) == Board.Mark.AVAILABLE) {
                response.add(x);
            }
        }
        return response;
    }

    public Boolean full () {
        Boolean response = false;
        response = ( 0 == this.availableSquares().size() );
        return response;
    }
}
