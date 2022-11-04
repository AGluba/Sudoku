package pl.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {
    private final int cols = 9;
    private final int rows = 9;
    private final SudokuField[][] board = new SudokuField[rows][cols];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solverType) {
        this.solver = solverType;
        Arrays.stream(board).forEach(a -> Arrays.fill(a, new SudokuField(0)));
        fillRandomDiagonal();
    }

    public void solveGame() {

        solver.solve(this);
    }

    public void fillRandomDiagonal() {

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);

        for (int i = 0; i < rows; i++) {

            SudokuField field = new SudokuField(intArray[i]);
            board[i][i] = field;
        }
    }

    public int get(int x, int y) {
        return board[x][y].getFieldValue();
    }

    public void set(int x, int y, int value) {
        board[x][y].setFieldValue(value);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
