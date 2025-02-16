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
        int rollInFrame = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isSpare(rollInFrame)) {
                score += SPARE + nextBollsForSpare(rollInFrame);
                rollInFrame += 2;
            } else if (isStrike(rollInFrame)) {
                score += SPARE + nextBollsForStrike(rollInFrame);
                rollInFrame += 1;
            } else {
                score += rolls[rollInFrame] + rolls[rollInFrame + 1];
                rollInFrame += 2;
            }
        }
        return score;
    }

    private int nextBollsForStrike(int rollInFrame) {
        return rolls[rollInFrame + 1] + rolls[rollInFrame + 2];
    }

    private int nextBollsForSpare(int rollInFrame) {
        return rolls[rollInFrame + 2];
    }

    private boolean isSpare(int roll) {
        return rolls[roll] + rolls[roll + 1] == 10;
    }

    private boolean isStrike(int roll) {
        return rolls[roll] == 10;
    }
}
