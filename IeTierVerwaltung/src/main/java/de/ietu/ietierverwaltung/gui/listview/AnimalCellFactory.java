package de.ietu.ietierverwaltung.gui.listview;

import de.ietu.ietierverwaltung.model.Animal;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class AnimalCellFactory implements Callback<ListView<Animal>, ListCell<Animal>> {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @Override
    public ListCell<Animal> call(ListView<Animal> param) {
        return new AnimalCell();
    }
    //Ende der Region Methoden
}
