public enum Status {
    NORMAL(0), SPARE(1), STRIKE(2);
    private final int code;

    Status(int code) {
        this.code = code;
    }
}
