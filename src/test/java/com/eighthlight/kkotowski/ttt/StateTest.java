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
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
        assertEquals( false, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );
    }

    @Test
    public void actionStartQuit() throws Exception {
        State state = new State();
        assertEquals( false, state.active() );

        state.action(Game.Action.START);
        assertEquals( State.Page.GAME, state.page() );
        assertEquals( true, state.active() );
        assertEquals( false, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );

        state.action(Game.Action.QUIT);
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
        assertEquals( false, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );
    }

    @Test
    public void actionStartEndgame() throws Exception {
        State state = new State();
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
        assertEquals( false, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );

        state.action(Game.Action.START);
        assertEquals( State.Page.GAME, state.page() );
        assertEquals( true, state.active() );
        assertEquals( false, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );

        state.action(Game.Action.ENDGAME);
        assertEquals( State.Page.OVER, state.page() );
        assertEquals( false, state.active() );
        assertEquals( true, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );
    }

    @Test
    public void winner() throws Exception {
        State state = new State();
        state.action(Game.Action.START);
        state.action(Game.Action.ENDGAME);

        assertEquals( true, state.endgame() );
        assertEquals( State.Winner.NONE, state.winner() );

        state.setWinner(State.Winner.PLAYER1);
        assertEquals( State.Winner.PLAYER1, state.winner() );

        state.setWinner(State.Winner.PLAYER2);
        assertEquals( State.Winner.PLAYER2, state.winner() );

        state.setWinner(State.Winner.TIE);
        assertEquals( State.Winner.TIE, state.winner() );
    }
}