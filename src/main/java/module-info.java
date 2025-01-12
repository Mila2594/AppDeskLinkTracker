/**
 * Módulo principal de la aplicación Link Tracker que gestiona el rastreo de enlaces
 * y su visualización utilizando JavaFX.
 */
module com.milacanete.linktracker {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.logging;

    opens com.milacanete.linktracker to javafx.fxml;
    exports com.milacanete.linktracker;
}