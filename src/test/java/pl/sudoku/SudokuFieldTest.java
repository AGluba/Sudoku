package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SudokuFieldTest {

    @Test
    void setValueTrueTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0, 1, 2);

        assertEquals(board.get(0, 1), 2);
    }

    @Test
    void setValueFalseHigherTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int temp = board.get(0, 1);
        board.set(0, 1, 12);

        assertEquals(board.get(0, 1), temp);
    }

    @Test
    void setValueFalseLowerTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        int temp = board.get(0, 1);
        board.set(0, 1, -30);

        assertEquals(board.get(0, 1), temp);
    }
}
