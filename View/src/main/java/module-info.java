module ViewProject {
    requires javafx.fxml;
    requires javafx.controls;
    requires ModelProject;

    opens pl.javafx to javafx.fxml;
    exports pl.javafx;
}