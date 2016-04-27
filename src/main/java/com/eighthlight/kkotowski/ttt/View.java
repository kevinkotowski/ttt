package com.eighthlight.kkotowski.ttt;

/**
 * Created by kevinkotowski on 4/27/16.
 */
public interface View {
    public void setMessage(String message);
    public String getMessage();
    public void showWelcome();
    public void showMenu();
    public void showOver();
    public void showExit();
    public void showBoard(Board board);
    public String getSquare(int position);
}
