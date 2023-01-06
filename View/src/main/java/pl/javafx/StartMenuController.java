package pl.javafx;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartMenuController implements Initializable {

    private static int level = Levels.EASY.level;
    private static boolean fromFile = false;
    private final ResourceBundle bundle = ResourceBundle.getBundle("bundles.text");
    @FXML
    private ComboBox<String> chooseDifficulty;

    @FXML
    void startButtonPress(ActionEvent event) throws IOException {
        setLevel();
        StageBuilder.changeStage("boardView");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        chooseDifficulty.getItems().addAll(bundle.getString("levelA"), bundle.getString("levelB"),
                                           bundle.getString("levelC"));
    }

    public static int getLevel() {
        return level;
    }

    public void setLevel() {
        if (Objects.equals(chooseDifficulty.getValue(), bundle.getString("levelA"))) {

            level = Levels.EASY.level;
        }

        if (Objects.equals(chooseDifficulty.getValue(), bundle.getString("levelB"))) {

            level = Levels.MEDIUM.level;
        }

        if (Objects.equals(chooseDifficulty.getValue(), bundle.getString("levelC"))) {

            level = Levels.HARD.level;
        }
    }

    public void loadGameButton(ActionEvent actionEvent) throws IOException {
        fromFile = true;
        StageBuilder.changeStage("boardView");
    }

    public void changeEnglishButton(ActionEvent actionEvent) throws IOException {
        Locale.setDefault(new Locale("en"));
        StageBuilder.changeStage("startMenu");
    }

    public void changePolishButton(ActionEvent actionEvent) throws IOException {
        Locale.setDefault(new Locale("pl"));
        StageBuilder.changeStage("startMenu");
    }

    public void authorsShowButton(ActionEvent actionEvent) {
        ResourceBundle authors = ResourceBundle.getBundle("pl.javafx.Authors");

        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);

        VBox vbox = new VBox(new Text((String) authors.getObject("1")),
                             new Text((String) authors.getObject("2")));
        vbox.setAlignment(Pos.CENTER);

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }

    public static boolean isFromFile() {
        return fromFile;
    }
}