package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 4/20/16.
 */
public class StateTest {
    @Test
    public void checkDefaults() throws Exception {
        State state = new State();
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
//        assertEquals( false, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );
    }

    @Test
    public void actionStartQuit() throws Exception {
        State state = new State();
        assertEquals( false, state.active() );

        state.action(Game.Action.START);
        assertEquals( State.Page.GAME, state.page() );
        assertEquals( true, state.active() );
//        assertEquals( false, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );

        state.action(Game.Action.QUIT);
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
//        assertEquals( false, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );
    }

    @Test
    public void actionStartEndgame() throws Exception {
        State state = new State();
        assertEquals( State.Page.MENU, state.page() );
        assertEquals( false, state.active() );
//        assertEquals( false, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );

        state.action(Game.Action.START);
        assertEquals( State.Page.GAME, state.page() );
        assertEquals( true, state.active() );
//        assertEquals( false, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );

        state.action(Game.Action.ENDGAME);
        assertEquals( State.Page.OVER, state.page() );
        assertEquals( false, state.active() );
//        assertEquals( true, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );
    }

    @Test
    public void winner() throws Exception {
        State state = new State();
        state.action(Game.Action.START);
        state.action(Game.Action.ENDGAME);

//        assertEquals( true, state.endgame() );
        assertEquals( Game.Winner.NONE, state.winner() );

        state.setWinner(Game.Winner.PLAYER1);
        assertEquals( Game.Winner.PLAYER1, state.winner() );

        state.setWinner(Game.Winner.PLAYER2);
        assertEquals( Game.Winner.PLAYER2, state.winner() );

        state.setWinner(Game.Winner.TIE);
        assertEquals( Game.Winner.TIE, state.winner() );
    }
}