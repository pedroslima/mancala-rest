package com.pslima.mancala.factory;

public class BoardPlayer1 extends BoardPlayer {
    @Override
    public int getPlayerMancala() {
        return PLAYER1_MANCALA;
    }

    @Override
    public int getRivalMancala() {
        return PLAYER2_MANCALA;
    }

    @Override
    public int getPlayerMinRange() {
        return PLAYER1_RANGE_MIN;
    }

    @Override
    public int getPlayerMaxRange() {
        return PLAYER1_RANGE_MAX;
    }
}
