package com.pslima.mancala.service;

import com.pslima.mancala.enums.Player;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BoardService {
    public static final List<Integer> DEFAULT_BOARD = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
    private static final int PLAYER1_MANCALA = 6;
    private static final int PLAYER2_MANCALA = 13;
    private static final int PLAYER1_RANGE_MIN = 0;
    private static final int PLAYER1_RANGE_MAX = 5;
    private static final int PLAYER2_RANGE_MIN = 7;
    private static final int PLAYER2_RANGE_MAX = 12;

    private List<Integer> board;

    public BoardService() {
        this.board = DEFAULT_BOARD;
    }

    public void setBoard(List<Integer> board) {
        this.board = board;
    }

    public List<Integer> getDefaultBoard() {
        return DEFAULT_BOARD;
    }

    public List<Integer> getCurrentBoard() {
        return board;
    }

    public int getPlayerMancala(Player player) {
        if (player == Player.PLAYER_1) {
            return board.get(PLAYER1_MANCALA);
        }
        return board.get(PLAYER2_MANCALA);
    }

    public boolean moveFrom(int fromIdx, Player player) {
        List<Integer> newBoard = this.board;

        if ((player == Player.PLAYER_1) && !inRange(fromIdx, PLAYER1_RANGE_MIN, PLAYER1_RANGE_MAX) ||
                (player == Player.PLAYER_2) && !inRange(fromIdx, PLAYER2_RANGE_MIN, PLAYER2_RANGE_MAX)) {
            return false;
        }

        int seeds = newBoard.get(fromIdx);
        newBoard.set(fromIdx, 0);
        int step = 0;
        while (seeds > 0) {
            int idx = (fromIdx + 1 + step) % newBoard.size();
            if ((player == Player.PLAYER_1 && idx != PLAYER2_MANCALA) || (player == Player.PLAYER_2 && idx != PLAYER1_MANCALA)) {
                Integer currValue = newBoard.get(idx);
                newBoard.set(idx, Integer.sum(currValue, 1));
                seeds--;
            }

            step++;
        }

        this.board = newBoard;
        return true;
    }


    private boolean inRange(int num, int min, int max) {
        return num >= min && num <= max;
    }

}
