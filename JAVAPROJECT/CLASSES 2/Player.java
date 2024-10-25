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

    private void setRollToColumn(boolean[] firstTurnArray, boolean isFirstTurn, int columnIndex) {

        // three conditions for the column to be valid:
        // the index has to be a positive number (exc. 1),
        // the index has to be smaller or equal than the length of the array,
        // and the element in the array has to be untaken (equals to 0)

        // This first conditional only checks for the first two
        if (columnIndex >= 2 && columnIndex <= diceRollArray.length + 1) {

            // first turn
            if (isFirstTurn && !firstTurnArray[columnIndex - 2]) {
                diceRollArray[columnIndex - 2] = dice.getRollValue();

                // prevent other players from picking the same column on first turn
                firstTurnArray[columnIndex - 2] = true;
                unselectedValidColumn = true;
                turnOne = false;

                // if it's turn 2+ check the index of the array has not been taken
            } else if (!isFirstTurn && diceRollArray[columnIndex - 2] == 0) {

                diceRollArray[columnIndex - 2] = dice.getRollValue();
                unselectedValidColumn = true;
            } else {
                System.out.println("Please enter a valid column number.");
            }
        } else {
            System.out.println("Please select a valid column.");
        }
    }

    // function to represent a turn
    public void turn(boolean[] firstTurnArray) {

        unselectedValidColumn = false;

        // call the function to roll dice and get the added value
        dice.generateDice();
        System.out.println("You rolled a(n) " + dice.getRollValue() + " this turn.");

        // while loop to make sure the selected
        while (!unselectedValidColumn) {
            if (turnOne) {
                Scanner input = new Scanner(System.in);
                System.out.print(
                        "What column would you like to assing this value to? (2-12 value - COLUMN MUST BE UNIQUE) ");
                int columnIndex = input.nextInt();

                setRollToColumn(firstTurnArray, true, columnIndex);
            } else {
                Scanner input = new Scanner(System.in);
                System.out.print("What column would you like to assing this value to? (2-12 value) ");
                int columnIndex = input.nextInt();
                setRollToColumn(firstTurnArray, false, columnIndex);
            }
        }
    }

    public int[] getDiceRollArray() {
        return diceRollArray;
    }

}