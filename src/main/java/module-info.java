module com.kierancaruana.adamusicplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;


    opens com.kierancaruana.adamusicplayer to javafx.fxml;
    exports com.kierancaruana.adamusicplayer;
    exports com.kierancaruana.adamusicplayer.controllers;
    opens com.kierancaruana.adamusicplayer.controllers to javafx.fxml;
}