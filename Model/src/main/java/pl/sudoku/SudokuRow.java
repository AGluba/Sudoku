package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuRow extends SudokuFragment {

    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuRow clone() throws CloneNotSupportedException {

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {

            fields[i] = getFields().get(i).clone();
        }

        return new SudokuRow(Arrays.asList(fields));
    }
}
