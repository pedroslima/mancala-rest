package com.pslima.mancala.service;

import com.pslima.mancala.enums.Player;
import com.pslima.mancala.factory.BoardPlayer;
import com.pslima.mancala.factory.BoardPlayer1;
import com.pslima.mancala.factory.BoardPlayer2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BoardService {
    public static final List<Integer> DEFAULT_BOARD = Arrays.asList(4, 4, 4, 4, 4, 4, 0, 4, 4, 4, 4, 4, 4, 0);
    public static final int TOTAL_SEEDS = 48;

    private List<Integer> currentBoard;

    public BoardService() {
        this.currentBoard = DEFAULT_BOARD;
    }

    public List<Integer> getDefaultBoard() {
        return DEFAULT_BOARD;
    }

    public List<Integer> getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(List<Integer> currentBoard) {
        this.currentBoard = currentBoard;
    }

    public int getPlayerMancala(Player player) {
        BoardPlayer boardPlayer = getBoardPlayer(player);
        return this.currentBoard.get(boardPlayer.getPlayerMancala());
    }

    public boolean isPlayerLanesEmpty(Player player) {
        BoardPlayer boardPlayer = getBoardPlayer(player);
        List<Integer> pockets = this.currentBoard.subList(boardPlayer.getPlayerMinRange(), boardPlayer.getPlayerMaxRange() + 1);
        return pockets.stream().allMatch(n -> n == 0);
    }

    public boolean moveFrom(int fromIdx, Player player) {
        BoardPlayer boardPlayer = getBoardPlayer(player);

        List<Integer> board = this.currentBoard;

        if (isPlayerOutRange(fromIdx, boardPlayer)) {
            return false;
        }

        int seeds = board.get(fromIdx);
        board.set(fromIdx, 0);

        int step = 0;
        while (seeds > 0) {
            int idx = (fromIdx + 1 + step) % board.size();
            if (idx != boardPlayer.getOpponentMancala()) {
                Integer currValue = board.get(idx);
                board.set(idx, Integer.sum(currValue, 1));

                int opponentPocket = Math.abs(12 - idx);
                Integer opponentTotal = board.get(opponentPocket);

                if (seeds == 1 &&
                        board.get(idx) == 1 &&
                        opponentTotal > 0 &&
                        boardPlayer.getPlayerMancala() != idx &&
                        boardPlayer.getOpponentMancala() != idx
                ) {
                    Integer mancalaTotal = board.get(boardPlayer.getPlayerMancala());
                    opponentTotal = Integer.sum(opponentTotal, 1);
                    board.set(opponentPocket, 0);
                    board.set(idx, 0);
                    board.set(boardPlayer.getPlayerMancala(), Integer.sum(opponentTotal, mancalaTotal));
                }
                seeds--;
            }
            step++;
        }

        checkVictory(board);

        this.currentBoard = board;
        return true;
    }


    private void checkVictory(List<Integer> board) {
        if (isPlayerLanesEmpty(Player.PLAYER_1) || isPlayerLanesEmpty(Player.PLAYER_2)) {
            int playerMancala1 = getPlayerMancala(Player.PLAYER_1);
            int playerMancala2 = getPlayerMancala(Player.PLAYER_2);
            int seedsLeft = TOTAL_SEEDS - (playerMancala1 + playerMancala2);
            Player winner = playerMancala1 > playerMancala2 ? Player.PLAYER_1 : Player.PLAYER_2;

            BoardPlayer winnerBoarPlayer = getBoardPlayer(winner);
            int winnerSeeds = board.get(winnerBoarPlayer.getPlayerMancala());
            board.set(winnerBoarPlayer.getPlayerMancala(), winnerSeeds + seedsLeft);

            cleanBoard(board, winnerBoarPlayer);
        }
    }

    private void cleanBoard(List<Integer> board, BoardPlayer winnerBoarPlayer) {
        for (int i = 0; i < board.size(); i++) {
            if (winnerBoarPlayer.getPlayerMancala() != i && winnerBoarPlayer.getOpponentMancala() != i) {
                board.set(i, 0);
            }
        }
    }

    private boolean isPlayerOutRange(int fromIdx, BoardPlayer player) {
        return !inRange(fromIdx, player.getPlayerMinRange(), player.getPlayerMaxRange());
    }

    private boolean inRange(int num, int min, int max) {
        return num >= min && num <= max;
    }

    private BoardPlayer getBoardPlayer(Player player) {
        if (player == Player.PLAYER_1) {
            return new BoardPlayer1();
        }
        return new BoardPlayer2();
    }
}
