package com.eighthlight.kkotowski.ttt;

import java.util.List;
import java.util.Random;

/**
 * Created by kevinkotowski on 4/25/16.
 */
public class StrategyHard extends StrategyEasy {
    public Integer recommend(Board board) {
        Integer position;
        if (board.availableSquares().size() == 9) {
            position = this.random(board);
        } else {
            position = this.unbeatable(board, 0, null);
        }
        return position;
    }

    private Integer unbeatable(Board board, int moves, Board.Mark maxPlayer) {
        Integer position = this.next(board);

        return position;
    }
}
