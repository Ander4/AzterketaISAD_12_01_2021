package ehu.isad;

import ehu.isad.controllers.UI.TaulaKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
public class Main extends Application {

    private Parent taulaUI;

    private Stage stage;

    private TaulaKud taulaKud;
    private Scene sceneTaula;


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("URL-en PHP Vertsioa aztertzen");
        stage.setScene(sceneTaula);
        stage.show();
    }

    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderLiburu = new FXMLLoader(getClass().getResource("/Taula.fxml"));
        taulaUI = (Parent) loaderLiburu.load();
        sceneTaula = new Scene(taulaUI, 600, 450);
        taulaKud = loaderLiburu.getController();
        taulaKud.setMainApp(this);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
