package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/20/16.
 */
public class State {
    public enum Page {
        MENU,
        PLAYER,
        GAME,
        OVER
    }

    public enum Winner {
        PLAYER1,
        PLAYER2,
        TIE,
        NONE
    }

    private Page page;
    private Boolean active;
    private Boolean endgame;
    private Winner winner;

    public State() {
        this.page = Page.MENU;
        this.active = false;
        this.endgame = false;
        this.winner = Winner.NONE;
    }

    public void action(Game.Action action) {
        switch (action) {
            case START:
            case MOVE:
                this.page = Page.GAME;
                this.active = true;
                this.endgame = false;
                break;
            case ENDGAME:
                this.page = Page.OVER;
                this.active = false;
                this.endgame = true;
                break;
            case QUIT:
                this.page = Page.MENU;
                this.active = false;
                this.endgame = false;
                break;
            default:
                throw new RuntimeException("Invalid game action!");
        }
    }

    public Page page() {
        return this.page;
    }

    public Boolean active() {
        return this.active;
    }

    public Boolean endgame() {
        return this.endgame;
    }

    public Winner winner() {
        return this.winner;
    }

    public void setWinner(Winner winner) {
        // TODO: Should I protect against setting winners without endgame == true?
        this.winner = winner;
    }
}
