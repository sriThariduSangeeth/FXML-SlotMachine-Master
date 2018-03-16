package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        //Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Sample.fxml")), 800, 500);
        primaryStage.setTitle("S L O T  M A C H I N E");
        primaryStage.setScene(new Scene(root, 800, 500));
        //primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
