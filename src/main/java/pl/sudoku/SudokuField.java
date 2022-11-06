package pl.sudoku;

public class SudokuField {

    private int value;

    public SudokuField(int value) {
        this.value = value;
    }

    public int getFieldValue() {

        return value;
    }

    public void setFieldValue(int value) {

        if (0 <= value && value <= 9) {

            this.value = value;
        }
    }
}
