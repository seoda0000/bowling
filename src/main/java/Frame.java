import java.util.ArrayList;

public class Frame {
    private int number;
    private int count;
    private ArrayList<Integer> pins;
    private int totalPins;
    private Status status; // 0: normal, 1: spare, 2: strike
    private int score;
    private boolean isDone;

    public Frame(int number) {
        this.number = number;
        this.count = 0;
        if (number == 10) {
            this.pins = new ArrayList<>(3);
        } else {
            this.pins = new ArrayList<>(2);
        }
        this.status = Status.NORMAL;
        this.isDone = false;
    }

    public void addCount() {
        count++;
    }

    public ArrayList<Integer> getPins() {
        return pins;
    }

    public int getTotalPins() {
        return totalPins;
    }

    public int getCount() {
        return count;
    }

    public void rollPin(int pin) {
        pins.add(pin);
        totalPins += pin;
        if (number != 10 && (totalPins > 10 || totalPins < 0)) throw new IllegalArgumentException("totalPins should be between 0 and 10");
        count++;

        if (totalPins == 10) {
            if (count == 1) { // strike
                status = Status.STRIKE;
            } else { // spare
                status = Status.SPARE;
            }
            if (number < 10) {
                isDone = true;
            }
        } else {
            if (number < 10 && count == 2) { // normal
                isDone = true;
            }
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public boolean isDone() {
        return isDone;
    }
}
