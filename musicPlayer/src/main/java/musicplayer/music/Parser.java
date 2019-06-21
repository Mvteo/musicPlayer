package musicplayer.music;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Klasa obsługująca pobieranie plików muzycznych do odtwarzacza
 */
public class Parser {


    /**
     * Metoda dodająca pojedynczą piosenke do odtwarzacza
     *
     * @param file wybrany przez nas plik
     * @return obiekt klasy Song który jest obslugiwany przez klase MusicPlayer
     * @throws IOException jest zwracany gdy nie powiedzie się obsługa plików
     * @throws TagException jest zwracany gdy nie powiedzie się obsługa tagów plików mp3
     */
    public static Song createSong(File file) throws IOException, TagException {


        MP3File mp3File = new MP3File(file);
        if(mp3File == null || !mp3File.hasID3v1Tag()){
            return new Song(file.getName(),"","",file.getAbsolutePath());
        }
        String absolutePath = file.getAbsolutePath();
        String title = mp3File.getID3v1Tag().getSongTitle();
        String author = mp3File.getID3v1Tag().getLeadArtist();
        String album = mp3File.getID3v1Tag().getAlbumTitle();


        return new Song(title, author, album, absolutePath);
    }

    /**
     * Klasa ładująca wszystkie pliki audio z folderu
     * @param dir jako parametr przekazujemy folder
     * @return lista obiektów klasy Song którą obsłuży klasa MusicPlayer
     * @throws IOException jest zwracany gdy nie powiedzie się obsługa plików
     * @throws TagException jest zwracany gdy nie powiedzie się obsługa tagów plików mp3
     */
    public static List<Song> createList(File dir) throws IOException, TagException {
        if(!dir.isDirectory()) {
            throw new IllegalArgumentException("Not a directory");
        }
        List<Song> songList = new ArrayList<>();
        File[] files = dir.listFiles();
        for(File f: files) {
            String fileExtension = f.getName().substring(f.getName().lastIndexOf(".") + 1);
            if(fileExtension.equals("mp3"))
                songList.add(createSong(f));
            else if (fileExtension.equals("wav"))
                songList.add(createSong(f));
        }

        return songList;
    }
}