package pl.javafx;

public enum Levels {
    EASY(15),
    MEDIUM(30),
    HARD(45);

    final int level;

    Levels(int level) {
        this.level = level;
    }
}
