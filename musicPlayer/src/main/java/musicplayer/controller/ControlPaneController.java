package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

/**
 * Klasa kontrolera przycisków oraz suwaków
 */
public class ControlPaneController {
    @FXML
    /**
     * Przycisk "Poprzednia piosenka"
     */
    private Button previousButton;

    @FXML
    /**
     * Przycisk "Play/Pause"
     */
    private ToggleButton playButton;

    @FXML
    /**
     * Przycisk "Następna piosenka"
     */
    private Button nextButton;

    @FXML
    /**
     * Suwak poziomu głośności
     */
    private Slider volumeSlider;

    @FXML
    /**
     * Suwak progresu piosenki
     */
    private Slider progressSlider;

    @FXML
    /**
     * Pole wiadomości tekstowej
     */
    private TextField messageTextField;

    /**
     * @return przyskisk poprzedniej piosenki
     */
    public Button getPreviousButton() {
        return previousButton;
    }

    /**
     * @return przycisk graj/pauza
     */
    public ToggleButton getPlayButton() {
        return playButton;
    }

    /**
     * @return przyskisk następnej piosenki
     */
    public Button getNextButton() {
        return nextButton;
    }

    /**
     * @return suwak głośności
     */
    public Slider getVolumeSlider() {
        return volumeSlider;
    }

    /**
     * @return suwak piosenki
     */
    public Slider getProgressSlider() {
        return progressSlider;
    }

    /**
     * @return pole tekstowe
     */
    public TextField getMessageTextField() {
        return messageTextField;
    }
}