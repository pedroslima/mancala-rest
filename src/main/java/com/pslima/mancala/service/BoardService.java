package com.pslima.mancala.service;

import com.pslima.mancala.enums.Player;

public class BoardService {
    public static final int[] DEFAULT_BOARD = new int[]{4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0};
    private static final int PLAYER1_MANCALA = 6;
    private static final int PLAYER2_MANCALA = 13;

    private int[] board;

    public BoardService() {
    }

    public BoardService(int[] board) {
        this.board = board;
    }

    public int[] getDefaultBoard() {
        return DEFAULT_BOARD;
    }

    public int[] getCurrentBoard() {
        return board;
    }

    public int getPlayerMancala(Player player){
        if (player == Player.PLAYER_1){
            return board[PLAYER1_MANCALA];
        }
        return board[PLAYER2_MANCALA];
    }

    public boolean moveFrom(int fromIdx, Player player){
        int[] newBoard = this.board;
        int seeds = newBoard[fromIdx];
        newBoard[fromIdx] = 0;
        int step = 0;
        while (seeds > 0){
            int idx = (fromIdx + 1 + step) % newBoard.length;
            newBoard[idx]++;
            seeds--;
            step++;
        }

        this.board = newBoard;
        return  true;
    }

}
