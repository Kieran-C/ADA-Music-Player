package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.helpers.Csv;
import com.kierancaruana.adamusicplayer.helpers.Idle;
import com.kierancaruana.adamusicplayer.helpers.music.MusicControls;
import com.kierancaruana.adamusicplayer.objects.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class HomeScreen extends StackPane {

    public Button shuffleButton;
    @FXML
    private VBox musicVbox;
    @FXML
    private TableView<Song> trackTable;
    @FXML
    private TableColumn trackNameColumn;
    @FXML
    private VBox playlistVbox;
    @FXML
    private Label playlistsTitleBar;
    @FXML
    private ProgressBar songProgress;

    MusicControls musicControls = new MusicControls();
    Csv csv = new Csv();

    ObservableList<Song> songList = FXCollections.observableArrayList();

    Logger logger = Logger.getLogger(StageManager.class.getName());

    @FXML
    public void initialize() {
        int winWidth = 1920;
        int winHeight = 1080;
        musicVbox.setStyle("-fx-background-color: #28353b;");
        musicVbox.setPrefSize(winWidth,winHeight);
        playlistsTitleBar.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.ITALIC, 30));
        playlistsTitleBar.setText("Playlists");
        playlistsTitleBar.setTextFill(Color.rgb(255,255,255));
        trackTable.setStyle("-fx-background-color: #28353b; -fx-alternative-row-fill-visible: #3a4c53;");
        trackTable.setPrefWidth(((winWidth-20)/100)*90);
        songProgress.setStyle("-fx-background-insets: 0; -fx-padding: 0.75em; -fx-background-radius: 2;");
        BackgroundFill bf = new BackgroundFill(Color.rgb(46, 46, 46), CornerRadii.EMPTY, Insets.EMPTY);
        Background bg = new Background(bf);
        musicVbox.setBackground(bg);

        logger.log(Level.INFO, "Home screen formatting completed");

        trackTable.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

        });

        List<Song> loadedSongs;
        loadedSongs = csv.readSongsFromFile();
        loadedSongs.forEach((n) -> {
            logger.log(Level.INFO,"---------Added: " + n.getTrackName() + " -------------");
            songList.add(n);
        });
        trackTable.setItems(songList);

        trackTable.setRowFactory(param -> {
            final TableRow<Song> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                if (mouseEvent.getButton() == MouseButton.PRIMARY){
                    logger.log(Level.INFO, "Primary mouse button clicked");
                    String fileLocation = trackTable.getSelectionModel().getSelectedItem().getTrackFileLocation();
                    musicControls.playMp3(fileLocation);
                }
            });
            return tableRow;
        });

        Idle thread = new Idle();
        thread.start();
    }

    public void onPlayButtonClick() {
        musicControls.playMp3("/music/Noisestorm-CrabRave.mp3");
    }

    public void onShuffleButtonClick() {
//        csv.readSongsFromFile();
    }
}
