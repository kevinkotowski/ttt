package com.eighthlight.kkotowski.ttt;

//import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class PlayerTest {

    Player player = new Player();

    @Test
    public void playerDefaults() throws Exception {
        assertEquals( "Default", this.player.getName() );
        assertEquals( "X", this.player.getSymbol() );
        assertEquals( Player.Mode.COMPUTER, this.player.getMode() );
        assertEquals( Game.Strategy.HARD, this.player.getStrategy() );
    }

    @Test
    public void playerOverloadHuman() throws Exception {
        Player overHuman = new Player("Kevin", "$", Player.Mode.HUMAN,
                Game.Strategy.HUMAN);
        assertEquals( "Kevin", overHuman.getName() );
        assertEquals( "$", overHuman.getSymbol() );
        assertEquals( Player.Mode.HUMAN, overHuman.getMode() );
        assertEquals( Game.Strategy.HUMAN, overHuman.getStrategy() );
    }

    @Test
    public void getName() throws Exception {
        assertEquals( "Default", this.player.getName() );
    }

    @Test
    public void setName() throws Exception {
        assertEquals( "Default", this.player.getName() );
        this.player.setName("Homer");
        assertEquals( "Homer", this.player.getName() );

        // put it back the way it was when we found it
        this.player.setName("Default");
        assertEquals( "Default", this.player.getName() );
    }

    @Test
    public void getMode() throws Exception {
        assertEquals( Player.Mode.COMPUTER, this.player.getMode() );
    }

    @Test
    public void setMode() throws Exception {
        assertEquals( Player.Mode.COMPUTER, this.player.getMode() );
        this.player.setMode( Player.Mode.HUMAN );
        assertEquals( Player.Mode.HUMAN, this.player.getMode() );

        // put it back the way it was when we found it
        this.player.setMode( Player.Mode.COMPUTER );
        assertEquals( Player.Mode.COMPUTER, this.player.getMode() );
    }

    @Test
    public void getStrategy() throws Exception {
        assertEquals( Game.Strategy.HARD, this.player.getStrategy() );
    }

    @Test
    public void setStrategy() throws Exception {
        assertEquals( Game.Strategy.HARD, this.player.getStrategy() );
        this.player.setStrategy( Game.Strategy.EASY );
        assertEquals( Game.Strategy.EASY, this.player.getStrategy() );

        // put it back the way it was when we found it
        this.player.setStrategy( Game.Strategy.HARD );
        assertEquals( Game.Strategy.HARD, this.player.getStrategy() );
    }

    @Test
    public void getSymbol() throws Exception {
        assertEquals( "X", this.player.getSymbol() );
    }

    @Test(expected = RuntimeException.class)
    public void badSymbol() throws Exception {
        this.player.setSymbol( "XX" );
    }

    @Test
    public void setSymbol() throws Exception {
        assertEquals( "X", this.player.getSymbol() );
        this.player.setSymbol("O");
        assertEquals( "O", this.player.getSymbol() );

        // put it back the way it was when we found it
        this.player.setSymbol("X");
        assertEquals( "X", this.player.getSymbol() );
    }
}