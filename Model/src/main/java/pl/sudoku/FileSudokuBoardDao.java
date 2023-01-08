package pl.sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private static final Logger logger = LoggerFactory.getLogger(FileSudokuBoardDao.class);

    public String fileName;

    public FileSudokuBoardDao(String file) {
        this.fileName = file + ".txt";
    }

    FileInputStream fileInputStream;
    ObjectInputStream objectInputStream;
    FileOutputStream fileOutputStream;
    ObjectOutputStream objectOutputStream;
    ResourceBundle bundle = ResourceBundle.getBundle("bundlesE.exceptions");

    @Override
    public SudokuBoard read() throws DaoException {
        SudokuBoard obj;

        try {
            fileInputStream = new FileInputStream(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            obj = (SudokuBoard) objectInputStream.readObject();
            logger.info(fileName + " " + bundle.getString("readFile"));
        } catch (ClassNotFoundException | IOException e) {
            throw new OperationOnFileException(bundle.getString("cantFindFile"));
        }

        return obj;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoException {

        try {
            fileOutputStream = new FileOutputStream(fileName);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);
            logger.info(fileName + " " + bundle.getString("writeFile"));
        } catch (IOException e) {
            throw new OperationOnFileException(bundle.getString("wrongFileName"));
        }
    }

    @Override
    public void close() throws IOException {
        if (fileOutputStream != null) {
            fileOutputStream.close();
            objectOutputStream.close();
        }

        logger.info(bundle.getString("closeFile"));
    }
}
