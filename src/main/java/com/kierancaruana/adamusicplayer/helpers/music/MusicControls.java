package com.kierancaruana.adamusicplayer.helpers.music;


import com.kierancaruana.adamusicplayer.controllers.StageManager;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicControls {

    public MediaPlayer player;

    Logger logger = Logger.getLogger(MusicControls.class.getName());

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

    public void pauseMp3(){
        if (player != null){
            player.pause();
        }
    }

    public void unpauseMp3(){
        if (player != null){
            player.play();
        }
    }

    public void playMp3Queue(List<Integer> songOrder){

    }

    public double getTrackLength(String filePath){
        double duration = player.getTotalDuration().toMillis();
        return duration;
    }
}
