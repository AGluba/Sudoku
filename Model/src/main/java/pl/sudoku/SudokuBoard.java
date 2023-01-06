package pl.sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SudokuBoard implements Serializable, Cloneable {
    private final int cols = 9;
    private final int rows = 9;
    private SudokuField[][] board = new SudokuField[rows][cols];
    private final SudokuSolver solver;

    public SudokuBoard(SudokuSolver solverType) {

        this.solver = solverType;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = new SudokuField(0);
            }
        }

        fillRandomDiagonal();
    }

    public void solveGame() {

        solver.solve(this);
        checkBoard();
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

    public boolean checkBoard() {

        int col = 0;
        int row = 0;
        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {

                row = 0;
                col++;
            }

            if (!getRow(i).verify()) {

                return false;
            }

            if (!getColumn(i).verify()) {

                return false;
            }

            if (!getBox(col, row).verify()) {

                return false;
            }

            row++;
        }

        return true;
    }

    public SudokuRow getRow(int y) {

        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {

            fields.set(i, new SudokuField(board[y][i].getFieldValue()));
        }

        return new SudokuRow(fields);
    }

    public SudokuColumn getColumn(int x) {

        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        for (int i = 0; i < 9; i++) {

            fields.set(i, new SudokuField(board[i][x].getFieldValue()));
        }

        return new SudokuColumn(fields);
    }

    public SudokuBox getBox(int x, int y) {

        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        int startR = 3 * x;
        int startC = 3 * y;
        int temp = startR;
        for (int i = 0; i < 9; i++) {

            if (i % 3 == 0 && i != 0) {

                temp = startR;
                startC++;
            }
            fields.set(i, new SudokuField(board[startC][temp].getFieldValue()));
            temp++;
        }
        return new SudokuBox(fields);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean checkValidation(int number, int row, int col) {

        for (int i = 0; i < getRows(); i++) {

            if (get(row, i) == number) {
                return false;
            }
        }

        for (int i = 0; i < getCols(); i++) {

            if (get(i, col) == number) {
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {

            for (int j = c; j < c + 3; j++) {

                if (get(i, j) == number) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE)
                .append("board", board).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;

        return new EqualsBuilder().append(board, that.board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(board).toHashCode();
    }

    @Override
    public SudokuBoard clone() throws CloneNotSupportedException {

        SudokuBoard clone = (SudokuBoard) super.clone();
        clone.board = new SudokuField[cols][rows];
        for (int i = 0; i < getCols(); i++) {

            for (int j = 0; j < getRows(); j++) {

                clone.board[i][j] = getField(i, j).clone();
            }
        }

        return clone;
    }

    public SudokuField getField(int i, int j) {

        return board[i][j];
    }

    public void clearBoard() {

        for (int i = 0; i < getCols(); i++) {

            for (int j = 0; j < getRows(); j++) {

                this.set(i, j, 0);
            }
        }
    }
}
