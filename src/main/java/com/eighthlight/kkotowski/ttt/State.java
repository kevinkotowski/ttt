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

    private Boolean active;
    private Page page;

    public State() {
        this.active = false;
        this.page = Page.MENU;
    }

    public void action(Game.Action action) {
        switch (action) {
            case START:
            case MOVE:
                this.active = true;
                this.page = Page.GAME;
                break;
            case QUIT:
                this.active = false;
                this.page = Page.OVER;
                break;
            default:
                throw new RuntimeException("Invalid game action!");
        }
    }

    public Page page() {
        return this.page;
    }

    public boolean active() {
        return this.active;
    }
}
