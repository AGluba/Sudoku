package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuFieldTest {

    @Test
    public void setValueTrueTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.set(0, 1, 2);

        assertEquals(board.get(0, 1), 2);
    }

    @Test
    public void setValueFalseHigherTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        assertThrows(SudokuFieldException.class, () -> {board.set(1, 1, 30);});
    }

    @Test
    public void setValueFalseLowerTest() {

        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);

        assertThrows(SudokuFieldException.class, () -> {board.set(1, 1, -30);});
    }

    @Test
    public void toStringTest() {

        SudokuField field = new SudokuField(9);

        assertNotNull(field.toString());
    }

    @Test
    public void hashCodeTest() {

        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(9);

        assertEquals(field1.hashCode(), field2.hashCode());
    }

    @Test
    public void equalsTest() {

        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(9);
        SudokuBoard board = new SudokuBoard(null);

        assertEquals(field1, field2);
        assertEquals(field1, field1);
        assertNotEquals(field1, board);
        assertNotEquals(field1, null);
    }

    @Test
    public void compareToTest() {

        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(10);
        SudokuField field3 = new SudokuField(8);
        SudokuField field4 = new SudokuField(9);

        assertEquals(field1.compareTo(field2), -1);
        assertEquals(field1.compareTo(field3), 1);
        assertEquals(field1.compareTo(field4), 0);
    }

    @Test
    public void compareToNullTest() {

        SudokuField field1 = new SudokuField(9);
        Object x = null;

        boolean check = false;

        try {
            assertEquals(field1.compareTo(null), -1);
        }
        catch (NullPointerException e) {
            check = true;
        }

        assertTrue(check);
    }

    @Test
    public void compareToAndEqualsTest() {

        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = new SudokuField(9);

        assertEquals((field1.compareTo(field2)==0), (field1.equals(field2)));
    }

    @Test
    public void fieldCloneTest() throws CloneException {

        SudokuField field1 = new SudokuField(9);
        SudokuField field2 = field1.clone();

        assertEquals(field1, field2);
        assertNotSame(field1, field2);

        field2.setFieldValue(4);

        assertNotEquals(field1.getFieldValue(), field2.getFieldValue());

        assertEquals(field1.compareTo(field2), 1);
        assertNotEquals(field1, field2);
        assertNotSame(field1, field2);
    }
}
