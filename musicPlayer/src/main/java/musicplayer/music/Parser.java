package musicplayer.music;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    public static Songs createSong(File file) throws IOException, TagException {
        MP3File mp3File = new MP3File(file);
        String absolutePath = file.getAbsolutePath();
        String title = mp3File.getID3v1Tag().getSongTitle();
        String author = mp3File.getID3v1Tag().getLeadArtist();
        String album = mp3File.getID3v1Tag().getAlbumTitle();
        return new Songs(title, author, album, absolutePath);
    }

    public static List<Songs> createList(File dir) throws IOException, TagException {
        if(!dir.isDirectory()) {
            throw new IllegalArgumentException("Not a directory");
        }
        List<Songs> songList = new ArrayList<>();
        File[] files = dir.listFiles();
        for(File f: files) {
            String fileExtension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
            if(fileExtension.equals("mp3"))
                songList.add(createSong(f));
        }

        return songList;
    }
}