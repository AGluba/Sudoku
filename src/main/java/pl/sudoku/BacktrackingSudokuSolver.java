package pl.sudoku;

public class BacktrackingSudokuSolver implements SudokuSolver {

    //https://www.youtube.com/watch?v=eqUwSA0xI-s&ab_channel=TechWithTim
    public boolean solve(SudokuBoard board) {

        for (int i = 0; i < board.getRows(); i++) {

            for (int j = 0; j < board.getCols(); j++) {

                if (board.get(i, j) == 0) {

                    for (int k = 1; k <= 9; k++) {

                        if (checkValidation(k, i, j, board)) {

                            board.set(i, j, k);
                            if (solve(board)) {
                                return true;
                            } else {
                                board.set(i, j, 0);
                            }
                        }
                    }

                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkValidation(int number, int row, int col, SudokuBoard board) {

        for (int i = 0; i < board.getRows(); i++) {

            if (board.get(row, i) == number) {
                return false;
            }
        }

        for (int i = 0; i < board.getCols(); i++) {

            if (board.get(i, col) == number) {
                return false;
            }
        }

        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {

            for (int j = c; j < c + 3; j++) {

                if (board.get(i, j) == number) {
                    return false;
                }
            }
        }

        return true;
    }
}
