package JAVAPROJECT.CLASSES;

import java.util.Scanner;

// in the class Player we:
// - set what a "Player" is.
// - set the logic for assigning our rolls to columns.
// - set our turn logic to be ran by Game.

public class Player {

    // variable for each player's total score
    int totalScore;

    // Array for the dice roll results
    int[] diceRollArray;

    // boolean to check if the column input is not repeated
    boolean unselectedValidColumn;

    // boolean to check if it's turn one for a unique column
    boolean turnOne;

    // initialize Dice constructor
    Dice dice = new Dice();

    String name;

    public Player(String name) {
        totalScore = 0;
        diceRollArray = new int[12];
        turnOne = true;

        this.name = name;
    }

    private void setRollToColumn(boolean[] firstTurnArray, boolean isFirstTurn, int columnIndex) {

        // three conditions for the column to be valid:
        // the index has to be a positive number (exc. 1),
        // the index has to be smaller or equal than the length of the array,
        // and the element in the array has to be untaken (equals to 0)

        // This first conditional only checks for the first two
        if (columnIndex >= 2 && columnIndex <= diceRollArray.length) {

            // first turn
            if (isFirstTurn && !firstTurnArray[columnIndex - 2]) {
                diceRollArray[columnIndex - 2] = dice.getRollValue();

                // prevent other players from picking the same column on first turn
                firstTurnArray[columnIndex - 2] = true;
                unselectedValidColumn = true;
                turnOne = false;

                // if two players choose the same on turn 1 print error
            } else if (isFirstTurn && firstTurnArray[columnIndex - 2]) {
                System.out.println("For round 1 you must select a different column to other players");

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
        int columnIndex;

        // call the function to roll dice and get the added value
        dice.generateDice();
        System.out.println("You scored " + dice.getRollValue() + "\n");

        // select column to assign dice roll, best attempt at not repeating code
        while (!unselectedValidColumn) {
            Scanner input = new Scanner(System.in);
            System.out.print(
                    "Enter the column in which you wish to insert your score (2 to 12) > ");

            while (!input.hasNextInt()) {
                System.out.print("Please enter a valid number! Choose new column: ");
                input.next();
            }
            columnIndex = input.nextInt();
            if (turnOne) {
                setRollToColumn(firstTurnArray, true, columnIndex);
            } else {
                setRollToColumn(firstTurnArray, false, columnIndex);
            }
        }
    }

    public int[] getDiceRollArray() {
        return diceRollArray;
    }

}
