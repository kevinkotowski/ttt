package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Player {
    private String name;
    private String symbol;
    private Mode mode;
    private Strategy strategy;

    public enum Mode {
        COMPUTER,
        HUMAN
    }

    public Player() {
        this.name = "Default";
        this.symbol = "X";
        this.mode = Mode.COMPUTER;
        this.strategy = new StrategyHard();
    }

    public Player(String name, String symbol, Mode mode, Game.Strategy gameStrategy) {
        this.setName(name);
        this.setSymbol(symbol);
        this.setMode(mode);
        this.setStrategy(gameStrategy);
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String setSymbol(String symbol) {
        if (symbol.length() != 1) {
            throw new RuntimeException("Symbol must be one text character.");
        }
        return this.symbol = symbol;
    }

    public Mode getMode() {
        return this.mode;
    }

    public Mode setMode(Mode mode) {
        this.mode = mode;
        if (this.mode == Mode.HUMAN) {
           this.setStrategy(Game.Strategy.HUMAN);
        }
        return this.mode;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public Strategy setStrategy(Game.Strategy gameStrategy) {
        this.strategy = null;
        switch (gameStrategy) {
            case EASY:
                this.strategy = new StrategyEasy();
                break;
            case HARD:
                this.strategy = new StrategyHard();
                break;
            default:
                // TODO: Ask if better to have an "empty" class for "Human"
                this.strategy = null;
                break;
        }
        return this.strategy;
    }
}
