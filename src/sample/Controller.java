package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;
import javax.sound.sampled.*;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.awt.event.ActionListener;
import javafx.scene.control.Alert;
import javafx.application.Application;

public class Controller implements Initializable  {

    @FXML
    public Button butt1;
    public Button butt2;
    public Button butt3;
    public Button butt4;
    public Button butt5;
    public Button butt6;

    public ImageView lab1;
    public ImageView lab2;
    public ImageView lab3;
    public ImageView lblPicDisplay;

    public Label lblMsg1;
    public Label creditArea;
    public Label betArea;
    private Game game;




    public void spinButton(ActionEvent event){

        game.spin();

    }

    public void betOneButton(MouseEvent mouseEvent) {
        //game.stopSpinning();
        butt2.setDisable(false);
        butt1.setDisable(false);
        butt3.setDisable(false);
    }

    public void betMaxButton(ActionEvent event){

        game.betMax();
        butt3.setDisable(true);

    }

    public void resetButton(ActionEvent event){

        game.resetPoints();

    }

    public void addCoinButton(ActionEvent event){

        game.addCoin();

    }

    public void statButton(){

        try {
            FXMLLoader satLoder = new FXMLLoader(getClass().getResource("about.fxml"));
            Parent root1 = (Parent) satLoder.load();
            Stage stage = new Stage();
            stage.setTitle("Statistic Window");
            stage.setScene(new Scene(root1));
            stage.show();

        }catch (Exception e){
            System.out.println("Can not load statistic window");
        }
    }

    //Set credit now you have for creditArea Label
    public void setCreditArea(int details){
        creditArea.setText("Credit: "+details);
    }

    //set bet amount for betArea Label
    public void setBetArea(int details){
        betArea.setText("Betting: "+details);
    }

    public void disableImageClick(){
        lab1.setDisable(true);
        lab2.setDisable(true);
        lab3.setDisable(true);

    }

    public void enableImageClick(){ //in order to prevent the user from clicking the images before spinning.
        lab1.setDisable(false);
        lab2.setDisable(false);
        lab3.setDisable(false);

    }

    public void disableBtns(){
        butt1.setDisable(true);
        butt3.setDisable(true);
        butt2.setDisable(true);//disables the spin button.
    }

    public void enableMaxBtn(){

        butt3.setDisable(false);
    }




    //close project
    public void closeButton(){
//        Platform.exit();
//        System.exit(0);

        boolean answer = ConfirmBox.display ("Titel", "Sure you want Exit ?");
        if(answer)
            Platform.exit();
    }

    public void alertUser(String title,String header,String message, Alert.AlertType type){
        if(type!= Alert.AlertType.CONFIRMATION) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setHeaderText(header);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }




    //sound thread
    private static synchronized void playSound(final String sound) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(getClass().getResource("sounds/" + sound + ".wav"));
                    clip.open(inputStream);
                    clip.start();
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        game = new Game(this,lab1,lab2,lab3);
        disableImageClick();

    }



}

