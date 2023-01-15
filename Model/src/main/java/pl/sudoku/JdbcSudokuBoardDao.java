package pl.sudoku;

import java.sql.*;
import java.util.ResourceBundle;

//https://www.sqlitetutorial.net/sqlite-java/
public class JdbcSudokuBoardDao implements Dao<SudokuBoard>{

    private final String databaseName;
    Connection conn = null;
    ResultSet rs = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResourceBundle bundle = ResourceBundle.getBundle("bundlesE.exceptions");

    public JdbcSudokuBoardDao(String databaseName) throws JdbcException {
        this.databaseName = databaseName;

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);

        } catch (SQLException e) {
            throw new JdbcException(bundle.getString("dbConFail"));
        }
    }

    @Override
    public SudokuBoard read() throws JdbcException {
        String sql = "SELECT col, row, value FROM field";
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                board.set(rs.getInt("col"), rs.getInt("row"), rs.getInt("value"));
            }

        } catch (SQLException e) {
                throw new JdbcException(bundle.getString("dbReadFail"));
            }

        return board;
    }

    @Override
    public void write(SudokuBoard sudokuBoard) throws JdbcException {
        String sql = """
                CREATE TABLE IF NOT EXISTS Board (
                id integer PRIMARY KEY AUTOINCREMENT,
                name text NOT NULL
                );""";

        String sql2 = """
                CREATE TABLE IF NOT EXISTS Field (
                id integer PRIMARY KEY AUTOINCREMENT,
                boardID INTEGER NOT NULL,
                col integer,
                row integer,
                value integer,
                FOREIGN KEY (boardID) REFERENCES Board (id)
                );""";

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:" + databaseName);
            stmt = conn.createStatement();
            stmt.execute(sql);
            stmt.execute(sql2);
        } catch (SQLException e) {
            throw new JdbcException(bundle.getString("dbWriteFail"));
        }

        sql = "INSERT INTO Board(name) VALUES(?)";
        sql2 = "INSERT INTO Field(boardID, col, row, value) VALUES(?,?,?,?)";

        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, databaseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new JdbcException(bundle.getString("dbWriteFail"));
        }

        try {
            pstmt = conn.prepareStatement(sql2);
            pstmt.setInt(1, conn.prepareStatement("SELECT id FROM board").executeQuery().
                         getInt("id"));

            for (int i = 0; i < sudokuBoard.getCols(); i++) {

                for (int j = 0; j < sudokuBoard.getRows(); j++) {
                    pstmt.setInt(2, i);
                    pstmt.setInt(3, j);
                    pstmt.setInt(4, sudokuBoard.get(i, j));
                    pstmt.executeUpdate();
                }
            }
        } catch (SQLException e) {
            throw new JdbcException(bundle.getString("dbWriteFail"));
        }
    }

    @Override
    public void close() throws Exception {
        if (conn != null) {
            conn.close();
        } else if (rs != null) {
            rs.close();
        } else if (stmt != null) {
            stmt.close();
        } else if (pstmt != null) {
            pstmt.close();
        }
    }
}
