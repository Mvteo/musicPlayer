package musicplayer.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Klasa kontrolera menu
 *
 * Odpowiada za obsługę przycisków menu
 */
public class MenuPaneController {
    @FXML
    /**
     * Zmienna menu uruchamiająca ładowanie pojedyńczej piosenki
     */
    private MenuItem fileMenuItem;

    @FXML
    /**
     * Zmienna menu uruchamiająca ładowanie piosenkek z folderu
     */
    private MenuItem dirMenuItem;

    @FXML
    /**
     * Zmienna menu wyłączająca odtwarzacz
     */
    private MenuItem closeMenuItem;

    @FXML
    /**
     * Zmienna menu wyświetlająca okienko z informacjami
     */
    private MenuItem aboutMenuItem;

    /**
     * Metoda zwracająca opcje menu
     * @return opcje
     */
    public MenuItem getFileMenuItem() {
        return fileMenuItem;
    }
    /**
     * Metoda zwracająca opcje menu
     * @return opcje
     */
    public MenuItem getDirMenuItem() {
        return dirMenuItem;
    }
    /**
     * Metoda zwracająca opcje menu
     * @return opcje
     */
    public MenuItem getCloseMenuItem() {
        return closeMenuItem;
    }
    /**
     * Metoda zwracająca opcje menu
     * @return opcje
     */
    public MenuItem getAboutMenuItem() {
        return aboutMenuItem;
    }

    /**
     * Metoda uruchamiająca pozostałe metody
     */
    public void initialize() {
        configureMenu();
    }

    /**
     * Metoda obsługująca wyświetlenie okna "MusicPlayer v1.0 - about"
     */
    private void configureMenu() {
        closeMenuItem.setOnAction(x -> Platform.exit());

        aboutMenuItem.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent arg0) {
                try {
                    Parent parent = FXMLLoader.load(getClass().getResource("/fxml/aboutPane.fxml"));
                    Scene scene = new Scene(parent);
                    Stage stage = new Stage();
                    stage.setTitle("MusicPlayer v1.0 - about");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace(); //ignore
                }
            }
        });
    }
}