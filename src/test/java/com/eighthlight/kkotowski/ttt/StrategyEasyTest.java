package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/28/16.
 */
public class StrategyEasyTest {
    @Test
    public void recommendRandomSquare() throws Exception {
        Board board = new Board();
        Strategy strategyEasy = new StrategyEasy();
        String recommendation = Integer.toString(strategyEasy.recommend(board));

        assertTrue( recommendation.matches("[1-9]") );
    }
}