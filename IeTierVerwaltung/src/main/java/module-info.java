module de.ietu.ietierverwaltung {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mariadb.jdbc;
    requires java.sql;
    requires org.kordamp.bootstrapfx.core;


    opens de.ietu.ietierverwaltung to javafx.fxml;
    exports de.ietu.ietierverwaltung;
    exports de.ietu.ietierverwaltung.logic;
    opens de.ietu.ietierverwaltung.logic to javafx.fxml;
    exports de.ietu.ietierverwaltung.gui;
    opens de.ietu.ietierverwaltung.gui to javafx.fxml;
}