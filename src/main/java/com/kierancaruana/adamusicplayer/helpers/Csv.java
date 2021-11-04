package com.kierancaruana.adamusicplayer.helpers;

import com.kierancaruana.adamusicplayer.objects.Song;

import java.io.File;
import java.io.FileNotFoundException;
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
                    songTemp.add(data.trim());
                    counter++;
                    if (songTemp.size() == 4){
                        Song newSong = new Song();
                        newSong.setTrackId(loops+1);
                        newSong.setTrackName(songTemp.get(0));
                        newSong.setTrackFileLocation(songTemp.get(1));
                        newSong.setTrackArtist(songTemp.get(2));
                        newSong.setTrackAlbum(songTemp.get(3));
                        loadedSongList.add(newSong);
                        counter = 0;
                        loops++;
                        songTemp.clear();
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
