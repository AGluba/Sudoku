package pl.javafx;

import pl.sudoku.SudokuBoard;

public class BoardBuilder {

    public static void getBoardToPlay(SudokuBoard board, int difficulty) {

        int counter = 0;
        while (counter != difficulty) {

            int x = (int)(Math.random() * board.getRows());
            int y = (int)(Math.random() * board.getCols());
            if (board.get(x, y) != 0) {

                board.set(x, y, 0);
                counter++;
            }
        }

    }
}
