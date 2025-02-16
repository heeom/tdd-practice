package com.example.tddpractice.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game(); // failing test
    }

    @Test
    public void canRoll() {
        game.roll(0);
    }

    @Test
    public void gutterGame() {
        rollMany(0, 10);
        assertThat(game.getScore()).isEqualTo(0);
    }

    @Test
    public void allOnes() {
        rollMany(1, 20);
        assertThat(game.getScore()).isEqualTo(20);
    }

    private void rollMany(int pins, int roll) {
        for (int i = 0; i < roll; i++) {
            game.roll(pins);
        }
    }

    @Test
    public void oneSpare() {
        // frame1 -> 5 + 5 + 3
        game.roll(5);
        game.roll(5); // spare
        // frame2 -> 3
        game.roll(3);
        rollMany(17, 0);

        // 13 failing test
        assertThat(game.getScore()).isEqualTo(16);
    }

    @Test
    public void rollSpare() {

    }

    @Test
    public void oneStrike() {
        // frame1
        rollStrike();
        game.roll(5);
        game.roll(3);

        rollMany(16, 0);
        assertThat(game.getScore()).isEqualTo(26);
    }

    private void rollStrike() {
        game.roll(10);
    }

    @Test
    public void perfectGame() {
        rollMany(10, 10);
        game.roll(10);
        game.roll(10);
        assertThat(game.getScore()).isEqualTo(300);
    }
}
