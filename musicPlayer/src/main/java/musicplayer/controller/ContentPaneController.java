package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import musicplayer.music.Song;

/**
 * Klasa kontrolera głównego panela
 *
 * W tym panelu wyświetlane są załadowane piosenki
 */
public class ContentPaneController {

    private static final String TITLE_COLUMN = "Tytuł";
    private static final String AUTHOR_COLUMN = "Autor";
    private static final String ALBUM_COLUMN = "Album";

    @FXML
    /**
     * Tablica piosenek
     */
    private TableView<Song> contentTable;

    /**
     * Metoda zwracająca tablice
     * @return tablica wyświetlana w odtwarzaczu
     */
    public TableView<Song> getContentTable() {
        return contentTable;
    }

    /**
     * Metoda urchamiająca pozostałe metody
     */
    public void initialize() {
        configureTableColumns();
    }

    /**
     * Metoda dzieląca panel głowny na 3 tabele: tytuł, autora, album
     */
    private void configureTableColumns() {
        TableColumn<Song, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Song, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Song, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }
}