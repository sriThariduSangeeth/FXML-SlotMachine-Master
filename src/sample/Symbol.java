package sample;

import javafx.scene.image.Image;

import java.util.Comparator;

/**
 * Created by Sangeeth on 11/14/17.
 */
public class Symbol implements iSymbol,Comparable{

    private Image image;
    private int value;
    private String title;




    public Symbol(Image image, int value, String titel) {

        this.image = image;
        this.value = value;
        this.title = title;


    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int v) {
        value = v;
    }

    public String getTitle(){
        return title;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Symbol)
            return ((Symbol) o).getValue() - value;
        else
            return -100;
    }

    public static int matchCheck(Symbol symbol1, Symbol symbol2,Symbol symbol3) {
        int match;

        if(symbol1.getValue()==symbol2.getValue() && symbol2.getValue()==symbol3.getValue()){
            match = 3;
        }else if(symbol1.getValue()==symbol2.getValue()){
            match = 2;
        }else if(symbol2.getValue()==symbol3.getValue()){
            match = 2;
        }else if(symbol1.getValue()==symbol3.getValue()){
            match = 2;
        }else{
            match =0;
        }
        return match;
    }

    @Override
    public String toString() {
        return title +','+ value;
    }

}
