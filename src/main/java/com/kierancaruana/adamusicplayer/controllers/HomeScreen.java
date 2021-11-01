package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.objects.Song;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class HomeScreen extends StackPane {

    @FXML
    private VBox musicVbox;
    @FXML
    private TableView trackTable;
    @FXML
    private TableColumn trackNameColumn;
    @FXML
    private VBox playlistVbox;
    @FXML
    private Label playlistsTitleBar;


    @FXML
    public void initialize() {
        int winWidth = 1920;
        int winHeight = 1080;
        musicVbox.setPrefSize(winWidth,winHeight);
        playlistsTitleBar.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 30));
        playlistsTitleBar.setText("Playlists");
        playlistsTitleBar.setTextFill(Color.rgb(255,255,255));
//        playlistVbox.setPrefSize(30,winHeight);
        trackTable.setPrefWidth(((winWidth-20)/100)*90);
        BackgroundFill bf = new BackgroundFill(Color.rgb(46, 46, 46), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bf);
        musicVbox.setBackground(bg);
    }
}
