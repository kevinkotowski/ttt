package com.eighthlight.kkotowski.ttt;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by kevinkotowski on 6/13/16.
 */
public class ApiTest {
    @Test
    public void initApiGetDefaultStatus() throws Exception {
        Api api = new Api();

        String status = api.getStatus();
        assertTrue( status.contains("active:false") );
        assertTrue( status.contains("winner:NONE") );
        assertTrue( status.contains("page:menu") );
    }

    @Test
    public void startGameGetStatus() throws Exception {
        Api api = new Api();
        api.postStart();

        String status = api.getStatus();
        assertTrue( status.contains("active:true") );
        assertTrue( status.contains("winner:NONE") );
        assertTrue( status.contains("page:board") );
    }

    @Test
    public void getBoard() throws Exception {
        Api api = new Api();
        api.postStart();

        String content = api.getBoard();
        assertTrue( content.contains("turn:PLAYER1") );
        assertTrue( content.contains("turn_player_name:Homer") );
        assertTrue( content.contains("move_reco:X") );
        assertTrue( content.contains("board:0,0,0,0,0,0,0,0,0") );
    }

    @Test
    public void postMenuSelection() throws Exception {
        Api api = new Api();
        api.postStart();

        api.postMove("8");
        assertTrue( api.getBoard().contains("0,0,0,0,0,0,0,0,1") );
    }

    @Test
    public void getEndgame() throws Exception {
        Api api = new Api();
        api.postStart();
        api.postMove("0");
        api.postMove("4");
        api.postMove("1");
        api.postMove("6");
        api.postMove("2");

        String status = api.getStatus();
        assertTrue( status.contains("active:true") );
        assertTrue( status.contains("winner:PLAYER1") );
        assertTrue( status.contains("page:endgame") );

        String content = api.getEndgame();
        assertTrue( content.contains("board:1,1,1,0,2,0,2,0,0") );
        assertTrue( status.contains("winner:PLAYER1") );
    }
}