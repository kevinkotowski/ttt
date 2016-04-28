package com.eighthlight.kkotowski.ttt;

import java.util.List;
import java.util.Random;

/**
 * Created by kevinkotowski on 4/25/16.
 */

public interface Strategy {
    public int recommend(Board board);

    default public int random(Board board) {
        List<Integer> available;
        Integer position;
        Random random = new Random();

        available = board.getAvailableSquares();
        int n = random.nextInt( available.size() );
        position = available.get(n) + 1;

        return position;
    }
}
