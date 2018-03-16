package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import sample.Controller;
import sample.Reel;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * Created by Sangeeth on 11/16/17.
 */
public class Game  {

    private User user;
    private int currentCoinsBetting;
    private int pointsNetted;
    private Controller controller; //TODO::CHANGE TO SUPERCLASS
    public static final String noCreditMessage = "No Credits!";
    private static final int maxBetAmount = 3;
    private static final int initialCredit = 10;
    private Reel reelOne;
    private Reel reelTwo;
    private Reel reelThree;
    //private Controller cen;

    public Game(Controller controller, ImageView imageOne, ImageView imageTwo, ImageView imageThree){
        user = new User();
        user.setCredit(initialCredit);
        this.controller = controller;
        controller.setCreditArea(user.getCredit());
        controller.setBetArea(currentCoinsBetting);

        this.reelOne = new Reel(imageOne);
        this.reelTwo = new Reel(imageTwo);
        this.reelThree = new Reel(imageThree);

    }

    public void rewardUser(){
        int creditsWon = getWinnings();
        user.setCredit(user.getCredit()+(creditsWon*currentCoinsBetting));
        controller.setCreditArea(user.getCredit());
        if(creditsWon!=0) {

            playSound("win");
            //Controller.showStats(new Image("res/starsopening.png"));
            controller.alertUser("WINNER!", "You've Won!", "Congrats,You've Won " + creditsWon * currentCoinsBetting + " credits!", Alert.AlertType.INFORMATION);
        } else{
            playSound("lose");
            // Image im = new Image("res/spinagain.png");
            //Controller.showStats(new Image("res/spinagain.png"));
            controller.alertUser("LOSER","You've Lost","Please Play Again!", Alert.AlertType.INFORMATION);
        }
        currentCoinsBetting = 0;//resetting the coins bet.
        controller.setBetArea(currentCoinsBetting);
        controller.enableMaxBtn(); //reenabling the bet max btn.
        controller.disableImageClick(); //disabling the images after points have been awarded.
    }

    public void addCoin(){
        user.setCredit(user.getCredit()+1);
        controller.setCreditArea(user.getCredit());
    }

    public boolean isEnoughCredit() {
        if(user.getCredit()>=1){
            return true;
        }else{
            return false;
        }

    }

    public boolean isEnoughCurrentBet(){
        if(currentCoinsBetting>0){
            return true;
        }else
            return false;
    }

    public void betOne(){
        if(isEnoughCredit()) {
            user.setCredit(user.getCredit() - 1);
            currentCoinsBetting++;
            controller.setCreditArea(user.getCredit());
            controller.setBetArea(currentCoinsBetting);
        }else{
            controller.alertUser("Cannot Bet!",noCreditMessage,"Please Add Credits to Play!", Alert.AlertType.WARNING);
        }
    }

    public void betMax() {
        if (user.getCredit() <= 0) {
            controller.alertUser("Cannot Bet!",noCreditMessage,"Please Add Credits to Play", Alert.AlertType.WARNING);
        }else{
            if(user.getCredit()<=maxBetAmount){
                currentCoinsBetting+=user.getCredit();
                controller.setBetArea(currentCoinsBetting);
                user.setCredit(0);
                controller.setCreditArea(user.getCredit());
            }else{
                currentCoinsBetting+=3;
                user.setCredit(user.getCredit()-3);
                controller.setBetArea(currentCoinsBetting);
                controller.setCreditArea(user.getCredit());
            }
        }
    }

    public void spin() {


        if (isEnoughCurrentBet()) {
            reelOne.setShouldSpin(true);
            Thread threadImageOne = new Thread(reelOne, "reelOneSpinningThread");
            threadImageOne.start();

            reelTwo.setShouldSpin(true);
            Thread threadImageTwo = new Thread(reelTwo, "reelTwoSpinningThread");
            threadImageTwo.start();

            reelThree.setShouldSpin(true);
            Thread threadImageThree = new Thread(reelThree, "reelThreeSpinningThread");
            threadImageThree.start();

            controller.disableBtns();
            controller.enableMaxBtn();
            controller.enableImageClick();


            playSound("spin");
        } else {
            playSound("lose");
            controller.alertUser("Cannot Play!", "No Bets Placed!", "Please Bet in Order to Play.", Alert.AlertType.WARNING);

        }
    }

    public void stopSpinning(){
        reelOne.setShouldSpin(false);
        reelTwo.setShouldSpin(false);
        reelThree.setShouldSpin(false);
        rewardUser();

    }

    public int getWinnings() {
        int winnings = 0;
        Symbol reelOneSym = reelOne.getCurrentSymbol();
        Symbol reelTwoSym = reelTwo.getCurrentSymbol();
        Symbol reelThreeSym = reelThree.getCurrentSymbol();
        if(reelOneSym.compareTo(reelTwoSym)==0){
            winnings = reelOneSym.getValue();
        }else if(reelOneSym.compareTo(reelThreeSym)==0){
            winnings = reelOneSym.getValue();
        }else if(reelTwoSym.compareTo(reelThreeSym)==0) {
            winnings = reelTwoSym.getValue();
        }
        return winnings;

    }

    public void resetPoints() {
        if(currentCoinsBetting==0){
            controller.alertUser("No Bets!","No Bets Have Been Placed","There are no bets to reset.", Alert.AlertType.WARNING);
        }else {
            user.setCredit(user.getCredit() + currentCoinsBetting);
            controller.setCreditArea(user.getCredit());
            currentCoinsBetting = 0;
            controller.setBetArea(currentCoinsBetting);
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



}