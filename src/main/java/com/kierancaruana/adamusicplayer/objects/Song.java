package com.kierancaruana.adamusicplayer.objects;

import javafx.beans.property.*;

import java.util.ArrayList;
import java.util.List;

public class Song {
        private final SimpleIntegerProperty trackId = new SimpleIntegerProperty();
        private final StringProperty trackName = new SimpleStringProperty();
        private final SimpleStringProperty trackArtist = new SimpleStringProperty();
        private final SimpleStringProperty trackAlbum = new SimpleStringProperty();
        private List<String> incInPlaylist = new ArrayList<String>();
        private String trackFileLocation;
        private long trackLength;

        public final double getTrackId(){return this.trackId.get();}
        public final void setTrackId(int id){trackId.set(id);}
        public SimpleIntegerProperty trackIdProperty() {return trackId;}

        public String getTrackName(){return this.trackName.get();}
        public void setTrackName(final String trackName){this.trackName.set(trackName);}
        public StringProperty trackNameProperty() {return trackName;}

        public String getTrackArtist(){return this.trackArtist.get();}
        public void setTrackArtist(final String trackArtist){this.trackArtist.set(trackArtist);}
        public StringProperty trackArtistProperty() {return trackArtist;}

        public String getTrackAlbum(){return this.trackAlbum.get();}
        public void setTrackAlbum(final String trackAlbum){this.trackAlbum.set(trackAlbum);}
        public StringProperty trackAlbumProperty() {return trackAlbum;}

        public List<String> getIncInPlaylist() {
                return incInPlaylist;
        }

        public void setIncInPlaylist(List<String> incInPlaylist) {
                this.incInPlaylist = incInPlaylist;
        }

        public void addSongToPlaylist(String playlistName){
                this.incInPlaylist.add(playlistName);
        }

        /**
         * checks if the song is in the playlist
         * @param playlistName playlist name
         * @return true if the song is in the playlist, false otherwise
         */
        public boolean isSongInPlaylist(String playlistName){
                if (this.incInPlaylist.contains(playlistName)){
                        return true;
                }else{
                        return false;
                }
        }

        public long getTrackLength() {
                return trackLength;
        }

        public void setTrackLength(long trackLength) {
                this.trackLength = trackLength;
        }

        public String getTrackFileLocation() {return trackFileLocation;}
        public void setTrackFileLocation(String trackFileLocation) {
                this.trackFileLocation = trackFileLocation;
        }
}
