package de.ietu.ietierverwaltung.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {

    //Region Konstanten
    public static final int DEFAULT_ID = -1;
    //Ende der Region Konstanten

    //Region Attribute
    private int id;
    private StringProperty species;
    private StringProperty name;
    private IntegerProperty age;
    private StringProperty color;
//    private ObjectProperty<AnimalChip> animalChip;
    //Ende der Region Attribute

    //Region Konstruktoren
    public Animal(String species, String name, int age, String color) {
        id = DEFAULT_ID;
        this.species = new SimpleStringProperty(species);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(age);
        this.color = new SimpleStringProperty(color);
//        this.animalChip = new SimpleObjectProperty<>(new AnimalChip());
    }

    public Animal(int id, String species, String name, int age, String color) {
        this(species, name, age, color);
        this.id = id;
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species.get();
    }

    public StringProperty speciesProperty() {
        return species;
    }

    public void setSpecies(String species) {
        this.species.set(species);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getColor() {
        return color.get();
    }

    public StringProperty colorProperty() {
        return color;
    }

    public void setColor(String color) {
        this.color.set(color);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", species='" + species.get() + '\'' +
                ", name='" + name.get() + '\'' +
                ", age=" + age.get() +
                ", color='" + color.get() + '\'' +
                '}';
    }

    //Ende der Region Methoden
}
