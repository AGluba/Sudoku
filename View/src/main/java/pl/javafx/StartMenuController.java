package pl.javafx;

import java.io.IOException;
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

    private static String level;
    @FXML
    private Button startButton;

    @FXML
    void startButtonPress(ActionEvent event) throws IOException {
        level = chooseDifficulty.getValue();

        StageBuilder.changeStage( "boardView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseDifficulty.getItems().addAll("Latwy", "Sredni", "Trudny");
    }

    public static int getLevel() {
        Levels l = Levels.valueOf(level.toUpperCase());
        return l.level;
    }
}