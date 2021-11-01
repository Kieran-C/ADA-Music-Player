package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.exceptions.SceneNotFoundException;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class stageManager {
    String currentStage = null;

    boolean swapStage(Stage stage, Scene scene, String stageName) throws SceneNotFoundException {
        if (stage != null) {
            setCurrentStage(stageName);
            stage.setScene(scene);
            return true;
        }else{
            throw new SceneNotFoundException(stageName + " is not a valid stage");
        }
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
}
