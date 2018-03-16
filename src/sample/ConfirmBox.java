package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Sangeeth on 11/15/17.
 */
public class ConfirmBox {


    static boolean answer;

    public static boolean display (String titel , String message ){

        Stage window = new Stage();


        // block event to other window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titel);
        window.setMaxWidth(250);
        Label label = new Label();
        label.setText(message);



        //create two button
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        //set acction what should do

        yesButton.setOnAction(e ->{

            answer = true;
            window.close();
            //Platform.exit();
            System.exit(0);
        });

        noButton.setOnAction(e ->{

            answer = false;
            window.close();
            //Platform.exit();

        });



        VBox layout = new VBox(10);
        layout.getChildren().addAll(label,yesButton,noButton);
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();


        return answer;
    }
}


