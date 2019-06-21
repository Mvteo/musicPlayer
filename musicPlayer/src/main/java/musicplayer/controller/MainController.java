package musicplayer.controller;


import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import musicplayer.music.Parser;
import musicplayer.music.Song;
import musicplayer.player.MusicPlayer;

import java.io.File;

/**
 * Klasa kontrolera głownego
 *
 * Klasa łącząca kontrolery menu,przycisków oraz panelu głównego oraz posiadająca głowne metody do obsługi odtwarzacza
 */
public class MainController {
    @FXML
    /**
     * Zmienna kontrolera panelu głownego
     */
    private ContentPaneController contentPaneController;
    @FXML
    /**
     * Zmienna kontrolera panelu kontrolnego
     */
    private ControlPaneController controlPaneController;
    @FXML
    /**
     * Zmienna kontrolera panelu menu
     */
    private MenuPaneController menuPaneController;

    /**
     * Odtwarzacz muzyki
     *
     * On obsługuje pliki dźwiękowe
     */
    private MusicPlayer player;

    /**
     * Metoda uruchamiająca pozostałe metody
     */
    public void initialize() {
        createPlayer();
        configureTableClick();
        configureButtons();
        configureMenu();
    }

    /**
     * Metoda tworząca player
     */
    private void createPlayer() {
        ObservableList<Song> items = contentPaneController.getContentTable().getItems();
        player = new MusicPlayer(items);
    }

    /**
     * Metoda obsługująca zdarzenia kliknięcia myszką na tabelach
     */
    private void configureTableClick() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
        contentTable.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if (event.getClickCount() == 2) {
                int selectedIndex = contentTable.getSelectionModel().getSelectedIndex();
                playSelectedSong(selectedIndex);
            }
        });
    }

    /**
     * Metoda odtwarzająca wybraną piosenkę
     *
     * @param selectedIndex wskaźnik na wybraną aktualnie piosenkę
     */
    private void playSelectedSong(int selectedIndex) {
        player.loadSong(selectedIndex);
        configureProgressBar();
        configureVolume();
        controlPaneController.getPlayButton().setSelected(true);
    }

    /**
     * Metoda do obsługi suwaka progresu piosenki
     *
     * Pozwala przesuwać piosenkę do wybranego momentu
     */
    private void configureProgressBar() {
        Slider progressSlider = controlPaneController.getProgressSlider();
        player.getMediaPlayer().setOnReady(() -> progressSlider.setMax(player.getLoadedSongLength()));
        player.getMediaPlayer().currentTimeProperty().addListener((arg, oldVal, newVal) ->
                progressSlider.setValue(newVal.toSeconds()));
        progressSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(progressSlider.isValueChanging()) {
                player.getMediaPlayer().seek(Duration.seconds(newValue.doubleValue()));
            }

        });
    }

    /**
     * Metoda obsługująca poziom głośności
     */
    private void configureVolume() {
        Slider volumeSlider = controlPaneController.getVolumeSlider();
        volumeSlider.valueProperty().unbind();
        volumeSlider.setMax(1.0);
        volumeSlider.valueProperty().bindBidirectional(player.getMediaPlayer().volumeProperty());
    }

    /**
     * Metoda obsługująca przyciski
     *
     * Po kliknięciu prevButtona wybieramy poprzednią piosenkę
     * Po kliknięciu playButton włączamy piosenke lub wstrzymujemy
     * Po kliknięciu nextButton wybieramy następną piosenkę
     */
    private void configureButtons() {
        TableView<Song> contentTable = contentPaneController.getContentTable();
        ToggleButton playButton = controlPaneController.getPlayButton();
        Button prevButton = controlPaneController.getPreviousButton();
        Button nextButton = controlPaneController.getNextButton();

        playButton.setOnAction(event -> {
            if (playButton.isSelected()) {
                player.play();
            } else {
                player.stop();
            }
        });

        nextButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() + 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });

        prevButton.setOnAction(event -> {
            contentTable.getSelectionModel().select(contentTable.getSelectionModel().getSelectedIndex() - 1);
            playSelectedSong(contentTable.getSelectionModel().getSelectedIndex());
        });
    }

    /**
     * Metoda obsługująca menu
     *
     * Obsługuje opcje załadowania pojedyńczej piosenki lub pobrania listy piosenek z danego folderu
     */
    private void configureMenu() {
        MenuItem openFile = menuPaneController.getFileMenuItem();
        MenuItem openDir = menuPaneController.getDirMenuItem();

        openFile.setOnAction(event -> {
            FileChooser fc = new FileChooser();
           // fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Mp3", "*.mp3"));
            File file = fc.showOpenDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().add(Parser.createSong(file));
                showMessage("Załadowano plik " + file.getName());
            } catch (Exception e) {
                showMessage("Nie można otworzyć pliku " + file.getName());
            }
        });

        openDir.setOnAction(event -> {
            DirectoryChooser dc = new DirectoryChooser();
            File dir = dc.showDialog(new Stage());
            try {
                contentPaneController.getContentTable().getItems().addAll(Parser.createList(dir));
                showMessage("Wczytano dane z folderu " + dir.getName());
            } catch (Exception e) {
                showMessage("Wystąpił błąd podczas odczytu folderu");
            }
        });
    }

    /**
     * Metoda wyświetlająca daną wiadomość na dolnym pasku
     *
     * @param message wiadomość która ma być wyświetlona
     */
    private void showMessage(String message) {
        controlPaneController.getMessageTextField().setText(message);
    }
}
