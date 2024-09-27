package JAVAPROJECT.CLASSES;

import java.util.Arrays;
import java.util.Scanner;

public class Dice {

    // create an array with the dice resoults
    int[] diceArray;

    // Create an empty array for the dice the user wants to keep
    int[] keptDice;

    // create constructor so hopefully the arrays keep their data
    public Dice() {
        diceArray = new int[5];
        keptDice = new int[5];
    }

    public void generateDice() {

        // for loop to get 5 random numbers and store them in the array
        for (int diceNumber = 0; diceNumber < diceArray.length; diceNumber++) {

            // make a variable that stores random numbers between 1 - 6 -- use a cast to
            // make randomNum into an int instead of double
            int randomNum = (int) Math.floor(Math.random() * 6 + 1);

            // use the index dice and assign the random number to it
            diceArray[diceNumber] = randomNum;

            // print the array
            System.out.println("Dice number " + (diceNumber + 1) + ": " + diceArray[diceNumber]);

        }

        // we call the second method so it plays right after we generate the dice
        keepDice();
    }

    public void keepDice() {
        // get user to select a dice
        Scanner input = new Scanner(System.in);
        System.out.print("Select the dice number you want to keep (1-5): ");
        int diceIndex = input.nextInt();

        // // if the selected dice is within the boundaries of the array
        if (0 < diceIndex && diceIndex <= diceArray.length) { // if statement does not work
            // to match both lists we need to substact the input index by 1 since we made
            // the list start 1 index number "ahead" (when we select 1, we really want to
            // pount at 0). In short, we are adjusting for a 0 base index.
            keptDice[diceIndex - 1] = diceArray[diceIndex - 1];
            System.out.println("Selected dice has a value of: " + keptDice[diceIndex - 1]);

            // Do this to print the array in a way I am used to seeing it
            System.out.println(Arrays.toString(keptDice));
        } else {
            // If the selection is out of bound, call the function again
            System.out.println("please enter a valid dice number");
            keepDice();
        }

        Scanner inputNewDice = new Scanner(System.in);
        System.out.print("Would you like to select an other dice to keep? y/n: ");
        String keepAnotherDice = inputNewDice.nextLine();

        if (keepAnotherDice.equalsIgnoreCase("y")) {
            keepDice();
        } else {
            System.out.println("Selected dice: " + Arrays.toString(keptDice));
            System.out.println(keepAnotherDice);
        }

        input.close();
        inputNewDice.close();
    }
}
