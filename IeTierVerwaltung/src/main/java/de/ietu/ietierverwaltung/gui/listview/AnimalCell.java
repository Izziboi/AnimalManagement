package de.ietu.ietierverwaltung.gui.listview;

import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.settings.AppText;
import javafx.scene.control.ListCell;
import javafx.scene.text.Font;

public class AnimalCell extends ListCell<Animal> {

    //Region Konstanten
    public static final String FONT_FAMILY = "Consolas";
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @Override
    protected void updateItem(Animal animal, boolean empty) {
        super.updateItem(animal, empty);

        if (animal == null && empty) {
            setText(null);
            setGraphic(null);
            return;
        }

//        setBackground(Background.fill(Paint.valueOf("#000000")));
        setFont(Font.font(FONT_FAMILY));
        setText(String.format(AppText.TEMPLATE_ANIMAL_LIST_VIEW_ENTRY,
                animal.getSpecies(), animal.getName(), animal.getAge(), animal.getColor()));
    }
    //Ende der Region Methoden
}
