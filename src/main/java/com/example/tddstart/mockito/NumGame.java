package com.example.tddstart.mockito;

public class NumGame {

    private GameNumGen gameNumGen;

    public NumGame(GameNumGen gameNumGen) {
        this.gameNumGen = gameNumGen;
    }

    public void init(GameLevel gameLevel) {
        gameNumGen.generate(gameLevel);
    }
}
