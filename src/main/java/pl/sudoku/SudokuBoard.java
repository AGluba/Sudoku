package pl.sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuBoard {
    private final int cols = 9;
    private final int rows = 9;
    private final int[][] board = new int[rows][cols];

    public SudokuBoard() {
        Arrays.stream(board).forEach(a -> Arrays.fill(a, 0));
        fillRandomDiagonal();
    }

    public boolean fillBoard() {

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (board[i][j] == 0) {

                    for (int k = 1; k <= 9; k++) {

                        if (checkValidation(k, i, j)) {

                            board[i][j] = k;
                            if (fillBoard()) {
                                return true;
                            } else {
                                board[i][j] = 0;
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public void fillRandomDiagonal() {

        Integer[] intArray = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> intList = Arrays.asList(intArray);
        Collections.shuffle(intList);
        intList.toArray(intArray);

        for (int i = 0; i < rows; i++) {

            board[i][i] = intArray[i];
        }
    }

    public boolean checkValidation(int number, int row, int col) {

        for (int i = 0; i < board.length; i++) {

            if (board[row][i] == number) {
                return false;
            }
        }

        for (int i = 0; i < board.length; i++) {

            if (board[i][col] == number) {
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {

            for (int j = c; j < c + 3; j++) {

                if (board[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    public int get(int x, int y) {
        return board[x][y];
    }

    public void set(int x, int y, int value) { board[x][y] = value; }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean checkRow(int number, int row) {

        int amount = 0;
        for (int i = 0; i < getCols(); i++) {

            if(board[row][i] == number) {
                amount++;
            }
        }

        return amount == 1;
    }

    public boolean checkCol(int number, int col) {

        int amount = 0;
        for (int i = 0; i < getCols(); i++) {

            if(board[i][col] == number) {
                amount++;
            }
        }

        return amount == 1;
    }

    public boolean checkMatrix(int number) {

        int amount = 0;
        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if(board[i][j] == number) amount++;
            }
        }

        return amount == 1;
    }
}
