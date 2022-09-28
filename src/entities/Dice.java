package entities;

import java.util.concurrent.ThreadLocalRandom;

public class Dice {
    int numberOfDice = 1;
    int minimumRange = 1;
    int maximumRange = 6;


    public Dice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
    }

    public int rollDice() {
        int diceValue = 0;
        int noOfDiceRolled = 0;

        for (int i = 1; i <= numberOfDice; i++) {
            diceValue += ThreadLocalRandom.current().nextInt(minimumRange, maximumRange + 1);
        }
        return diceValue;
    }

}
