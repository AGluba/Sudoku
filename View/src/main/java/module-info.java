module pl.sudoku {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.sudoku to javafx.fxml;
    exports pl.sudoku;
}
