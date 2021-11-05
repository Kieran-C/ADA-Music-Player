package com.kierancaruana.adamusicplayer.helpers;

import com.kierancaruana.adamusicplayer.LaunchApplication;
import com.kierancaruana.adamusicplayer.objects.Song;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Csv {
    Logger logger = Logger.getLogger(Csv.class.getName());

    /**
     * Reads songs in from the csv file and saves them to the songs playlist as song objects
     * @return List of Songs containing all songs from csv file
     */
    public List<Song> readSongsFromFile(){
        List<Song> loadedSongList = new ArrayList<Song>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream is = classLoader.getResourceAsStream("musicDb.csv");
        Scanner scanner = new Scanner(is);
        scanner.useDelimiter(",");
        int counter = 0;
        int loops = 0;
        List<String> songTemp = new ArrayList<String>();
        while (scanner.hasNext()){
            String data = scanner.next();
            if (counter < 4){
                songTemp.add(data.trim());
                counter++;
                if (songTemp.size() == 4){
                    Song newSong = new Song();
                    newSong.setTrackId(loops+1);
                    newSong.setTrackName(songTemp.get(0));
                    newSong.setTrackFileLocation(songTemp.get(1));
                    newSong.setTrackArtist(songTemp.get(2));
                    newSong.setTrackAlbum(songTemp.get(3));
                    new Thread(() -> {
                        try {
                            MediaPlayer playerInfo;
                            Media media = new Media(getClass().getResource(newSong.getTrackFileLocation()).toURI().toString());
                            playerInfo = new MediaPlayer(media);
                            double trackLength = playerInfo.getTotalDuration().toMillis();
                            while (Double.isNaN(trackLength)){
                                System.out.printf("Track " + newSong.getTrackName() + " is not ready yet.\n");
                                trackLength = playerInfo.getTotalDuration().toMillis();
                            }
                            newSong.setTrackLength((long) trackLength);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }).start();
                    loadedSongList.add(newSong);
                    counter = 0;
                    loops++;
                    songTemp.clear();
                }
            }
        }
        scanner.close();
        return loadedSongList;
    }

    float getSongLength(File file) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            AudioFormat format = audioInputStream.getFormat();
            long audioFileLength = file.length();
            int frameSize = format.getFrameSize();
            float frameRate = format.getFrameRate();
            float durationInSeconds = (audioFileLength / (frameSize * frameRate));
            return durationInSeconds;
        }catch (UnsupportedAudioFileException | IOException e){
            e.printStackTrace();
        }
        return 0;
    }
}
