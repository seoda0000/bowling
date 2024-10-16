import java.util.ArrayList;

public class BowlingGame {

    private ArrayList<Frame> frames;
    private int now;

    public BowlingGame() {

        frames = new ArrayList<>(11);
        for (int i = 0; i <= 11; i++) {
            frames.add(new Frame(i));
        }
        now = 1;
    }

    public void roll(int pins) {
        if (pins > 10 || pins < 0) throw new IllegalArgumentException("pins should be between 0 and 10");

        if (now >= 10) {
            frames.get(10).rollPin(pins);
        } else {

            frames.get(now).rollPin(pins);
        }

        // spare
        if (now > 0 && frames.get(now - 1).getStatus() == 1) {
            frames.get(now - 1).addScore(pins);
        }


        // strike
        if (now > 0) {
            for (int frame = now - 1; frame >= now - 2 && frame >= 0; frame--) {
                if (frames.get(frame).getStatus() == 2) {
                    frames.get(frame).addScore(pins);
                }
            }
        }


        // plate completed
        if (frames.get(now).getTotalPins() == 10) {
            if (frames.get(now).getCount() == 1) {
                if (now <= 10) {
                    frames.get(now).setStatus(2);
                }
            } else {
                frames.get(now).setStatus(1);
            }

            if (now < 10) {
                frames.get(now).addScore(frames.get(now).getTotalPins());
            }

            now++;
        } else {
            if (now < 10 && frames.get(now).getCount() == 2) {
                frames.get(now).addScore(frames.get(now).getTotalPins());
                now++;
            }
        }
    }

    public int score() {
        int score = 0;
        int i = 0;
        System.out.println("Now:" + (now-1));
        for (Frame frame : frames) {
            System.out.println(i + " " + frame.getScore());
            score += frame.getScore();
        }
        return score;
    }
}