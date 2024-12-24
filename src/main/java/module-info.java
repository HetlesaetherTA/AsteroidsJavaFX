module com.hetlesaetherta.asteroids {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.hetlesaetherta.asteroids to javafx.fxml;
    exports com.hetlesaetherta.asteroids;
}