package sample;

/**
 * Created by Sangeeth on 11/14/17.
 */

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Reel implements Runnable {

    private Symbol redseven;
    private Symbol bell;
    private Symbol watermelon;
    private Symbol plum;
    private Symbol lemon;
    private Symbol cherry;

    private boolean shouldSpin;
    private ImageView image;
    private Symbol currentSymbolinReel;
    public static final int reelSwitchTime = 100;
    private static final int reelCount = 6;
    public List<Symbol> symbols;

    //ArrayList<Symbol> symbols = new ArrayList<Symbol>();

    public Reel(ImageView image) {

        this.image = image;

        // cherry object
        cherry = new Symbol(new Image(getClass().getResourceAsStream("images/cherry.png")), 2, "cherry");

        lemon = new Symbol(new Image(getClass().getResourceAsStream("images/lemon.png")), 3, "lemon");

        plum = new Symbol(new Image(getClass().getResourceAsStream("images/plum.png")), 4, "plum");

        watermelon = new Symbol(new Image(getClass().getResourceAsStream("images/watermelon.png")), 5, "watermelon");

        bell = new Symbol(new Image(getClass().getResourceAsStream("res/bell.png")), 6, "bell");

        redseven = new Symbol(new Image(getClass().getResourceAsStream("images/redseven.png")), 7, "seven");

//        symbols.add(cherry);
//        symbols.add(watermelon);
//        symbols.add(lemon);
//        symbols.add(redseven);
//        symbols.add(bell);
//        symbols.add(plum);

        symbols = new ArrayList<>(6);

    }

    public List<Symbol> spin() {
        return symbols;
    }

//    public ArrayList Spin(ArrayList symbols){
//        Collections.shuffle(symbols);
//        return symbols;
//    }



    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (shouldSpin) {
                int randomNum = (int)(Math.random()*((symbols.size()-1)+1)); //getting the random index number in order to obtain a random symbol from the array
                Symbol symbol = symbols.get(randomNum);
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            image.setImage(symbol.getImage()); //updates the image
                            currentSymbolinReel = symbol;
                        }
                    });

                } catch (IllegalStateException e) { //will be thrown if the fx runtime has not been initalized
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(reelSwitchTime); //puts the current thread to sleep
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
        }
    }

    public void setShouldSpin(boolean shouldSpin) {
        this.shouldSpin = shouldSpin;
    }

    public Symbol getCurrentSymbol(){
        return currentSymbolinReel;
    }

}
