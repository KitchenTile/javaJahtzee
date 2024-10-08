package JAVAPROJECT.CLASSES;

import java.util.Arrays;
import java.util.Scanner;

//in this class we are going to create an array with all the columns initialized at 0,
//then we are going to ask the player where they want to put the dice roll, if the column is full, ask again.

public class Player {

    // variable for each player's total score
    int totalScore;

    // Array with 13 spaces for the roll results
    int[] diceRollArray;

    // boolean to check if the column input is not repeated
    boolean unselectedValidColumn;

    // boolean to check if it's turn one for a unique column
    boolean turnOne;

    // initialize Dice constructor
    Dice dice = new Dice();

    String name;

    public Player() {
        totalScore = 0;
        diceRollArray = new int[11];
        turnOne = true;

        Scanner nameInput = new Scanner(System.in); // needs closing
        System.out.print("Please enter your name: ");
        name = nameInput.nextLine();

    }

    // function to representa turn, ideally will be called for each player back to
    // back for a set number of turns
    public void turn(boolean[] firstTurnArray) {

        unselectedValidColumn = false;

        // call the function to roll dice and get the added value
        dice.generateDice();
        System.out.println("You rolled a(n) " + dice.getRollValue() + " this turn.");

        // while loop to make sure the selected
        while (!unselectedValidColumn /* && !turnOne */) {
            // chewck if it's the first turn to ask for an untaken cell.
            if (turnOne) {
                while (turnOne) {

                    // scanner to assign the value to the selected index
                    Scanner input = new Scanner(System.in);
                    System.out.print("What column would you like to assing this value to? (COLUMN MUST BE UNIQUE) ");
                    int columnIndex = input.nextInt();

                    if (columnIndex > 1 && !firstTurnArray[columnIndex - 2]) {

                        if (columnIndex <= diceRollArray.length + 1) {
                            // adjust for a 0 index array by subtracting 1
                            diceRollArray[columnIndex - 2] = dice.getRollValue();

                            // switch variable to true
                            unselectedValidColumn = true;
                            turnOne = false;

                        } else {
                            System.out.println("Please enter a valid column number.");
                        }

                        firstTurnArray[columnIndex - 2] = true;

                        System.out.println(Arrays.toString(diceRollArray));

                    } else {
                        System.out.println("Please select a unique column.");
                    }

                }
            } else {
                // scanner to assign the value to the selected index
                Scanner input = new Scanner(System.in);
                System.out.print("What column would you like to assing this value to? ");
                int columnIndex = input.nextInt();

                // three conditions for the column to be valid:
                // the index has to be bigger or equal than 0,
                // the index has to be smaller or equal than the length of the array,
                // and the element in the array has to be untaken (equals to 0)
                if (columnIndex > 1 && columnIndex <= diceRollArray.length + 1 && diceRollArray[columnIndex - 2] == 0) {

                    // adjust for a 0 index array by subtracting 1
                    diceRollArray[columnIndex - 2] = dice.getRollValue();

                    // switch variable to true
                    unselectedValidColumn = true;

                    System.out.println(Arrays.toString(diceRollArray));

                } else {
                    System.out.println("Please enter a valid column number.");
                }
            }

        }
    }

    public int[] getDiceRollArray() {
        return diceRollArray;
    }

}