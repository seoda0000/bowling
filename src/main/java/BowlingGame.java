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

        // bonus
        for (int pastTurn = turn - 1; pastTurn > 0 && turn - pastTurn <= 2; pastTurn--) {
            frames.get(pastTurn - 1).addBonus(pins);
        }

        if (currentFrame.isDone()) frames.add(new Frame(++turn));

    }

    public int score() {
        if (frames.size() < 11 || frames.get(10).isBegin() || !frames.get(9).isDone())
            throw new IllegalArgumentException("frames size should be 10");
        return frames.stream().mapToInt(Frame::getScore).sum();
    }
}