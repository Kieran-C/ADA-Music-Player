package com.kierancaruana.adamusicplayer.helpers;

import com.kierancaruana.adamusicplayer.LaunchApplication;
import com.kierancaruana.adamusicplayer.controllers.StageManager;
import com.kierancaruana.adamusicplayer.objects.Song;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Csv {
    Logger logger = Logger.getLogger(Csv.class.getName());
    public List<Song> readSongsFromFile(){
        List<Song> loadedSongList = new ArrayList<Song>();
        try{
            File file = new File("src/main/java/com/kierancaruana/adamusicplayer/musicDb.csv");
            Scanner scanner = new Scanner(file);
            scanner.useDelimiter(",");
            int counter = 0;
            int loops = 0;
            List<String> songTemp = new ArrayList<String>();
            while (scanner.hasNext()){
                String data = scanner.next();
                if (counter < 4){
                    logger.log(Level.INFO,"Counter: " + counter);
                    songTemp.add(data);
                    logger.log(Level.INFO, "Added " + data + " to temp list");
                    counter++;
                    if (songTemp.size() == 4){
                        logger.log(Level.INFO,"Counter = " + counter);
                        Song newSong = new Song();
                        newSong.setTrackId(loops+1);
                        newSong.setTrackName(songTemp.get(0));
                        newSong.setTrackFileLocation(songTemp.get(1));
                        loadedSongList.add(newSong);
                        logger.log(Level.INFO,"New song track ID: " + newSong.getTrackId());
                        counter = 0;
                        loops++;
                    }
                }
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return loadedSongList;
    }
}
