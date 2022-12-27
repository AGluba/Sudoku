package pl.javafx;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import pl.sudoku.BacktrackingSudokuSolver;
import pl.sudoku.SudokuBoard;

import java.net.URL;
import java.util.ResourceBundle;

public class BoardViewController implements Initializable {

    @FXML
    private GridPane gridP;
    private final SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
    private final SudokuBoard correctBoard = board.clone();

    public BoardViewController() throws CloneNotSupportedException {
    }

    private void fillGrid() {

        for (int i = 0; i < board.getCols(); i++) {

            for (int j = 0; j < board.getRows(); j++) {

                TextField textField = new TextField();
                textField.setMinSize(50, 50);
                textField.setFont(Font.font(20));
                textField.setStyle("-fx-font-weight: bold");
                if (board.get(i, j) != 0) {
                    textField.setDisable(true);
                    textField.setText(String.valueOf(board.get(i, j)));
                }
                gridP.add(textField, i, j);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        board.solveGame();
        BoardBuilder.getBoardToPlay(board, StartMenuController.getLevel());
        fillGrid();
    }
}

