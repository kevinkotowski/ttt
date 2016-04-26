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
        assertEquals( null, player.getStrategy() );

        player = players.get(1);
        assertEquals( "Joshua", player.getName() );
        assertEquals( "O", player.getSymbol() );
        assertEquals( Player.Mode.COMPUTER, player.getMode() );
        assertEquals( StrategyHard.class, player.getStrategy().getClass() );

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
        assertEquals( null, players.get(1).getStrategy() );

        assertEquals( "Brian", players.get(1).getName() );
        assertEquals( Player.Mode.HUMAN, players.get(1).getMode() );
        assertEquals( null, players.get(1).getStrategy() );
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
    public void getTurnPlayerMode() throws Exception {
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

    @Test(expected = RuntimeException.class)
    public void getTurnRecommendationHuman() throws Exception {
        Game game = new Game();
        game.start();
        game.getTurnRecommendation();
    }

    @Test
    public void getTurnRecommendation() throws Exception {
        Game game = new Game();
        game.start();
        game.setTurn(Game.Turn.PLAYER2);

        assertEquals( "Joshua", game.getTurnPlayer().getName() );
        assertTrue( game.getTurnRecommendation().matches("[1-9]") );
    }

    @Test
    public void start() throws Exception {
        Game game = new Game();
        assertEquals( false, game.isActive() );
        game.start();
        assertEquals( true, game.isActive() );

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
        game.move(9);
    }

    @Test
        public void move() throws Exception {
        Game game = new Game();
        assertEquals( false, game.isActive() );
        game.start();
        assertEquals( true, game.isActive() );

        assertEquals( "1", game.showSquare(0) );
        game.move(0);
        assertEquals( "X", game.showSquare(0) );

        assertEquals( "2", game.showSquare(1) );
        game.move(1);
        assertEquals( "O", game.showSquare(1) );
    }

    @Test
    public void quit() throws Exception {
        Game game = new Game();
        assertEquals( false, game.isActive() );
        game.start();
        assertEquals( true, game.isActive() );
        game.quit();
        assertEquals( false, game.isActive() );
    }

    @Test
    public void showSquare() throws Exception {
        Game game = new Game();
        game.start();
        assertEquals( "1", game.showSquare(0) );
    }

    @Test
    public void checkWinner() throws Exception {
        Game game = new Game();
        game.start();

        // horizontal 012
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(0); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(1); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(2); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();

        // horizontal 345
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(3); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(4); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(5); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();

        // horizontal 678
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(0); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(1); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(3); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(8); //p2
        assertEquals( Game.Winner.PLAYER2, game.getWinner() );
        game.quit();

        // vertical 036
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(0); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(4); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(3); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();

        // vertical 147
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(0); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(1); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(2); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(4); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(8); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.PLAYER2, game.getWinner() );
        game.quit();

        // vertical 258
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(2); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(5); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(8); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();

        // diagonal 048
        game.start();
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(0); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(6); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(4); //p1
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(7); //p2
        assertEquals( Game.Winner.NONE, game.getWinner() );
        game.move(8); //p1
        assertEquals( Game.Winner.PLAYER1, game.getWinner() );
        game.quit();

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
