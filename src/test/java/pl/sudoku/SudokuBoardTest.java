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
    void rowCheck() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.checkRow(7, 2));
    }

    @Test
    void colCheck() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.checkCol(3, 7));
    }

    @Test
    void matrixCheck() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.checkMatrix(7));
    }
}
