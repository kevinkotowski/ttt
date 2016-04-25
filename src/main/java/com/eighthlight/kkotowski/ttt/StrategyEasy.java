package com.eighthlight.kkotowski.ttt;

import java.util.List;
import java.util.Random;

/**
 * Created by kevinkotowski on 4/25/16.
 */
public class StrategyEasy implements Strategy {
    public Integer next(Board board) {
        List<Integer> available;
        Integer position;

        available = board.availableSquares();
        position = available.get(0) + 1;

        return position;
    }

    public Integer random(Board board) {
        List<Integer> available;
        Integer position;
        Random random = new Random();

        available = board.availableSquares();
        int n = random.nextInt( available.size() );
        position = available.get(n) + 1;

        return position;
    }

    public Integer recommend(Board board) {
        return this.random(board);
    };
}
