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
        SudokuBoard boardFirst = new SudokuBoard();
        SudokuBoard boardSecond = new SudokuBoard();

        boardFirst.fillBoard();
        boardSecond.fillBoard();

        boolean checked = false;
        for(int i = 0; i < boardFirst.getRows(); i++){

            for(int j = 0; j < boardFirst.getCols(); j++){

                if(boardFirst.get(i, j) != boardSecond.get(i, j)) checked = true;
            }
        }

        assertTrue(checked);
    }

    @Test
    void rowCheck() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        assertTrue(board.checkRow(7, 2));
    }

    @Test
    void colCheck() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        assertTrue(board.checkCol(3, 7));
    }

    @Test
    void matrixCheck() {
        SudokuBoard board = new SudokuBoard();
        board.fillBoard();

        assertTrue(board.checkMatrix(7));
    }
}
