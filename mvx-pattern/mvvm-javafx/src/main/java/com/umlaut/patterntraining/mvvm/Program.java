package com.umlaut.patterntraining.mvvm;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Program extends Application {
    private View view;

    @Override
    public void start(Stage stage) throws Exception {
        Injector injector = Guice.createInjector(new AbstractModule() {
            @Override
            protected void configure() {
            }
        });

        URL resource = Program.class.getResource("greetings-view.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(resource);
        fxmlLoader.setControllerFactory(injector::getInstance);

        Scene scene = new Scene(fxmlLoader.load(), 383, 255);
        stage.setTitle("Hallo, Hello, Hola, Bonjour, Ciao.");
        stage.setScene(scene);
        stage.show();
        view = fxmlLoader.getController();
    }

    @Override
    public void stop() {
        view.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}
