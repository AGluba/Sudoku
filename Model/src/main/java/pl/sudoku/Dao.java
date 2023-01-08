package pl.sudoku;

public interface Dao<T> extends AutoCloseable {

    T read() throws DaoException;

    void write(T t) throws DaoException;
}
