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

    private Page page;
    private Boolean active;
    private Boolean endgame;

    public State() {
        this.active = false;
        this.endgame = false;
        this.page = Page.MENU;
    }

    public void action(Game.Action action) {
        switch (action) {
            case START:
            case MOVE:
                this.active = true;
                this.endgame = false;
                this.page = Page.GAME;
                break;
            case ENDGAME:
                this.active = true;
                this.endgame = true;
                this.page = Page.OVER;
                break;
            case QUIT:
                this.active = false;
                this.endgame = false;
                this.page = Page.MENU;
                break;
            default:
                throw new RuntimeException("Invalid game action!");
        }
    }

    public Boolean active() {
        return this.active;
    }

    public Boolean endgame() {
        return this.endgame;
    }

    public Page page() {
        return this.page;
    }
}
