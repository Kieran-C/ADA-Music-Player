package com.kierancaruana.adamusicplayer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;

import java.io.IOException;

import static java.awt.Toolkit.getDefaultToolkit;

public class LaunchApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = getDefaultToolkit().getScreenSize();
        FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource("launch-controller.fxml"));
        Scene launchScn = new Scene(fxmlLoader.load(), 450, 844);
        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/musicLogo.png")));
        stage.setTitle("Flow");
        stage.setScene(launchScn);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
