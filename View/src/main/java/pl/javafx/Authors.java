package pl.javafx;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][] {
                {"1", "Artur Gluba"},
                {"2", "Martyna Brzezowska"}
        };
    }
}
