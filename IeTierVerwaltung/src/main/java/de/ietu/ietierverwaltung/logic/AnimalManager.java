package de.ietu.ietierverwaltung.logic;

import de.ietu.ietierverwaltung.logic.db.DbManager;
import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.test.TestData;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.Comparator;

public class AnimalManager {

    private static AnimalManager instance;

    private ObservableList<Animal> animals;

    private boolean sortDescending;
    private int sortToggle;


    private AnimalManager() {
        //Liste laden
        //Database is read here
        animals = FXCollections.observableList(DbManager.getInstance().readAllAnimals(), animal -> new Observable[] {
                animal.speciesProperty(), animal.nameProperty(), animal.ageProperty(), animal.colorProperty()
        });

        //ChangeListener der Liste zuweisen
        animals.addListener((ListChangeListener<Animal>) change -> {

            System.out.println(change);

            while (change.next()) {

                if (change.wasReplaced()) {

                    System.out.println("Element ersetzt");

                } else if (change.wasAdded()) {

                    Animal animalToInsert = change.getAddedSubList().getFirst();
                    DbManager.getInstance().insert(animalToInsert); //Connecting to the database for inserting new values
                    System.out.println(animalToInsert);

                } else if (change.wasRemoved()) {

                    Animal animalToRemove = change.getRemoved().getFirst();
                    DbManager.getInstance().delete(animalToRemove); //Connecting to the database for removing values
                    System.out.println(animalToRemove);

                } else if (change.wasUpdated()) {

                    Animal animalToUpdate = change.getList().get(change.getFrom());
                    DbManager.getInstance().update(animalToUpdate); //Connecting to the database for changing values
                    System.out.println(animalToUpdate);

                } else if (change.wasPermutated()) {

                    System.out.println("Reihenfolge geändert");
                }
            }
        });

        sortToggle = 1;
    }


    public static synchronized AnimalManager getInstance() {
        if (instance == null) {
            instance = new AnimalManager();
        }
        return instance;
    }


    public void add(Animal animal) {
        animals.add(animal);
    }

    public void remove(Animal animal) {
        animals.remove(animal);
    }

    public void replace(int index, Animal animal) {
        animals.set(index, animal);
    }

    public void sortBySpecies() {
        animals.sort(new Comparator<Animal>() {
            @Override
            public int compare(Animal current, Animal next) {

                int compareResult = current.getSpecies().compareTo(next.getSpecies());

                return compareResult * sortToggle;
            }
        });

        sortToggle *= -1;
    }


    public void sortByName() {
        if (sortDescending)
            animals.sort(Comparator.comparing(Animal::getName).reversed());
        else
            animals.sort(Comparator.comparing(Animal::getName));

        sortDescending = !sortDescending;
    }


    public void sortByAge() {
        if (sortDescending)
            animals.sort(Comparator.comparing(Animal::getAge).reversed());
        else
            animals.sort(Comparator.comparing(Animal::getAge));

        sortDescending = !sortDescending;

//        animals.sort(new Comparator<Animal>() {
//            @Override
//            public int compare(Animal currentAnimal, Animal nextAnimal) {
//                Integer currentAnimalAge = currentAnimal.getAge();
//                Integer nextAnimalAge = nextAnimal.getAge();
//
//                 return currentAnimalAge - nextAnimalAge;
//                return currentAnimalAge.compareTo(nextAnimalAge);
//            }
//        });
    }


    public void sortByColor() {
        if (sortDescending)
            animals.sort(Comparator.comparing(Animal::getColor).reversed());
        else
            animals.sort(Comparator.comparing(Animal::getColor));

        sortDescending = !sortDescending;
    }


    public void sortBySpeciesAndName() {

        animals.sort(Comparator.comparing(Animal::getSpecies).thenComparing(Animal::getName));

//        animals.sort((current, next) -> {
//
//            int speciesCompareResult = current.getSpecies().compareTo(next.getSpecies());
//
//            if (speciesCompareResult != 0) return speciesCompareResult;
//
//            int nameCompareResult = current.getName().compareTo(next.getName());
//
//            return nameCompareResult;
//        });
    }

    public ObservableList<Animal> getAnimalsFilteredBy(String value) {
        return animals.filtered(animal -> animal.getSpecies().toLowerCase().contains(value.toLowerCase())
                || animal.getName().toLowerCase().contains(value.toLowerCase())
                || String.valueOf(animal.getAge()).toLowerCase().contains(value.toLowerCase())
                || animal.getColor().toLowerCase().contains(value.toLowerCase()));
    }

    public ObservableList<Animal> getAnimals() {
        return animals;
    }

}