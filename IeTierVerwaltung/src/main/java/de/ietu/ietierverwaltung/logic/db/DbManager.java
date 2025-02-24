package de.ietu.ietierverwaltung.logic.db;

import de.ietu.ietierverwaltung.gui.AlertManager;
import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.model.AnimalChip;
import de.ietu.ietierverwaltung.settings.AppText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class DbManager {

    //Region Konstanten
    private static final String PROTOCOL = "jdbc:mariadb://";
    private static final String SERVER_DOMAIN = "127.0.0.1"; //You can also use "localhost" instead of "127.0.0.1"
    private static final String PORT = "3306";
    private static final String DB_NAME = "dbanimal";

    private static final String CONNECTION_URL =
            PROTOCOL + SERVER_DOMAIN + ":" + PORT + "/" + DB_NAME;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    //Ende der Region Konstanten

    //Region Attribute
    private static DbManager instance;

    private DaoAnimal daoAnimal;
    //Ende der Region Attribute

    //Region Konstruktoren
    private DbManager() {
        daoAnimal = new DaoAnimal();
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public static synchronized DbManager getInstance() {
        if (instance == null) instance = new DbManager();
        return instance;
    }


    private static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(CONNECTION_URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();

            AlertManager.getInstance().showError(AppText.ERROR_ESTABLISHING_DB_CONNECTION);
        }

        return connection;
    }


    public void insert(Animal animal) {
        daoAnimal.create(getConnection(), animal);
    }


    public void insert(AnimalChip animalChip) {
        //create über DaoAnimalChip-Objekt aufrufen
    }


    public List<Animal> readAllAnimals() {
        return daoAnimal.readAll(getConnection());
    }


    public void update(Animal animal) {
        daoAnimal.update(getConnection(), animal);
    }

    public void delete(Animal animal) {
        daoAnimal.delete(getConnection(), animal);
    }
    //Ende der Region Methoden
}
