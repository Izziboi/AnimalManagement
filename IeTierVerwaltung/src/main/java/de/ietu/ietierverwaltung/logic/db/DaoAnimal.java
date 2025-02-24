package de.ietu.ietierverwaltung.logic.db;

import de.ietu.ietierverwaltung.model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DaoAnimal implements Dao<Animal>{

    //Region Konstanten
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SPECIES = "species";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_COLOR = "color";
    public static final String SQL_SELECT_ALL_FROM_ANIMALS = "SELECT * FROM animal;";
    public static final String SQL_INSERT_ANIMAL = "INSERT INTO animal (species, name, age, color) VALUES (?,?,?,?);";
    public static final String COLUMN_INSERT_ID = "insert_id";
    public static final String SQL_UPDATE_ANIMAL_BY_ID = "UPDATE animal SET species=?, name=?, age=?, color=? WHERE id=?;";
    public static final String SQL_DELETE_ANIMAL_BY_ID = "DELETE FROM animal WHERE id=?;";
    //Ende der Region Konstanten

    //Region Attribute
    //Ende der Region Attribute

    //Region Konstruktoren
    //Ende der Region Konstruktoren

    //Region Methoden
    @Override
    public void create(Connection connection, Animal animal) {
        try (
                PreparedStatement statement =
                        connection.prepareStatement(SQL_INSERT_ANIMAL, PreparedStatement.RETURN_GENERATED_KEYS)
                ){
            statement.setString(1, animal.getSpecies());
            statement.setString(2, animal.getName());
            statement.setInt(3, animal.getAge());
            statement.setString(4, animal.getColor());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                animal.setId(generatedKeys.getInt(COLUMN_INSERT_ID)); //You can also use 1 instead of the value of COLUMN_INSERT_ID which is "insert_id"
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Animal> readAll(Connection connection) {
        List<Animal> animals = new ArrayList<>();

        try ( PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL_FROM_ANIMALS)){

            statement.execute();

            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {

                Animal animal = new Animal(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getString(COLUMN_SPECIES),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getInt(COLUMN_AGE),
                        resultSet.getString(COLUMN_COLOR)
                );

                animals.add(animal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return animals;
    }




    @Override
    public void update(Connection connection, Animal animal) {
        try (
                PreparedStatement statement =
                        connection.prepareStatement(SQL_UPDATE_ANIMAL_BY_ID)
        ) {

            statement.setString(1, animal.getSpecies());
            statement.setString(2, animal.getName());
            statement.setInt(3, animal.getAge());
            statement.setString(4, animal.getColor());
            statement.setInt(5, animal.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    @Override
    public void delete(Connection connection, Animal animal) {
        try (
                PreparedStatement statement =
                        connection.prepareStatement(SQL_DELETE_ANIMAL_BY_ID)
        ) {

            statement.setInt(1, animal.getId());

            statement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ende der Region Methoden
}
