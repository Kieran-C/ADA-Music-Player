package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.LaunchApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StageManager extends LaunchApplication {
    String currentScene = null;
    Logger logger = Logger.getLogger(StageManager.class.getName());

    /**
     * Loads the scene with the given name.
     * @param FXMLFile  Name The name of the FXML file to load.
     * @param stageName Name The name of the stage to load the scene into.
     * @param styleSheetPath Path The path to the style sheet to load.
     * @return returns true if loaded successfully , false otherwise.
     */
    public boolean swapScene(String FXMLFile, String stageName, String styleSheetPath){
        if (primaryStage != null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(LaunchApplication.class.getResource(FXMLFile));
//                logger.log(Level.INFO, fxmlLoader.);
                Scene scene = new Scene(fxmlLoader.load());
                if (styleSheetPath != null){
                    scene.getStylesheets().add(styleSheetPath);
                }
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
