package com.example.tddpractice.bowling;

public class Game {
    private static final int SPARE = 10;
    private int[] rolls = new int[21];
    private int currentRoll = 0;

    public void roll(int pins) {
        this.rolls[currentRoll++] = pins;
    }

    public int getScore() {
        int score = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (rolls[frame * 2] + rolls[frame * 2 + 1] == 10) {
                score = SPARE + rolls[frame * 2 + 2];
            } else {
                score += rolls[frame * 2] + rolls[frame * 2 + 1];
            }
        }
        return score;
    }
}
