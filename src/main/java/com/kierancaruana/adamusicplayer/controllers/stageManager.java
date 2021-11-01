package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.exceptions.StageNotFoundException;
import javafx.stage.Stage;

public class stageManager {
    String currentStage = null;

    boolean swapStage(Stage stage, String stageName) throws StageNotFoundException {
        if (stage != null) {
            setCurrentStage(stageName);
            stage.show();
            return true;
        }else{
            throw new StageNotFoundException(stageName + " is not a valid stage");
        }
    }

    public String getCurrentStage() {
        return currentStage;
    }

    public void setCurrentStage(String currentStage) {
        this.currentStage = currentStage;
    }
}
