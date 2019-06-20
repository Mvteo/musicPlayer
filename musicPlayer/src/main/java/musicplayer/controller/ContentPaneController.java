package musicplayer.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import musicplayer.music.Songs;

public class ContentPaneController {

    private static final String TITLE_COLUMN = "Tytuł";
    private static final String AUTHOR_COLUMN = "Autor";
    private static final String ALBUM_COLUMN = "Album";

    @FXML
    private TableView<Songs> contentTable;

    public TableView<Songs> getContentTable() {
        return contentTable;
    }

    public void initialize() {
        configureTableColumns();
    }

    private void configureTableColumns() {
        TableColumn<Songs, String> titleColumn = new TableColumn<>(TITLE_COLUMN);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Songs, String> authorColumn = new TableColumn<>(AUTHOR_COLUMN);
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Songs, String> albumColumn = new TableColumn<>(ALBUM_COLUMN);
        albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));

        contentTable.getColumns().add(titleColumn);
        contentTable.getColumns().add(authorColumn);
        contentTable.getColumns().add(albumColumn);
    }
}