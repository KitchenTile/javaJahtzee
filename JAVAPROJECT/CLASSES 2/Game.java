package JAVAPROJECT.CLASSES;

import java.util.Arrays;
import java.util.Scanner;

public class Game {

    // in the game class we:
    // - access the getters from the three player instances and compare the
    // values of the index of the arrays when the column is complete, then
    // we give the winner the points.
    // - print the game table after every turn.
    // - compare the scores when the game is over and decide a winner.
    // - run the turn logic.

    // create the players and put them on a list
    Player player1 = new Player("player 1");
    Player player2 = new Player("player 2");
    Player player3 = new Player("player 3");

    Player[] playersList = { player1, player2, player3 };

    int winner;

    int NUMBEROFTURNS = 11;

    int XCHAR = -1;

    // Array of booleans to check the first turn column is not chosen by two people
    boolean[] firstTurnArray = new boolean[11];

    // initializes as 12 falses. I'll use them to accurately check arrays for scores
    boolean[] takenColumn = new boolean[11];

    String[] columnWinner = new String[11];

    // Template for the top of the board
    String[] scoreBoardTop = { "", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "SCORE" };

    private void checkArrays() { // function to check the state of all the table columns
        int[] player1list = player1.getDiceRollArray();
        int[] player2list = player2.getDiceRollArray();
        int[] player3list = player3.getDiceRollArray();

        for (int i = 0; i < 11; i++) {
            // check that all the values of the column are taken and that column has not
            // been evaluated before

            if (player1list[i] != 0 && player2list[i] != 0
                    && player3list[i] != 0 && !takenColumn[i]) {
                // if two players have the same roll in a column, discard the points. If there's
                // a max give points to the max regardless of the tie
                if (player1list[i] == player2list[i] || player1list[i] == player3list[i]
                        || player2list[i] == player3list[i]) {
                    // if there is some instance of a tie, check if there's a max
                    if (player1list[i] > player2list[i] && player1list[i] > player3list[i]) {
                        // assign score to winner
                        player1.totalScore += (i + 2);

                        // set the other rows to XCHAR
                        player2list[i] = XCHAR;
                        player3list[i] = XCHAR;
                        System.out.println("\n column " + (i + 2) + " value is now complete.");
                        System.out.println(player1.name + " has the highest value so earns " + (i + 2)
                                + " points, added to tehir score");

                        // populate columnWinner
                        columnWinner[i] = player1.name;

                    } else if (player2list[i] > player1list[i] && player2list[i] > player3list[i]) {
                        // assign score to winner
                        player2.totalScore += (i + 2);

                        // set the other rows to XCHAR
                        player1list[i] = XCHAR;
                        player3list[i] = XCHAR;
                        System.out.println("\n column " + (i + 2) + " value is now complete.");
                        System.out.println(player2.name + " has the highest value so earns " + (i + 2)
                                + " points, added to tehir score");

                        // populate columnWinner
                        columnWinner[i] = player2.name;

                    } else if (player3list[i] > player2list[i] && player3list[i] > player1list[i]) {
                        // assign score to winner
                        player3.totalScore += (i + 2);

                        // set the other rows to XCHAR
                        player2list[i] = XCHAR;
                        player1list[i] = XCHAR;
                        System.out.println("\n column " + (i + 2) + " value is now complete.");
                        System.out.println(player3.name + " has the highest value so earns " + (i + 2)
                                + " points, added to tehir score");

                        // populate columnWinner
                        columnWinner[i] = player3.name;

                    } else {
                        // if there's no max, the column is discarded ( all * )
                        System.out.println("\n column " + (i + 2) + " tie -- 0 points");
                        System.out.println("However, there is a tie, so nothing is added to any player's score");
                        // populate columnWinner
                        columnWinner[i] = "tie";

                        // set all rows to XCHAR
                        player2list[i] = XCHAR;
                        player1list[i] = XCHAR;
                        player3list[i] = XCHAR;
                    }

                    // if either player has a higher roll, then give the points to the player
                } else if (player1list[i] > player2list[i] && player1list[i] > player3list[i]) {
                    // assign score to winner
                    player1.totalScore += (i + 2);

                    // set the other rows to XCHAR
                    player2list[i] = XCHAR;
                    player3list[i] = XCHAR;
                    System.out.println("\n column " + (i + 2) + " value is now complete.");
                    System.out.println(player1.name + " has the highest value so earns " + (i + 2)
                            + " points, added to tehir score");

                    // populate columnWinner
                    columnWinner[i] = player1.name;

                } else if (player2list[i] > player1list[i] && player2list[i] > player3list[i]) {
                    // assign score to winner
                    player2.totalScore += (i + 2);

                    // set the other rows to XCHAR
                    player1list[i] = XCHAR;
                    player3list[i] = XCHAR;
                    System.out.println("\n column " + (i + 2) + " value is now complete.");
                    System.out.println(player2.name + " has the highest value so earns " + (i + 2)
                            + " points, added to tehir score");

                    // populate columnWinner
                    columnWinner[i] = player2.name;

                } else {
                    // assign score to winner
                    player3.totalScore += (i + 2);

                    // set the other rows to XCHAR
                    player2list[i] = XCHAR;
                    player1list[i] = XCHAR;
                    System.out.println("\n column " + (i + 2) + " value is now complete.");
                    System.out.println(player3.name + " has the highest value so earns " + (i + 2)
                            + " points, added to tehir score");

                    // populate columnWinner
                    columnWinner[i] = player3.name;
                }
                takenColumn[i] = true;
            }
        }

    }

