package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.helpers.animations.Animation;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LaunchController {
    @FXML
    private Label welcomeText;
    @FXML
    private ImageView musicLogo;
    @FXML
    private VBox launchVbox;
    @FXML
    private Button startButton;

    Animation animation = new Animation();

    @FXML
    public void initialize() {
        BackgroundFill bf = new BackgroundFill(Color.rgb(46,46,46), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bf);
        launchVbox.setBackground(bg);

        Image music = new Image(getClass().getResourceAsStream("/images/musicLogo.png"));
        musicLogo.setImage(music);

        welcomeText.setTextFill(Color.rgb(228,228,228));
        welcomeText.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 30));
        welcomeText.setText("Welcome To Flow");
    }

    @FXML
    protected void onHelloButtonClick() {
        startButton.setVisible(false);
        animation.fadeText(welcomeText,1,0,600,1);
        animation.scaleImageOnce(musicLogo, 500,500,850,1);
    }
}
