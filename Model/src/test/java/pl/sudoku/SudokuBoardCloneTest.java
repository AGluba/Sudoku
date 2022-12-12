package pl.sudoku;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuBoardCloneTest {

    @Test
    public void cloneBoardTest() throws CloneNotSupportedException {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard boardFirst = new SudokuBoard(solver);
        boardFirst.solveGame();

        SudokuBoardClone boardClone = new SudokuBoardClone();
        SudokuBoard boardSecond = boardClone.createClone(boardFirst);

        assertEquals(boardFirst.hashCode(), boardSecond.hashCode());
        assertEquals(boardFirst, boardSecond);

        boardSecond.set(0, 0, boardSecond.get(1, 1));

        assertNotEquals(boardFirst, boardSecond);
        assertNotSame(boardFirst, boardSecond);
    }
}