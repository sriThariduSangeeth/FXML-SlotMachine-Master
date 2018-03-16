package sample;
import java.util.Random;

/**
 * Created by Sangeeth on 11/14/17.
 */
public class ReelThread implements Runnable {

    String reelName;
    int sleepTime;
    Random random = new Random();

    public ReelThread(String reelName){
        this.reelName=reelName;
        sleepTime = random.nextInt();
    }
    @Override
    public void run() {

    }

}
