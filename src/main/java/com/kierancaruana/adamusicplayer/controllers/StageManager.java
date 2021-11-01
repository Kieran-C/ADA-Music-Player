package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.LaunchApplication;
import com.kierancaruana.adamusicplayer.exceptions.SceneNotFoundException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StageManager extends LaunchApplication {
    String currentScene = null;
    Logger logger = Logger.getLogger(StageManager.class.getName());

    public boolean swapScene(String FXMLFile, String stageName){
        if (primaryStage != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource(FXMLFile));
//                logger.log(Level.INFO, fxmlLoader.);
                Scene scene = new Scene(fxmlLoader.load());
                setCurrentScene(stageName);
                primaryStage.setScene(scene);
                return true;
            }catch (IOException e){
                logger.log(Level.SEVERE, "Failed to load fxmlLoader when switching to " + stageName);
                System.out.println(e);
                return false;
            }
        }else{
            return false;
        }
    }

    public String getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(String currentScene) {
        this.currentScene = currentScene;
    }
}
