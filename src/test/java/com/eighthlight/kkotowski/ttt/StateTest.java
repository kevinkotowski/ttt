package com.eighthlight.kkotowski.ttt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/20/16.
 */
public class StateTest {

    State state = new State();

    @Before
    public void setup() throws Exception {
        System.out.println( "...StateTest before starting" );
    }

    @Test
    public void checkDefaults() throws Exception {
        assertEquals( false, this.state.over() );
        assertEquals( State.Page.MENU, this.state.page() );
    }

    @Test
    public void setQuit() throws Exception {
        assertEquals( false, this.state.over() );
        this.state.action(Game.Action.QUIT);
        assertEquals( State.Page.OVER, this.state.page() );
    }
}