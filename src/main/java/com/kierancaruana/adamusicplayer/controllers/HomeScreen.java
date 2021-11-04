package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.helpers.Csv;
import com.kierancaruana.adamusicplayer.helpers.Idle;
import com.kierancaruana.adamusicplayer.helpers.music.MusicControls;
import com.kierancaruana.adamusicplayer.objects.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
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
    @FXML
    private Button playButton;

    MusicControls musicControls = new MusicControls();
    Csv csv = new Csv();

    ObservableList<Song> songList = FXCollections.observableArrayList();

    Logger logger = Logger.getLogger(StageManager.class.getName());

    Song nowPlaying;

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

        ContextMenu contextMenu = new ContextMenu();
        MenuItem playSongOption = new MenuItem("Play");
        MenuItem addSongToPlaylist = new MenuItem("Add to Playlist");
        contextMenu.getItems().addAll(playSongOption, addSongToPlaylist);

        playSongOption.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                musicControls.playMp3(nowPlaying.getTrackFileLocation());
                playButton.setText("Pause");
            }
        });

        trackTable.setRowFactory(param -> {
            final TableRow<Song> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                nowPlaying = trackTable.getSelectionModel().getSelectedItem();
                if ((mouseEvent.getButton() == MouseButton.PRIMARY) && (mouseEvent.getClickCount() == 2)){
                    logger.log(Level.INFO, "Primary mouse button clicked");
                    String fileLocation = trackTable.getSelectionModel().getSelectedItem().getTrackFileLocation();
                    musicControls.playMp3(fileLocation);
                    playButton.setText("Pause");
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY){
                    tableRow.setContextMenu(contextMenu);
                }
            });
            return tableRow;
        });

        Idle thread = new Idle();
        thread.start();
    }



    public void onPlayButtonClick() {
        System.out.println("Button clicked: " + playButton.getText());
        if ((playButton.getText()).equals("Play")){
            System.out.println("Entered first if");
            if (nowPlaying == null){
                musicControls.playMp3(songList.get(0).getTrackFileLocation());
                nowPlaying = songList.get(0);
            }else{
                musicControls.unpauseMp3();
            }
            playButton.setText("Pause");
        }else{
            musicControls.pauseMp3();
            playButton.setText("Play");
        }
    }

    public void onShuffleButtonClick() {
        new Thread(() -> {
            int numOfSongs = songList.size();
            int counter = 0;
            List<Integer> songOrder = new ArrayList<Integer>();
            while (counter < numOfSongs){
                int songNum = (int) Math.round(Math.random()*(numOfSongs));
                if (!(songOrder.contains(songNum))){
                    System.out.println("Song added: " + songNum);
                    songOrder.add(songNum);
                    counter++;
                }
            }
            System.out.println("Song Order Length: " + songOrder.size());
            System.out.println("Song Order:");

            counter = 0;
            while (counter < songOrder.size()){
                System.out.println("In player loop counter: " + counter);
                musicControls.playMp3((getSongObject(songOrder.get(counter))).getTrackFileLocation());
                try {
//                    Thread.sleep(10000);
                    Thread.sleep((long) musicControls.getTrackLength());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            }
        }).start();
    }

    public Song getSongObject(int songId){
        return songList.get(songId);
    }
}
