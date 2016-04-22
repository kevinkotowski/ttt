package com.eighthlight.kkotowski.ttt;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevinkotowski on 4/22/16.
 */
public class Utils {
    public static Boolean boardFull (Board board) {
        Boolean response = false;
        response = ( 0 == openPositions(board).size() );
        return response;
    }

    public static List<Integer> openPositions (Board board) {
        Integer size = board.get().size();
        List<Integer> response = new ArrayList<Integer>(size);

        for (int x = 0; x < size; x++) {
            if (board.getSquare(x) == Board.Mark.AVAILABLE) {
                response.add(x);
            }
        }
        return response;
    }
}
