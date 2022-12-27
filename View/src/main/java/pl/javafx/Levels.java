package pl.javafx;

import pl.sudoku.SudokuBoard;

public enum Levels {
    LATWY(15),
    SREDNI(30),
    TRUDNY(45);

    final int level;

    Levels(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
