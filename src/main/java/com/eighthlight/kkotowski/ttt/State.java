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

    private Boolean over;
    private Page page;

    public State() {
        this.over = false;
        this.page = Page.MENU;
    }

    public void action(Game.Action action) {
        switch (action) {
            case PLAY:
                this.over = false;
                this.page = Page.GAME;
                break;
            case QUIT:
                this.over = true;
                this.page = Page.OVER;
                break;
            default:
                throw new RuntimeException("Invalid game action!");
        }
    }

    public Page page() {
        return this.page;
    }

    public boolean over() {
        return this.over;
    }
}
