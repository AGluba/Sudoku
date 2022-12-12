package pl.sudoku;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class StartMenuController implements Initializable {

    @FXML
    private ComboBox<String> chooseDifficulty;

    @FXML
    private Button startButton;

    @FXML
    void startButtonPress(ActionEvent event) {
        String level = chooseDifficulty.getValue();
        System.out.println(level);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseDifficulty.getItems().addAll("Latwy", "Sredni", "Trudny");
    }
}