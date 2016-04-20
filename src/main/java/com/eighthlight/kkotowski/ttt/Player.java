package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/18/16.
 */
public class Player {
    private String name;
    private Mode mode;
    private Strategy strategy;
    private String symbol;

    public Player() {
        this.name = "Joshua";
        this.mode = Mode.COMPUTER;
        this.strategy = Strategy.HARD;
        this.symbol = "X";
    }

    public String getName() {
        return this.name;
    }

    public String setName(String name) {
        return this.name = name;
    }

    public Mode getMode() {
        return this.mode;
    }

    public Mode setMode(Mode mode) {
        this.mode = mode;
        return this.mode;
    }

    public Strategy getStrategy() {
        return this.strategy;
    }

    public Strategy setStrategy(Strategy strategy) {
        this.strategy = strategy;
        return this.strategy;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public String setSymbol(String symbol) {
        return this.symbol = symbol;
    }
}
