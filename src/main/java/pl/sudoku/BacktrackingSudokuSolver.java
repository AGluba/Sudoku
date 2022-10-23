package pl.sudoku;

public class BacktrackingSudokuSolver implements SudokuSolver {

    public boolean solve(SudokuBoard board) {

        for (int i = 0; i < board.getRows(); i++) {

            for (int j = 0; j < board.getCols(); j++) {

                if (board.get(i, j) == 0) {

                    for (int k = 1; k <= 9; k++) {

                        if (board.checkValidation(k, i, j)) {

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
}
