package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class PlayerTest {

    @Test
    public void playerDefaults() throws Exception {
        Player player = new Player();
        assertEquals( "Default", player.getName() );
        assertEquals( "X", player.getSymbol() );
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );
    }

    @Test
    public void playerOverloadHuman() throws Exception {
        Player overHuman = new Player("Kevin", "$", Player.Mode.HUMAN,
                Game.Strategy.HUMAN);
        assertEquals( "Kevin", overHuman.getName() );
        assertEquals( "$", overHuman.getSymbol() );
        assertEquals( Player.Mode.HUMAN, overHuman.getMode() );
        assertEquals( null, overHuman.getStrategy() );
    }

    @Test
    public void getName() throws Exception {
        Player player = new Player();
        assertEquals( "Default", player.getName() );
    }

    @Test
    public void setName() throws Exception {
        Player player = new Player();
        assertEquals( "Default", player.getName() );
        player.setName("Homer");
        assertEquals( "Homer", player.getName() );

        // put it back the way it was when we found it
        player.setName("Default");
        assertEquals( "Default", player.getName() );
    }

    @Test
    public void getMode() throws Exception {
        Player player = new Player();
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
    }

    @Test
    public void setMode() throws Exception {
        Player player = new Player();
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        player.setMode( Player.Mode.HUMAN );
        assertEquals( Player.Mode.HUMAN, player.getMode() );

        // put it back the way it was when we found it
        player.setMode( Player.Mode.COMPUTER );
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
    }

    @Test
    public void getStrategy() throws Exception {
        Player player = new Player();
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );
    }

    @Test
    public void setStrategy() throws Exception {
        Player player = new Player();
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );
        player.setStrategy( Game.Strategy.EASY );
        assertEquals( StrategyEasy.class, player.getStrategy().getClass() );

        // put it back the way it was when we found it
        player.setStrategy( Game.Strategy.HARD );
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );
    }

    @Test
    public void getSymbol() throws Exception {
        Player player = new Player();
        assertEquals( "X", player.getSymbol() );
    }

    @Test(expected = RuntimeException.class)
    public void badSymbol() throws Exception {
        Player player = new Player();
        player.setSymbol( "XX" );
    }

    @Test
    public void setSymbol() throws Exception {
        Player player = new Player();
        assertEquals( "X", player.getSymbol() );
        player.setSymbol("O");
        assertEquals( "O", player.getSymbol() );

        // put it back the way it was when we found it
        player.setSymbol("X");
        assertEquals( "X", player.getSymbol() );
    }
}