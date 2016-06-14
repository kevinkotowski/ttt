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

    public int getSize() {
        return this.size;
    }

    public Board copy() {
        Board copyBoard = new Board();

        for (int x = 0; x < this.size; x++) {
            copyBoard.setSquare(x, this.getSquare(x) );
        }
        return copyBoard;
    }

    public Boolean isEmpty() {
        return this.getSize() == getAvailableSquares().size();
    }

    public Boolean isFull() {
        Boolean response = false;
        response = ( 0 == this.getAvailableSquares().size() );
        return response;
    }

    private boolean isAvailable(int position) {
        if ( (position < -1) || (position > 8) ) {
            throw new RuntimeException("Must place in position 0-8");
        }
        if ( (this.squares.get(position) != Mark.AVAILABLE) ) {
            return false;
        }
        return true;
    }

    public List<Integer> getAvailableSquares () {
        List<Integer> response = new ArrayList<Integer>(this.size);

        for (int x = 0; x < this.size; x++) {
            if (this.getSquare(x) == Board.Mark.AVAILABLE) {
                response.add(x);
            }
        }
        return response;
    }

    public List<Integer> getData() {
        List<Integer> board = new ArrayList<Integer>(this.size);
        for (Mark mark : this.squares) {
            switch (mark) {
                case PLAYER1:
                    board.add(1);
                    break;
                case PLAYER2:
                    board.add(2);
                    break;
                case AVAILABLE:
                    board.add(0);
                    break;
            }
        }
        return board;
    }

    public Mark getSquare(int position) {
        return this.squares.get(position);
    }

    public void setSquare(int position, Mark mark) {
        if ( this.isAvailable(position) ) {
            this.squares.set(position, mark);
        } else {
            throw new RuntimeException("Position is not available.");
        }
    }

    public Game.Winner getWinner() {
        Game.Winner winner = Game.Winner.NONE;
        int[][] winCombos = getWinCombinations();
        int x;
        int[] winCombo = null;
        Board.Mark mark = null;

        for (x = 0; x < winCombos.length; x++) {
            winCombo = winCombos[x];
            mark = this.getSquare( winCombo[0] );
            if ( mark != Board.Mark.AVAILABLE &&
                    mark == this.getSquare( winCombo[1] ) &&
                    mark == this.getSquare( winCombo[2] )
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
        if ( winner == Game.Winner.NONE && this.isFull() ) {
            winner = Game.Winner.TIE;
        }
        return winner;
    }

    public static int[][] getWinCombinations () {
        int[][] response = new int[8][3];

        // horizontal
        response[0] = new int[]{ 0, 1, 2 };
        response[1] = new int[]{ 3, 4, 5 };
        response[2] = new int[]{ 6, 7, 8 };

        // vertical
        response[3] = new int[]{ 0, 3, 6 };
        response[4] = new int[]{ 1, 4, 7 };
        response[5] = new int[]{ 2, 5, 8 };

        // diagonal
        response[6] = new int[]{ 0, 4, 8 };
        response[7] = new int[]{ 2, 4, 6 };

        return response;
    }
}
