package pl.sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DaoTest {

    private final SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    Dao<SudokuBoard> dao;

    @Test
    public void writeAndReadTest() {
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        board.solveGame();

        dao = factory.getFileDao("board");
        dao.write(board);

        SudokuBoard board2 = dao.read();

        assertEquals(board, board2);
    }

    @Test
    public void IOExceptionTest() {
        SudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard testBoard = new SudokuBoard(solver);
        testBoard.solveGame();
        dao = factory.getFileDao("?.txt");

        assertThrows(RuntimeException.class, () -> dao.write(testBoard));
    }

    @Test
    public void readIOExceptionTest() {
        dao = factory.getFileDao("file.txt");
        assertThrows(RuntimeException.class, dao::read);
    }
}
