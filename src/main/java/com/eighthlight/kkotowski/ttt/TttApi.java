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

        status = "active:" + active +"\n";
        status += "winner:" + game.getWinner().toString() +"\n";
        status += "page:" + game.getPageName() + "\n";
//        System.out.println("...api.getStatus \n" + status);
        return status;
    }

    public String getBoard() {
        String content = "";
        content += "turn:" + game.getTurn().toString() +"\n";
        content += "turn_player_name:"
                + game.getTurnPlayerName().toString() + "\n";
        content += "move_reco:" + game.getTurnRecommendation() + "\n";
        content += "board:" + game.getBoardData() + "\n";
//        System.out.println("...api.getBoard \n" + content);
        return content;
    }

    public String getEndgame() {
        String content = "";
        content += "board:" + game.getBoardData() + "\n";
        content += "winner_player_name:" + game.getWinnerPlayerName() + "\n";
//        System.out.println("...api.getEndgame \n" + content);
        return content;
    }

    public void postStart() {
        this.game.start();
    }

    public void postMove(String move) {
        this.game.move(Integer.parseInt(move));
    }

}


