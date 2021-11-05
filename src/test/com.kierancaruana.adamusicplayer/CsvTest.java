package com.kierancaruana.adamusicplayer;

import com.kierancaruana.adamusicplayer.helpers.Csv;
import com.kierancaruana.adamusicplayer.objects.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvTest {
    Csv csv = new Csv();
    @Test
    void testReadSongsSuccessful() {
        List<Song> songList = csv.readSongsFromFile();
        Assertions.assertEquals(4, songList.size());
    }
    @Test
    void testGetFirstSongName() {
        List<Song> songList = csv.readSongsFromFile();
        Assertions.assertEquals("Everything You Need", songList.get(0).getTrackName());
    }
}
