import java.util.ArrayList;

public class Frame {
    private final int number;
    private final ArrayList<Integer> pins;
    private int rollCount;
    private int bonusCount;
    private int totalPins;
    private int bonus;
    private boolean isDone;
    private Status status; // 0: normal, 1: spare, 2: strike

    public Frame(int number) {
        this.number = number;
        this.rollCount = 2;
        this.pins = new ArrayList<>();
        this.status = Status.NORMAL;
    }

    public void roll(int pin) {
        pins.add(pin);
        totalPins += pin;
        if (number != 10 && (totalPins > 10 || totalPins < 0))
            throw new IllegalArgumentException("totalPins should be between 0 and 10");
        rollCount--;

        if (totalPins == 10) {
            if (rollCount > 0) { // strike
                status = Status.STRIKE;
            } else { // spare
                status = Status.SPARE;
            }
            if (number == 10) {
                rollCount++;
            } else {
                rollCount = 0;
            }
            bonusCount = status.getBonusCount();
        }

        if (rollCount == 0) isDone = true;
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

    public boolean isBegin() {
        return !pins.isEmpty();
    }
}
