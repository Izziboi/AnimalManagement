package de.ietu.ietierverwaltung.logic.db;

import java.sql.Connection;
import java.util.List;

public interface Dao<T> {


    void create(Connection connection, T object);

    List<T> readAll(Connection connection);

    void update(Connection connection, T object);

    void delete(Connection connection, T object);
}
