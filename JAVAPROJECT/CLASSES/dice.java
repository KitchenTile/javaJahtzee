package JAVAPROJECT.CLASSES;

import java.util.Arrays;
import java.util.Scanner;

public class dice {
    public static void generateDice() {

        // create an array with the dice resoults
        double diceArray[] = new double[6];

        // for loop to get 5 random numbers and store them in the array
        for (int dice = 1; dice < 6; dice++) {

            // make a variable that stores random numbers between 1 - 6
            double randomNum = Math.floor(Math.random() * 6 + 1);

            // use the index dice and assign the random number to it
            diceArray[dice] = randomNum;

            // print the array
            System.out.println("Dice number " + dice + ": " + diceArray[dice]);

        }
        dice.keepDice(diceArray);
    }

    public static void keepDice(double[] args) {
        // get user to select a dice
        Scanner input = new Scanner(System.in);
        System.out.println("Select the dice number you want to keep: ");
        int diceIndex = input.nextInt();

        double keptDice[] = new double[6];

        keptDice[diceIndex] = args[diceIndex];

        System.out.println(keptDice[diceIndex]);

        System.out.println(Arrays.toString(keptDice));
    }
}
