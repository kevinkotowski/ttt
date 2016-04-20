package com.eighthlight.kkotowski.ttt;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class GameTest extends TestCase {
    public void testTest() throws Exception {
        Hello hello = new Hello();
        assertEquals(hello.getName(), "");
    }

    public void testGameDefaults() throws Exception {
        Game game = new Game();
        List<Player> players = new ArrayList();

        assertEquals( game.getBoard(), "ttt" );

        players = game.getPlayers();
        for(Player player : players) {
            System.out.println( "...GameTest.defaults name: " + player.getName() );
            System.out.println( "...GameTest.defaults mode: " + player.getMode() );
        }

//        assertTrue( players.get(0).contains("Player") );
//        assertTrue( players.get(1).contains("Player") );

    }
}