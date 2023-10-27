module com.example.preparcial {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.logging;
    requires java.desktop;
    requires javafx.graphics;

    opens com.example.preparcial to javafx.fxml;
    exports com.example.preparcial;
    exports com.example.preparcial.model;
    opens com.example.preparcial.model to javafx.fxml;
}