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
        for (int position = 0; position < this.getSize(); position++) {
            this.squares.add(position, Mark.AVAILABLE);
        }
    }

    public List<Mark> get() {
        return this.squares;
    }

    public int getSize() {
        return this.size;
    }

    public Mark getSquare(int position) {
        return this.squares.get(position);
    }

    public boolean setSquare(int position, Mark mark) {
        this.validPosition(position);

        if ( (mark != Mark.PLAYER1) && (mark != Mark.PLAYER2) ) {
            throw new RuntimeException("Must place PLAYER1 or PLAYER2.");
        }

        this.squares.set(position, mark);
        return true;
    }

    private boolean validPosition(int position) {
        if ( (position < -1) || (position > 8) ) {
            throw new RuntimeException("Must place in position 0-8");
        }
        return true;
    }
}
