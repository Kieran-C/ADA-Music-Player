module com.kierancaruana.adamusicplayer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kierancaruana.adamusicplayer to javafx.fxml;
    exports com.kierancaruana.adamusicplayer;
    exports com.kierancaruana.adamusicplayer.controllers;
    opens com.kierancaruana.adamusicplayer.controllers to javafx.fxml;
}
