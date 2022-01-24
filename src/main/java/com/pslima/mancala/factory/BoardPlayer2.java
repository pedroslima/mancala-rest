package com.pslima.mancala.factory;

public class BoardPlayer2 extends BoardPlayer {
    @Override
    public int getPlayerMancala() {
        return PLAYER2_MANCALA;
    }

    @Override
    public int getRivalMancala() {
        return PLAYER1_MANCALA;
    }

    @Override
    public int getPlayerMinRange() {
        return PLAYER2_RANGE_MIN;
    }

    @Override
    public int getRivalMinRange() {
        return PLAYER1_RANGE_MIN;
    }

    @Override
    public int getPlayerMaxRange() {
        return PLAYER2_RANGE_MAX;
    }

    @Override
    public int getRivalMaxRange() {
        return PLAYER1_RANGE_MAX;
    }
}
