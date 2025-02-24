package de.ietu.ietierverwaltung.test;

import de.ietu.ietierverwaltung.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    private TestData() {
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public static List<Animal> getTestAnimals() {
        List<Animal> animals = new ArrayList<>();

        animals.add(new Animal("Hund", "Bello", 5, "braun"));
        animals.add(new Animal("Katze", "Maja", 2, "grau"));
        animals.add(new Animal("Bär", "Zorro", 99, "grau"));
        animals.add(new Animal("Kanarienvogel", "Tweety", 108, "gelb"));
        animals.add(new Animal("Hund", "Troy", 5, "braun"));
        animals.add(new Animal("Maus", "Mickey", 110, "schwarz-weiß"));
        animals.add(new Animal("Kanarienvogel", "Albin", 50, "gelb"));
        animals.add(new Animal("Katze", "Nikita", 2, "grau"));
        animals.add(new Animal("Bär", "Balu", 99, "grau"));

        return animals;
    }
    //Ende der Region Methoden
}
