package com.kierancaruana.adamusicplayer.helpers.music;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;

public class MusicControls {
    public MediaPlayer player;
    public void playMp3(String filePath){
        try{
            Media media = new Media(getClass().getResource(filePath).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
    }
}
