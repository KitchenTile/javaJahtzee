package JAVAPROJECT.CLASSES;

import java.util.Arrays;

public class Dice {

    // create an array for the dice resoults
    int[] diceArray;

    // create constructor so hopefully the arrays keep their data
    public Dice() {
        diceArray = new int[2];
    }

    public void generateDice() {

        // for loop to get 2 random numbers and store them in the array
        for (int diceNumber = 0; diceNumber < diceArray.length; diceNumber++) {

            // make a variable that stores random numbers between 1 - 6 -- use a cast to
            // make randomNum into an int instead of double
            int randomNum = (int) Math.floor(Math.random() * 6 + 1);

            // use the index dice and assign the random number to it
            diceArray[diceNumber] = randomNum;
        }
        // print the array
        System.out.println("Rolled dice: " + Arrays.toString(diceArray));
    }

    public int getRollValue() {
        return diceArray[0] + diceArray[1];
    }

}
