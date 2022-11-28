package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.net.SocketOption;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void listSizeTest() {
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

        boolean thrown = false;

        try {
            fields.set(9, new SudokuField(7));
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }

        assertTrue(thrown);
    }

    @Test
    void equalsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0,0,1);
        board.set(1,0,1);
        board.set(1,1,0);

        SudokuRow row = board.getRow(0);
        SudokuRow row2 = board.getRow(1);
        SudokuColumn column = board.getColumn(1);

        assertEquals(row, row2);
        assertEquals(row, row);
        assertNotEquals(row, column);
        assertNotEquals(row, null);
    }

    @Test
    void hashCodeTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0,0,1);
        board.set(1,0,1);
        board.set(1,1,0);

        SudokuRow row = board.getRow(0);
        SudokuRow row2 = board.getRow(1);

        assertEquals(row.hashCode(), row2.hashCode());
    }

    @Test
    void toStringTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0,0,1);
        board.set(1,0,1);
        board.set(1,1,0);

        SudokuRow row = board.getRow(0);

        System.out.println(row);

        assertNotNull(row.toString());
    }
}
