package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.testng.AssertJUnit.assertNotSame;

public class DaoTest {
    @Test
    public void writeAndReadTest() throws Exception {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();
        SudokuBoard board2;

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("board")) {
            dao.write(board);
            board2 = dao.read();
        }

        assertEquals(board, board2);
    }

    @Test
    public void IOExceptionTest() throws Exception {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(solver);
        testBoard.solveGame();

        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("?")) {
            assertThrows(OperationOnFileException.class, () -> dao.write(testBoard));
        }
    }

    @Test
    public void readIOExceptionTest() throws Exception {
        try (Dao<SudokuBoard> dao = SudokuBoardDaoFactory.getFileDao("test")) {
            assertThrows(OperationOnFileException.class, dao::read);
        }
    }

    @Test
    public void databaseTest() throws Exception {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(solver);
        SudokuBoard testBoard2;
        testBoard.solveGame();

        try(Dao<SudokuBoard> database = SudokuBoardDaoFactory.getDatabaseDao("board")) {
            database.write(testBoard);
            testBoard2 = database.read();
            assertEquals(testBoard, testBoard2);
            assertNotSame(testBoard, testBoard2);
        }
    }
    @Test
    public void readDataBaseTest() throws Exception {
        try (Dao<SudokuBoard> database = SudokuBoardDaoFactory.getDatabaseDao("?")) {
            assertThrows(JdbcException.class, database::read);
        }
    }
}
