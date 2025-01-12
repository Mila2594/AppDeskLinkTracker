package com.milacanete.linktracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Clase principal de la aplicación LinkTracker para la gestión de vuelos.
 * Extiende de {@link javafx.application.Application} y se encarga de inicializar
 * y mostrar la ventana principal de la aplicación utilizando JavaFX
 * donde los usuarios pueden rastrear enlaces y gestionar información relacionada.
 */
public class LinkTracker extends Application {

    /**
     * Método principal de inicio de la aplicación.
     * Configura y muestra la ventana principal de la aplicación.
     * @param stage escenario principal proporcionado por JavaFX.
     * @throws Exception sí ocurre un error al cargar el archivo FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMainView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Link Tracker");
        stage.setMinWidth(750);
        stage.setMinHeight(550);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Método principal de inicio de la aplicación.
     * Llama al método {@link javafx.application.Application#launch(String...)} para iniciar JavaFX.
     * @param args argumentos de línea de comandos, no utilizados en esta aplicación.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
