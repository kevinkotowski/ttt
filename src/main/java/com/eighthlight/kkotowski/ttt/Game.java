package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Game {
    private String board = "";
    private List<Player> players = new ArrayList<Player>(2);

    public Game() {
        this.board = "ttt";
        this.players.add( new Player() );
        this.players.add( new Player() );
    }

    public String getBoard() {
        return this.board;
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
