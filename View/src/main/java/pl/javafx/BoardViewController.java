package pl.javafx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.Dao;
import pl.sudoku.SudokuBoard;
import pl.sudoku.SudokuBoardDaoFactory;

public class BoardViewController implements Initializable {

    @FXML
    private GridPane gridP;
    private SudokuBoard board;
    private SudokuBoard correctBoard;
    Dao<SudokuBoard> dao;
    private SudokuBoard availableFields;
    ResourceBundle bundle = ResourceBundle.getBundle("bundles.text");

    private void fillGrid() {

        for (int i = 0; i < board.getCols(); i++) {

            for (int j = 0; j < board.getRows(); j++) {

                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(20));
                textField.setStyle("-fx-font-weight: bold");
                if (board.get(i, j) != 0 && availableFields.get(i, j) == 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(board.get(i, j)));
                } else if (board.get(i, j) != 0 && availableFields.get(i, j) == 1) {
                    textField.setText(String.valueOf(board.get(i, j)));
                }

                int finalJ = j;
                int finalI = i;
                textField.textProperty().addListener((observableValue, s, t1) -> {
                    if (t1.matches("[1-9]")) {
                        textField.setText(t1);
                        board.set(finalI, finalJ, Integer.parseInt(t1));
                    } else if (t1.matches("")) {
                        textField.setText(t1);
                        board.set(finalI, finalJ, 0);
                    } else {
                        textField.setText(s);
                    }
                });

                gridP.add(textField, j, i);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            loadFiles();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        fillGrid();
    }

    public void checkButton(ActionEvent actionEvent) {
        String s;
        if (correctBoard.equals(board)) {
            s = bundle.getString("win");
        } else {
            s = bundle.getString("lose");
        }

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        VBox vbox = new VBox(new Text(s));
        vbox.setAlignment(Pos.CENTER);

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    public void saveButton(ActionEvent actionEvent) throws Exception {
        try (Dao<SudokuBoard> daoBoard = SudokuBoardDaoFactory.getFileDao(
                "View/src/main/resources/board");
             Dao<SudokuBoard> daoFields = SudokuBoardDaoFactory.getFileDao(
                     "View/src/main/resources/availableFields");
             Dao<SudokuBoard> daoCorrect = SudokuBoardDaoFactory.getFileDao(
                     "View/src/main/resources/correctBoard")) {

             daoBoard.write(board);
             daoCorrect.write(correctBoard);
             daoFields.write(availableFields);
        }
    }

    public void loadFiles() throws Exception {
        if (StartMenuController.isFromFile()) {
            try (Dao<SudokuBoard> daoBoard = SudokuBoardDaoFactory.getFileDao(
                    "View/src/main/resources/board");
                 Dao<SudokuBoard> daoFields = SudokuBoardDaoFactory.getFileDao(
                         "View/src/main/resources/availableFields");
                 Dao<SudokuBoard> daoCorrect = SudokuBoardDaoFactory.getFileDao(
                         "View/src/main/resources/correctBoard")) {

                 board = daoBoard.read();
                 correctBoard = daoCorrect.read();
                 availableFields = daoFields.read();
            }

        } else {
            board = new SudokuBoard(new BacktrackingSudokuSolver());
            board.solveGame();
            correctBoard = board.clone();
            BoardBuilder.getBoardToPlay(board, StartMenuController.getLevel());
            availableFields = new SudokuBoard(new BacktrackingSudokuSolver());
            availableFields.clearBoard();

            for (int i = 0; i < board.getCols(); i++) {

                for (int j = 0; j < board.getRows(); j++) {

                    if (board.get(i, j) == 0) {

                        availableFields.set(i, j, 1);
                    }
                }
            }

        }
    }
}

