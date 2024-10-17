import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {

    BowlingGame game;

    @BeforeEach
    void setUp() {
        game = new BowlingGame();
    }

    @Test
    void whenPlayerRollsZeroPinAndGetZeroPoint() {

        for (int i = 0; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }
        assertEquals(0, game.score());
    }

    @Test
    void whenPlayerRollsOnePinAndGetOnePoint() {
        game.roll(1);
        game.roll(0);
        for (int i = 1; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }
        assertEquals(1, game.score());
    }

    @Test
    void whenPlayerRollsMoreThanTenPinsAndError() {
        assertThrowsExactly(IllegalArgumentException.class, () -> game.roll(11));
    }

    @Test
    void whenPlayerRollsLessThanZeroPinsAndError() {
        assertThrowsExactly(IllegalArgumentException.class, () -> game.roll(-1));
    }

    @Test
    void whenPlayerRollsSpareAndGetBonus() {
        game.roll(7);
        game.roll(3);
        game.roll(1);
        game.roll(0);
        for (int i = 2; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }

        assertEquals(12, game.score());
    }

    @Test
    void whenPlayerRollsStrikeAndGetBonus() {
        game.roll(10);
        game.roll(1);
        game.roll(1);
        for (int i = 2; i < 10; i++) {
            game.roll(0);
            game.roll(0);
        }

        assertEquals(14, game.score());
    }

    @Test
    void whenPlayerRollsMoreThanTenPinsForPlateAndError() {
        game.roll(4);
        assertThrowsExactly(IllegalArgumentException.class, () -> game.roll(7));
    }

    @Test
    void whenPlayerRollsLessThanZeroPinsForPlateAndError() {
        game.roll(0);
        assertThrowsExactly(IllegalArgumentException.class, () -> game.roll(-1));
    }

    @Test
    void whenPlayerRollsStrikeAllAndGetBonus() {
        for (int i = 0; i < 12; i++) {
            game.roll(10);
        }

        assertEquals(300, game.score());
    }

    @Test
    void whenPlayerRollsMoreThanTenPointsAtTenFrame() {
        for (int i = 0; i < 9; i++) {
            game.roll(0);
            game.roll(0);
        }
        game.roll(10);
        game.roll(3);
        game.roll(0);

        assertEquals(13, game.score());
    }

}