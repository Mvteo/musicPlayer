package musicplayer.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import musicplayer.music.Song;

import java.io.File;

/**
 * Klasa obsługująca plik audio
 */
public class MusicPlayer {
    private ObservableList<Song> songList;

    private Media media;
    private MediaPlayer mediaPlayer;

    /**
     * Konstruktor klasy ładujący piosenki do odtwarzacza
     * @param songList lista piosenek
     */
    public MusicPlayer(ObservableList<Song> songList) {
        this.songList = songList;
    }

    /**
     * Metoda zwracająca media playera
     * @return media playera
     */
    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    /**
     * Metoda odtwarzająca piosenkę
     */
    public void play() {
        if(mediaPlayer != null && (mediaPlayer.getStatus() == Status.READY || mediaPlayer.getStatus() == Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    /**
     * Metoda zatrzymująca piosenkę
     */
    public void stop() {
        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    /**
     * Metoda zwracająca długość załadowanej piosenki
     * @return długość załadowanej piosenki
     */
    public double getLoadedSongLength() {
        if(media != null) {
            return media.getDuration().toSeconds();
        } else {
            return 0;
        }
    }

    /**
     * Metoda ustawiająca głośność na podany poziom
     * @param volume poziom głośności
     */
    public void setVolume(double volume) {
        if(mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    /**
     * Metoda ładująca wybraną piosenkę
     * @param index index wybranej piosenki
     */
    public void loadSong(int index) {
        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.stop();
        }
        Song song = songList.get(index);
        media = new Media(new File(song.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldStatus, newStatus) -> {
            if (newStatus == Status.READY)
                mediaPlayer.setAutoPlay(true);
        });
    }
}
