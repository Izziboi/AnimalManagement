package de.ietu.ietierverwaltung.gui;

import de.ietu.ietierverwaltung.logic.AnimalManager;
import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.settings.AppText;
import de.ietu.ietierverwaltung.settings.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import java.util.Optional;

public class DetailController {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    @FXML
    private TextField txtSpecies;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtColor;

    @FXML
    private Button btnConvey;

    private Animal selectedAnimal;
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    public void setSelectedAnimalAndDetails(Animal animal) {
        selectedAnimal = animal;

        if (selectedAnimal != null) {
            txtSpecies.setText(selectedAnimal.getSpecies());
            txtName.setText(selectedAnimal.getName());
            txtAge.setText(String.valueOf(selectedAnimal.getAge()));
            txtColor.setText(selectedAnimal.getColor());
            btnConvey.setDisable(false);
        }
    }


    @FXML
    private void save() {
        try {
            String species = txtSpecies.getText();
            String name = txtName.getText();
            int age = Integer.parseInt(txtAge.getText());
            String color = txtColor.getText();

            if (species.isBlank() || name.isBlank() || color.isBlank()) {
                AlertManager.getInstance().showError(AppText.ERROR_EMPTY_INPUT_FIELDS);
                return;
            }

            if (selectedAnimal == null) createAnimal(species, name, age, color);
            else updateSelectedAnimal(species, name, age, color);

            switchToDashboardScene();
        } catch (NumberFormatException e) {
            AlertManager.getInstance().showError(AppText.ERROR_INVALID_AGE);
        }
    }


    private static void createAnimal(String species, String name, int age, String color) {
        Animal animal = new Animal(species, name, age, color);
        AnimalManager.getInstance().add(animal);
    }


    private void updateSelectedAnimal(String species, String name, int age, String color) {
        if (!species.equals(selectedAnimal.getSpecies())) selectedAnimal.setSpecies(species);
        if (!name.equals(selectedAnimal.getName())) selectedAnimal.setName(name);
        if (age != selectedAnimal.getAge()) selectedAnimal.setAge(age);
        if (!color.equals(selectedAnimal.getSpecies())) selectedAnimal.setColor(color);
    }


    @FXML
    private void convey() {

        Optional<ButtonType> typeOfClickedButton = AlertManager.getInstance().showConfirmation(
                AppText.DELETE_ANIMAL_CONFIRMATION, selectedAnimal.toString()
        );

        if (typeOfClickedButton.isEmpty() || typeOfClickedButton.get() != ButtonType.OK) return;

        AnimalManager.getInstance().remove(selectedAnimal);
        switchToDashboardScene();
    }

    @FXML
    private void switchToDashboardScene() {
        SceneManager.getInstance().openScene(View.DASHBOARD);
    }


    //Ende der Region Methoden
}
