package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SudokuFragmentTest {

    @Test
    void verifyTrueTest() {
        SudokuField[] fields = new SudokuField[9];
        fields[0] = new SudokuField(1);
        fields[1] = new SudokuField(2);
        fields[2] = new SudokuField(3);
        fields[3] = new SudokuField(4);
        fields[4] = new SudokuField(5);
        fields[5] = new SudokuField(6);
        fields[6] = new SudokuField(7);
        fields[7] = new SudokuField(8);
        fields[8] = new SudokuField(9);
        SudokuRow row = new SudokuRow(fields);

        assertTrue(row.verify());
    }

    @Test
    void verifyFalseTest() {
        SudokuField[] fields = new SudokuField[9];
        fields[0] = new SudokuField(1);
        fields[1] = new SudokuField(1);
        fields[2] = new SudokuField(3);
        fields[3] = new SudokuField(4);
        fields[4] = new SudokuField(5);
        fields[5] = new SudokuField(6);
        fields[6] = new SudokuField(7);
        fields[7] = new SudokuField(8);
        fields[8] = new SudokuField(9);
        SudokuRow row = new SudokuRow(fields);

        assertFalse(row.verify());
    }
}
