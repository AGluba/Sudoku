package pl.sudoku;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFragmentTest {

    @Test
    public void verifyTrueTest() {
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
    public void verifyFalseTest() {
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
    public void listSizeTest() {
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
    public void equalsTest() {
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
    public void hashCodeTest() {
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
    public void toStringTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0,0,1);
        board.set(1,0,1);
        board.set(1,1,0);

        SudokuRow row = board.getRow(0);

        System.out.println(row);

        assertNotNull(row.toString());
    }

    @Test
    public void setFieldTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        SudokuColumn column = board.getColumn(0);
        column.setValue(1, 2);

        assertEquals(2, column.getField(1).getFieldValue());
    }

    @Test
    public void cloneColumnTest() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        SudokuColumn column = board.getColumn(0);
        SudokuColumn columnClone = column.clone();

        assertEquals(column, columnClone);

        columnClone.getField(1).setFieldValue(columnClone.getField(0).getFieldValue());
        assertNotEquals(column, columnClone);

        assertNotSame(column, columnClone);
    }

    @Test
    public void cloneRowTest() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        SudokuRow row = board.getRow(0);
        SudokuRow rowClone = row.clone();

        assertEquals(row, rowClone);

        rowClone.getField(1).setFieldValue(rowClone.getField(0).getFieldValue());
        assertNotEquals(row, rowClone);

        assertNotSame(row, rowClone);
    }

    @Test
    public void cloneBoxTest() throws CloneNotSupportedException {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        SudokuBox box = board.getBox(0, 0);
        SudokuBox boxClone = box.clone();

        assertEquals(box, boxClone);

        boxClone.getField(1).setFieldValue(boxClone.getField(0).getFieldValue());
        assertNotEquals(box, boxClone);

        assertNotSame(box, boxClone);
    }
}
