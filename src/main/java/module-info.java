module com.kierancaruana.adamusicplayer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kierancaruana.adamusicplayer to javafx.fxml;
    exports com.kierancaruana.adamusicplayer;
}
