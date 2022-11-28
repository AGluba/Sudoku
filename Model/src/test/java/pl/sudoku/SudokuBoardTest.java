package pl.sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SudokuBoardTest {

    @Test
    void CompareTwoBoards() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        final SudokuBoard boardFirst = new SudokuBoard(solver);
        final SudokuBoard boardSecond = new SudokuBoard(solver);

        boardFirst.solveGame();
        boardSecond.solveGame();

        boolean checked = false;
        for (int i = 0; i < boardFirst.getRows(); i++){

            for(int j = 0; j < boardFirst.getCols(); j++){

                if(boardFirst.get(i, j) != boardSecond.get(i, j)) checked = true;
            }
        }

        assertTrue(checked);
    }

    @Test
    void getFieldTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();


        board.set(0, 0, 9);
        assertEquals(board.get(0, 0), 9);
    }

    @Test
    void getRowsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.getRows(), 9);
    }

    @Test
    void getColsTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertEquals(board.getCols(), 9);
    }

    @Test
    void checkGetRow() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuRow row = board.getRow(0);

        boolean checked = true;
        for (int i = 0; i < 9; i++) {

            if (row.getFields().get(i).getFieldValue() != board.get(0, i)) {

                checked = false;
            }
        }

        assertTrue(checked);
    }

    @Test
    void checkGetColumn() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuColumn column = board.getColumn(0);

        boolean checked = true;
        for (int i = 0; i < 9; i++) {

            if (column.getFields().get(i).getFieldValue() != board.get(i, 0)) {

                checked = false;
            }
        }

        assertTrue(checked);
    }

    @Test
    void checkGetBox() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        SudokuBox box = board.getBox(0, 0);

        boolean checked = true;
        int x = 0;
        int y = 0;
        for (int i = 0; i < 9; i++) {

            if(i % 3 == 0 && i != 0) {

                x++;
                y = 0;
            }

            if (box.getFields().get(i).getFieldValue() != board.get(x, y)) {

                checked = false;
            }

            y++;
        }

        assertTrue(checked);
    }

    @Test
    void checkBoardTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertTrue(board.checkBoard());
    }

    @Test
    void checkBoardFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(1, 1, board.get(0, 0));

        assertFalse(board.checkBoard());
    }

    @Test
    void checkBoardRowFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(0, 4, board.get(0, 0));

        assertFalse(board.checkBoard());
    }

    @Test
    void checkBoardColumnFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        board.set(2, 0, board.get(0, 0));

        assertFalse(board.checkBoard());
    }

    @Test
    void checkBoardToStringTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(solver);
        board.solveGame();

        assertNotNull(board.toString());
    }

    @Test
    void checkBoardEqualsTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();

        //Domyslnie board1.equals(board2) True, Po nadpisaniu True
        //SudokuBoard board1 = new SudokuBoard(solver);
        //SudokuBoard board2 = board1;

        //Domyslnie board1.equals(board2) False, Po nadpisaniu True
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);
        SudokuBoard board3 = board1;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1.set(i, j, 2);
                board2.set(i, j, 2);
            }
        }

        assertTrue(board1.equals(board2));
        assertTrue(board1.equals(board3));
    }

    @Test
    void checkBoardEqualsFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();

        //Domyslnie board1.equals(board2) True, Po nadpisaniu True
        //SudokuBoard board1 = new SudokuBoard(solver);
        //SudokuBoard board2 = board1;

        //Domyslnie board1.equals(board2) False, Po nadpisaniu True
        SudokuBoard board1 = new SudokuBoard(solver);


        assertNotEquals(board1, null);
        assertNotEquals(board1, solver);
    }

    @Test
    void checkBoardHashCodeTrueTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();

        //Domyslnie board1.hashCode() == board2.hashCode(), Po nadpisaniu board1.hashCode() == board2.hashCode()
        //SudokuBoard board1 = new SudokuBoard(solver);
        //SudokuBoard board2 = board1;

        //Domyslnie board1.hashCode() != board2.hashCode(), Po nadpisaniu board1.hashCode() == board2.hashCode()
        SudokuBoard board1 = new SudokuBoard(solver);
        SudokuBoard board2 = new SudokuBoard(solver);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board1.set(i, j, 2);
                board2.set(i, j, 2);
            }
        }

        assertEquals(board1.hashCode(), board2.hashCode());
    }



    @Test
    void checkBoardHashCodeFalseTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();

        //Domyslnie board1.hashCode() == board2.hashCode(), Po nadpisaniu board1.hashCode() == board2.hashCode()
        //SudokuBoard board1 = new SudokuBoard(solver);
        //SudokuBoard board2 = board1;

        //Domyslnie board1.hashCode() != board2.hashCode(), Po nadpisaniu board1.hashCode() == board2.hashCode()
        SudokuBoard board1 = new SudokuBoard(solver);
        board1.solveGame();
        SudokuBoard board2 = new SudokuBoard(solver);
        board2.solveGame();

        assertNotEquals(board1.hashCode(), board2.hashCode());
    }
}
