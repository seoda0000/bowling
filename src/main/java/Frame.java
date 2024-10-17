import java.util.ArrayList;

public class Frame {
    private final int number;
    private final ArrayList<Integer> pins;
    private int rollCount;
    private int bonusCount;
    private int totalPins;
    private int bonus;
    private Status status;

    public Frame(int number) {
        this.number = number;
        this.pins = new ArrayList<>();
        this.rollCount = 2;
        this.status = Status.NORMAL;
    }

    public void roll(int pin) {
        pins.add(pin);
        totalPins += pin;
        if (number != 10 && (totalPins > 10 || totalPins < 0))
            throw new IllegalArgumentException("totalPins should be between 0 and 10");
        rollCount--;

        if (totalPins == 10) {
            // strike or spare
            if (rollCount > 0) status = Status.STRIKE;
            else status = Status.SPARE;

            bonusCount = status.getBonusCount();

            if (number == 10) rollCount++;
            else rollCount = 0;
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
        return rollCount == 0;
    }

    public boolean isBegin() {
        return !pins.isEmpty();
    }
}
