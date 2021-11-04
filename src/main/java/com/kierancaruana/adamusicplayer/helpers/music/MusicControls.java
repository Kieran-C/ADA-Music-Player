package com.kierancaruana.adamusicplayer.helpers.music;


import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicControls {

    public MediaPlayer player;

    Logger logger = Logger.getLogger(MusicControls.class.getName());

    /**
     * Plays the mp3 file at the given path
     * @param filePath filepath of MP3 to play
     */
    public void playMp3(String filePath){
        try{
            if (player != null){
                player.stop();
            }
            Media media = new Media(getClass().getResource(filePath).toURI().toString());
            player = new MediaPlayer(media);
            player.play();
            logger.log(Level.INFO, "Now Playing file: " + filePath);

        }catch (URISyntaxException e){
            e.printStackTrace();
        }
    }

    /**
     * Pauses the mp3 file
     */
    public void pauseMp3(){
        if (player != null){
            player.pause();
        }
    }

    /**
     * un-pauses the mp3 file
     */
    public void unPauseMp3(){
        if (player != null){
            player.play();
        }
    }

    /**
     * gets the length of the mp3 file
     */
    public double getTrackLength(String filePath){
        double duration = player.getTotalDuration().toMillis();
        return duration;
    }
}
