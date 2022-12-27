package pl.javafx;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageBuilder {
    private static Stage stage;

    private static void setStage(Stage stage) {
        StageBuilder.stage = stage;
    }

    private static Parent loadFxml(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void buildStage(Stage stage, String fxml, String title) throws IOException {
        setStage(stage);
        stage.setScene(new Scene(loadFxml(fxml)));
        stage.setTitle(title);
        stage.show();
    }

    public static void changeStage(String fxml) throws IOException {
        stage.setScene(new Scene(loadFxml(fxml)));
        stage.show();
    }
}
