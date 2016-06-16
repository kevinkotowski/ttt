package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class GameTest {
    @Test
    public void gameDefaults() throws Exception {
        Game game = new Game();
        List<Player> players = new ArrayList();

        players = game.getPlayers();

        Player player = new Player();
        player = players.get(0);
        assertEquals( "Homer", player.getName() );
        assertEquals( "X", player.getSymbol() );
        assertEquals( Player.Mode.HUMAN, player.getMode() );
        assertEquals( null, player.getStrategy() );

        player = players.get(1);
        assertEquals( "Joshua", player.getName() );
        assertEquals( "O", player.getSymbol() );
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );

        assertEquals( null, game.getTurn() );
    }

    @Test
    public void gamePlayerConfigure() throws Exception {
        Game game = new Game();
        List<Player> players = new ArrayList();

        players = game.getPlayers();

        players.get(1).setName("Brian");
        players.get(1).setMode(Player.Mode.HUMAN);

        assertEquals( "Homer", players.get(0).getName() );
        assertEquals( null, players.get(1).getStrategy() );

        assertEquals( "Brian", players.get(1).getName() );
        assertEquals( Player.Mode.HUMAN, players.get(1).getMode() );
        assertEquals( null, players.get(1).getStrategy() );
    }

    @Test
    public void changePlayerTurn() throws Exception {
        Game game = new Game();
        game.start();

        assertEquals( Game.Turn.PLAYER1, game.getTurn() );
        game.setTurn(Game.Turn.PLAYER2);
        assertEquals( Game.Turn.PLAYER2, game.getTurn() );
    }

    @Test
    public void getCurrentTurnPlayerMode() throws Exception {
        Game game = new Game();
        Player player = new Player();
        game.start();

        player = game.getTurnPlayer();
        assertEquals( Player.Mode.HUMAN, player.getMode() );
        assertEquals( null, player.getStrategy() );
        game.move(0);

        player = game.getTurnPlayer();
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );
    }

    @Test
    public void getTurnRecommendationHuman() throws Exception {
        Game game = new Game();
        game.start();
        assertEquals("", game.getTurnRecommendation());
        game.move(8);
        assertEquals("5", game.getTurnRecommendation());
    }

    @Test
    public void getTurnRecommendationComputer() throws Exception {
        Game game = new Game();
        game.start();
        game.setTurn(Game.Turn.PLAYER2);

        assertEquals( "Joshua", game.getTurnPlayer().getName() );
        assertTrue( game.getTurnRecommendation().matches("[1-9]") );
    }

    @Test
    public void startVerifyBoard() throws Exception {
        Game game = new Game();
        game.start();

        Board board = game.getBoard();
        assertEquals( 9,  board.getSize() );

        for (int x = 0; x < board.getSize(); x++) {
            assertEquals( Board.Mark.AVAILABLE, board.getSquare(x) );
        }

        assertEquals( Game.Turn.PLAYER1, game.getTurn() );
    }

    @Test(expected = RuntimeException.class)
    public void playerInvalidMove() throws Exception {
        Game game = new Game();
        game.move(9);
    }

    @Test
        public void playerMoves() throws Exception {
        Game game = new Game();
        game.start();

        assertEquals( "1", game.getSquare(0) );
        game.move(0);
        assertEquals( "X", game.getSquare(0) );

        assertEquals( "2", game.getSquare(1) );
        game.move(1);
        assertEquals( "O", game.getSquare(1) );
    }

    @Test
    public void quit() throws Exception {
        Game game = new Game();
        assertFalse( game.isActive() );
        game.start();
        assertTrue( game.isActive() );
        game.quit();
        assertFalse( game.isActive() );
    }

    @Test
    public void getSquare() throws Exception {
        Game game = new Game();
        game.start();
        assertEquals( "1", game.getSquare(0) );
    }

    @Test
    public void checkWinner() throws Exception {
        Game game = new Game();
        game.start();

        // diagonal 246
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(2); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(8); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(4); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();
    }
}
