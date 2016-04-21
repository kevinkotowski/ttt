package com.eighthlight.kkotowski.ttt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/20/16.
 */
public class StateTest {

    @Before
    public void setup() throws Exception {
        System.out.println( "...StateTest before starting" );
    }

    @Test
    public void checkDefaults() throws Exception {
        State state = new State();
        assertEquals( false, state.active() );
        assertEquals( State.Page.MENU, state.page() );
    }

    @Test
    public void actionStartQuit() throws Exception {
        State state = new State();
        assertEquals( false, state.active() );
        state.action(Game.Action.START);
        assertEquals( true, state.active() );
        assertEquals( State.Page.GAME, state.page() );
        state.action(Game.Action.QUIT);
        assertEquals( false, state.active() );
        assertEquals( State.Page.OVER, state.page() );
    }
}