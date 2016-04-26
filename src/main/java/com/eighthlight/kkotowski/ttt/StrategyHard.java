package com.eighthlight.kkotowski.ttt;

import java.util.List;

/**
 * Created by kevinkotowski on 4/25/16.
 */
public class StrategyHard implements Strategy {
    public int recommend(Board board) {
        return this.unbeatable(board);
    }

    private Integer unbeatable(Board board) {
        Integer position;
        List<Integer> availableSquares = board.availableSquares();
        int availableSize = availableSquares.size();
        int[] best = new int[2];
        // best[0] stores the score
        // best[1] stores the move move

        if ( availableSize == board.get().size() ) {
            // You can win and protect from losing with any starting move,
            // so return random position
            position = this.random(board);
        } else {
            best = minimax(board.copy(), 0, null);
            position = best[1] + 1;
        }
        return position;
    }

    private int[] minimax(Board board, int moves, Board.Mark maxPlayer) {
        List<Integer> availableSquares = board.availableSquares();
        int availableSize = availableSquares.size();
        Board.Mark currentPlayer;
        Game.Winner winner;
        int[] best = new int[2];
        // best[0] stores the score
        // best[1] stores the move move

        currentPlayer = (availableSize % 2 == 1) ? Board.Mark.PLAYER1 : Board.Mark.PLAYER2;

//        System.out.println("...minimax: --------------------------------------" );
//        System.out.println("...minimax moves: " + moves + " player: " + currentPlayer );
//        for (int z = 0; z < availableSize; z++) {
//            System.out.println("...minimax available: " + availableSquares.get(z) );
//        }

        winner = board.getWinner();
        if (winner != Game.Winner.NONE) {
//            System.out.println("...minimax winner: " + winner );
            int score = 0;
            int[] response = new int[2];
            switch (winner) {
                case PLAYER1:
                    score = (maxPlayer == Board.Mark.PLAYER1) ? 10 - moves : moves -10;
                    break;
                case PLAYER2:
                    score = (maxPlayer == Board.Mark.PLAYER2) ? 10 - moves : moves -10;
                    break;
                case TIE:
                    score = 0;
                    break;
            }
            response[0] = score;
            response[1] = -1;
            return response;
        }

        if (maxPlayer == null) { maxPlayer = currentPlayer; }
        if (maxPlayer == currentPlayer) {
            int[] maxResult = new int[2];
            best[0] = -1000; //score
            for (int x = 0; x < availableSize; x++) {
//                System.out.println("...minimax max move: " + x + ", " + availableSquares.get(x) );
                Board maxBoard = board.copy();
                maxBoard.setSquare(availableSquares.get(x), currentPlayer);
                maxResult = minimax(maxBoard, moves + 1, maxPlayer);
                if ( maxResult[0] > best[0] ) {
                    best[0] = maxResult[0];
                    best[1] = availableSquares.get(x);
                }
            }
        } else {
            int[] minResult = new int[2];
            best[0] = 1000; //score
            for (int y = 0; y < availableSize; y++) {
//                System.out.println("...minimax max move: " + y + ", " + availableSquares.get(y) );
                Board minBoard = board.copy();
                minBoard.setSquare(availableSquares.get(y), currentPlayer);
                minResult = minimax(minBoard, moves + 1, maxPlayer);
                if ( minResult[0] < best[0] ) {
                    best[0] = minResult[0];
                    best[1] = availableSquares.get(y);
                }
            }
        }

        return best;
    }
}
