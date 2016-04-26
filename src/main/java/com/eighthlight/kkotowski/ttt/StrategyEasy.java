package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/25/16.
 */
public class StrategyEasy implements Strategy {
    public int recommend(Board board) {
        return this.random(board);
    };
}
