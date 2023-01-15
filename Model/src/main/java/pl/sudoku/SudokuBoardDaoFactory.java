package pl.sudoku;

public class SudokuBoardDaoFactory {

    public static Dao<SudokuBoard> getFileDao(String fileName) {
        return new FileSudokuBoardDao(fileName);
    }

    public static Dao<SudokuBoard> getDatabaseDao(String databaseName) throws JdbcException { return new JdbcSudokuBoardDao(databaseName); }
}
