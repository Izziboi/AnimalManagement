package de.ietu.ietierverwaltung.gui;

import de.ietu.ietierverwaltung.Main;
import de.ietu.ietierverwaltung.model.Animal;
import de.ietu.ietierverwaltung.settings.AppText;
import de.ietu.ietierverwaltung.settings.View;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

public class SceneManager {

    //Region Konstanten
    //Ende der Region Konstanten

    //Region Attribute
    private static SceneManager instance;

    private Stage mainStage;
    //Ende der Region Attribute

    //Region Konstruktoren
    private SceneManager() {
    }
    //Ende der Region Konstruktoren

    //Region Methoden
    public static synchronized SceneManager getInstance() {
        if (instance == null) instance = new SceneManager();
        return instance;
    }


    public void setAndConfigureMainStage(Stage stage) {
        mainStage = stage;
        mainStage.setTitle(AppText.APP_NAME);
        openScene(View.DASHBOARD);
    }


    public void openScene(String fileName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fileName));
            Scene scene = new Scene(fxmlLoader.load());
            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void openDetailScene(Animal selectedAnimal) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(View.DETAIL));
            Scene scene = new Scene(fxmlLoader.load());

            if (fxmlLoader.getController() instanceof DetailController detailController) {
                detailController.setSelectedAnimalAndDetails(selectedAnimal);
            }

            mainStage.setScene(scene);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Ende der Region Methoden
}
