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

    @Before
    public void setup() throws Exception {
        assertTrue( true );
        System.out.println( "...PlayerTest before starting" );
    }

    @Test
    public void getName() throws Exception {
        assertEquals( this.player.getName(), "Joshua" );
    }

    @Test
    public void setName() throws Exception {
        assertEquals( this.player.getName(), "Joshua" );
        this.player.setName("Homer");
        assertEquals( this.player.getName(), "Homer" );

        // put it back the way it was when we found it
        this.player.setName("Joshua");
        assertEquals( this.player.getName(), "Joshua" );
    }

    @Test
    public void getMode() throws Exception {
        assertEquals( this.player.getMode(), Mode.COMPUTER );
    }

    @Test
    public void setMode() throws Exception {
        assertEquals( this.player.getMode(), Mode.COMPUTER );
        this.player.setMode( Mode.HUMAN );
        assertEquals( this.player.getMode(), Mode.HUMAN );

        // put it back the way it was when we found it
        this.player.setMode( Mode.COMPUTER );
        assertEquals( this.player.getMode(), Mode.COMPUTER );
    }

    @Test
    public void getStrategy() throws Exception {
        assertEquals( this.player.getStrategy(), Strategy.HARD );
    }

    @Test
    public void setStrategy() throws Exception {
        assertEquals( this.player.getStrategy(), Strategy.HARD );
        this.player.setStrategy( Strategy.EASY );
        assertEquals( this.player.getStrategy(), Strategy.EASY );

        // put it back the way it was when we found it
        this.player.setStrategy( Strategy.HARD );
        assertEquals( this.player.getStrategy(), Strategy.HARD );
    }

    @Test
    public void getSymbol() throws Exception {
        assertEquals( this.player.getSymbol(), "X" );
    }

    @Test
    public void setSymbol() throws Exception {
        assertEquals( this.player.getSymbol(), "X" );
        this.player.setSymbol("O");
        assertEquals( this.player.getSymbol(), "O" );

        // put it back the way it was when we found it
        this.player.setSymbol("X");
        assertEquals( this.player.getSymbol(), "X" );
    }
}