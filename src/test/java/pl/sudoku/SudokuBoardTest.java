package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class SudokuBoardTest {

    @Test
    void CompareTwoBoards() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        final SudokuBoard boardFirst = new SudokuBoard(solver);
        final SudokuBoard boardSecond = new SudokuBoard(solver);

        boardFirst.solveGame();
        boardSecond.solveGame();

        boolean checked = false;
        for (int i = 0; i < boardFirst.getRows(); i++){

            for(int j = 0; j < boardFirst.getCols(); j++){

                if(boardFirst.get(i, j) != boardSecond.get(i, j)) checked = true;
            }
        }

        assertTrue(checked);
    }

    @Test
    void getFieldTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();


        board.set(0, 0, 9);
        assertEquals(board.get(0, 0), 9);
    }

    @Test
    void getRowsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.getRows(), 9);
    }

    @Test
    void getColsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.getCols(), 9);
    }

    @Test
    void checkGetRow() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuRow row = board.getRow(0);

        boolean checked = true;
        for (int i = 0; i < 9; i++) {

            if (row.getFields()[i].getFieldValue() != board.get(0, i)) {

                checked = false;
            }
        }

        assertTrue(checked);
    }

    @Test
    void checkGetColumn() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuColumn column = board.getColumn(0);

        boolean checked = true;
        for (int i = 0; i < 9; i++) {

            if (column.getFields()[i].getFieldValue() != board.get(i, 0)) {

                checked = false;
            }
        }

        assertTrue(checked);
    }

    @Test
    void checkGetBox() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBox box = board.getBox(0, 0);

        boolean checked = true;
        int x = 0;
        int y = 0;
        for (int i = 0; i < 9; i++) {

            if(i % 3 == 0 && i != 0) {

                x++;
                y = 0;
            }

            if (box.getFields()[i].getFieldValue() != board.get(x, y)) {

                checked = false;
            }

            y++;
        }

        assertTrue(checked);
    }

    @Test
    void checkBoardTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.checkBoard());
    }

    @Test
    void checkBoardFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(1, 1, board.get(0, 0));

        assertFalse(board.checkBoard());
    }

    @Test
    void checkBoardRowFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(0, 4, board.get(0, 0));

        assertFalse(board.checkBoard());
    }

    @Test
    void checkBoardColumnFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(2, 0, board.get(0, 0));

        assertFalse(board.checkBoard());
    }
}
