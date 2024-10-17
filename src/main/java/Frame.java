import java.util.ArrayList;

public class Frame {
    private final int number;
    private int count;
    private final ArrayList<Integer> pins;
    private int totalPins;
    private Status status; // 0: normal, 1: spare, 2: strike
    private int bonus;
    private boolean isDone;
    private int bonusCount;

    public Frame(int number) {
        this.number = number;
        if (number == 10) {
            this.pins = new ArrayList<>(3);
        } else {
            this.pins = new ArrayList<>(2);
        }
        this.status = Status.NORMAL;
    }

    public void roll(int pin) {
        pins.add(pin);
        totalPins += pin;
        if (number != 10 && (totalPins > 10 || totalPins < 0))
            throw new IllegalArgumentException("totalPins should be between 0 and 10");
        count++;

        if (totalPins == 10) {
            if (count == 1) { // strike
                status = Status.STRIKE;
            } else { // spare
                status = Status.SPARE;
            }
            bonusCount = status.getBonusCount();
            if (number < 10) isDone = true;

        } else {
            if (number < 10 && count == 2) isDone = true;
        }
    }

    public void addBonus(int score) {
        if (bonusCount == 0) return;
        this.bonus += score;
        bonusCount--;
    }

    public int getScore() {
        return totalPins + bonus;
    }

    public boolean isDone() {
        return isDone;
    }
}
