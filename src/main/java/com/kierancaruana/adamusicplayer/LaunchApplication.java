package com.kierancaruana.adamusicplayer;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;

import java.io.IOException;
import java.util.Set;

import static java.awt.Toolkit.getDefaultToolkit;

public class LaunchApplication extends Application {

    public static Stage primaryStage;

    /**
     * This method is called when the application starts. It creates the main window of the application.
     * @param stage The stage of the application.
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Dimension screenSize = getDefaultToolkit().getScreenSize();
        FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource("launch-controller.fxml"));
        Scene launchScn = new Scene(fxmlLoader.load(), 1920, 1080);
//        stage.setMaximized(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/musicLogo.png")));
        stage.setTitle("Flow");
        stage.setScene(launchScn);
        stage.setResizable(false);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        setPrimaryStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public void setPrimaryStage(Stage stage){
        this.primaryStage = stage;
    }
}
