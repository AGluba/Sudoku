package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuBox extends SudokuFragment {

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuBox clone() throws CloneNotSupportedException {

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {

            fields[i] = getFields().get(i).clone();
        }

        return new SudokuBox(Arrays.asList(fields));
    }
}
