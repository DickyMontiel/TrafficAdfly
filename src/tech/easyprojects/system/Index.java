package tech.easyprojects.system;

import java.util.Random;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Index extends Application {
    private final String root = "/tech/easyprojects/views/";
    public static int cicles;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(this.root + "IndexView.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("TrafficWeb");
        stage.getIcons().add(new Image("/tech/easyprojects/images/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    private static int getRandomInt(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
