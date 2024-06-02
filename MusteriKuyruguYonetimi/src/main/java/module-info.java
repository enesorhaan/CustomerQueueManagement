module com.example.musterikuyruguyonetimi {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.sql;

            requires com.dlsc.formsfx;
                    requires org.kordamp.bootstrapfx.core;

    opens com.example.musterikuyruguyonetimi to javafx.fxml;
    exports com.example.musterikuyruguyonetimi;
}