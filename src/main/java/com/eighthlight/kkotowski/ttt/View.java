package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/27/16.
 */
public interface View {
    public void setMessage(String message);
    public void showWelcome();
    public void showMenu();
    public void showBoard(Board board);
    public String getSquare(int position);
    public void showMove(String move);
    public void showQuit();
    public void showEndgame(Game.Winner winner);
    public void showExit();
}
