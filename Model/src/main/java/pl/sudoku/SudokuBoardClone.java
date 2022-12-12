package pl.sudoku;

public class SudokuBoardClone {

    public SudokuBoard createClone(SudokuBoard board) throws CloneNotSupportedException {
        return board.clone();
    }
}
