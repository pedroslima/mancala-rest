package com.pslima.mancala.factory;


public abstract class BoardPlayer {
    protected static final int PLAYER1_MANCALA = 6;
    protected static final int PLAYER2_MANCALA = 13;
    protected static final int PLAYER1_RANGE_MIN = 0;
    protected static final int PLAYER1_RANGE_MAX = 5;
    protected static final int PLAYER2_RANGE_MIN = 7;
    protected static final int PLAYER2_RANGE_MAX = 12;

    public abstract int getPlayerMancala();

    public abstract int getRivalMancala();

    public abstract int getPlayerMinRange();

    public abstract int getPlayerMaxRange();
}
