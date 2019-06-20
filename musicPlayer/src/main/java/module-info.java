module musicplayer {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires jid3lib;

    exports musicplayer.main to javafx.graphics;
    opens musicplayer.controller to javafx.fxml;
    opens musicplayer.music to javafx.base;
}