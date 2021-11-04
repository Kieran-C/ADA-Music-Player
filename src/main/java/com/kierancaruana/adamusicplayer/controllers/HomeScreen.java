package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.helpers.Csv;
import com.kierancaruana.adamusicplayer.helpers.Idle;
import com.kierancaruana.adamusicplayer.helpers.music.MusicControls;
import com.kierancaruana.adamusicplayer.objects.Song;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.kierancaruana.adamusicplayer.LaunchApplication.primaryStage;


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
    @FXML
    VBox playlistButtonList;
    @FXML
    private TextField searchBox;

    MusicControls musicControls = new MusicControls();
    Csv csv = new Csv();

    ObservableList<Song> masterSongList = FXCollections.observableArrayList();
    ObservableList<Song> currentSongList = FXCollections.observableArrayList();
    ObservableList<String> playlists = FXCollections.observableArrayList();

    Logger logger = Logger.getLogger(StageManager.class.getName());

    Song nowPlaying;
    String currentPlaylist;

    @FXML
    public void initialize() {
        playlists.add("All Songs");
        playlists.add("My Favourites");
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
            masterSongList.add(n);
        });
        currentSongList.clear();
        currentSongList.addAll(loadTrackTable(masterSongList));
        trackTable.setItems(filterSongList());

        trackTable.setRowFactory(param -> {
            final TableRow<Song> tableRow = new TableRow<>();
            tableRow.setOnMouseClicked(mouseEvent -> {
                nowPlaying = trackTable.getSelectionModel().getSelectedItem();
                if ((mouseEvent.getButton() == MouseButton.PRIMARY) && (mouseEvent.getClickCount() == 2)){
                    String fileLocation = trackTable.getSelectionModel().getSelectedItem().getTrackFileLocation();
                    musicControls.playMp3(fileLocation);
                    playButton.setText("Pause");
                }
                if (mouseEvent.getButton() == MouseButton.SECONDARY){
                    ContextMenu contextMenu = new ContextMenu();
                    MenuItem playSongOption = new MenuItem("Play");
                    Menu addSongToPlaylist = new Menu("Add to Playlist");
                    playlists.forEach(name -> {
                        MenuItem playlistOption = new MenuItem(name);
                        playlistOption.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (!(nowPlaying.isSongInPlaylist(name))){
                                    nowPlaying.addSongToPlaylist(name);
                                    System.out.println(nowPlaying.getIncInPlaylist());
                                }else{
                                    logger.log(Level.INFO, "Song already in playlist - " + name);
                                }
                            }
                        });
                        addSongToPlaylist.getItems().add(playlistOption);
                    });
                    contextMenu.getItems().addAll(playSongOption, addSongToPlaylist);

                    playSongOption.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            musicControls.playMp3(nowPlaying.getTrackFileLocation());
                            playButton.setText("Pause");
                        }
                    });
                    tableRow.setContextMenu(contextMenu);
                }
            });
            return tableRow;
        });

        Idle thread = new Idle();
        thread.start();

        loadPlaylistList();
    }

    /**
     * Filters the currentSongList based on text in search text field
     *
     * @return filtered list of songs
     */
    private FilteredList<Song> filterSongList() {
        final FilteredList<Song> songsFiltered = new FilteredList<>(currentSongList, p -> true);
        searchBox.textProperty().addListener((observable, oldValue, newValue) -> songsFiltered.setPredicate(song -> {
            System.out.println("New Value: " + newValue);
            trackTable.getSelectionModel().clearSelection();
            if (newValue.isBlank()) {
                return true;
            }
            final String lowerCaseFilter = newValue.toLowerCase().trim();
            boolean contains = song.getTrackName().toLowerCase().contains(lowerCaseFilter);
            if (contains){
                System.out.println("Match Found: " + song.getTrackName());
            }
            return contains;
        }));
        return songsFiltered;
    }

    /**
     *  load
     */
    public void loadPlaylistList(){
        playlistButtonList.getChildren().clear();
        if (playlists != null){
            playlists.forEach(list -> {
                Button b1 = new Button(list);
                b1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (list.equals("All Songs")){
                            currentPlaylist = null;
                        }else{
                            currentPlaylist = list;
                        }
                        System.out.println("Current playlist set to: " + currentPlaylist);
                        nowPlaying = null;
                        currentSongList.clear();
                        currentSongList.addAll(loadTrackTable(masterSongList));
                    }
                });
                playlistButtonList.getChildren().add(b1);
            });
        }
    }

    /**
     * loads the correct songs into current song list to be displayed in the music table
     * @param masterSongList List containing all the songs
     * @return List of songs with the correct songs needing to be displayed in the song table
     */
    public List<Song> loadTrackTable(ObservableList<Song> masterSongList){
        if (currentPlaylist == null){
            System.out.println("Current plist null");
            List<Song> returnTrackList = new ArrayList<>();
            returnTrackList = masterSongList;
            return returnTrackList;
        }else{
            List<Song> returnTrackList = new ArrayList<>();
            masterSongList.forEach(song -> {
                System.out.println("Loop");
                if (song.isSongInPlaylist(currentPlaylist)){
                    System.out.println("Added " + " to returnTrackList");
                    returnTrackList.add(song);
                }
            });
            return returnTrackList;
        }
    }

    /**
     * pauses and unpauses song. If no song is playing then it starts playing the first song in the list
     */
    public void onPlayButtonClick() {
        if ((playButton.getText()).equals("Play")){
            System.out.println("Now Playing: " + nowPlaying);
            if (nowPlaying == null){
                musicControls.playMp3(currentSongList.get(0).getTrackFileLocation());
                nowPlaying = currentSongList.get(0);
            }else{
                musicControls.unpauseMp3();
            }
            playButton.setText("Pause");
        }else{
            musicControls.pauseMp3();
            playButton.setText("Play");
        }
    }

    /**
     * Generates a random order for the songs in current playlist then queues them and plays them one after another
     */
    public void onShuffleButtonClick() {
        playButton.setText("Pause");
        new Thread(() -> {
            int numOfSongs = currentSongList.size();
            List<Integer> songIds = new ArrayList<Integer>();
            currentSongList.forEach(song -> {
                songIds.add((int) song.getTrackId());
            });
            System.out.println("Song ID's: " + songIds);
            int counter = 0;
            List<Integer> songOrder = new ArrayList<Integer>();
            while (counter < numOfSongs){
                int songNum = (int) Math.floor(Math.random()*(numOfSongs));
                if (!(songOrder.contains(songNum))){
                    songOrder.add(songNum);
                    counter++;
                }
            }
            counter = 0;
            songOrder.forEach(songOrderNum -> {
                System.out.println("Order: " + songOrderNum);
            });
            while (counter < songOrder.size()){
//                System.out.println("Shuffle Playing: " + getSongObject(songIds.get(songOrder.get(counter))).getTrackFileLocation());
                String musicFile = (getSongObject(songIds.get(songOrder.get(counter))-1)).getTrackFileLocation();
                musicControls.playMp3(musicFile);
                nowPlaying = (getSongObject(songIds.get(songOrder.get(counter))-1));
                double trackLength = musicControls.getTrackLength(musicFile);
                while (Double.isNaN(trackLength)){
                    trackLength = musicControls.getTrackLength(musicFile);
                    System.out.println("Waiting for trackLength");
                }
                try {
//                    Thread.sleep(10000);

                    System.out.println("Track Length: " + trackLength);
                    Thread.sleep((long) trackLength);
                    counter++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * returns song with provided songId
     * @param songId
     * @return Song object of song with ID requested
     */
    public Song getSongObject(int songId){
        return masterSongList.get(songId);
    }

    /**
     * Opens new window to create playlist and adds it to playlist list
     */
    public void onNewPlaylistButtonClick() {
        final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(primaryStage);
        Button getNameBtn = new Button("Create");
        Text titlePopupText = new Text();
        titlePopupText.setText("Create New Playlist");
        TextField nameField = new TextField();
        getNameBtn.setOnAction(actionEvent -> {
            playlists.add(nameField.getText());
            logger.log(Level.INFO, "Playlist created: " + playlists.get((playlists.size())-1));
            dialog.hide();
            loadPlaylistList();
        });
        nameField.setText("Playlist Name");
        VBox dialogVbox = new VBox(titlePopupText, nameField, getNameBtn);
        Scene dialogScene = new Scene(dialogVbox, 200, 200);
        dialog.setScene(dialogScene);
        dialog.show();
    }
}
