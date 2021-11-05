package com.kierancaruana.adamusicplayer;

import com.kierancaruana.adamusicplayer.objects.Song;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SongTest {
    Song song = new Song();
    @Test
    void testCreateSong(){
        song.setTrackName("test");
        song.setTrackArtist("test");
        song.setTrackAlbum("test");
        Assertions.assertEquals("test", song.getTrackName());
        Assertions.assertEquals("test", song.getTrackArtist());
        Assertions.assertEquals("test", song.getTrackAlbum());
    }
}
