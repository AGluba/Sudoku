package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SudokuFragmentTest {

    @Test
    void verifyTrueTest() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        fields.set(0, new SudokuField(1));
        fields.set(1, new SudokuField(2));
        fields.set(2, new SudokuField(3));
        fields.set(3, new SudokuField(4));
        fields.set(4, new SudokuField(5));
        fields.set(5, new SudokuField(6));
        fields.set(6, new SudokuField(7));
        fields.set(7, new SudokuField(8));
        fields.set(8, new SudokuField(9));
        SudokuRow row = new SudokuRow(fields);

        assertTrue(row.verify());
    }

    @Test
    void verifyFalseTest() {
        List<SudokuField> fields = Arrays.asList(new SudokuField[9]);
        fields.set(0, new SudokuField(1));
        fields.set(1, new SudokuField(1));
        fields.set(2, new SudokuField(3));
        fields.set(3, new SudokuField(4));
        fields.set(4, new SudokuField(5));
        fields.set(5, new SudokuField(6));
        fields.set(6, new SudokuField(7));
        fields.set(7, new SudokuField(8));
        fields.set(8, new SudokuField(9));
        SudokuRow row = new SudokuRow(fields);

        assertFalse(row.verify());
    }
}
