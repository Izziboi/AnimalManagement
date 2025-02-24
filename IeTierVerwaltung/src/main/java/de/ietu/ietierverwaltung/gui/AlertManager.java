package de.ietu.ietierverwaltung.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertManager {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    private static AlertManager instance;
    //Ende der Region Attribute

    //Region Konstruktoren
    private AlertManager() {
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public static synchronized AlertManager getInstance() {
        if (instance == null) instance = new AlertManager();
        return instance;
    }


    public Optional<ButtonType> showError(String headerMessage) {
        //Alert alert = createAlert(Alert.AlertType.ERROR, headerMessage, null);
        //return alert.showAndWait();
        return createAlert(Alert.AlertType.ERROR, headerMessage, null).showAndWait();
    }

    public Optional<ButtonType> showConfirmation(String headerMessage, String contentMessage) {
        Alert alert = createAlert(Alert.AlertType.CONFIRMATION, headerMessage, contentMessage);
        return alert.showAndWait();
    }

    private Alert createAlert(Alert.AlertType type, String headerMessage, String contentMessage) {
        Alert alert = new Alert(type);
        alert.setHeaderText(headerMessage);
        alert.setContentText(contentMessage);
        return alert;
    }

    //Ende der Region Methoden
}
