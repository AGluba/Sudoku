package pl.sudoku;

import java.util.Arrays;
import java.util.List;

public class SudokuColumn extends SudokuFragment {

    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    @Override
    public SudokuColumn clone() throws CloneNotSupportedException {

        SudokuField[] fields = new SudokuField[9];
        for (int i = 0; i < 9; i++) {

            fields[i] = getFields().get(i).clone();
        }

        return new SudokuColumn(Arrays.asList(fields));
    }
}
