package musicplayer.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * Głowna klasa projektu
 */
    public class Main extends Application {

    /**
     * Metoda main
     */
        public static void main(String[] args) {
            launch(args);
        }

    /**
     * Metoda uruchamiająca odtwarzacz
     * @param stage wskaźnik na panel JavaFX
     * @throws Exception wyrzuca wyjątek
     */
        @Override
        public void start(Stage stage) throws Exception {
            Pane mainPane = FXMLLoader.load(getClass().getResource("/fxml/mainPane.fxml"));
            Scene scene = new Scene(mainPane);
            stage.setScene(scene);
            stage.setTitle("Music Player");
            stage.show();
        }
        }


