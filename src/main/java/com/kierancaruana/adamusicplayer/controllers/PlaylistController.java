package com.kierancaruana.adamusicplayer.controllers;

import com.kierancaruana.adamusicplayer.objects.Song;

public class PlaylistController extends Song {
    private String[] playlists;



    public String[] getPlaylists() {
        return playlists;
    }

    public void setPlaylists(String[] playlists) {
        this.playlists = playlists;
    }

}
