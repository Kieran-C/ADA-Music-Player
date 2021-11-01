package com.kierancaruana.adamusicplayer.helpers.animations;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Animation {
    /**
     * Method to move an ImageView in a smoothly animated way
     * @param subjectImage imageView of the image to move
     * @param x distance to move on x-axis
     * @param y distance to move on y-axis
     * @param duration length of animation
     * @param repeats number of times animation repeats, -1 for indefinite
     * @return returns true if successful
     */
    public boolean moveImageOnce(ImageView subjectImage, int x, int y, int duration, int repeats){
        TranslateTransition translate = new TranslateTransition();
        translate.setNode(subjectImage);
        translate.setDuration(Duration.millis(duration));
        translate.setCycleCount(repeats);
        translate.setByX(x);
        translate.setByY(y);
        translate.setAutoReverse(true);
        translate.play();
        return true;
    }

    /**
     * Method ot scale an ImageView smoothly
     * @param subjectImage imageView of the image to scale
     * @param x x-axis scale value
     * @param y y-axis scale value
     * @param duration duration the animation lasts
     * @param repeats number of times animation repeats, -1 for indefinite
     * @return returns true if successful
     */
    public ScaleTransition scaleImageOnce(ImageView subjectImage, double x, double y, int duration, int repeats){
        ScaleTransition scale = new ScaleTransition();
        scale.setNode(subjectImage);
        scale.setDuration(Duration.millis(duration));
        scale.setCycleCount(repeats);
        scale.setInterpolator(Interpolator.LINEAR);
        scale.setByX(x);
        scale.setByY(y);
        scale.setAutoReverse(true);
        scale.play();
        return scale;
    }


    public boolean fadeText(Label subjectText, int fadeFromValue, int fadeToValue, int duration, int repeats){
        FadeTransition fade = new FadeTransition();
        fade.setNode(subjectText);
        fade.setDuration(Duration.millis(duration));
        fade.setCycleCount(repeats);
        fade.setInterpolator(Interpolator.LINEAR);
        fade.setFromValue(fadeFromValue);
        fade.setToValue(fadeToValue);
        fade.play();
        return true;
    }

}
