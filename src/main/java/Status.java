public enum Status {
    NORMAL(0), SPARE(1), STRIKE(2);
    private final int bonusCount;

    Status(int bonusCount) {
        this.bonusCount = bonusCount;
    }

    int getBonusCount() {
        return bonusCount;
    }
}
