package musicplayer.player;

import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import musicplayer.music.Songs;

import java.io.File;

public class MusicPlayer {
    private ObservableList<Songs> songList;

    private Media media;
    private MediaPlayer mediaPlayer;

    public MusicPlayer(ObservableList<Songs> songList) {
        this.songList = songList;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void play() {
        if(mediaPlayer != null && (mediaPlayer.getStatus() == Status.READY || mediaPlayer.getStatus() == Status.PAUSED)) {
            mediaPlayer.play();
        }
    }

    public void stop() {
        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.pause();
        }
    }

    public double getLoadedSongLength() {
        if(media != null) {
            return media.getDuration().toSeconds();
        } else {
            return 0;
        }
    }

    public void setVolume(double volume) {
        if(mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public void loadSong(int index) {
        if(mediaPlayer != null && mediaPlayer.getStatus() == Status.PLAYING) {
            mediaPlayer.stop();
        }
        Songs song = songList.get(index);
        media = new Media(new File(song.getFilePath()).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.statusProperty().addListener((observable, oldStatus, newStatus) -> {
            if (newStatus == Status.READY)
                mediaPlayer.setAutoPlay(true);
        });
    }
}
