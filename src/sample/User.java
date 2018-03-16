package sample;
/**
 * Created by Sangeeth on 11/16/17.
 */
public class User {

    private int credit;
    private int wins;
    private int losses;
    private int averageNumOfCredits;

    public User(){

    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getAverageNumOfCredits() {
        return averageNumOfCredits;
    }

    public void setAverageNumOfCredits(int averageNumOfCredits) {
        this.averageNumOfCredits = averageNumOfCredits;
    }
}
