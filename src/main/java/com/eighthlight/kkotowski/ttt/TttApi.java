package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 6/13/16.
 */
public class TttApi {
    private Game game;

    public TttApi() {
        this.game = new Game();
    }

    public String getStatus() {
        String status;
        boolean active = game.isActive();

        status = "active=" + String.valueOf(active) +"\n";
        status += "winner=" + game.getWinner().toString() +"\n";
        return status;
    }

    public String getBoard() {
        String content = "";
        if (!game.isActive() && (game.getWinner() == Game.Winner.NONE) ) {
            content = "active=false";
        } else {
            content += "turn=" + game.getTurn().toString() +"\n";
            content += "turn_player_name="
                    + game.getTurnPlayerName().toString() + "\n";
            content += "move_reco=" + game.getTurnRecommendation() + "\n";
            content += "board=" + game.getBoardData() + "\n";
        }
        return content;
    }

    public String getEndgame() {
        String content = "";
        content += "board=" + game.getBoardData() + "\n";
        content += "winner_player_name=" + game.getWinnerPlayerName() + "\n";
        return content;
    }

    public void postStart() {
        this.game.start();
    }

    public void postMove(String move) {
        this.game.move(Integer.parseInt(move));
    }

    public void postExit() {
        this.game.quit();
    }

}


