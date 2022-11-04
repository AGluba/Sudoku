package pl.sudoku;

abstract class SudokuFragment {

    private final SudokuField[] fields;

    public SudokuFragment(SudokuField[] fields) {
        this.fields = fields;
    }

    boolean verify() {
        for (int i = 0; i < 9; i++){

            for (int j = i + 1; j < 9; j++){

                if (fields[i].getFieldValue() == fields[j].getFieldValue()) {

                    return false;
                }
            }
        }

        return true;
    }
}
