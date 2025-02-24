package de.ietu.ietierverwaltung.gui;

import de.ietu.ietierverwaltung.Main;
import de.ietu.ietierverwaltung.gui.listview.AnimalCellFactory;
import de.ietu.ietierverwaltung.logic.AnimalManager;
import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.settings.AppText;
import de.ietu.ietierverwaltung.settings.View;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class DashboardController {

    public static final int SWITCH_SCENE_CLICK_COUNT = 2;


    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    @FXML
    private ListView<Animal> animalListView;
    @FXML
    private ImageView searchIconView;
    @FXML
    TextField txtSearch;
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @FXML
    private void initialize() {
        animalListView.setCellFactory(new AnimalCellFactory());

        animalListView.setItems(AnimalManager.getInstance().getAnimals());

        animalListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == SWITCH_SCENE_CLICK_COUNT)
                    openDetailSceneWithSelectedAnimal();
            }
        });
        animalListView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER)
                openDetailSceneWithSelectedAnimal();
        });

        //Imageview konfigurieren
        String url = Main.class.getResource("searchIcon.png").toString();
        Image image = new Image(url);
        searchIconView.setImage(image);
        searchIconView.setVisible(true);
        //Such Funktion
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            animalListView.setItems(AnimalManager.getInstance().getAnimalsFilteredBy(newValue));
        });
    }

    @FXML
    private void sortAnimals(ActionEvent event) {
        if (event.getSource() instanceof Button clickedButton) {
            switch (clickedButton.getText()) {
                case AppText.SPECIES -> AnimalManager.getInstance().sortBySpecies();
                case AppText.NAME -> AnimalManager.getInstance().sortByName();
                case AppText.AGE -> AnimalManager.getInstance().sortByAge();
                case AppText.COLOR -> AnimalManager.getInstance().sortByColor();
                case AppText.SPECIES_AND_NAME -> AnimalManager.getInstance().sortBySpeciesAndName();
            }
        }
    }


    private void openDetailSceneWithSelectedAnimal() {
        Animal selectedAnimal = animalListView.getSelectionModel().getSelectedItem();
        SceneManager.getInstance().openDetailScene(selectedAnimal);
    }


    @FXML
    public void switchToDetailScene() {
        SceneManager.getInstance().openScene(View.DETAIL);
    }


    //Ende der Region Methoden
}
