import java.util.ArrayList;

public class BowlingGame {

    private final ArrayList<Frame> frames;
    private int turn;

    public BowlingGame() {
        turn = 1;
        frames = new ArrayList<>();
        frames.add(new Frame(1));
    }

    public void roll(int pins) {
        if (pins > 10 || pins < 0) throw new IllegalArgumentException("pins should be between 0 and 10");

        Frame currentFrame = frames.get(turn - 1);
        currentFrame.roll(pins);

        Frame previousFrame;

        // bonus
        for (int pastTurn = turn - 1; pastTurn > 0 && turn - pastTurn <= 2; pastTurn--) {
            previousFrame = frames.get(pastTurn - 1);
            previousFrame.addBonus(pins);
        }

        if (currentFrame.isDone()) {
            turn++;
            frames.add(new Frame(turn));
        }
    }

    public int score() {
        if (frames.get(10).isBegin()) throw new IllegalArgumentException("frames size should be less than 10");
        int score = 0;
        for (Frame frame : frames) score += frame.getScore();
        return score;
    }
}