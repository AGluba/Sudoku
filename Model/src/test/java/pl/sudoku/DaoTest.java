package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