    private void gameTable() {

        // I use rowFormat to format my table. It works similarly to String.format()
        // but printing straight to the console, so it saves a step.
        String rowFormat = "| %-10s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-2s | %-5s |%n";

        System.out.println("-".repeat(78));

        // Print header - asks me to cast Object[] on scoreBoardTop
        System.out.printf(rowFormat, (Object[]) scoreBoardTop);

        // for loop to get all three players
        for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
            int[] playersDiceRollArray = playersList[playerIndex].getDiceRollArray();

            System.out.println("\n" + "-".repeat(78));

            // Print player row - Tried looping through playerDiceRollArray but I could not
            // get it to work as I wanted, so I had to manually feed the formatter the
            // individual values
            // and use a ternary operator to change the loosing values to a star sign.

            System.out.printf(rowFormat,
                    playersList[playerIndex].name,
                    (playersDiceRollArray[0] == XCHAR) ? "*" : playersDiceRollArray[0],
                    (playersDiceRollArray[1] == XCHAR) ? "*" : playersDiceRollArray[1],
                    (playersDiceRollArray[2] == XCHAR) ? "*" : playersDiceRollArray[2],
                    (playersDiceRollArray[3] == XCHAR) ? "*" : playersDiceRollArray[3],
                    (playersDiceRollArray[4] == XCHAR) ? "*" : playersDiceRollArray[4],
                    (playersDiceRollArray[5] == XCHAR) ? "*" : playersDiceRollArray[5],
                    (playersDiceRollArray[6] == XCHAR) ? "*" : playersDiceRollArray[6],
                    (playersDiceRollArray[7] == XCHAR) ? "*" : playersDiceRollArray[7],
                    (playersDiceRollArray[8] == XCHAR) ? "*" : playersDiceRollArray[8],
                    (playersDiceRollArray[9] == XCHAR) ? "*" : playersDiceRollArray[9],
                    (playersDiceRollArray[10] == XCHAR) ? "*" : playersDiceRollArray[10],
                    playersList[playerIndex].totalScore);

        }

        System.out.println("-".repeat(78));
    }

    private void compareTotalScores() {
        // compare the score when the game is over and decide a winner
        int topScore = 0;

        for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
            if (playersList[playerIndex].totalScore > topScore) {
                topScore = playersList[playerIndex].totalScore;
                winner = playerIndex;
            }
        }

        System.out.println("The winner is " + playersList[winner].name + " with a score of " + topScore + "!");

    }

    private void gameAnalysis() {

        // use the column winner array to print the game analysis
        System.out.println("\n GAME ANALYSIS");
        for (int i = 0; i < columnWinner.length; i++) {
            String winner = columnWinner[i];
            if (winner != "tie") {
                System.out.println("Column " + (i + 2) + " won by " + winner);
            } else {
                System.out.println("Column " + (i + 2) + " was tied");
            }
        }
    }

    private void printSign(int roundNumber) {

        // prints the sign for the rounds
        if (roundNumber + 1 < 10) {
            System.out.println(" \n" + "*".repeat(15));
            System.out.println("*** Round " + (roundNumber + 1) + " ***");
            System.out.println("*".repeat(15) + " \n");
        } else {
            System.out.println(" \n" + "*".repeat(16));
            System.out.println("*** Round " + (roundNumber + 1) + " ***");
            System.out.println("*".repeat(16) + " \n");
        }

    }

    public void play() {

        System.out.println("*".repeat(27));
        System.out.println("*** Strategic Dice Game ***");
        System.out.println("*".repeat(27) + "\n");

        
            System.out.println(
                    "Three players take turn throwing two dice over 11 rounds \nThis is simulated at the console, with all players simply \ntaking turns at the keyboard. The game simply involves \nthrowing two dice in each round and selecting a column \nor position in the score table, but it is quite strategic \nin order to optimise scores. Note that for the first round \nplayers must select different columns ... let's play \n");


        // for the duration of a game play each player's turn -- need to add first turn
        // restriction
        for (int currentTurn = 0; currentTurn < NUMBEROFTURNS; currentTurn++) {

            // print round sign
            printSign(currentTurn);

            for (int playerIndex = 0; playerIndex < playersList.length; playerIndex++) {
                System.out.println(playersList[playerIndex].name + " to throw \n");

                // ask to press t before playing player.turn();
                Scanner pressT = new Scanner(System.in);

                System.out.print("Enter 't' to take your throw > ");

                while (!pressT.hasNext("t")) {
                    System.out.println("Enter 't' to take your throw > ");
                    pressT.next();
                }

                playersList[playerIndex].turn(firstTurnArray);

                checkArrays();
                gameTable();

            }
        }
        compareTotalScores();
        gameAnalysis();

    }

}
