package pl.javafx;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        StageBuilder.buildStage(stage, "startMenu", "Sudoku");
    }

    public static void main(String[] args) {
        launch();
    }

}