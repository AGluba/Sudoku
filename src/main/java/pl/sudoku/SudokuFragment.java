package pl.sudoku;

import java.util.List;

abstract class SudokuFragment {
    private final List<SudokuField> fields;

    public SudokuFragment(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        for (int i = 0; i < 9; i++) {

            for (int j = i + 1; j < 9; j++) {

                if (fields.get(i).getFieldValue() == fields.get(j).getFieldValue()) {

                    return false;
                }
            }
        }

        return true;
    }

    public List<SudokuField> getFields() {
        return fields;
    }
}
