package pl.sudoku;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class SudokuBoardTest {

    public SudokuBoardTest() {
    }

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
    void checkRowTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.checkRow(7, 2), 1);
    }

    @Test
    void checkColTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.checkCol(7, 2), 1);
    }

    @Test
    void checkMatrixTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.checkMatrix(7), 1);
    }
}
