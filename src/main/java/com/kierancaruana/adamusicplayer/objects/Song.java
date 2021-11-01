package com.kierancaruana.adamusicplayer.objects;

import javafx.beans.property.*;

public class Song {
        private DoubleProperty trackId = new SimpleDoubleProperty();
        private StringProperty trackName = new SimpleStringProperty();
        private StringProperty trackArtist = new SimpleStringProperty();
        private StringProperty trackAlbum = new SimpleStringProperty();
        private String trackFileLocation;

        public final double getTrackId(){return trackId.get();}
        public final void setTrackId(double id){trackId.set(id);}
        public DoubleProperty trackIdProperty() {return trackId;}



        public String getTrackFileLocation() {
                return trackFileLocation;
        }

        public void setTrackFileLocation(String trackFileLocation) {
                this.trackFileLocation = trackFileLocation;
        }
}
