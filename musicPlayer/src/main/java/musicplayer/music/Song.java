package musicplayer.music;

/**
 * Klasa tworzaca obiekty Song
 *
 * Są one obsługiwane przez klase MusicPlayer z tych obiektów możemy pozyskać takie informacje jak:
 * ścierzka pliku, tytuł piosenki, autora piosenki, album
 */
public class Song {
    private String title;
    private String author;
    private String album;
    private String filePath;

    /**
     * Konstruktor klasy Song. Przy tworzeniu obiektu nadaje mu podane wartości.
     * @param title Tytuł utworu
     * @param author Autor utworu
     * @param album Album
     * @param filePath ścieżka pliku
     */
    public Song(String title, String author, String album, String filePath) {
        this.title = title;
        this.author = author;
        this.album = album;
        this.filePath = filePath;
    }

    /**
     * Metoda zwracajaca tytuł piosenki
     * @return tytuł
     */
    public String getTitle() {
        return title;
    }

    /**
     * Metoda ustawia podany tytuł
     * @param title tytuł piosenki
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metoda zwracająca autora utworu
     * @return wykonawce utworu
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Metoda ustawia autora
     * @param author nazwa wykonawcy
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *Metoza zwracająca nazwe albumu
     * @return album utworu
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Metoda ustawia albym utworu
     * @param album nazwa albumu
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * Metoda zwraca ścieżkę pliku
     * @return ścieżka pliku
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Metoda ustawia ścieżkę pliku
     * @param filePath ścieżka pliku
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    /**
     * Metoda zwraca do String zawartość obiektu
     */
    public String toString() {
        return "Mp3Song [title=" + title + ", author=" + author + ", album=" + album + "]";
    }
}