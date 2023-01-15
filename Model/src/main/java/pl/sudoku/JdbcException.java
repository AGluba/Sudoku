package pl.sudoku;

import java.sql.SQLException;

public class JdbcException extends SQLException {
    public JdbcException(String message) {
        super(message);
    }
}
