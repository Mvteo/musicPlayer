package musicplayer.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import musicplayer.music.Songs;

public class MainController {

    @FXML
    private MenuItem fileMenuItem;

    @FXML
    private MenuItem dirMenuItem;

    @FXML
    private MenuItem closeMenuItem;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private TableView<Songs> contentTable;

    @FXML
    private Button previousButton;

    @FXML
    private ToggleButton playButton;

    @FXML
    private Button nextButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Slider progressSlider;

    public void initialize() {
        configureButtons();
        configureVolume();
        configureSliders();
        configureTableColumns();
        createTestData();
    }



        private static final String TITLE_COLUMN = "Tytuł";
        private static final String AUTHOR_COLUMN = "Autor";
        private static final String ALBUM_COLUMN = "Album";




        private void configureTableColumns() {
            TableColumn<Songs, String> titleColumn = new TableColumn<Songs, String>(TITLE_COLUMN);
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

            TableColumn<Songs, String> authorColumn = new TableColumn<Songs, String>(AUTHOR_COLUMN);
            authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

            TableColumn<Songs, String> albumColumn = new TableColumn<Songs, String>(ALBUM_COLUMN);
            albumColumn.setCellValueFactory(new PropertyValueFactory<>("album"));

            contentTable.getColumns().add(titleColumn);
            contentTable.getColumns().add(authorColumn);
            contentTable.getColumns().add(albumColumn);
        }

        private void createTestData() {
            ObservableList<Songs> items = contentTable.getItems();
            items.add(new Songs("a", "a", "a", "a"));
            items.add(new Songs("b", "b", "b", "b"));
            items.add(new Songs("c", "c", "c", "c"));
            items.add(new Songs("d", "d", "d", "d"));
        }


    private void configureVolume() {
        volumeSlider.addEventFilter(MouseEvent.MOUSE_PRESSED, event ->
                System.out.println("Wciśnięto przycisk na suwaku głośności")
        );
    }

    private void configureSliders() {
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                System.out.println("Zmiana głośności " + newValue.doubleValue())
        );

        progressSlider.valueProperty().addListener(x ->
                System.out.println("Przesunięcie piosenki")
        );
    }

    private void configureButtons() {
        previousButton.setOnAction(event -> System.out.println("Poprzednia piosenka"));
        nextButton.setOnAction(x -> System.out.println("Następna piosenka"));
        playButton.setOnAction(event -> {
            if(playButton.isSelected()) {
                System.out.println("Play");
            } else {
                System.out.println("Stop");
            }
        });
    }
}