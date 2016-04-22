package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class GameTest {
    @Test
    public void testTest() throws Exception {
        assertTrue( true );
    }

    @Test
    public void gameDefaults() throws Exception {
        Game game = new Game();
        List<Player> players = new ArrayList();

        players = game.getPlayers();

        for(Player player : players) {
            System.out.println( "...GameTest.defaults name: " + player.getSymbol() +
                    ", " + player.getName() +
                    ", " + player.getMode()
            );
        }

        Player player = new Player();
        player = players.get(0);
        assertEquals( "Homer", player.getName() );
        assertEquals( "X", player.getSymbol() );
        assertEquals( Player.Mode.HUMAN, player.getMode() );
        assertEquals( Game.Strategy.HUMAN, player.getStrategy() );

        player = players.get(1);
        assertEquals( "Joshua", player.getName() );
        assertEquals( "O", player.getSymbol() );
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        assertEquals( Game.Strategy.HARD, player.getStrategy() );

        assertEquals( null, game.getTurn() );
    }

    @Test
    public void gameConfigure() throws Exception {
        Game game = new Game();
        List<Player> players = new ArrayList();

        players = game.getPlayers();

        players.get(1).setName("Brian");
        players.get(1).setMode(Player.Mode.HUMAN);

        assertEquals( "Homer", players.get(0).getName() );
        assertEquals( Player.Mode.HUMAN, players.get(0).getMode() );
        assertEquals( Game.Strategy.HUMAN, players.get(0).getStrategy() );

        assertEquals( "Brian", players.get(1).getName() );
        assertEquals( Player.Mode.HUMAN, players.get(1).getMode() );
        assertEquals( Game.Strategy.HUMAN, players.get(1).getStrategy() );
    }

    @Test
    public void changePlayer() throws Exception {
        Game game = new Game();
        game.start();

        assertEquals( Game.Turn.PLAYER1, game.getTurn() );
        game.setTurn(Game.Turn.PLAYER2);
        assertEquals( Game.Turn.PLAYER2, game.getTurn() );
    }

    @Test
    public void start() throws Exception {
        Game game = new Game();
        assertEquals( false, game.getState().active() );
        game.start();
        assertEquals( true, game.getState().active() );

        Board board = game.getBoard();
        int boardSize = board.get().size();
        assertEquals( 9,  boardSize);

        for (int x = 0; x < boardSize; x++) {
            assertEquals( Board.Mark.AVAILABLE, board.getSquare(x) );
        }

        assertEquals( Game.Turn.PLAYER1, game.getTurn() );
    }

    @Test(expected = RuntimeException.class)
    public void moveInvalid() throws Exception {
        Game game = new Game();
        assertEquals( false, game.move(9) );
    }

    @Test
        public void move() throws Exception {
        Game game = new Game();
        assertEquals( false, game.getState().active() );
        game.start();
        assertEquals( true, game.getState().active() );

        assertEquals( "1", game.showSquare(0) );
        assertEquals( true, game.move(0) );
        assertEquals( "X", game.showSquare(0) );

        assertEquals( "2", game.showSquare(1) );
        assertEquals( true, game.move(1) );
        assertEquals( "O", game.showSquare(1) );
    }

    @Test
    public void quit() throws Exception {
        Game game = new Game();
        assertEquals( false, game.getState().active() );
        game.start();
        assertEquals( true, game.getState().active() );
        game.quit();
        assertEquals( false, game.getState().active() );
    }

    @Test
    public void showSquare() throws Exception {
        Game game = new Game();
//        assertEquals( "1", game.showSquare(0) );
    }
}
