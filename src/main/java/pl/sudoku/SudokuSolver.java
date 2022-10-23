package pl.sudoku;

public interface SudokuSolver {

    default boolean solve(SudokuBoard board) {

        return true;
    }
}
