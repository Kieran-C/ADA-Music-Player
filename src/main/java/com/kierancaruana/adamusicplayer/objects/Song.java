package com.kierancaruana.adamusicplayer.objects;

import javafx.beans.property.*;

public class Song {
        private final SimpleDoubleProperty trackId = new SimpleDoubleProperty();
        private final StringProperty trackName = new SimpleStringProperty();
        private final SimpleStringProperty trackArtist = new SimpleStringProperty();
        private final SimpleStringProperty trackAlbum = new SimpleStringProperty();
        private String trackFileLocation;

        public final double getTrackId(){return this.trackId.get();}
        public final void setTrackId(double id){trackId.set(id);}
        public SimpleDoubleProperty trackIdProperty() {return trackId;}

        public String getTrackName(){return this.trackName.get();}
        public void setTrackName(final String trackName){this.trackName.set(trackName);}
        public StringProperty trackNameProperty() {return trackName;}


        public String getTrackFileLocation() {
                return trackFileLocation;
        }

        public void setTrackFileLocation(String trackFileLocation) {
                this.trackFileLocation = trackFileLocation;
        }
}
