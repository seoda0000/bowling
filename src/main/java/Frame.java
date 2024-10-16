import java.util.ArrayList;

public class Frame {
    private int number;
    private int count;
    private ArrayList<Integer> pins;
    private int totalPins;
    private int status; // 0: normal, 1: spare, 2: strike
    private int score;

    public Frame(int number) {
        this.number = number;
        this.count = 0;
        if (number == 10) {
            this.pins = new ArrayList<>(3);
        } else {
            this.pins = new ArrayList<>(2);
        }
        this.status = 0;
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
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }
}
