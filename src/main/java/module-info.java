module com.example.weatherappfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens com.example.weatherappfx to javafx.fxml;
    exports com.example.weatherappfx;
}